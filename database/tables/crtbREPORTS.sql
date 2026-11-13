/************************************************************************************
Description: Reports table
Author:      Gina Riveros
Date:        10-11-2023
Project:     Oasis Resort and Spa
************************************************************************************/
CREATE TABLE REPORTS (
--    REPORT_ID NUMBER(15),
    USER_ID NUMBER(15),
    USER_NAME VARCHAR2(50) NOT NULL,
    USER_LAST_NAME VARCHAR2(50) NOT NULL,
    GENDER VARCHAR2(2),
    CELPHONE VARCHAR2(15),
    EMAIL VARCHAR2(100) NOT NULL,
    MEMBERSHIP_TYPE VARCHAR2(20) NOT NULL, 
    COUNT_APPOINTMENTS NUMBER(10) NOT NULL
);

COMMENT ON TABLE GEST_SPA.REPORTS IS 'Oasis Resort and Spa reports table';