package com.oasisresort.booking.service;

import com.oasisresort.booking.entity.Report;

import java.sql.Timestamp;
import java.util.List;

public interface IReportService {

    List<Report> getReport();
    Report getReportById(Long userId);
    String getCurrentDate();
    String createCSV();
    void executeProcess(Timestamp startDate, Timestamp endDate);

}
