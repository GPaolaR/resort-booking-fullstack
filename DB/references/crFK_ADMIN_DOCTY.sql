/************************************************************************************
Description: Object responsible for the foreign key for ADMINISTRATORS references document_type
Author:      Gina Riveros
Date:        12-10-2023
@copyright:  TechCamp
************************************************************************************/
ALTER TABLE GEST_SPA.ADMINISTRATORS ADD CONSTRAINT FK_ADMIN_DOCTY FOREIGN KEY (DOCUMENT_TYPE_ID) REFERENCES GEST_SPA.DOCUMENT_TYPES (DOCUMENT_TYPE_ID);