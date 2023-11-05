package com.spabelleza.gestion_spa.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlansListDTO {
    private Long planId;
    private String planType;
}
