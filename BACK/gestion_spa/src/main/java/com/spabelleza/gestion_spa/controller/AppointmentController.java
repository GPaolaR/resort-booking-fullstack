package com.spabelleza.gestion_spa.controller;

import com.spabelleza.gestion_spa.DTO.AppointmentDTO;
import com.spabelleza.gestion_spa.DTO.TYRC_APPOI;
import com.spabelleza.gestion_spa.entity.Appointment;
import com.spabelleza.gestion_spa.service.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final IAppointmentService iAppointmentService;

    @Autowired
    public AppointmentController(IAppointmentService iAppointmentService) {
        this.iAppointmentService = iAppointmentService;
    }

    @PostMapping("/get-agenda")
    public ResponseEntity<List<TYRC_APPOI>> getAppointmentAgenda(@RequestBody AppointmentDTO request) {
        //LocalDateTime appointmentDate = request.getAppointmentDate();
        //Timestamp timestamp = Timestamp.valueOf(appointmentDate);
        List<TYRC_APPOI> appointments = iAppointmentService.getAppointmentAgenda(request);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @GetMapping("/calculate-start-date")
    public ResponseEntity<LocalDate> calculateStartDate(
            @RequestParam Integer membershipTypeId,
            @RequestParam Integer planId) {

        LocalDate startDate = iAppointmentService.getPriorityAppointmentDate(membershipTypeId, planId);

        return ResponseEntity.ok(startDate);
    }

    @GetMapping("/by-speciality-and-date")
    public ResponseEntity<List<AppointmentBySpecialityDTO>> getAppointmentsBySpecialityAndDate(
            @RequestParam("specialityId") Integer specialityId,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate
    ) {
        List<AppointmentBySpecialityDTO> appointments = iAppointmentService.getAppointmentsBySpecialityAndDate(specialityId, Timestamp.valueOf(startDate));
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/available-time-slots")
    public ResponseEntity<List<MatchingTimeSlotResult>> calculateAvailableTimeSlots(
            @RequestParam("specialityId") List<Integer> specialityIds,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate
    ) {
        List<MatchingTimeSlotResult> appointments = iAppointmentService.getAvailableTimeSlots(specialityIds, Timestamp.valueOf(startDate));
        return ResponseEntity.ok(appointments);
    }
    @GetMapping("/available-time-slots-by-speciality")
    public ResponseEntity<List<AvailableTimeSlotDTO>> calculateAvailableTimeSlotsBySpeciality(
            @RequestParam("specialityId") Integer specialityId,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate
    ) {
        List<AvailableTimeSlotDTO> appointments = iAppointmentService.getAvailableTimeSlotsBySpeciality(specialityId, Timestamp.valueOf(startDate));
        return ResponseEntity.ok(appointments);
    }


}
