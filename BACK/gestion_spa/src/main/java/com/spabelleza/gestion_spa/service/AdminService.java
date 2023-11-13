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
    public Administrator getAdminById(Long AdminId) {
        Administrator admin = this.adminRepository.findById(AdminId).orElse(null);
        return admin;
    }
    @Override
    public Administrator getAdminByLoginId(Long loginId) {
        Administrator admin = this.adminRepository.findAdminByLogin_LoginId(loginId).orElse(null);
        return admin;
    }

    @Override
    public List<Administrator> getAdmins(){
        return this.adminRepository.findAll();
    }
}
