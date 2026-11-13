package com.oasisresort.booking.service;

import com.oasisresort.booking.entity.Login;
import com.oasisresort.booking.exception.BadRequestException;
import com.oasisresort.booking.DTO.LoginDTO;

import java.util.List;


public interface ILoginService {
	public Login validateLogin(LoginDTO loginDTO) throws BadRequestException;
    
	//public boolean validarCredenciales(String USERNAME, String PASSWORD_ENCRYPTED);
    
	Login getLoginById(Long loginId);
	
	List<Login> getLogins();
}
