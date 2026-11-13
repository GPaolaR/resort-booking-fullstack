CREATE OR REPLACE PACKAGE PKG_APPOINTMENT IS    

    PROCEDURE GET_APPOINTMENT_AGENDA (
        p_specialist_id IN Appointments.specialist_id%TYPE DEFAULT NULL,
        p_appointment_date IN Appointments.appointment_date%TYPE DEFAULT NULL,
        p_user_id IN Appointments.USER_ID%TYPE DEFAULT NULL,
        p_plan_id IN Appointments.PLAN_ID%TYPE DEFAULT NULL,
        p_start_date IN Appointments.appointment_date%TYPE DEFAULT NULL,
        p_end_date IN Appointments.appointment_date%TYPE DEFAULT NULL,
        orc out nocopy TYTB_APPOI
    );
    
    PROCEDURE CANCEL_APPOINTMENT( p_appointment_id IN Appointments.appointment_id%TYPE);
    
        
END PKG_APPOINTMENT;
/
CREATE OR REPLACE PACKAGE BODY PKG_APPOINTMENT IS

    PROCEDURE GET_APPOINTMENT_AGENDA (
        p_specialist_id IN Appointments.specialist_id%TYPE DEFAULT NULL,
        p_appointment_date IN Appointments.appointment_date%TYPE DEFAULT NULL,
        p_user_id IN Appointments.USER_ID%TYPE DEFAULT NULL,
        p_plan_id IN Appointments.PLAN_ID%TYPE DEFAULT NULL,
        p_start_date IN Appointments.appointment_date%TYPE DEFAULT NULL,
        p_end_date IN Appointments.appointment_date%TYPE DEFAULT NULL,
        orc out nocopy TYTB_APPOI
    ) IS
        CUSTOM_EXCEPTION EXCEPTION;
        cuAPPOI SYS_REFCURSOR;
        v_sql_query VARCHAR2(1000);
         
    BEGIN
        /*DBMS_OUTPUT.PUT_LINE('SpecialistId: ' || p_specialist_id);
        DBMS_OUTPUT.PUT_LINE('AppointmentDate: ' || TRUNC(p_appointment_date));
        DBMS_OUTPUT.PUT_LINE('UserId: ' || p_user_id);
        DBMS_OUTPUT.PUT_LINE('Plan_id: ' || p_plan_id);
        DBMS_OUTPUT.PUT_LINE('Start_date: ' || p_start_date);
        DBMS_OUTPUT.PUT_LINE('End_date: ' || p_end_date);*/
    
        v_sql_query := 'SELECT TYRC_APPOI(
            A.APPOINTMENT_ID,
            S.SPECIALIST_NAME || '' '' || S.SPECIALIST_LAST_NAME,
            U.USER_NAME|| '' ''|| U.USER_LAST_NAME,
            MT.MEMBERSHIP_TYPE,
            P.PLAN_TYPE,
            SP.SPECIALITY_NAME,
            A.APPOINTMENT_DATE,
            A.TOTAL_PRICE,
            A.STATUS
            )
            FROM Appointments A
            JOIN Users U ON A.user_id = U.user_id
            JOIN Specialists S ON A.specialist_id = S.specialist_id
            JOIN Membership_Types MT ON U.membership_type_id = MT.membership_type_id
            JOIN Plans P ON A.plan_id = P.plan_id
            JOIN Specialities SP ON A.speciality_id = SP.speciality_id 
            WHERE 1=1 ';

        IF p_specialist_id IS NOT NULL THEN
            v_sql_query := v_sql_query || ' AND S.specialist_id = ' || p_specialist_id;
        END IF;

        IF p_user_id IS NOT NULL THEN
            v_sql_query := v_sql_query || ' AND U.user_id = ' || p_user_id;
        END IF;

       IF p_plan_id IS NOT NULL THEN
            v_sql_query := v_sql_query || ' AND P.plan_id = ' || p_plan_id;
        END IF;
        
        IF p_appointment_date IS NOT NULL THEN
            v_sql_query := v_sql_query || ' AND TRUNC(A.appointment_date) = TRUNC(TO_TIMESTAMP(''' || TO_CHAR(p_appointment_date, 'DD/MM/YY HH24:MI:SS') || ''', ''DD/MM/YY HH24:MI:SS''))';
        END IF;

       IF p_start_date IS NOT NULL AND p_end_date IS NOT NULL THEN
            v_sql_query := v_sql_query || 'AND TRUNC(A.appointment_date) BETWEEN TRUNC(TO_TIMESTAMP(''' || TO_CHAR(p_start_date, 'DD/MM/YY HH24:MI:SS') || ''', ''DD/MM/YY HH24:MI:SS'')) AND TRUNC(TO_TIMESTAMP(''' || TO_CHAR(p_end_date, 'DD/MM/YY HH24:MI:SS') || ''', ''DD/MM/YY HH24:MI:SS''))';
        END IF;
        
        DBMS_OUTPUT.PUT_LINE('query: ' || v_sql_query);

            OPEN cuAPPOI FOR v_sql_query;
            FETCH cuAPPOI BULK COLLECT INTO orc;
            CLOSE cuAPPOI;
 
    EXCEPTION
    WHEN CUSTOM_EXCEPTION THEN
      -- Revision de cursores
      if cuAPPOI%isopen then
        close cuAPPOI;
      end if;
      -- Codigo DETALLE_PEDIDOlizado para manejo de ecepciones de usuario  - Crear funcionalidad para menejo de mensajes en un tabla
      raise_application_error(-20001, 'Error GET_APPOINTMENT_AGENDA',false);
    WHEN OTHERS THEN
      -- Revision de cursores
      if cuAPPOI%isopen then
      close cuAPPOI;
      end if;   
      raise_application_error(-20000,SQLCODE||'=>'|| SQLERRM ,true);   
            
    END GET_APPOINTMENT_AGENDA;
    
    
    PROCEDURE CANCEL_APPOINTMENT(
        p_appointment_id IN Appointments.appointment_id%TYPE
    ) IS
    BEGIN
        UPDATE Appointments
        SET STATUS = 'Cancelada'
        WHERE appointment_id = p_appointment_id;
        
        COMMIT; -- Realizar la confirmación para hacer permanentes los cambios en la base de datos
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            -- Manejar la excepción si no se encuentra la cita con el ID proporcionado
            DBMS_OUTPUT.PUT_LINE('No se encontró la cita con el ID proporcionado.');
        WHEN OTHERS THEN
            -- Manejar otras excepciones
            DBMS_OUTPUT.PUT_LINE('Ocurrió un error: ' || SQLCODE || ' - ' || SQLERRM);
    END CANCEL_APPOINTMENT;

    
END PKG_APPOINTMENT;
/
