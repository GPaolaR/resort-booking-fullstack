/************************************************************************************
Description: Object responsible for the foreign key for APPOINTMENTS references SPECIALISTS
Author:      Gina Riveros
Date:        12-10-2023
Project:     Oasis Resort and Spa
************************************************************************************/
ALTER TABLE GEST_SPA.APPOINTMENTS ADD CONSTRAINT FK_APPOI_SPLTS FOREIGN KEY (SPECIALIST_ID) REFERENCES GEST_SPA.SPECIALISTS (SPECIALIST_ID);