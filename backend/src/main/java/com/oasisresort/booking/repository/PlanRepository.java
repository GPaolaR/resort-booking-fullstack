package com.oasisresort.booking.repository;

import com.oasisresort.booking.DTO.PlanDTO;
import com.oasisresort.booking.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {
    @Query("SELECT new com.oasisresort.booking.DTO.PlanDTO(p.planId, p.planType, p.planDescription, p.basePrice * (1 - m.discount), p.pricePerPerson) FROM Plan p, MembershipType m WHERE m.membershipTypeId = :membershipTypeId")
    List<PlanDTO> getPlansByMembershipTypeId(@Param("membershipTypeId") Integer membershipTypeId);
}

