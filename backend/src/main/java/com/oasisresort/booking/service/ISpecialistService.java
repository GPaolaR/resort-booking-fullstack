package com.oasisresort.booking.service;

import com.oasisresort.booking.DTO.SpecialistDTO;
import com.oasisresort.booking.DTO.SpecialistScheduleDTO;

import java.util.List;

public interface ISpecialistService {
    List<SpecialistDTO> getSpecialists();
    List<SpecialistScheduleDTO> getSpecialistScheduleBySpecialityId(Integer specialityId);
}
