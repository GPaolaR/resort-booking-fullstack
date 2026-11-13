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

PROMPT Applying Object ...  @..\database\types\crTYRC_LOGIN.sql
@..\database\types\crTYRC_LOGIN.sql
/
PROMPT Applying Object ...  @..\database\types\crTYRC_APPOI.sql
@..\database\types\crTYRC_APPOI.sql
/
PROMPT Applying Object ...  @..\database\types\crTYTB_APPOI.sql
@..\database\types\crTYTB_APPOI.sql
/
/****************************************************************************************************/
PROMPT ---------------------------Objects: tables-------------------------------------

PROMPT Applying Object ...  @..\database\tables\crtbADMINISTRATORS.sql
@..\database\tables\crtbADMINISTRATORS.sql
/
PROMPT Applying Object ...  @..\database\tables\crtbAPPOINTMENTS.sql
@..\database\tables\crtbAPPOINTMENTS.sql
/
PROMPT Applying Object ...  @..\database\tables\crtbDOCUMENT_TYPES.sql
@..\database\tables\crtbDOCUMENT_TYPES.sql
/
PROMPT Applying Object ...  @..\database\tables\crtbLOGIN.sql
@..\database\tables\crtbLOGIN.sql
/
PROMPT Applying Object ...  @..\database\tables\crtbMEMBERSHIP_TYPES.sql
@..\database\tables\crtbMEMBERSHIP_TYPES.sql
/
PROMPT Applying Object ...  @..\database\tables\crtbPLANS.sql
@..\database\tables\crtbPLANS.sql
/
PROMPT Applying Object ...  @..\database\tables\crtbROLES.sql
@..\database\tables\crtbROLES.sql
/
PROMPT Applying Object ...  @..\database\tables\crtbSPECIALISTS.sql
@..\database\tables\crtbSPECIALISTS.sql
/
PROMPT Applying Object ...  @..\database\tables\crtbSPECIALITIES.sql
@..\database\tables\crtbSPECIALITIES.sql
/
PROMPT Applying Object ...  @..\database\tables\crtbUSERS.sql
@..\database\tables\crtbUSERS.sql
/
PROMPT Applying Object ... @..\database\tables\crtbREPORTS.sql
@..\database\tables\crtbREPORTS.sql
/
/****************************************************************************************************/

PROMPT ---------------------------Objects: sequences-------------------------------------

PROMPT Applying Object ...  @..\database\sequences\crsqSEQ_ADMIN.sql
@..\database\sequences\crsqSEQ_ADMIN.sql
/
PROMPT Applying Object ...  @..\database\sequences\crsqSEQ_APPOI.sql
@..\database\sequences\crsqSEQ_APPOI.sql
/
PROMPT Applying Object ...  @..\database\sequences\crsqSEQ_DOCTY.sql
@..\database\sequences\crsqSEQ_DOCTY.sql
/
PROMPT Applying Object ...  @..\database\sequences\crsqSEQ_LOGIN.sql
@..\database\sequences\crsqSEQ_LOGIN.sql
/
PROMPT Applying Object ...  @..\database\sequences\crsqSEQ_MEMTY.sql
@..\database\sequences\crsqSEQ_MEMTY.sql
/
PROMPT Applying Object ...  @..\database\sequences\crsqSEQ_PLAN.sql
@..\database\sequences\crsqSEQ_PLAN.sql
/
PROMPT Applying Object ...  @..\database\sequences\crsqSEQ_ROL.sql
@..\database\sequences\crsqSEQ_ROL.sql
/
PROMPT Applying Object ...  @..\database\sequences\crsqSEQ_SPETY.sql
@..\database\sequences\crsqSEQ_SPETY.sql
/
PROMPT Applying Object ...  @..\database\sequences\crsqSEQ_SPLTS.sql
@..\database\sequences\crsqSEQ_SPLTS.sql
/
PROMPT Applying Object ...  @..\database\sequences\crsqSEQ_USER.sql
@..\database\sequences\crsqSEQ_USER.sql
/
PROMPT Applying Object ...  @..\database\sequences\crsqSEQ_REPOR.sql
@..\database\sequences\crsqSEQ_REPOR.sql
/

/****************************************************************************************************/

PROMPT ---------------------------Objects: indexes-------------------------------------
PROMPT Applying Object ...  @..\database\indexes\crPK_ADMINISTRATORS.sql
@..\database\indexes\crPK_ADMINISTRATORS.sql
/
PROMPT Applying Object ...  @..\database\indexes\crPK_APPOINTMENT_TYPES.sql
@..\database\indexes\crPK_APPOINTMENT_TYPES.sql
/
PROMPT Applying Object ...  @..\database\indexes\crPK_APPOINTMENTS.sql
@..\database\indexes\crPK_APPOINTMENTS.sql
/
PROMPT Applying Object ...  @..\database\indexes\crPK_DOCUMENT_TYPES.sql
@..\database\indexes\crPK_DOCUMENT_TYPES.sql
/
PROMPT Applying Object ...  @..\database\indexes\crPK_LOGIN.sql
@..\database\indexes\crPK_LOGIN.sql
/
PROMPT Applying Object ...  @..\database\indexes\crPK_MEMBERSHIP_TYPES.sql
@..\database\indexes\crPK_MEMBERSHIP_TYPES.sql
/
PROMPT Applying Object ...  @..\database\indexes\crPK_PLANS.sql
@..\database\indexes\crPK_PLANS.sql
/
PROMPT Applying Object ...  @..\database\indexes\crPK_ROLES.sql
@..\database\indexes\crPK_ROLES.sql
/
PROMPT Applying Object ...  @..\database\indexes\crPK_SPECIALISTS.sql
@..\database\indexes\crPK_SPECIALISTS.sql
/
PROMPT Applying Object ...  @..\database\indexes\crPK_USERS.sql
@..\database\indexes\crPK_USERS.sql
/
PROMPT Applying Object ...  @..\database\indexes\crPK_REPORTS.sql
@..\database\indexes\crPK_REPORTS.sql
/
PROMPT Applying Object ...  @..\database\indexes\crUK_ADMIN_01.sql
@..\database\indexes\crUK_ADMIN_01.sql
/
PROMPT Applying Object ...  @..\database\indexes\crUK_SPLTS_01.sql
@..\database\indexes\crUK_SPLTS_01.sql
/
PROMPT Applying Object ...  @..\database\indexes\crUK_USER_01.sql
@..\database\indexes\crUK_USER_01.sql
/

/****************************************************************************************************/
PROMPT ---------------------------Objects: references-------------------------------------

PROMPT Applying Object ...  @..\database\references\crFK_ADMIN_DOCTY.sql
@..\database\references\crFK_ADMIN_DOCTY.sql
/
PROMPT Applying Object ...  @..\database\references\crFK_ADMIN_LOGIN.sql
@..\database\references\crFK_ADMIN_LOGIN.sql
/
PROMPT Applying Object ...  @..\database\references\crFK_APPOI_PLAN.sql
@..\database\references\crFK_APPOI_PLAN.sql
/
PROMPT Applying Object ...  @..\database\references\crFK_APPOI_SPETY.sql
@..\database\references\crFK_APPOI_SPETY.sql
/
PROMPT Applying Object ...  @..\database\references\crFK_APPOI_SPLTS.sql
@..\database\references\crFK_APPOI_SPLTS.sql
/
PROMPT Applying Object ...  @..\database\references\crFK_APPOI_USERS.sql
@..\database\references\crFK_APPOI_USERS.sql
/
PROMPT Applying Object ...  @..\database\references\crFK_LOGIN_ROL.sql
@..\database\references\crFK_LOGIN_ROL.sql
/
PROMPT Applying Object ...  @..\database\references\crFK_SPLTS_DOCTY.sql
@..\database\references\crFK_SPLTS_DOCTY.sql
/
PROMPT Applying Object ...  @..\database\references\crFK_SPLTS_SPETY.sql
@..\database\references\crFK_SPLTS_SPETY.sql
/
PROMPT Applying Object ...  @..\database\references\crFK_USERS_DOCTY.sql
@..\database\references\crFK_USERS_DOCTY.sql
/
PROMPT Applying Object ...  @..\database\references\crFK_USERS_LOGIN.sql
@..\database\references\crFK_USERS_LOGIN.sql
/
PROMPT Applying Object ...  @..\database\references\crKF_USERS_MEMTY.sql
@..\database\references\crKF_USERS_MEMTY.sql
/

/****************************************************************************************************/

PROMPT ---------------------------Objects: packages-------------------------------------

PROMPT Applying Object ...  @..\database\packages\PKG_APPOINTMENT.sql
@..\database\packages\PKG_APPOINTMENT.sql
/
PROMPT Applying Object ...  @..\database\packages\PKG_REPORT.sql
@..\database\packages\PKG_REPORT.sql
/

/****************************************************************************************************/

PROMPT ---------------------------Objects: sql-------------------------------------

PROMPT Applying Object ...  @..\database\sql\insDataTest.sql
@..\database\sql\insDataTest.sql
/
PROMPT Applying Object ...  @..\database\sql\crJOB_APPOINTMENTS.sql
@..\database\sql\crJOB_APPOINTMENTS.sql
/


/****************************************************************************************************/
PROMPT ---------------------------Objects: sinonimos-------------------------------------

-- PROMPT Applying Object ...  @..\database\sinonimos\crSYN_TYRC_PERSON.sql
-- @..\database\sinonimos\crSYN_TYRC_PERSON.sql
-- /

/****************************************************************************************************/

PROMPT ---------------------------Objects: permisos-------------------------------------

-- PROMPT Applying Object ...  @..\database\permisos\crGrantSYN_TYRC_PERSON.sql
-- @..\database\permisos\crGrantSYN_TYRC_PERSON.sql
-- /

/****************************************************************************************************/

PROMPT ---------------------------Objects: triggers-------------------------------------

/****************************************************************************************************/

PROMPT ----------------------------------------------------------------
PROMPT Fin de APLICA_GEST_SPA.sql
PROMPT ----------------------------------------------------------------
