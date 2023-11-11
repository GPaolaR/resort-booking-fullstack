package com.spabelleza.gestion_spa.service;

import com.spabelleza.gestion_spa.DTO.*;

import java.sql.Timestamp;
import java.util.List;

public interface IAppointmentService {
    List<AppointmentBySpecialityDTO> getAppointmentsBySpecialityAndDate(Integer specialityId, Timestamp startDate);
    List<MatchingTimeSlotResult> getAvailableTimeSlots(List<Integer> specialityId, Timestamp startDate);
    List<AvailableTimeSlotDTO> getAvailableTimeSlotsBySpeciality (Integer specialityId, Timestamp startDate);
    List<TYRC_APPOI> getAppointmentAgenda(AppointmentDTO appointmentDTO);

}
