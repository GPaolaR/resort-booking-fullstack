package com.oasisresort.booking.service;

import com.oasisresort.booking.DTO.SpecialistDTO;
import com.oasisresort.booking.DTO.SpecialistScheduleDTO;
import com.oasisresort.booking.entity.Specialist;
import com.oasisresort.booking.repository.SpecialistRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
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

    @Override
    public List<SpecialistScheduleDTO> getSpecialistScheduleBySpecialityId(Integer specialityId){
        List<Specialist> specialists = specialistRepository.findBySpecialitySpeciality(specialityId);
        ZoneId bogotaZone = ZoneId.of("America/Bogota"); // Zona horaria de Bogotá

        return specialists.stream()
                .map(specialist -> {
                    // Crear el objeto SpecialistScheduleDTO con los datos formateados
                    SpecialistScheduleDTO dto = new SpecialistScheduleDTO();
                    dto.setSpecialistId(specialist.getSpecialistId());
                    dto.setSpecialistName(specialist.getSpecialistName() + ' ' + specialist.getSpecialistLastname());
                    dto.setStartTime(specialist.getStartTime().toInstant().atZone(bogotaZone).toLocalTime());
                    dto.setEndTime(specialist.getEndTime().toInstant().atZone(bogotaZone).toLocalTime());
                     return dto;
                })
                .collect(Collectors.toList());
    }
}
