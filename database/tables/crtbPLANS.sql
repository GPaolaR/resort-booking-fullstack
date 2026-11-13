/************************************************************************************
Description: Plans table
Author:      Gina Riveros
Date:        12-10-2023
Project:     Oasis Resort and Spa
************************************************************************************/
CREATE TABLE PLANS (
    PLAN_ID NUMBER(4),
    PLAN_TYPE VARCHAR2(50) NOT NULL,
    PLAN_DESCRIPTION VARCHAR(500)NOT NULL,
    BASE_PRICE NUMBER(8) NOT NULL,
    PRICE_PER_PERSON NUMBER(1) NOT NULL
);

COMMENT ON TABLE GEST_SPA.PLANS IS 'Oasis Resort and Spa plans table';