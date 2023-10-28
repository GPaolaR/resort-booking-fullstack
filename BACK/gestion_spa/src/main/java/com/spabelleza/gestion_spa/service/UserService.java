package com.spabelleza.gestion_spa.service;

import com.spabelleza.gestion_spa.entity.User;
import com.spabelleza.gestion_spa.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService{

    private final UserRepository userRepository;

    public UserService ( UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserByLoginId(Long loginId) {
        User user = this.userRepository.findUserByLogin_LoginId(loginId).orElse(null);
        return user;
    }

    @Override
    public User obtenerUsPorId(Long userId) {
        User user = this.userRepository.findById(userId).orElse(null);
        return user;
    }

    @Override
    public List<User> obtenerUsers(){
        return this.userRepository.findAll();
    }
}
