/************************************************************************************
Description: Document_types table
Author:      Gina Riveros
Date:        12-10-2023
@copyright:  TechCamp
************************************************************************************/
CREATE TABLE DOCUMENT_TYPES (
    DOCUMENT_TYPE_ID NUMBER(4),
    CODE VARCHAR2(2)NOT NULL,
    DOCUMENT_TYPE_NAME VARCHAR2(50)
);

COMMENT ON TABLE GEST_SPA.DOCUMENT_TYPES IS 'SPA Belleza document types table';
