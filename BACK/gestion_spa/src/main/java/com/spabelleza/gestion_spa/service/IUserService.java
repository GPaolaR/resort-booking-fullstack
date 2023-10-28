package com.spabelleza.gestion_spa.service;

import com.spabelleza.gestion_spa.entity.User;

import java.util.List;

public interface IUserService {
    User getUserByLoginId(Long loginId);
    User obtenerUsPorId(Long usId);

    List<User> obtenerUsers();
}
