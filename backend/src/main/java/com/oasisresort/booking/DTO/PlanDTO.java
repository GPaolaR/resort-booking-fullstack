package com.oasisresort.booking.DTO;

import lombok.*;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanDTO {
    private Long planId;
    private String planType;
    private String planDescription;
    private BigDecimal discountedPrice;
    private Integer pricePerPerson;


   /* public PlanDTO(Long planId, String planType, String planDescription, BigDecimal discountedPrice, Integer pricePerPerson) {
        this.planId = planId;
        this.planType = planType;
        this.planDescription = planDescription;
        this.discountedPrice = discountedPrice;
        this.pricePerPerson = pricePerPerson;
    }*/

}

