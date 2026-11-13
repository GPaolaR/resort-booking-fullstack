package com.oasisresort.booking.service;

import com.oasisresort.booking.DTO.*;
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

    String insertAppointment(String userId, String specialistId, String specialityId, String planId,
                                    String appointmentDate, String appointmentDuration, String totalPrice,
                                    String status);
    String editAppointment(String userId, String specialistId, String specialityId, String planId,
                             String appointmentDate, String appointmentDuration, String totalPrice,
                             String status, String appointmentId);
    String cancelAppointmente(Integer appointmentId);
    String uploadFile(MultipartFile file) throws IOException;

}
