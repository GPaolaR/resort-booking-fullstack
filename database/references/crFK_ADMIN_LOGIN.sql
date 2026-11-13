/************************************************************************************
Description: Object responsible for the foreign key for ADMINISTRATORS references login
Author:      Gina Riveros
Date:        12-10-2023
Project:     Oasis Resort and Spa
************************************************************************************/
ALTER TABLE GEST_SPA.ADMINISTRATORS ADD CONSTRAINT FK_ADMIN_LOGIN FOREIGN KEY (LOGIN_ID) REFERENCES GEST_SPA.LOGIN (LOGIN_ID);