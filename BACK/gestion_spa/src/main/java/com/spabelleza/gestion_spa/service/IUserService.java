package com.spabelleza.gestion_spa.service;

import com.spabelleza.gestion_spa.entity.User;

import java.util.List;

public interface IUserService {
    User getUserByLoginId(Long loginId);
    User getUserById(Long usId);

    List<User> getUsers();
}
