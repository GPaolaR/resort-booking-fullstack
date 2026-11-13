/************************************************************************************
Description: Object responsible for the unique key for SPECIALISTS references DOCUMENT_SPECIALIST
Author:      Gina Riveros
Date:        12-10-2023
Project:     Oasis Resort and Spa
************************************************************************************/
CREATE UNIQUE INDEX UK_SPLTS_01 ON GEST_SPA.SPECIALISTS (DOCUMENT_SPECIALIST)