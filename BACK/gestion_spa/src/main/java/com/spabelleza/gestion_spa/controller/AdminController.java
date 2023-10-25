package com.spabelleza.gestion_spa.controller;

import com.spabelleza.gestion_spa.entity.Administrator;
import com.spabelleza.gestion_spa.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@CrossOrigin("*") //pendiente
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private final IAdminService iAdminService;

    public AdminController(IAdminService iAdminService) {
        this.iAdminService = iAdminService;
    }

    @GetMapping("/{idAd}")
    public Administrator obtenerAdminPorId(@PathVariable("idAd") Long AdminId) {
        return this.iAdminService.obtenerAdminPorId(AdminId);
    }

    @GetMapping("/Admins")
    public ResponseEntity<List<Administrator>> obtenerAdmins(){
        List<Administrator> admins = iAdminService.obtenerAdmins();
        return new ResponseEntity<>(admins, HttpStatus.OK);
    }
}
