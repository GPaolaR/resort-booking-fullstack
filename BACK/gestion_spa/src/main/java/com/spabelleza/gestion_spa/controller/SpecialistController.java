package com.spabelleza.gestion_spa.controller;

import com.spabelleza.gestion_spa.DTO.SpecialistDTO;
import com.spabelleza.gestion_spa.service.ISpecialistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
