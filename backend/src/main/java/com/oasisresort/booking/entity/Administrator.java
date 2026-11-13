package com.oasisresort.booking.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name="ADMINISTRATORS")
@Data
@JsonIgnoreProperties({ "administratorId", "documentNumber", "documentType", "celphone", "lastChangeDate" })
public class Administrator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "SEQ_ADMIN", sequenceName = "SEQ_ADMIN")
    @Column(name = "ADMINISTRATOR_ID")
    private Long administratorId;

    @OneToOne
    @JoinColumn(name = "LOGIN_ID")
    private Login login;

    @Column(name = "ADMINISTRATOR_NAME")
    private String administratorName;

    @Column(name = "ADMINISTRATOR_LAST_NAME")
    private String administratorLastName;

    @ManyToOne
    @JoinColumn(name = "DOCUMENT_TYPE_ID")
    private DocumentType documentType;

    @Column(name = "DOCUMENT_NUMBER")
    private String documentNumber;

    @Column(name = "CELPHONE")
    private String celphone;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "LAST_CHANGE_DATE")
    private Timestamp lastChangeDate;
}
