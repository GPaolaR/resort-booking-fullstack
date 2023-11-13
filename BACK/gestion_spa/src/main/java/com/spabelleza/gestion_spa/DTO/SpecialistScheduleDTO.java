package com.spabelleza.gestion_spa.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecialistScheduleDTO {
    private Long specialistId;
    private String specialistName;
    private LocalTime startTime;
    private LocalTime  endTime;


}
