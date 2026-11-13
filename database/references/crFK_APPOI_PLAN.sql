/************************************************************************************
Description: Object responsible for the foreign key for APPOINTMENTS references PLANS
Author:      Gina Riveros
Date:        12-10-2023
Project:     Oasis Resort and Spa
************************************************************************************/
ALTER TABLE GEST_SPA.APPOINTMENTS ADD CONSTRAINT FK_APPOI_PLAN FOREIGN KEY (PLAN_ID) REFERENCES GEST_SPA.PLANS (PLAN_ID);