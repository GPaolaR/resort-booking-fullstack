package com.spabelleza.gestion_spa.controller;

import com.spabelleza.gestion_spa.entity.*;
import com.spabelleza.gestion_spa.service.*;
import com.spabelleza.gestion_spa.repository.*;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/login")
public class LoginController {
	
	@Autowired
    private final ILoginService iLoginService;
    
    public LoginController(ILoginService iLoginService) {
        this.iLoginService = iLoginService;
    }

    @PostMapping("/validate")
    public ResponseEntity<String> autenticar(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("USERNAME");
        String password = credentials.get("PASSWORD_ENCRYPTED");

        if (iLoginService.validarCredenciales(username, password)) {
            return ResponseEntity.ok("Inicio de sesión exitoso");
        } else {
            return ResponseEntity.ok("Credenciales incorrectas");
            //return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        }
    }
    
    @GetMapping("/{idUs}")
    public Login obtenerClientePorId(@PathVariable ("idUs") Long loginId) {		
        return this.iLoginService.obtenerUsPorId(loginId);
    }
    
    @GetMapping("/Usuarios")
	public ResponseEntity<List<Login>> obtenerClientes (){
		List<Login> clientes = iLoginService.obtenerClientes();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
	}
    
}



