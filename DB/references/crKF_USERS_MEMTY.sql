/************************************************************************************
Description: Object responsible for the foreign key for USERES references MEMBERSHIP_TYPE
Author:      Gina Riveros
Date:        12-10-2023
@copyright:  TechCamp
************************************************************************************/
ALTER TABLE GEST_SPA.USERS ADD CONSTRAINT FK_USERS_MEMTY FOREIGN KEY (MEMBERSHIP_TYPE_ID) REFERENCES GEST_SPA.MEMBERSHIP_TYPES (MEMBERSHIP_TYPE_ID);