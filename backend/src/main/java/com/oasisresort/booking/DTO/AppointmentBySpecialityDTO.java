package com.oasisresort.booking.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentBySpecialityDTO {
    private Long specialistId;
    private Integer specialityId;
    private LocalDateTime appointmentDate;
    private int duration;
}
