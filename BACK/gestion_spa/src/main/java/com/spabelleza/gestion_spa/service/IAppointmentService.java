package com.spabelleza.gestion_spa.service;

import com.spabelleza.gestion_spa.entity.Plan;
import com.spabelleza.gestion_spa.entity.Speciality;

import java.util.Date;
import java.util.List;

public interface IAppointmentService {

    List<Date> getDatesWithoutAvailableSpecialists(Speciality speciality, Plan plan);


}
