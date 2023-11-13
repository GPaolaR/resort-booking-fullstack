package com.spabelleza.gestion_spa.service;

import com.spabelleza.gestion_spa.DTO.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

public interface IAppointmentService {
    List<AppointmentBySpecialityDTO> getAppointmentsBySpecialityAndDate(Integer specialityId, Timestamp startDate);
    List<MatchingTimeSlotResult> getAvailableTimeSlots(List<Integer> specialityId, Timestamp startDate);
    List<AvailableTimeSlotDTO> getAvailableTimeSlotsBySpeciality (Integer specialityId, Timestamp startDate);
    List<TYRC_APPOI> getAppointmentAgenda(AppointmentDTO appointmentDTO);
    LocalDate getPriorityAppointmentDate(Integer membershipTypeId, Integer planId);

    String uploadFile(MultipartFile file) throws IOException;

}
