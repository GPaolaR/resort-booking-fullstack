package com.spabelleza.gestion_spa.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
@Entity
@Table(name="APPOINTMENTS")
@Data
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "SEQ_APPOI", sequenceName = "SEQ_APPOI")
    @Column(name = "APPOINTMENT_ID")
    private Long appointmentId;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "SPECIALIST_ID")
    private Specialist specialist;

    @ManyToOne
    @JoinColumn(name = "SPECIALITY_ID")
    private Speciality speciality;

    @ManyToOne
    @JoinColumn(name = "PLAN_ID")
    private Plan plan;

    @Column(name = "APPOINTMENT_DATE")
    private Timestamp appointmentDate;

    @Column(name = "APPOINTMENT_TIME")
    private Timestamp appointmentTime;

    @Column(name = "APPOINTMENT_DURATION")
    private int duration;

    @Column(name = "TOTAL_PRICE")
    private Long totalPrice;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "LAST_CHANGE_DATE")
    private Timestamp lastChangeDate;
}
