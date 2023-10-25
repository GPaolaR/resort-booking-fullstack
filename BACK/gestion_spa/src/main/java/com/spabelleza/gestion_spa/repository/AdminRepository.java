package com.spabelleza.gestion_spa.repository;

import com.spabelleza.gestion_spa.entity.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Administrator, Long> {
}
