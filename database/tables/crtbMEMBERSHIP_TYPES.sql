/************************************************************************************
Description: Membership_types table
Author:      Gina Riveros
Date:        12-10-2023
Project:     Oasis Resort and Spa
************************************************************************************/
CREATE TABLE MEMBERSHIP_TYPES (
    MEMBERSHIP_TYPE_ID NUMBER(4),
    MEMBERSHIP_TYPE VARCHAR2(20) NOT NULL,
    DISCOUNT NUMBER(4,2) NOT NULL
);

COMMENT ON TABLE GEST_SPA.MEMBERSHIP_TYPES IS 'Oasis Resort and Spa membership types table';
