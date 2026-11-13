/************************************************************************************
Description: Object responsible for the foreign key for SPECIALISTS references SPECIALITY_ID
Author:      Gina Riveros
Date:        12-10-2023
Project:     Oasis Resort and Spa
************************************************************************************/
ALTER TABLE GEST_SPA.SPECIALISTS ADD CONSTRAINT FK_SPLTS_SPETY FOREIGN KEY (SPECIALITY_ID) REFERENCES GEST_SPA.SPECIALITIES (SPECIALITY_ID);