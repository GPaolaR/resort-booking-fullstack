package com.spabelleza.gestion_spa.request;

import com.spabelleza.gestion_spa.entity.Plan;
import com.spabelleza.gestion_spa.entity.Speciality;
import lombok.Data;

@Data
public class AppointmentRequest {
    private Speciality speciality;
    private Plan plan;
}
