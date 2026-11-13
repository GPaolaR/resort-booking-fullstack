package com.oasisresort.booking.service;

import com.oasisresort.booking.entity.Report;
import com.oasisresort.booking.repository.ReportRepository;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
@Service
public class ReportService implements IReportService{

    private final ReportRepository reportRepository;

    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public List<Report> getReport(){
        return this.reportRepository.findAll();
    }

    @Override
    public Report getReportById(Long userId) {
        Report report = this.reportRepository.findById(userId).orElse(null);
        return report;
    }

    public String getCurrentDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        Calendar date = Calendar.getInstance();
        String currentDate = dateFormat.format(date.getTime());
        return currentDate;
    }
    @Override
    public void executeProcess(Timestamp startDate, Timestamp endDate) {
        reportRepository.excuteProcess(startDate,endDate);
    }

    public String createCSV(){

        String csvFilePath = "reports/ReporteMensual"+"_"+getCurrentDate()+".csv";

        List<Report> reportValues = getReport();
        String csvReport;
        String CSV_HEADER = "UserID,Name,LastName,Gender,Cellphone,Email,MembershipType,CountAppointments\n";

        try{
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(csvFilePath));
            fileWriter.write(CSV_HEADER);

            for(int i=0;i<reportValues.size();i++){
                csvReport = reportValues.get(i).getUserId()+","+
                        reportValues.get(i).getUserName() +","+reportValues.get(i).getUserLastName()+","+
                        reportValues.get(i).getGender()+","+ reportValues.get(i).getCelPhone()+","+
                        reportValues.get(i).getEmail()+","+ reportValues.get(i).getMembershipType()+","+
                        reportValues.get(i).getCountAppointments()+"\n";
                fileWriter.write(csvReport);
            }
            fileWriter.close();

        }catch (IOException e) {
            System.out.println("File IO error:");
            e.printStackTrace();
        }
        return csvFilePath;
    }
}
