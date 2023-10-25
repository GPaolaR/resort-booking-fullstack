package com.spabelleza.gestion_spa.repository;

import com.spabelleza.gestion_spa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
