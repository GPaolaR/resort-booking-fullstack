package com.spabelleza.gestion_spa.DTO;

import com.spabelleza.gestion_spa.entity.Plan;
import com.spabelleza.gestion_spa.entity.Speciality;
import lombok.Data;

@Data
public class AppointmentDTO {
    private Speciality speciality;
    private Plan plan;
}
