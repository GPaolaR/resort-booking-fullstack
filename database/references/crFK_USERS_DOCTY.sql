/************************************************************************************
Description: Object responsible for the foreign key for USERS references DOCUMENT_TYPES
Author:      Gina Riveros
Date:        12-10-2023
@copyright:  TechCamp
************************************************************************************/
ALTER TABLE GEST_SPA.USERS ADD CONSTRAINT FK_USERS_DOCTY FOREIGN KEY (DOCUMENT_TYPE_ID) REFERENCES GEST_SPA.DOCUMENT_TYPES (DOCUMENT_TYPE_ID);