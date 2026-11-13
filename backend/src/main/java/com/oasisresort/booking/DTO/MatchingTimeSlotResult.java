package com.oasisresort.booking.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchingTimeSlotResult {
    private Long specialistId1;
    private String specialistName1;
    private Integer specialityId1;
    private Long specialistId2;
    private String specialistName2;
    private Integer specialityId2;
    private LocalDate date;
    private List<LocalTime> matchingTimeSlots;
}
