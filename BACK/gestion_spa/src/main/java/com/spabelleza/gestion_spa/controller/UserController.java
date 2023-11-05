package com.spabelleza.gestion_spa.controller;

import com.spabelleza.gestion_spa.DTO.UserDTO;
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


    @GetMapping("/us/{LoginId}")
    public User getUserByLoginId(@PathVariable ("LoginId") Long loginId) {
        return this.iUserService.getUserByLoginId(loginId);
    }

    @GetMapping("/{idUs}")
    public User getUserById(@PathVariable ("idUs") Long userId) {
        return this.iUserService.getUserById(userId);
    }

    @GetMapping("/list")
    public ResponseEntity<List<UserDTO>> getUsers(){
        List<UserDTO> users = iUserService.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}




