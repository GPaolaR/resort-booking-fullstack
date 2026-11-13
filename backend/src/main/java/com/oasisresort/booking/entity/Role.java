package com.oasisresort.booking.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="roles")
@Data
@JsonIgnoreProperties({ "roleId", "rolDescription" })
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "SEQ_ROL", sequenceName = "SEQ_ROL")
    @Column(name = "ROL_ID")
    private Long roleId;

    @Column(name = "ROL_NAME")
    private String rolName;

    @Column(name = "ROL_DESCRIPTION")
    private String rolDescription;
}