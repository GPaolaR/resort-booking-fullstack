package com.oasisresort.booking.service;

import com.oasisresort.booking.entity.Administrator;

import java.util.List;

public interface IAdminService {
    Administrator getAdminById(Long AdminId);
    Administrator getAdminByLoginId(Long loginId);
    List<Administrator> getAdmins();
}
