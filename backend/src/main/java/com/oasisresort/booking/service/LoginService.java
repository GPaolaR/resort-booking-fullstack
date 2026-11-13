package com.oasisresort.booking.service;

import com.oasisresort.booking.entity.Login;
import com.oasisresort.booking.exception.BadRequestException;
import com.oasisresort.booking.exception.NotFoundException;
import com.oasisresort.booking.repository.LoginRepository;
import com.oasisresort.booking.DTO.LoginDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class LoginService implements ILoginService{

    private final LoginRepository loginRepository;
	
	public LoginService ( LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
	}

    /*@Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public boolean validarCredenciales(String USERNAME, String PASSWORD_ENCRYPTED) {
        String sql = "SELECT COUNT(*) FROM LOGIN WHERE USERNAME = ? AND PASSWORD_ENCRYPTED = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, USERNAME, PASSWORD_ENCRYPTED);
        return count > 0;
    }*/

    @Override
    public Login validateLogin(LoginDTO loginDTO) throws BadRequestException {
        Login clientLogin = (Login) loginRepository.findByUsername(loginDTO.getUsername()).orElseThrow(
                () -> new NotFoundException("El nombre de usuario '" + loginDTO.getUsername() + "' no existe"));

        if (!Objects.equals(clientLogin.getPasswordEncrypted(), loginDTO.getPassword())) {
            throw new BadRequestException("Contraseña incorrecta. Por favor, intente de nuevo");
        }

        return clientLogin;
    }

    @Override
	public Login getLoginById(Long loginId) {
		Login login = this.loginRepository.findById(loginId).orElse(null);
		return login;
	}
    
    @Override
	public List<Login> getLogins(){
		return this.loginRepository.findAll();
	}
}

