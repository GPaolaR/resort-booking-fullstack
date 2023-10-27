package com.spabelleza.gestion_spa.service;

import com.spabelleza.gestion_spa.DTO.PlanDTO;

import java.util.List;

public interface IPlanService {
    List<PlanDTO> getPlansByMembershipTypeId(Integer membershipTypeId);
}
