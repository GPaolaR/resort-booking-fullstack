package com.spabelleza.gestion_spa.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailableTimeSlotDTO {
    //private LocalTime startTime;
    //private LocalTime endTime;
    private Long specialistId;
    private String specialistName;
    private LocalDate date;
    private List<LocalTime> availableTimeSlots;
    /*public AvailableTimeSlotDTO(Long specialistId, LocalDate date, List<LocalTime> availableTimeSlotsOnDate) {
    }*/
}
