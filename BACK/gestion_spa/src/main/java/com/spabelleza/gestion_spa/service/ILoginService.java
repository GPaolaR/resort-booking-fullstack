package com.spabelleza.gestion_spa.service;

import java.util.List;

import com.spabelleza.gestion_spa.entity.Login;


public interface ILoginService {
    
	public boolean validarCredenciales(String USERNAME, String PASSWORD_ENCRYPTED);
    
	Login obtenerUsPorId(Long loginId);
	
	List<Login> obtenerClientes();
}
