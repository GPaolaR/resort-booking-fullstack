package com.spabelleza.gestion_spa.controller;

import com.spabelleza.gestion_spa.entity.User;
import com.spabelleza.gestion_spa.service.IUserService;
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
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private final IUserService iUserService;

    public UserController(IUserService iUserService) {
        this.iUserService = iUserService;
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




