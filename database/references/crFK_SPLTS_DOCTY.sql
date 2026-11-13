/************************************************************************************
Description: Object responsible for the foreign key for SPECIALISTS references DOCUMENT_TYPES
Author:      Gina Riveros
Date:        12-10-2023
@copyright:  TechCamp
************************************************************************************/
ALTER TABLE GEST_SPA.SPECIALISTS ADD CONSTRAINT FK_SPLTS_DOCTY FOREIGN KEY (DOCUMENT_TYPE_ID) REFERENCES GEST_SPA.DOCUMENT_TYPES (DOCUMENT_TYPE_ID);
