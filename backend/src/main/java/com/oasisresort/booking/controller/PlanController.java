package com.oasisresort.booking.controller;

import com.oasisresort.booking.DTO.PlanDTO;
import com.oasisresort.booking.DTO.PlansListDTO;
import com.oasisresort.booking.service.IPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @GetMapping("/list")
    public ResponseEntity<List<PlansListDTO>> getPlans() {
        List<PlansListDTO> plans = iPlanService.getPlans();
        return new ResponseEntity<>(plans, HttpStatus.OK);
    }

}

