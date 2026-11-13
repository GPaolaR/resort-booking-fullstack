package com.oasisresort.booking.controller;

import com.oasisresort.booking.entity.Login;
import com.oasisresort.booking.exception.BadRequestException;
import com.oasisresort.booking.DTO.LoginDTO;
import com.oasisresort.booking.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*") //pendiente
@RequestMapping("/api/login")
public class LoginController {
	
	@Autowired
    private final ILoginService iLoginService;
    
    public LoginController(ILoginService iLoginService) {
        this.iLoginService = iLoginService;
    }

    @PostMapping("/validate")
    public Login autenticate(@RequestBody LoginDTO credentials) throws BadRequestException {
        return this.iLoginService.validateLogin(credentials);

        /*if (iLoginService.validarCredenciales(username, password)) {
            return ResponseEntity.ok("Inicio de sesión exitoso");
        } else {
            return ResponseEntity.ok("Credenciales incorrectas");
            //return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        }*/
    }
    
    @GetMapping("/{idUs}")
    public Login getClientById(@PathVariable ("idUs") Long loginId) {
        return this.iLoginService.getLoginById(loginId);
    }
    
    @GetMapping("/clients")
	public ResponseEntity<List<Login>> getClients (){
		List<Login> clients = iLoginService.getLogins();
        return new ResponseEntity<>(clients, HttpStatus.OK);
	}
    
}



