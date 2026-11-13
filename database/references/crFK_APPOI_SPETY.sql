/************************************************************************************
Description: Object responsible for the foreign key for APPOINTMENTS references APPOINTMENT_TYPES
Author:      Gina Riveros
Date:        12-10-2023
Project:     Oasis Resort and Spa
************************************************************************************/
ALTER TABLE GEST_SPA.APPOINTMENTS ADD CONSTRAINT FK_APPOI_SPETY FOREIGN KEY (SPECIALITY_ID) REFERENCES GEST_SPA.SPECIALITIES (SPECIALITY_ID);