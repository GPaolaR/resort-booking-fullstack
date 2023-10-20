/************************************************************************************
Description: Roles table
Author:      Gina Riveros
Date:        12-10-2023
@copyright:  TechCamp
************************************************************************************/
CREATE TABLE ROLES (
    ROL_ID NUMBER(4),
    ROL_NAME VARCHAR2(20),
    ROL_DESCRIPTION VARCHAR2(150)
);

COMMENT ON TABLE GEST_SPA.ROLES IS 'SPA Belleza roles table';