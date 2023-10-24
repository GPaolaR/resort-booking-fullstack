/************************************************************************************
Description: Specialties table
Author:      Gina Riveros
Date:        12-10-2023
@copyright:  TechCamp
************************************************************************************/
CREATE TABLE SPECIALITIES (
    SPECIALITY_ID NUMBER(4),
    SPECIALITY_NAME VARCHAR2(20),
    --PRICE NUMBER(8),
    LAST_CHANGE_DATE TIMESTAMP NOT NULL
);

COMMENT ON TABLE GEST_SPA.SPECIALITIES IS 'SPA Belleza specialty types table';