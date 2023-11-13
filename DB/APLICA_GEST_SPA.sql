/************************************************************************************
-- Equipo de Sistemas - "TechCamp"
-- Archivo:	 APLICA_GEST_SPA.sql
-- Autor:	 Gina Riveros
-- Tel:	     3160000000
-- Registro:	 time.Date(2023, time.October, 18, 17, 30, 41, 660120100, time.Local)
-- HU:		 9999
************************************************************************************/

PROMPT APLICA_GEST_SPA.sql

ALTER SESSION SET CURRENT_SCHEMA=GEST_SPA;

/****************************************************************************************************/
PROMPT ---------------------------Objects: types-------------------------------------

PROMPT Applying Object ...  @..\DB\types\crTYRC_LOGIN.sql
@..\DB\types\crTYRC_LOGIN.sql
/
PROMPT Applying Object ...  @..\DB\types\crTYRC_APPOI.sql
@..\DB\types\crTYRC_APPOI.sql
/
PROMPT Applying Object ...  @..\DB\types\crTYTB_APPOI.sql
@..\DB\types\crTYTB_APPOI.sql
/
/****************************************************************************************************/
PROMPT ---------------------------Objects: tables-------------------------------------

PROMPT Applying Object ...  @..\DB\tables\crtbADMINISTRATORS.sql
@..\DB\tables\crtbADMINISTRATORS.sql
/
PROMPT Applying Object ...  @..\DB\tables\crtbAPPOINTMENTS.sql
@..\DB\tables\crtbAPPOINTMENTS.sql
/
PROMPT Applying Object ...  @..\DB\tables\crtbDOCUMENT_TYPES .sql
@..\DB\tables\crtbDOCUMENT_TYPES.sql
/
PROMPT Applying Object ...  @..\DB\tables\crtbLOGIN.sql
@..\DB\tables\crtbLOGIN.sql
/
PROMPT Applying Object ...  @..\DB\tables\crtbMEMBERSHIP_TYPES .sql
@..\DB\tables\crtbMEMBERSHIP_TYPES .sql
/
PROMPT Applying Object ...  @..\DB\tables\crtbPLANS.sql
@..\DB\tables\crtbPLANS.sql
/
PROMPT Applying Object ...  @..\DB\tables\crtbROLES.sql
@..\DB\tables\crtbROLES.sql
/
PROMPT Applying Object ...  @..\DB\tables\crtbSPECIALISTS.sql
@..\DB\tables\crtbSPECIALISTS.sql
/
PROMPT Applying Object ...  @..\DB\tables\crtbSPECIALITIES.sql
@..\DB\tables\crtbSPECIALITIES.sql
/
PROMPT Applying Object ...  @..\DB\tables\crtbUSERS.sql
@..\DB\tables\crtbUSERS.sql
/
PROMPT Applying Object ... @..\DB\tables\crtbREPORTS.sql
@..\DB\tables\crtbREPORTS.sql
/
/****************************************************************************************************/

PROMPT ---------------------------Objects: sequences-------------------------------------

PROMPT Applying Object ...  @..\DB\sequences\crsqSEQ_ADMIN.sql
@..\DB\sequences\crsqSEQ_ADMIN.sql
/
PROMPT Applying Object ...  @..\DB\sequences\crsqSEQ_APPOI.sql
@..\DB\sequences\crsqSEQ_APPOI.sql
/
PROMPT Applying Object ...  @..\DB\sequences\crsqSEQ_DOCTY.sql
@..\DB\sequences\crsqSEQ_DOCTY.sql
/
PROMPT Applying Object ...  @..\DB\sequences\crsqSEQ_LOGIN.sql
@..\DB\sequences\crsqSEQ_LOGIN.sql
/
PROMPT Applying Object ...  @..\DB\sequences\crsqSEQ_MEMTY.sql
@..\DB\sequences\crsqSEQ_MEMTY.sql
/
PROMPT Applying Object ...  @..\DB\sequences\crsqSEQ_PLAN.sql
@..\DB\sequences\crsqSEQ_PLAN.sql
/
PROMPT Applying Object ...  @..\DB\sequences\crsqSEQ_ROL.sql
@..\DB\sequences\crsqSEQ_ROL.sql
/
PROMPT Applying Object ...  @..\DB\sequences\crsqSEQ_SPETY.sql
@..\DB\sequences\crsqSEQ_SPETY.sql
/
PROMPT Applying Object ...  @..\DB\sequences\crsqSEQ_SPLTS.sql
@..\DB\sequences\crsqSEQ_SPLTS.sql
/
PROMPT Applying Object ...  @..\DB\sequences\crsqSEQ_USER.sql
@..\DB\sequences\crsqSEQ_USER.sql
/
PROMPT Applying Object ...  @..\DB\sequences\crsqSEQ_REPOR.sql
@..\DB\sequences\crsqSEQ_REPOR.sql
/

/****************************************************************************************************/

PROMPT ---------------------------Objects: indexes-------------------------------------
PROMPT Applying Object ...  @..\DB\indexes\crPK_ADMINISTRATORS.sql
@..\DB\indexes\crPK_ADMINISTRATORS.sql
/
PROMPT Applying Object ...  @..\DB\indexes\crPK_APPOINTMENT_TYPES.sql
@..\DB\indexes\crPK_APPOINTMENT_TYPES.sql
/
PROMPT Applying Object ...  @..\DB\indexes\crPK_APPOINTMENTS.sql
@..\DB\indexes\crPK_APPOINTMENTS.sql
/
PROMPT Applying Object ...  @..\DB\indexes\crPK_DOCUMENT_TYPES.sql
@..\DB\indexes\crPK_DOCUMENT_TYPES.sql
/
PROMPT Applying Object ...  @..\DB\indexes\crPK_LOGIN.sql
@..\DB\indexes\crPK_LOGIN.sql
/
PROMPT Applying Object ...  @..\DB\indexes\crPK_MEMBERSHIP_TYPES.sql
@..\DB\indexes\crPK_MEMBERSHIP_TYPES.sql
/
PROMPT Applying Object ...  @..\DB\indexes\crPK_PLANS.sql
@..\DB\indexes\crPK_PLANS.sql
/
PROMPT Applying Object ...  @..\DB\indexes\crPK_ROLES.sql
@..\DB\indexes\crPK_ROLES.sql
/
PROMPT Applying Object ...  @..\DB\indexes\crPK_SPECIALISTS.sql
@..\DB\indexes\crPK_SPECIALISTS.sql
/
PROMPT Applying Object ...  @..\DB\indexes\crPK_USERS.sql
@..\DB\indexes\crPK_USERS.sql
/
PROMPT Applying Object ...  @..\DB\indexes\crPK_REPORTS.sql
@..\DB\indexes\crPK_REPORTS.sql
/
PROMPT Applying Object ...  @..\DB\indexes\crUK_ADMIN_01.sql
@..\DB\indexes\crUK_ADMIN_01.sql
/
PROMPT Applying Object ...  @..\DB\indexes\crUK_SPLTS_01.sql
@..\DB\indexes\crUK_SPLTS_01.sql
/
PROMPT Applying Object ...  @..\DB\indexes\crUK_USER_01.sql
@..\DB\indexes\crUK_USER_01.sql
/

/****************************************************************************************************/
PROMPT ---------------------------Objects: references-------------------------------------

PROMPT Applying Object ...  @..\DB\references\crFK_ADMIN_DOCTY.sql
@..\DB\references\crFK_ADMIN_DOCTY.sql
/
PROMPT Applying Object ...  @..\DB\references\crFK_ADMIN_LOGIN.sql
@..\DB\references\crFK_ADMIN_LOGIN.sql
/
PROMPT Applying Object ...  @..\DB\references\crFK_APPOI_PLAN.sql
@..\DB\references\crFK_APPOI_PLAN.sql
/
PROMPT Applying Object ...  @..\DB\references\crFK_APPOI_SPETY.sql
@..\DB\references\crFK_APPOI_SPETY.sql
/
PROMPT Applying Object ...  @..\DB\references\crFK_APPOI_SPLTS.sql
@..\DB\references\crFK_APPOI_SPLTS.sql
/
PROMPT Applying Object ...  @..\DB\references\crFK_APPOI_USERS.sql
@..\DB\references\crFK_APPOI_USERS.sql
/
PROMPT Applying Object ...  @..\DB\references\crFK_LOGIN_ROL.sql
@..\DB\references\crFK_LOGIN_ROL.sql
/
PROMPT Applying Object ...  @..\DB\references\crFK_SPLTS_DOCTY.sql
@..\DB\references\crFK_SPLTS_DOCTY.sql
/
PROMPT Applying Object ...  @..\DB\references\crFK_SPLTS_SPETY.sql
@..\DB\references\crFK_SPLTS_SPETY.sql
/
PROMPT Applying Object ...  @..\DB\references\crFK_USERS_DOCTY.sql
@..\DB\references\crFK_USERS_DOCTY.sql
/
PROMPT Applying Object ...  @..\DB\references\crFK_USERS_LOGIN.sql
@..\DB\references\crFK_USERS_LOGIN.sql
/
PROMPT Applying Object ...  @..\DB\references\crKF_USERS_MEMTY.sql
@..\DB\references\crKF_USERS_MEMTY.sql
/

/****************************************************************************************************/

PROMPT ---------------------------Objects: packages-------------------------------------

PROMPT Applying Object ...  @..\DB\packages\PKG_APPOINTMENT.sql
@..\DB\packages\PKG_APPOINTMENT.sql
/
PROMPT Applying Object ...  @..\DB\packages\PKG_REPORT.sql
@..\DB\packages\PKG_REPORT.sql
/

/****************************************************************************************************/

PROMPT ---------------------------Objects: sql-------------------------------------

PROMPT Applying Object ...  @..\DB\sql\insDataTest.sql
@..\DB\sql\insDataTest.sql
/
PROMPT Applying Object ...  @..\DB\sql\crJOB_APPOINTMENTS.sql
@..\DB\sql\crJOB_APPOINTMENTS.sql
/


/****************************************************************************************************/
PROMPT ---------------------------Objects: sinonimos-------------------------------------

-- PROMPT Applying Object ...  @..\DB\sinonimos\crSYN_TYRC_PERSON.sql
-- @..\DB\sinonimos\crSYN_TYRC_PERSON.sql
-- /

/****************************************************************************************************/

PROMPT ---------------------------Objects: permisos-------------------------------------

-- PROMPT Applying Object ...  @..\DB\permisos\crGrantSYN_TYRC_PERSON.sql
-- @..\DB\permisos\crGrantSYN_TYRC_PERSON.sql
-- /

/****************************************************************************************************/

PROMPT ---------------------------Objects: triggers-------------------------------------

/****************************************************************************************************/

PROMPT ----------------------------------------------------------------
PROMPT Fin de APLICA_GEST_SPA.sql
PROMPT ----------------------------------------------------------------
