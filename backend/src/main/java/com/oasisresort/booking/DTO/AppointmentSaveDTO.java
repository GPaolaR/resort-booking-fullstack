package com.oasisresort.booking.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentSaveDTO {
    private String userId;
    private String specialistId;
    private String specialityId;
    private String planId;
    private String appointmentDate;
    private String duration;
    private String totalPrice;
    private String status;
    private String appointmentId;
}