package com.spabelleza.gestion_spa.service;

import com.spabelleza.gestion_spa.entity.Login;
import com.spabelleza.gestion_spa.exception.BadRequestException;
import com.spabelleza.gestion_spa.DTO.LoginDTO;

import java.util.List;


public interface ILoginService {
	public Login validateLogin(LoginDTO loginDTO) throws BadRequestException;
    
	//public boolean validarCredenciales(String USERNAME, String PASSWORD_ENCRYPTED);
    
	Login getLoginById(Long loginId);
	
	List<Login> getLogins();
}
