package com.spabelleza.gestion_spa.service;

import com.spabelleza.gestion_spa.entity.Plan;
import com.spabelleza.gestion_spa.entity.Speciality;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;


@Service
public class AppointmentService implements IAppointmentService{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Date> getDatesWithoutAvailableSpecialists(Speciality speciality, Plan plan) {
        LocalDateTime currentDate = LocalDateTime.now();
        Date startDate = (Date) Date.from(currentDate.atZone(ZoneId.systemDefault()).toInstant());

        TypedQuery<Date> query = (TypedQuery<Date>) entityManager.createQuery(
                "SELECT DISTINCT a.appointmentDate FROM Appointment a " +
                        "WHERE a.specialist.speciality = :speciality " +
                        "AND a.specialist.plan = :plan " +
                        "AND a.appointmentDate >= :startDate");

        query.setParameter("speciality", speciality);
        query.setParameter("plan", plan);
        query.setParameter("startDate", startDate);

        /* // Generar una lista de fechas desde la fecha actual hasta una fecha futura (por ejemplo, 30 días después).
        List<Date> allDates = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            allDates.add(Date.from(currentDate.plusDays(i).atZone(ZoneId.systemDefault()).toInstant()));
        }

        // Obtener las fechas sin especialistas disponibles
        allDates.removeAll(busyDates);*/

        return query.getResultList();
    }
}
