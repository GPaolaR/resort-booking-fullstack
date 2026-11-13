package com.oasisresort.booking.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="DOCUMENT_TYPES")
@Data
@JsonIgnoreProperties({ "documentTypeId", "documentTypeName" })
public class DocumentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "SEQ_DOCTY", sequenceName = "SEQ_DOCTY")
    @Column(name = "DOCUMENT_TYPE_ID")
    private Integer documentTypeId;

    @Column(name = "CODE")
    private String code;

    @Column(name = "DOCUMENT_TYPE_NAME")
    private String documentTypeName;
}
