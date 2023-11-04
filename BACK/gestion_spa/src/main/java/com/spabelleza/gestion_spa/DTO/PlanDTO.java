package com.spabelleza.gestion_spa.DTO;

import lombok.*;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
public class PlanDTO {
    private Long planId;
    private String planType;
    private String planDescription;
    private BigDecimal discountedPrice;
    private Integer pricePerPerson;


    /*public PlanDTO(String planType, String planDescription, BigDecimal discountedPrice, Integer pricePerPerson) {
        this.planType = planType;
        this.planDescription = planDescription;
        this.discountedPrice = discountedPrice;
        this.pricePerPerson = pricePerPerson;
    }*/

}

