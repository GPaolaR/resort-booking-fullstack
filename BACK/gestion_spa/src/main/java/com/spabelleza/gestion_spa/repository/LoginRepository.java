package com.spabelleza.gestion_spa.repository;

import com.spabelleza.gestion_spa.entity.Login;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {
    
}
