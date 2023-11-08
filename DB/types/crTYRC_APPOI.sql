/************************************************************************************
Description: Object responsible for .
Author:      Gina Riveros
Date:        18-10-2023
@copyright:  TechCamp
************************************************************************************/

CREATE OR REPLACE TYPE TYRC_APPOI AS OBJECT (
    specialist_name VARCHAR2(200),
    user_name VARCHAR2(200),
    membership_type VARCHAR2(20),
    plan_type VARCHAR2(50),
    speciality_name VARCHAR2(20),
    appointment_date TIMESTAMP,
    total_price NUMBER(8),
    status VARCHAR2(15)
);
/
