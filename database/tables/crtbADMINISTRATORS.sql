/************************************************************************************
Description: Administrators table
Author:      Gina Riveros
Date:        12-10-2023
Project:     Oasis Resort and Spa
************************************************************************************/
CREATE TABLE ADMINISTRATORS (
    ADMINISTRATOR_ID NUMBER(15) ,
    LOGIN_ID NUMBER(15),
    ADMINISTRATOR_NAME VARCHAR2(50),
    ADMINISTRATOR_LAST_NAME VARCHAR2(50),
    DOCUMENT_TYPE_ID NUMBER(4),
    DOCUMENT_NUMBER VARCHAR2(10),
    CELPHONE VARCHAR2(15),
    EMAIL VARCHAR2(100) NOT NULL,
    LAST_CHANGE_DATE TIMESTAMP NOT NULL
   -- CONSTRAINT FK_ADMINISTRATORS_LOGIN_ID FOREIGN KEY (LOGIN_ID) REFERENCES LOGIN (LOGIN_ID),
   -- CONSTRAINT FK_ADMINISTRATORS_DOCUMENT_TYPE_ID FOREIGN KEY (DOCUMENT_TYPE_ID) REFERENCES DOCUMENTTYPES (DOCUMENT_TYPE_ID)
);

COMMENT ON TABLE GEST_SPA.ADMINISTRATORS IS 'Oasis Resort and Spa Adminstrators table';
