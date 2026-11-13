package com.spabelleza.gestion_spa.DTO;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
@Data
public class TYRC_APPOI {
    private BigDecimal appointmentId;
    private String specialistName;
    private String userName;
    private String membershipType;
    private String planType;
    private String specialityName;
    private Timestamp appointmentDate;
    private BigDecimal totalPrice;
    private String status;
}
