package com.spabelleza.gestion_spa.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name="SPECIALITIES")
@Data
public class Speciality {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "SEQ_SPETY", sequenceName = "SEQ_SPETY")
    @Column(name = "SPECIALITY_ID")
    private Integer speciality;

    @Column(name = "SPECIALITY_NAME")
    private String specialityName;

    @Column(name = "LAST_CHANGE_DATE")
    private Timestamp lastChangeDate;
}
