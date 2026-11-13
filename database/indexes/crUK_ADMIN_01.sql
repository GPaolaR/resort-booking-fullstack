/************************************************************************************
Description: Object responsible for the unique key for ADMINISTRATORS references DOCUMENT_NUMBER
Author:      Gina Riveros
Date:        12-10-2023
@copyright:  TechCamp
************************************************************************************/
CREATE UNIQUE INDEX UK_ADMIN_01 ON GEST_SPA.ADMINISTRATORS (DOCUMENT_NUMBER)