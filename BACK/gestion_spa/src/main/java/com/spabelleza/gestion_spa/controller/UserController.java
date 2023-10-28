package com.spabelleza.gestion_spa.controller;

import com.spabelleza.gestion_spa.entity.User;
import com.spabelleza.gestion_spa.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private final IUserService iUserService;

    public UserController(IUserService iUserService) {
        this.iUserService = iUserService;
    }


    @GetMapping("/Us/{LoginId}")
    public User getUserByLoginId(@PathVariable ("LoginId") Long loginId) {
        return this.iUserService.getUserByLoginId(loginId);
    }

    @GetMapping("/{idUs}")
    public User obtenerUsPorId(@PathVariable ("idUs") Long userId) {
        return this.iUserService.obtenerUsPorId(userId);
    }

    @GetMapping("/Usuarios")
    public ResponseEntity<List<User>> obtenerUsers(){
        List<User> usuarios = iUserService.obtenerUsers();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

}




