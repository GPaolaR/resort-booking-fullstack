package com.oasisresort.booking.service;

import com.oasisresort.booking.DTO.PlanDTO;
import com.oasisresort.booking.DTO.PlansListDTO;

import java.util.List;

public interface IPlanService {
    List<PlanDTO> getPlansByMembershipTypeId(Integer membershipTypeId);
    List<PlansListDTO> getPlans();
}
