/************************************************************************************
Descripci�n: Drop GEST_SPA JOB_INSAPPOINTMENTS
Autor:       Gina Riveros
Fecha:       12-11-2023
Project:     Oasis Resort and Spa
************************************************************************************/
BEGIN
  DBMS_SCHEDULER.DROP_JOB(
    job_name => 'JOB_INSAPPOINTMENTS',
    force    => TRUE
  );
END;
/