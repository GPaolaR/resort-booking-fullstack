CREATE OR REPLACE PACKAGE PKG_REPORT IS

    PROCEDURE GET_REPORT (
        p_start_date IN Appointments.appointment_date%TYPE DEFAULT NULL,
        p_end_date IN Appointments.appointment_date%TYPE DEFAULT NULL
    );
        
END PKG_REPORT;
/
CREATE OR REPLACE PACKAGE BODY PKG_REPORT IS

    PROCEDURE GET_REPORT (
        
        p_start_date IN Appointments.appointment_date%TYPE DEFAULT NULL,
        p_end_date IN Appointments.appointment_date%TYPE DEFAULT NULL
    ) IS
        CUSTOM_EXCEPTION EXCEPTION;
    
    BEGIN
        DELETE FROM GEST_SPA.REPORTS;

        INSERT INTO GEST_SPA.REPORTS (USER_ID, USER_NAME, USER_LAST_NAME, GENDER, CELPHONE, EMAIL, MEMBERSHIP_TYPE, COUNT_APPOINTMENTS) 
        SELECT U.USER_ID,
               U.USER_NAME,
               U.USER_LAST_NAME,
               U.GENDER,
               U.CELPHONE,
               U.EMAIL,
               MT.MEMBERSHIP_TYPE,
               COUNT(*)
        FROM APPOINTMENTS A
        JOIN USERS U ON A.user_id = U.user_id 
        JOIN Membership_Types MT ON U.membership_type_id = MT.membership_type_id 
        WHERE TRUNC(A.appointment_date) BETWEEN TRUNC(p_start_date) AND TRUNC(p_end_date)
        GROUP BY U.USER_ID, U.USER_NAME, U.USER_LAST_NAME, U.GENDER, U.CELPHONE, U.EMAIL, MT.MEMBERSHIP_TYPE;
        COMMIT;
    EXCEPTION
    WHEN CUSTOM_EXCEPTION THEN
      raise_application_error(-20001, 'Error REPORTE',false);
   WHEN OTHERS THEN
      raise_application_error(-20000,SQLCODE||'=>'|| SQLERRM ,true);
            
    END GET_REPORT;
    
END PKG_REPORT;
/
