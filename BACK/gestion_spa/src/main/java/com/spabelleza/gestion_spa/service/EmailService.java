package com.spabelleza.gestion_spa.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;
    private final IReportService iReportService;

    public EmailService(JavaMailSender mailSender, IReportService iReportService) {
        this.mailSender = mailSender;
        this.iReportService = iReportService;
    }

    @Scheduled(cron = "0 0 8 1 * *") //Ejecución cada día 1 del mes a las 08:00 a.m.
    //@Scheduled(fixedDelay = 60000)
    public void sendEmail(){

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo("usuario.demo@example.com");
            helper.setSubject("Informe Mensual "+ iReportService.getCurrentDate());
            helper.setText("Buenas noches\n\nSe hace envio del reporte de especialistas mensual.");

            FileSystemResource file = new FileSystemResource(new File(iReportService.createCSV()));
            helper.addAttachment(file.getFilename(), file);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
