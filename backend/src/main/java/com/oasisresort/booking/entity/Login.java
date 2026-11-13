package com.oasisresort.booking.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
@Table(name="LOGIN")
@JsonIgnoreProperties({ "username", "passwordEncrypted", "lastChangeDate" })
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

    @ManyToOne
    @JoinColumn(name = "ROL_ID")
    private Role role;

    @Column(name = "LAST_CHANGE_DATE", nullable = false)
    private Timestamp lastChangeDate;

}

