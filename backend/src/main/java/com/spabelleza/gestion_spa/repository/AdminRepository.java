package com.spabelleza.gestion_spa.repository;

import com.spabelleza.gestion_spa.entity.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Administrator, Long> {
    Optional<Administrator> findAdminByLogin_LoginId(Long loginId);
}
