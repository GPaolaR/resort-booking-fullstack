package com.spabelleza.gestion_spa.repository;

import com.spabelleza.gestion_spa.entity.Appointment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment, Long> {

}
