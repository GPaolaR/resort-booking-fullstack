package com.spabelleza.gestion_spa.service;

import com.spabelleza.gestion_spa.entity.Administrator;
import com.spabelleza.gestion_spa.repository.AdminRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService implements IAdminService{
    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public Administrator obtenerAdminPorId(Long AdminId) {
        Administrator admin = this.adminRepository.findById(AdminId).orElse(null);
        return admin;
    }

    @Override
    public List<Administrator> obtenerAdmins(){
        return this.adminRepository.findAll();
    }
}
