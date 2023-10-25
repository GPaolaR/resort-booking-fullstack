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
@Table(name="roles")
@Data
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