package com.oasisresort.booking.repository;

import com.oasisresort.booking.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    //List<Appointment> findBySpecialityIdAndAppointmentDateAfter(Long specialityId, Timestamp startDate);
    List<Appointment> findBySpecialitySpecialityAndAppointmentDateAfterAndStatusNot(Integer specialityId, Timestamp startDate, String status);

}
