package com.spabelleza.gestion_spa.repository;

import com.spabelleza.gestion_spa.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    //StoredProcedureQuery createNamedQuery(String s);

    @Procedure(value="PKG_REPORT.GET_REPORT")
    void excuteProcess(Timestamp p_start_date, Timestamp p_end_date);
}
