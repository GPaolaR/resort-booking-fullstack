/************************************************************************************
-- Equipo de Sistemas - "TechCamp"
-- Archivo:	 ROLLBACK_GEST_SPA.sql
-- Autor:	 Gina Riveros
-- Tel:	     3160000000
-- Registro:	 time.Date(2023, time.October, 18, 17, 30, 41, 660120100, time.Local)
-- HU:		 9999
************************************************************************************/

PROMPT ROLLBACK_GEST_SPA.sql

ALTER SESSION SET CURRENT_SCHEMA=GEST_SPA;

/****************************************************************************************************/

PROMPT ---------------------------Objetos: synonyms-------------------------------------

PROMPT Aplicando Objeto ...  @..\rollback\synonyms\dropSYN
@..\rollback\synonyms\dropSYN.sql
/

/****************************************************************************************************/

PROMPT ---------------------------Objetos: packages-------------------------------------

PROMPT Aplicando Objeto ...  @..\rollback\packages\dropPKG.sql
@..\rollback\packages\dropPKG.sql
/

/****************************************************************************************************/

PROMPT ---------------------------Objetos: sequences-------------------------------------

PROMPT Aplicando Objeto ...  @..\rollback\sequences\dropSEQ.sql
@..\rollback\sequences\dropSEQ.sql
/

/****************************************************************************************************/

PROMPT ---------------------------Objetos: tables-------------------------------------

PROMPT Aplicando Objeto ...  @..\rollback\tables\dropTABLES.sql
@..\rollback\tables\dropTABLES.sql
/

/****************************************************************************************************/
PROMPT ---------------------------Objetos: types-------------------------------------

PROMPT Aplicando Objeto ...  @..\rollback\types\dropTYPES.sql  
@..\rollback\types\dropTYPES.sql
/

/****************************************************************************************************/
PROMPT ---------------------------Objetos: jobs-------------------------------------

PROMPT Aplicando Objeto ...  @..\rollback\jobs\dropJOBS.sql  
@..\rollback\jobs\dropJOBS.sql
/

/****************************************************************************************************/
PROMPT ----------------------------------------------------------------
PROMPT Fin de ROLLBACK_GEST_SPA.sql
PROMPT ----------------------------------------------------------------