package com.spabelleza.gestion_spa.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentDTO {
    private Long specialistId;
    private LocalDateTime appointmentDate;
    private Long userId;
    private Long planId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
