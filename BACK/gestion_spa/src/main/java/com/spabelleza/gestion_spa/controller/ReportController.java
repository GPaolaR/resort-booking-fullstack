package com.spabelleza.gestion_spa.controller;

import com.spabelleza.gestion_spa.entity.Report;
import com.spabelleza.gestion_spa.exception.BadRequestException;
import com.spabelleza.gestion_spa.service.IReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/report")
public class ReportController {
    @Autowired
    private final IReportService iReportService;

    public ReportController(IReportService iReportService) {
        this.iReportService = iReportService;
    }

    @GetMapping("/get-report")
    public ResponseEntity<List<Report>> sendEmail(){
        List<Report> report = iReportService.getReport();
        return new ResponseEntity<>(report,HttpStatus.OK);
    }

    @GetMapping("/{idRe}")
    public Report getReportById(@PathVariable("idRe") Long userId) {
        return this.iReportService.getReportById(userId);
    }

    @GetMapping("/create")
    public String createCsv(){
        return "Reporte creado en "+this.iReportService.createCSV();
    }

    @GetMapping("/execute-process")
    public String executeProcess(@RequestParam("p_start_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime p_start_date,
                                 @RequestParam("p_end_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime p_end_date) throws BadRequestException {

        try{
            this.iReportService.executeProcess(Timestamp.valueOf(p_start_date),Timestamp.valueOf(p_end_date));
            return "file uploaded successfully!";
        } catch (Exception e){
            return "Failed to upload file!" + e.getMessage();
        }
    }

    @GetMapping("/descargar-csv")
    public ResponseEntity<Resource> descargarCSV(@RequestParam("p_start_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime p_start_date,
                                                 @RequestParam("p_end_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime p_end_date)
            throws BadRequestException {

        try{
            this.iReportService.executeProcess(Timestamp.valueOf(p_start_date),Timestamp.valueOf(p_end_date));
            String[] parts = this.iReportService.createCSV().split("/");
            Path path = Paths.get(this.iReportService.createCSV());
            Resource resource = new FileSystemResource(path.toFile());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("text/csv"));
            headers.setContentDispositionFormData("attachment", parts[1]);

            return new ResponseEntity<>(resource, headers, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
