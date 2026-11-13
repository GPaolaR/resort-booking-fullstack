package com.spabelleza.gestion_spa.repository;

import com.spabelleza.gestion_spa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByLogin_LoginId(Long loginId);

}
