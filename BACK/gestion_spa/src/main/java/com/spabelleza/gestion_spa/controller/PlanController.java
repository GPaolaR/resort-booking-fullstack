package com.spabelleza.gestion_spa.controller;

import com.spabelleza.gestion_spa.DTO.PlanDTO;
import com.spabelleza.gestion_spa.service.IPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/plans")
public class PlanController {

    private final IPlanService iPlanService;

    @Autowired
    public PlanController(IPlanService iPlanService) {
        this.iPlanService = iPlanService;
    }

    @GetMapping("/{idMemty}")
    public ResponseEntity<List<PlanDTO>> getDiscountedPlans(@PathVariable("idMemty") Integer membershipTypeId) {
        List<PlanDTO> plans = iPlanService.getPlansByMembershipTypeId(membershipTypeId);
        return ResponseEntity.ok(plans);
    }
}

