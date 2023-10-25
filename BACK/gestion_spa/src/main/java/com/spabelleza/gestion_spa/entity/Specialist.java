package com.spabelleza.gestion_spa.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name="SPECIALISTS")
@Data
public class Specialist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "SEQ_SPLTS", sequenceName = "SEQ_SPLTS")
    @Column(name = "SPECIALIST_ID")
    private Long specialistId;

    @Column(name = "SPECIALIST_NAME")
    private String specialistName;

    @Column(name = "SPECIALIST_LAST_NAME")
    private String specialistLastname;

    @ManyToOne
    @JoinColumn(name = "DOCUMENT_TYPE_ID")
    private DocumentType documentType;

    @Column(name = "DOCUMENT_SPECIALIST")
    private String document;

    @Column(name = "CELPHONE")
    private String celphone;

    @Column(name = "EMAIL")
    private String email;

    @ManyToOne
    @JoinColumn(name = "SPECIALITY_ID")
    private Speciality specialty;

    @Column(name = "START_TIME")
    private Timestamp startTime;

    @Column(name = "END_TIME")
    private Timestamp endTime;

    @Column(name = "LAST_CHANGE_DATE")
    private Timestamp lastChangeDate;
}
