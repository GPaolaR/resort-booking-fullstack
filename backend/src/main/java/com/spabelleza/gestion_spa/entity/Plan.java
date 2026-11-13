package com.spabelleza.gestion_spa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="PLANS")
@Data
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "SEQ_PLAN", sequenceName = "SEQ_PLAN")
    @Column(name = "PLAN_ID")
    private Long planId;

    @Column(name = "PLAN_TYPE")
    private String planType;

    @Column(name = "PLAN_DESCRIPTION")
    private String planDescription;

    @Column(name = "BASE_PRICE")
    private Long basePrice;

    @Column(name = "PRICE_PER_PERSON")
    private Integer pricePerPerson;
}
