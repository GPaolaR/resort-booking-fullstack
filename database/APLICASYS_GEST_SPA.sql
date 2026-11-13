/************************************************************************************
-- Equipo de Sistemas - "TechCamp"
-- Archivo:	 APLICASYS_GEST_SPA.sql
-- Autor:	 Gina Riveros
-- Tel:	     3160000000
-- Registro:	 time.Date(2023, time.October, 18, 17, 30, 41, 660120100, time.Local)
-- HU:		 9999
************************************************************************************/

PROMPT APLICASYS_GEST_SPA.sql

ALTER SESSION SET CURRENT_SCHEMA=SYS;

/****************************************************************************************************/

PROMPT ---------------------------Objetos: sys-------------------------------------

/****************************************************************************************************/

PROMPT ---------------------------Objetos: tablespaces-------------------------------------

-- Crear tablespace
CREATE TABLESPACE TS_GEST_SPA_M
    DATAFILE 'C:\Oracle\oradata\XE\DF_GEST_SPA_M.DBF' 
    SIZE 5M
    AUTOEXTEND ON NEXT 1M
    MAXSIZE 200M
    DEFAULT STORAGE(INITIAL 64K NEXT 64K);


/****************************************************************************************************/

PROMPT ---------------------------Objects: Users-------------------------------------

ALTER SESSION SET "_ORACLE_SCRIPT"=TRUE;

-- Crear un nuevo esquema llamado GEST_SPA
CREATE USER GEST_SPA IDENTIFIED BY CHANGE_ME;

-- Asignar permisos al esquema GEST_SPA
GRANT CONNECT, RESOURCE, DBA TO GEST_SPA;

GRANT CREATE TABLE TO GEST_SPA;

GRANT CREATE SYNONYM, CREATE VIEW TO GEST_SPA;

GRANT CREATE SESSION TO GEST_SPA;

GRANT CREATE ANY PROCEDURE TO GEST_SPA;

GRANT CREATE ANY INDEX TO GEST_SPA;

GRANT CREATE TABLESPACE TO GEST_SPA;

GRANT UNLIMITED TABLESPACE TO GEST_SPA;


-- Establecer tablespace por defecto
ALTER USER GEST_SPA DEFAULT TABLESPACE TS_GEST_SPA_M;
/

/****************************************************************************************************/

PROMPT ---------------------------Ocjects: triggers-------------------------------------

/****************************************************************************************************/

PROMPT ----------------------------------------------------------------
PROMPT Fin de APLICASYS_GEST_SPA.sql
PROMPT ----------------------------------------------------------------