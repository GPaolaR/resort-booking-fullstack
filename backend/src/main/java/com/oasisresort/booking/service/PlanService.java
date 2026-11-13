package com.oasisresort.booking.service;

import com.oasisresort.booking.DTO.PlanDTO;
import com.oasisresort.booking.DTO.PlansListDTO;
import com.oasisresort.booking.entity.Plan;
import com.oasisresort.booking.repository.PlanRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlanService implements IPlanService{
    private final PlanRepository planRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PlanService(PlanRepository planRepository, ModelMapper modelMapper) {

        this.planRepository = planRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public List<PlanDTO> getPlansByMembershipTypeId(Integer membershipTypeId) {
        return planRepository.getPlansByMembershipTypeId(membershipTypeId);
    }
    @Override
    public List<PlansListDTO> getPlans(){
        List<Plan> plans = planRepository.findAll();
        return plans.stream()
                .map(plan -> modelMapper.map(plan, PlansListDTO.class))
                .collect(Collectors.toList());
    }
}
