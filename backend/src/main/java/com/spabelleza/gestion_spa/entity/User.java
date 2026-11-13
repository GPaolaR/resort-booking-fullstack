package com.spabelleza.gestion_spa.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name="USERS")
@Data
@JsonIgnoreProperties({ "lastChangeDate" })
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "SEQ_USER", sequenceName = "SEQ_USER")
    @Column(name = "USER_ID")
    private Long userId;

    @OneToOne
    @JoinColumn(name = "LOGIN_ID")
    private Login login;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "USER_LAST_NAME")
    private String userLastName;

    @ManyToOne
    @JoinColumn(name = "DOCUMENT_TYPE_ID")
    private DocumentType documentType;

    @Column(name = "DOCUMENT_NUMBER")
    private String documentNumber;

    @Column(name = "AGE")
    private int age;

    @Column(name = "GENDER")
    private String gender;

    @Column(name = "CELPHONE")
    private String celphone;

    @Column(name = "EMAIL")
    private String email;

    @ManyToOne
    @JoinColumn(name = "MEMBERSHIP_TYPE_ID")
    private MembershipType membershipType;

    @Column(name = "LAST_CHANGE_DATE")
    private Timestamp lastChangeDate;
}