/************************************************************************************
Description: Plans table
Author:      Gina Riveros
Date:        12-10-2023
@copyright:  TechCamp
************************************************************************************/
CREATE TABLE PLANS (
    PLAN_ID NUMBER(4),
    PLAN_TYPE VARCHAR2(20) NOT NULL,
    BASE_PRICE NUMBER(8) NOT NULL
);

COMMENT ON TABLE GEST_SPA.PLANS IS 'SPA Belleza plans table';