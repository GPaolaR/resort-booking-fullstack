package com.oasisresort.booking.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecialistDTO {
    private Long specialistId;
    private String specialistFullName; // Campo para el nombre completo

    public SpecialistDTO(Long specialistId, String specialistName, String specialistLastname) {
        this.specialistId = specialistId;
        this.specialistFullName = specialistName + " " + specialistLastname;
    }
}
