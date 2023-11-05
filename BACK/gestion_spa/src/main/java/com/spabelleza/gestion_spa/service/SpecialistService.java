package com.spabelleza.gestion_spa.service;

import com.spabelleza.gestion_spa.DTO.SpecialistDTO;
import com.spabelleza.gestion_spa.entity.Specialist;
import com.spabelleza.gestion_spa.repository.SpecialistRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpecialistService implements ISpecialistService{

    private final SpecialistRepository specialistRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public SpecialistService(SpecialistRepository specialistRepository, ModelMapper modelMapper) {
        this.specialistRepository = specialistRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<SpecialistDTO> getSpecialists(){
        List<Specialist> specialists = specialistRepository.findAll();
        return specialists.stream()
                .map(specialist -> {
                    String fullName = String.join(" ", specialist.getSpecialistName(), specialist.getSpecialistLastname())
                            .replaceAll("\\s+", " ").trim();
                    SpecialistDTO specialistDTO = modelMapper.map(specialist, SpecialistDTO.class);
                    specialistDTO.setSpecialistFullName(fullName);
                    return specialistDTO;
                })
                .collect(Collectors.toList());
    }
}
