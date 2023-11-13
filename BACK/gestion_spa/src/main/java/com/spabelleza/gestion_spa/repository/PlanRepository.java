package com.spabelleza.gestion_spa.repository;

import com.spabelleza.gestion_spa.DTO.PlanDTO;
import com.spabelleza.gestion_spa.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {
    @Query("SELECT new com.spabelleza.gestion_spa.DTO.PlanDTO(p.planId, p.planType, p.planDescription, p.basePrice * (1 - m.discount), p.pricePerPerson) FROM Plan p, MembershipType m WHERE m.membershipTypeId = :membershipTypeId")
    List<PlanDTO> getPlansByMembershipTypeId(@Param("membershipTypeId") Integer membershipTypeId);
}

