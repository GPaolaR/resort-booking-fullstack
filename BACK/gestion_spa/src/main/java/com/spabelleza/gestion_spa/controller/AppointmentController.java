package com.spabelleza.gestion_spa.controller;

import com.spabelleza.gestion_spa.DTO.*;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @PostMapping("/upload")
    public ResponseEntity<?> cargarArchivo(@RequestParam("file") MultipartFile file) {
        try {
            // Verifica si el archivo está vacío
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body(getErrorResponse("El archivo está vacío"));
            }

            // Verifica el tipo de archivo (puedes agregar más validaciones según tus necesidades)
            if (!file.getContentType().equals("text/csv")) {
                return ResponseEntity.badRequest().body(getErrorResponse("El archivo debe ser de tipo CSV"));
            }

            // Pasa el contenido del archivo al servicio
            iAppointmentService.cargarArchivo(file);

            Map<String, String> successResponse = new HashMap<>();
            successResponse.put("status", "success");
            successResponse.put("message", "Carga de archivo exitosa.");

            return ResponseEntity.ok(successResponse);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(getErrorResponse("Error al cargar el archivo: " + e.getMessage()));
        }
    }

    private Map<String, String> getErrorResponse(String errorMessage) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("status", "error");
        errorResponse.put("message", errorMessage);
        return errorResponse;
    }
        /*try {
            // Verifica si el archivo está vacío
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("El archivo está vacío");
            }

            // Verifica el tipo de archivo (puedes agregar más validaciones según tus necesidades)
            if (!file.getContentType().equals("text/csv")) {
                return ResponseEntity.badRequest().body("El archivo debe ser de tipo CSV");
            }

            // Pasa el contenido del archivo al servicio
            iAppointmentService.cargarArchivo(file);

            return ResponseEntity.ok("Carga de archivo exitosa.");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al cargar el archivo: " + e.getMessage());
        }*/

}
