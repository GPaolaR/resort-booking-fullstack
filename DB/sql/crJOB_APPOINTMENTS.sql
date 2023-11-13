begin
    sys.dbms_scheduler.create_job(
    job_name =>'JOB_INSAPPOINTMENTS',
    job_type =>'PLSQL_BLOCK',
    job_action =>'declare
                    v_fecha_fin Appointments.appointment_date%TYPE:= TRUNC(SYSDATE);
                    v_fecha_inicio Appointments.appointment_date%TYPE:= ADD_MONTHS(SYSDATE, -1);
                BEGIN
                    PKG_REPORT.GET_REPORT(v_fecha_inicio, v_fecha_fin);
                END;',
    start_date =>systimestamp,
    repeat_interval => 'FREQ=MONTHLY;BYTIME=000000;BYMONTHDAY=1',
    end_date =>null,
    enabled =>true,
    comments =>'Insert data in REPORTS Table');
end;