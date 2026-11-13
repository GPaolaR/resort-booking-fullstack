/************************************************************************************
Description: Appointments table
Author:      Gina Riveros
Date:        12-10-2023
@copyright:  TechCamp
************************************************************************************/
CREATE TABLE APPOINTMENTS (
    APPOINTMENT_ID NUMBER(15),
    USER_ID NUMBER(15),
    SPECIALIST_ID NUMBER(15),
    SPECIALITY_ID NUMBER(4),
    PLAN_ID NUMBER(4),
    APPOINTMENT_DATE TIMESTAMP NOT NULL,
    --APPOINTMENT_TIME TIMESTAMP NOT NULL,
    APPOINTMENT_DURATION NUMBER(2),
    TOTAL_PRICE NUMBER(8) NOT NULL,
    STATUS VARCHAR2(15) NOT NULL,
    LAST_CHANGE_DATE TIMESTAMP NOT NULL
);

COMMENT ON TABLE GEST_SPA.APPOINTMENTS IS 'SPA Belleza appointments table';