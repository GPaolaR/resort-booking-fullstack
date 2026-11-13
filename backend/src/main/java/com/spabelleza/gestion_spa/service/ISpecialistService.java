package com.spabelleza.gestion_spa.service;

import com.spabelleza.gestion_spa.DTO.SpecialistDTO;
import com.spabelleza.gestion_spa.DTO.SpecialistScheduleDTO;

import java.util.List;

public interface ISpecialistService {
    List<SpecialistDTO> getSpecialists();
    List<SpecialistScheduleDTO> getSpecialistScheduleBySpecialityId(Integer specialityId);
}
