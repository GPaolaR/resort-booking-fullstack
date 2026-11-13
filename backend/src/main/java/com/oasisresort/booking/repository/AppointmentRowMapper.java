package com.oasisresort.booking.repository;


import com.oasisresort.booking.entity.*;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class AppointmentRowMapper implements RowMapper<Appointment> {
    @Override
    public Appointment mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Appointment appointment = new Appointment();
        appointment.setAppointmentId(resultSet.getLong("APPOINTMENT_ID"));

        User user = new User();
        user.setUserId(resultSet.getLong("USER_ID"));
        //user.setUserName(resultSet.getString("USER_NAME"));
        //user.setUserLastName(resultSet.getString("USER_LAST_NAME"));
        // ... mapear otras propiedades de User desde el ResultSet
        appointment.setUser(user);

        Specialist specialist = new Specialist();
        specialist.setSpecialistId(resultSet.getLong("SPECIALIST_ID"));
        //specialist.setSpecialistName(resultSet.getString("SPECIALIST_NAME"));
        //specialist.setSpecialistLastname(resultSet.getString("SPECIALIST_LAST_NAME"));
        // ... mapear otras propiedades de Specialist desde el ResultSet
        appointment.setSpecialist(specialist);

        Speciality speciality = new Speciality();
        speciality.setSpeciality(resultSet.getInt("SPECIALITY_ID"));
        //speciality.setSpecialityName(resultSet.getString("SPECIALITY_NAME"));
        // ... mapear otras propiedades de Speciality desde el ResultSet
        appointment.setSpeciality(speciality);

        Plan plan = new Plan();
        plan.setPlanId(resultSet.getLong("PLAN_ID"));
        //plan.setPlanType(resultSet.getString("PLAN_TYPE"));
        //plan.setPlanDescription(resultSet.getString("PLAN_DESCRIPTION"));
        // ... mapear otras propiedades de Plan desde el ResultSet
        appointment.setPlan(plan);

        appointment.setAppointmentDate(Timestamp.valueOf(resultSet.getTimestamp("APPOINTMENT_DATE").toLocalDateTime()));
        appointment.setDuration(resultSet.getInt("APPOINTMENT_DURATION"));
        appointment.setTotalPrice(resultSet.getBigDecimal("TOTAL_PRICE"));
        appointment.setStatus(resultSet.getString("STATUS"));
        appointment.setLastChangeDate(Timestamp.valueOf(resultSet.getTimestamp("LAST_CHANGE_DATE").toLocalDateTime()));

        return appointment;
    }
}

/*
public class AppointmentRowMapper implements RowMapper<Appointment> {

    @Override
    public Appointment mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Appointment appointment = new Appointment();
        appointment.setAppointmentId(resultSet.getLong("APPOINTMENT_ID"));

        User user = new User();
        user.setUserId(resultSet.getLong("USER_ID"));
        appointment.setUser(user);

        Specialist specialist = new Specialist();
        specialist.setSpecialistId(resultSet.getLong("SPECIALIST_ID"));
        appointment.setSpecialist(specialist);

        Speciality speciality = new Speciality();
        speciality.setSpecialityId(resultSet.getInt("SPECIALITY_ID"));
        appointment.setSpeciality(speciality);

        Plan plan = new Plan();
        plan.setPlanId(resultSet.getLong("PLAN_ID"));
        appointment.setPlan(plan);

        Timestamp appointmentDateTimestamp = resultSet.getTimestamp("APPOINTMENT_DATE");
        LocalDateTime appointmentDateTime = (appointmentDateTimestamp != null ? appointmentDateTimestamp.toLocalDateTime() : null);
        assert appointmentDateTime != null;
        appointment.setAppointmentDate(Timestamp.valueOf(appointmentDateTime));

        Timestamp appointmentTimeTimestamp = resultSet.getTimestamp("APPOINTMENT_TIME");
        LocalDateTime appointmentTimeDateTime = (appointmentTimeTimestamp != null ? appointmentTimeTimestamp.toLocalDateTime() : null);
        assert appointmentTimeDateTime != null;
        appointment.setAppointmentTime(Timestamp.valueOf(appointmentTimeDateTime));

        appointment.setDuration(resultSet.getInt("APPOINTMENT_DURATION"));
        appointment.setTotalPrice(resultSet.getLong("TOTAL_PRICE"));
        appointment.setStatus(resultSet.getString("STATUS"));

        Timestamp lastChangeDateTimestamp = resultSet.getTimestamp("LAST_CHANGE_DATE");
        LocalDateTime lastChangeDateTime = (lastChangeDateTimestamp != null ? lastChangeDateTimestamp.toLocalDateTime() : null);
        assert lastChangeDateTime != null;
        appointment.setLastChangeDate(Timestamp.valueOf(lastChangeDateTime));


        return appointment;
    }

}
*/
