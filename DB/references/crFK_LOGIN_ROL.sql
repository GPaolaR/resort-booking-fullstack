/************************************************************************************
Description: Object responsible for the foreign key for login references rol
Author:      Gina Riveros
Date:        12-10-2023
@copyright:  TechCamp
************************************************************************************/
ALTER TABLE GEST_SPA.LOGIN ADD CONSTRAINT FK_LOGIN_ROL FOREIGN KEY (ROL_ID) REFERENCES GEST_SPA.ROLES (ROL_ID);