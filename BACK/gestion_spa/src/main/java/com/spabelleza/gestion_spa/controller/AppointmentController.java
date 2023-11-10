package com.spabelleza.gestion_spa.controller;

import com.spabelleza.gestion_spa.DTO.AppointmentBySpecialityDTO;
import com.spabelleza.gestion_spa.DTO.AppointmentDTO;
import com.spabelleza.gestion_spa.DTO.AvailableTimeSlotDTO;
import com.spabelleza.gestion_spa.DTO.TYRC_APPOI;
import com.spabelleza.gestion_spa.service.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
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

    @GetMapping("/by-speciality-and-date")
    public ResponseEntity<List<AppointmentBySpecialityDTO>> getAppointmentsBySpecialityAndDate(
            @RequestParam("specialityId") Integer specialityId,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate
    ) {
        List<AppointmentBySpecialityDTO> appointments = iAppointmentService.getAppointmentsBySpecialityAndDate(specialityId, Timestamp.valueOf(startDate));
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/prueba")
    public ResponseEntity<List<AvailableTimeSlotDTO>> calculateAvailableTimeSlots(
            @RequestParam("specialityId") Integer specialityId,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate
    ) {
        List<AvailableTimeSlotDTO> appointments = iAppointmentService.getAvailableTimeSlots(specialityId, Timestamp.valueOf(startDate));
        return ResponseEntity.ok(appointments);
    }

    /*@GetMapping("/filter")
    public List<Appointment> getAppointments(
            @RequestParam int specialistId,
            @RequestParam(required = false) long userId,
            @RequestParam(required = false) Integer planId,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {
        LocalDate parsedStartDate = (startDate != null) ? LocalDate.parse(startDate) : null;
        LocalDate parsedEndDate = (endDate != null) ? LocalDate.parse(endDate) : null;
        return iAppointmentService.getAppointmentsByCriteria(specialistId, userId, planId, parsedStartDate, parsedEndDate);
    }

    @GetMapping("/userFuture")
    public List<Appointment> getAppointmentsForUserInFuture(
            @RequestParam long userId) {
            //@RequestParam String startDate
        //LocalDate parsedStartDate = LocalDate.parse(startDate);
        return iAppointmentService.getAppointmentsForUserInFuture(userId);
    }*/

    /*@GetMapping("/datesWithoutAvailableSpecialists")
    public List<Date> getDatesWithoutAvailableSpecialists(@RequestBody AppointmentDTO appointmentDTO) {
        Speciality speciality = appointmentDTO.getSpeciality();
        Plan plan = appointmentDTO.getPlan();
        return iAppointmentService.getDatesWithoutAvailableSpecialists(speciality, plan);
    }*/
}
