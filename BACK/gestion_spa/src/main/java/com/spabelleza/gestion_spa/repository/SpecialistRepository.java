package com.spabelleza.gestion_spa.repository;

import com.spabelleza.gestion_spa.entity.Specialist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialistRepository extends JpaRepository<Specialist, Long> {

}
