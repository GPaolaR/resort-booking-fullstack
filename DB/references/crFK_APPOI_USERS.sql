/************************************************************************************
Description: Object responsible for the foreign key for APPOINTMENTS references USERS
Author:      Gina Riveros
Date:        12-10-2023
@copyright:  TechCamp
************************************************************************************/
ALTER TABLE GEST_SPA.APPOINTMENTS ADD CONSTRAINT FK_APPOI_USERS FOREIGN KEY (USER_ID) REFERENCES GEST_SPA.USERS (USER_ID);