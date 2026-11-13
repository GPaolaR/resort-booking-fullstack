package com.oasisresort.booking.controller;

import com.oasisresort.booking.entity.Administrator;
import com.oasisresort.booking.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private final IAdminService iAdminService;

    public AdminController(IAdminService iAdminService) {
        this.iAdminService = iAdminService;
    }

    @GetMapping("/{idAd}")
    public Administrator getAdminById(@PathVariable("idAd") Long AdminId) {
        return this.iAdminService.getAdminById(AdminId);
    }

    @GetMapping("/us/{LoginId}")
    public Administrator getAdminByLoginId(@PathVariable ("LoginId") Long loginId) {
        return this.iAdminService.getAdminByLoginId(loginId);
    }

    @GetMapping("/admins")
    public ResponseEntity<List<Administrator>> getAdmins(){
        List<Administrator> admins = iAdminService.getAdmins();
        return new ResponseEntity<>(admins, HttpStatus.OK);
    }
}
