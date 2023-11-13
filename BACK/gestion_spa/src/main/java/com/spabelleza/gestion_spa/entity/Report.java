package com.spabelleza.gestion_spa.entity;

import jakarta.persistence.*;
import lombok.Data;

/*@NamedNativeQuery(
        name = "getReport",
        query = "{call PKG_REPORT.GET_REPORT(:p_start_date, :p_end_date)}",
        resultClass = Report.class
)*/
@Entity
@Table(name = "REPORTS")
@Data
public class Report {

    @Id
    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "USER_NAME", nullable = false)
    private String userName;

    @Column(name = "USER_LAST_NAME", nullable = false)
    private String userLastName;

    @Column(name = "GENDER", length = 2)
    private String gender;

    @Column(name = "CELPHONE", length = 15)
    private String celPhone;

    @Column(name = "EMAIL", nullable = false, length = 100)
    private String email;

    @Column(name = "MEMBERSHIP_TYPE", nullable = false, length = 20)
    private String membershipType;

    @Column(name = "COUNT_APPOINTMENTS", nullable = false)
    private Integer countAppointments;
}
