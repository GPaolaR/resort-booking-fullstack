package com.spabelleza.gestion_spa.entity;

import lombok.Data;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Data
@Table(name="LOGIN")
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "SEQ_LOGIN", sequenceName = "SEQ_LOGIN")
    @Column(name = "LOGIN_ID")
    private Long loginId;

    @Column(name = "USERNAME", nullable = false, length = 50)
    private String username;

    @Column(name = "PASSWORD_ENCRYPTED", nullable = false, length = 255)
    private String passwordEncrypted;

    @Column(name = "ROL_ID")
    private Integer roleId;

    @Column(name = "LAST_CHANGE_DATE", nullable = false)
    private Timestamp lastChangeDate;

}

