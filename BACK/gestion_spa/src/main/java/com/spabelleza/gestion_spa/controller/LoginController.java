package com.spabelleza.gestion_spa.controller;

import com.spabelleza.gestion_spa.entity.Login;
import com.spabelleza.gestion_spa.exception.BadRequestException;
import com.spabelleza.gestion_spa.DTO.LoginDTO;
import com.spabelleza.gestion_spa.service.ILoginService;
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
    
    @GetMapping("/Usuarios")
	public ResponseEntity<List<Login>> getClients (){
		List<Login> clientes = iLoginService.getLogins();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
	}
    
}



