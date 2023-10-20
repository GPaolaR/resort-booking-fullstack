/************************************************************************************
Description: Object responsible for the unique key for USERS references DOCUMENT_NUMBER
Author:      Gina Riveros
Date:        12-10-2023
@copyright:  TechCamp
************************************************************************************/
CREATE UNIQUE INDEX UK_USER_01 ON GEST_SPA.USERS (DOCUMENT_NUMBER)