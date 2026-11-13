#!/usr/bin/env bash
# Creates the GEST_SPA schema with all its objects and sample data on an
# Oracle XE container (gvenzl/oracle-xe). Used by GitHub Actions and Codespaces.
#
# Runs INSIDE the Oracle container (it needs sqlplus) with the database/ folder
# mounted at $DB_DIR. Required env vars:
#   ORACLE_PASSWORD  password of SYSTEM (set when the container starts)
#   DB_PASSWORD      password to give the GEST_SPA user
#
# The original deploy script (APLICA_GEST_SPA.sql) was written for SQL Developer
# on Windows; this script applies the same objects in the same order, but
# normalizes statement terminators so SQL*Plus stops on the first real error.
# The object list is read from APLICA_GEST_SPA.sql itself.
set -euo pipefail

DB_DIR="${DB_DIR:-/database}"
PDB="${PDB:-XEPDB1}"
: "${ORACLE_PASSWORD:?ORACLE_PASSWORD is required}"
: "${DB_PASSWORD:?DB_PASSWORD is required}"

WORK="$(mktemp -d)"
trap 'rm -rf "$WORK"' EXIT

# Common cleanup: drop Windows CRs; neutralize lines starting with "@" (e.g.
# "@copyright:" in header comments), which SQL*Plus would run as a script; and
# drop "-- comments" after a ";", which keep SQL*Plus from ending the statement.
clean() {
  tr -d '\r' < "$1" \
    | sed -e 's/^\([[:space:]]*\)@/\1(c) /' \
          -e 's/;[[:space:]]*--.*$/;/'
}

# PL/SQL scripts (types, packages, anonymous blocks) end with "/" in SQL*Plus.
is_plsql() {
  clean "$1" | grep -qiE '^[[:space:]]*(create([[:space:]]+or[[:space:]]+replace)?[[:space:]]+(type|package)|begin|declare)\b'
}

# DDL/DML file: drop lone "/" lines (they would re-run the last statement)
# and make sure the last statement ends with ";".
plain() {
  clean "$1" | sed '/^[[:space:]]*\/[[:space:]]*$/d' > "$WORK/f"
  cat "$WORK/f"; echo
  if ! grep -v '^[[:space:]]*$' "$WORK/f" | tail -n1 | grep -q ';[[:space:]]*$'; then echo ';'; fi
}

# PL/SQL file (types, packages): make sure the block is closed with "/".
plsql() {
  clean "$1" > "$WORK/f"
  cat "$WORK/f"; echo
  if ! grep -v '^[[:space:]]*$' "$WORK/f" | tail -n1 | grep -q '^[[:space:]]*/[[:space:]]*$'; then echo '/'; fi
}

echo ">> Creating user GEST_SPA"
sqlplus -s -L "system/${ORACLE_PASSWORD}@//localhost:1521/${PDB}" <<SQL
WHENEVER SQLERROR EXIT SQL.SQLCODE
SET FEEDBACK OFF
BEGIN
  FOR u IN (SELECT 1 FROM dba_users WHERE username = 'GEST_SPA') LOOP
    EXECUTE IMMEDIATE 'DROP USER GEST_SPA CASCADE';
  END LOOP;
END;
/
CREATE USER GEST_SPA IDENTIFIED BY "${DB_PASSWORD}" QUOTA UNLIMITED ON USERS;
GRANT CREATE SESSION, CREATE TABLE, CREATE SEQUENCE, CREATE VIEW,
      CREATE PROCEDURE, CREATE TYPE, CREATE SYNONYM, CREATE JOB TO GEST_SPA;
EXIT
SQL

echo ">> Building install script"
{
  echo "WHENEVER SQLERROR EXIT SQL.SQLCODE"
  echo "SET DEFINE OFF"
  echo "SET SQLBLANKLINES ON"
  echo "SET FEEDBACK ON"
  # Same objects, in the same order, as the original deploy script
  # (APLICA_GEST_SPA.sql): every "@..\database\<path>" line that is not commented out.
  tr -d '\r' < "$DB_DIR/APLICA_GEST_SPA.sql" \
    | sed -n 's/^@\.\.\\database\\//p' | tr '\\' '/' \
    | while read -r rel; do
        f="$DB_DIR/$rel"
        [ -f "$f" ] || { echo "PROMPT Missing script: $rel" >&2; exit 1; }
        if is_plsql "$f"; then plsql "$f"; else plain "$f"; fi
      done
  echo "COMMIT;"
  # Fail if the package the API calls did not compile
  cat <<'SQL'
SET SERVEROUTPUT ON
DECLARE
  n NUMBER;
BEGIN
  FOR e IN (SELECT name, type, line, text FROM user_errors ORDER BY name, sequence) LOOP
    DBMS_OUTPUT.PUT_LINE('WARN ' || e.type || ' ' || e.name || ' line ' || e.line || ': ' || e.text);
  END LOOP;
  SELECT COUNT(*) INTO n FROM user_objects
   WHERE object_name IN ('PKG_APPOINTMENT', 'PKG_REPORT', 'TYRC_APPOI', 'TYTB_APPOI') AND status <> 'VALID';
  IF n > 0 THEN
    RAISE_APPLICATION_ERROR(-20001, 'PKG_APPOINTMENT, PKG_REPORT or their types are INVALID');
  END IF;
END;
/
SQL
  echo "EXIT"
} > "$WORK/install.sql"

echo ">> Installing objects and sample data as GEST_SPA"
NLS_LANG=AMERICAN_AMERICA.AL32UTF8 \
  sqlplus -s -L "GEST_SPA/${DB_PASSWORD}@//localhost:1521/${PDB}" @"$WORK/install.sql"

echo ">> GEST_SPA schema ready"
