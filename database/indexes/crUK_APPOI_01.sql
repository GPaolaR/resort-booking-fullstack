/************************************************************************************
Description: Object responsible for the unique key for ADMINISTRATORS references DOCUMENT_NUMBER
Author:      Gina Riveros
Date:        12-10-2023
Project:     Oasis Resort and Spa
************************************************************************************/
CREATE UNIQUE INDEX UK_APPOI_01 ON GEST_SPA.APPOINTMENTS (SPECIALIST_ID, APPOINTMENT_DATE, STATUS)