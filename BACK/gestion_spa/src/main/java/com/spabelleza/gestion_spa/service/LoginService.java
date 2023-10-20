package com.spabelleza.gestion_spa.service;

import org.springframework.stereotype.Service;
import com.spabelleza.gestion_spa.entity.Login;
import com.spabelleza.gestion_spa.repository.LoginRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;


@Service
public class LoginService implements ILoginService{

    private final LoginRepository loginRepository;
	
	public LoginService ( LoginRepository loginRepository) {
		this.loginRepository = loginRepository;
	}

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public boolean validarCredenciales(String USERNAME, String PASSWORD_ENCRYPTED) {
        String sql = "SELECT COUNT(*) FROM LOGIN WHERE USERNAME = ? AND PASSWORD_ENCRYPTED = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, USERNAME, PASSWORD_ENCRYPTED);
        return count > 0;
    }
    
    @Override
	public Login obtenerUsPorId(Long loginId) {
		Login login = this.loginRepository.findById(loginId).orElse(null);
		return login;
	}
    
    @Override
	public List<Login> obtenerClientes(){
		return this.loginRepository.findAll();
	}
}

