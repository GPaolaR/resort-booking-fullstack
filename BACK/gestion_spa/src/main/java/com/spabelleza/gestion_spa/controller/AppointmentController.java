package com.spabelleza.gestion_spa.controller;

import com.spabelleza.gestion_spa.entity.Plan;
import com.spabelleza.gestion_spa.entity.Speciality;
import com.spabelleza.gestion_spa.DTO.AppointmentDTO;
import com.spabelleza.gestion_spa.service.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final IAppointmentService iAppointmentService;

    @Autowired
    public AppointmentController(IAppointmentService iAppointmentService) {
        this.iAppointmentService = iAppointmentService;
    }

    @GetMapping("/datesWithoutAvailableSpecialists")
    public List<Date> getDatesWithoutAvailableSpecialists(@RequestBody AppointmentDTO appointmentDTO) {
        Speciality speciality = appointmentDTO.getSpeciality();
        Plan plan = appointmentDTO.getPlan();
        return iAppointmentService.getDatesWithoutAvailableSpecialists(speciality, plan);
    }
}
