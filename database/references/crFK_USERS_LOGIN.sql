/************************************************************************************
Description: Object responsible for the foreign key for USERS references LOGIN
Author:      Gina Riveros
Date:        12-10-2023
@copyright:  TechCamp
************************************************************************************/
ALTER TABLE GEST_SPA.USERS ADD CONSTRAINT FK_USERS_LOGIN FOREIGN KEY (LOGIN_ID) REFERENCES GEST_SPA.LOGIN (LOGIN_ID);