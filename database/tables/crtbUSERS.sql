/************************************************************************************
Description: Users table
Author:      Gina Riveros
Date:        12-10-2023
Project:     Oasis Resort and Spa
************************************************************************************/
CREATE TABLE USERS (
    USER_ID NUMBER(15),
    LOGIN_ID NUMBER(15),
    USER_NAME VARCHAR2(50) NOT NULL,
    USER_LAST_NAME VARCHAR2(50) NOT NULL,
    DOCUMENT_TYPE_ID NUMBER(4),
    DOCUMENT_NUMBER VARCHAR2(10) NOT NULL,
    AGE NUMBER(3),
    GENDER VARCHAR2(2),
    CELPHONE VARCHAR2(15),
    EMAIL VARCHAR2(100) NOT NULL,
    MEMBERSHIP_TYPE_ID NUMBER(4), 
    LAST_CHANGE_DATE TIMESTAMP NOT NULL
);

COMMENT ON TABLE GEST_SPA.USERS IS 'Oasis Resort and Spa users table';