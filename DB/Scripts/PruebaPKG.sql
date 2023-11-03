SET SERVEROUTPUT ON

DECLARE
    v_specialist_id       Appointments.specialist_id%TYPE := 1;
    v_appointment_date    Appointments.appointment_date%TYPE := '01/11/2023 12:00';
    /*v_user_id       Appointments.user_id%TYPE :=1;
    v_plan_id       Appointments.plan_id%TYPE :=1;
    v_start_date    Appointments.appointment_date%TYPE := '01/11/2023 12:00';
    v_end_date    Appointments.appointment_date%TYPE := '01/11/2023 12:00';*/
  v_info                TYTB_APPOI;
BEGIN
  PKG_APPOINTMENT.GET_APPOINTMENT_AGENDA(v_specialist_id, v_appointment_date, NULL,NULL, NULL, NULL, v_info);
  dbms_output.put_line('Este es un mensaje de prueba1');
  DBMS_OUTPUT.PUT_LINE('Número de filas: ' || v_info.COUNT);

  FOR i IN 1..v_info.COUNT LOOP
  dbms_output.put_line('Este es un mensaje de prueba2');
        DBMS_OUTPUT.PUT_LINE('Specialist Name: ' || v_info(i).specialist_name);
        DBMS_OUTPUT.PUT_LINE('User Name: ' || v_info(i).user_name);
        DBMS_OUTPUT.PUT_LINE('Membership Type: ' || v_info(i).membership_type);
        DBMS_OUTPUT.PUT_LINE('Plan Type: ' || v_info(i).plan_type);
        DBMS_OUTPUT.PUT_LINE('Speciality Name: ' || v_info(i).speciality_name);
        DBMS_OUTPUT.PUT_LINE('Appointment Date: ' || TO_CHAR(v_info(i).appointment_date, 'YYYY-MM-DD HH24:MI:SS'));
        DBMS_OUTPUT.PUT_LINE('Total Price: ' || v_info(i).total_price);
        DBMS_OUTPUT.PUT_LINE('Status: ' || v_info(i).status);
        DBMS_OUTPUT.PUT_LINE('--------------------------------------');
    END LOOP;
END;

DECLARE v_appointment_date    Appointments.appointment_date%TYPE := '01/11/2023 12:00';
Begin
SELECT S.SPECIALIST_NAME,
            U.USER_NAME,
            MT.MEMBERSHIP_TYPE,
            P.PLAN_TYPE,
            SP.SPECIALITY_NAME,
            A.APPOINTMENT_DATE,
            A.TOTAL_PRICE,
            A.STATUS            
            FROM Appointments A
            JOIN Users U ON A.user_id = U.user_id
            JOIN Specialists S ON A.specialist_id = S.specialist_id
            JOIN Membership_Types MT ON U.membership_type_id = MT.membership_type_id
            JOIN Plans P ON A.plan_id = P.plan_id
            JOIN Specialities SP ON A.speciality_id = SP.speciality_id 
            WHERE 1=1  AND S.specialist_id = 1 AND TRUNC(A.appointment_date) = TRUNC(TO_TIMESTAMP(TO_CHAR(v_appointment_date, 'DD/MM/YY HH24:MI:SS') , 'DD/MM/YY HH24:MI:SS'));

END;

SET SERVEROUTPUT ON
BEGIN
   dbms_output.put_line('Este es un mensaje de prueba');
END;


SELECT object_name, status FROM user_objects WHERE object_name = 'PKG_APPOINTMENT';