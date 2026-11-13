/************************************************************************************
Description: Specialists table
Author:      Gina Riveros
Date:        12-10-2023
@copyright:  TechCamp
************************************************************************************/
CREATE TABLE SPECIALISTS (
    SPECIALIST_ID NUMBER(15),
    SPECIALIST_NAME VARCHAR2(50) NOT NULL,
    SPECIALIST_LAST_NAME VARCHAR2(50) NOT NULL,
    DOCUMENT_TYPE_ID NUMBER(4),
    DOCUMENT_SPECIALIST VARCHAR2(10) NOT NULL,
    CELPHONE VARCHAR2(20),
    EMAIL VARCHAR2(100) NOT NULL,
    SPECIALITY_ID NUMBER(4)NOT NULL,
    START_TIME TIMESTAMP,
    END_TIME TIMESTAMP,
    LAST_CHANGE_DATE TIMESTAMP NOT NULL
);

COMMENT ON TABLE GEST_SPA.SPECIALISTS IS 'SPA Belleza specialists table';