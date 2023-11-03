package com.spabelleza.gestion_spa.service;

import com.spabelleza.gestion_spa.DTO.AppointmentDTO;
import com.spabelleza.gestion_spa.DTO.TYRC_APPOI;
import com.spabelleza.gestion_spa.entity.Appointment;

import java.time.LocalDate;
import java.util.List;

public interface IAppointmentService {

    List<TYRC_APPOI> getAppointmentAgenda(AppointmentDTO appointmentDTO);
    //List<Date> getDatesWithoutAvailableSpecialists(Speciality speciality, Plan plan);
    //List<Appointment> getAppointmentsBySpecialistAndDate(int specialistId, LocalDate date);
    List<Appointment> getAppointmentsForUserInFuture(long userId);
    List<Appointment> getAppointmentsByCriteria(int specialistId, long userId, int planId, LocalDate startDate, LocalDate endDate);



}
