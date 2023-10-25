package com.spabelleza.gestion_spa.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;


@Entity
@Table(name="MEMBERSHIP_TYPES")
@Data
@JsonIgnoreProperties({ "membershipTypeId"})
public class MembershipType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "SEQ_MEMTY", sequenceName = "SEQ_MEMTY")
    @Column(name = "MEMBERSHIP_TYPE_ID")
    private Integer membershipTypeId;

    @Column(name = "MEMBERSHIP_TYPE")
    private String membershipType;

    @Column(name = "DISCOUNT", precision = 4, scale = 2)
    private BigDecimal discount;
}
