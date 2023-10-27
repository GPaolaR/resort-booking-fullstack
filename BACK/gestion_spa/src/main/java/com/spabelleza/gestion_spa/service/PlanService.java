package com.spabelleza.gestion_spa.service;

import com.spabelleza.gestion_spa.DTO.PlanDTO;
import com.spabelleza.gestion_spa.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanService implements IPlanService{
    private final PlanRepository planRepository;

    @Autowired
    public PlanService(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }
    @Override
    public List<PlanDTO> getPlansByMembershipTypeId(Integer membershipTypeId) {
        return planRepository.getPlansByMembershipTypeId(membershipTypeId);
    }
}
