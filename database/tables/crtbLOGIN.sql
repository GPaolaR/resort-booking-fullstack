/************************************************************************************
Description: Login table
Author:      Gina Riveros
Date:        12-10-2023
Project:     Oasis Resort and Spa
************************************************************************************/
CREATE TABLE LOGIN (
    LOGIN_ID NUMBER(15),
    USERNAME VARCHAR2(50) NOT NULL,
    PASSWORD_ENCRYPTED VARCHAR2(255) NOT NULL,
    ROL_ID NUMBER(4),
    LAST_CHANGE_DATE TIMESTAMP NOT NULL   
);

COMMENT ON TABLE GEST_SPA.LOGIN IS 'Oasis Resort and Spa login table';