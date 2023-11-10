package com.spabelleza.gestion_spa.service;

import com.spabelleza.gestion_spa.DTO.AppointmentBySpecialityDTO;
import com.spabelleza.gestion_spa.DTO.AppointmentDTO;
import com.spabelleza.gestion_spa.DTO.AvailableTimeSlotDTO;
import com.spabelleza.gestion_spa.DTO.TYRC_APPOI;

import java.sql.Timestamp;
import java.util.List;

public interface IAppointmentService {
    List<AppointmentBySpecialityDTO> getAppointmentsBySpecialityAndDate(Integer specialityId, Timestamp startDate);
    List<AvailableTimeSlotDTO> getAvailableTimeSlots(Integer specialityId, Timestamp startDate);
    List<TYRC_APPOI> getAppointmentAgenda(AppointmentDTO appointmentDTO);
    //List<Date> getDatesWithoutAvailableSpecialists(Speciality speciality, Plan plan);
    //List<Appointment> getAppointmentsBySpecialistAndDate(int specialistId, LocalDate date);

    //List<Appointment> getAppointmentsForUserInFuture(long userId);
    //List<Appointment> getAppointmentsByCriteria(int specialistId, long userId, int planId, LocalDate startDate, LocalDate endDate);



}
