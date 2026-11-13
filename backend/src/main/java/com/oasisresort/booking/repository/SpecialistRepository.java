package com.oasisresort.booking.repository;

import com.oasisresort.booking.entity.Specialist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecialistRepository extends JpaRepository<Specialist, Long> {

    List<Specialist> findBySpecialitySpeciality(Integer specialtyId);

}
