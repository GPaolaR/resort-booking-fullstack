package com.oasisresort.booking.controller;

import com.oasisresort.booking.DTO.SpecialistDTO;
import com.oasisresort.booking.DTO.SpecialistScheduleDTO;
import com.oasisresort.booking.service.ISpecialistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/specialist")
public class SpecialistController {
    @Autowired
    private final ISpecialistService iSpecialistService;
    public SpecialistController(ISpecialistService iSpecialistService) {
        this.iSpecialistService = iSpecialistService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<SpecialistDTO>> getSpecialists(){
        List<SpecialistDTO> specialists = iSpecialistService.getSpecialists();
        return new ResponseEntity<>(specialists, HttpStatus.OK);
    }

    @GetMapping("/schedule")
    public ResponseEntity<List<SpecialistScheduleDTO>> getSpecialistSchedule(
            @RequestParam("specialityId") Integer specialityId
    ){
        List<SpecialistScheduleDTO> specialists = iSpecialistService.getSpecialistScheduleBySpecialityId(specialityId);
        return new ResponseEntity<>(specialists, HttpStatus.OK);
    }
}
