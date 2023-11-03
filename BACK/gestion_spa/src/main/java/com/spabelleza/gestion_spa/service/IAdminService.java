package com.spabelleza.gestion_spa.service;

import com.spabelleza.gestion_spa.entity.Administrator;

import java.util.List;

public interface IAdminService {
    Administrator getAdminById(Long AdminId);

    List<Administrator> getAdmins();
}
