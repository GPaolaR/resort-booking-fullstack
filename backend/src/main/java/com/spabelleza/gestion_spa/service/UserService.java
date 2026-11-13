package com.spabelleza.gestion_spa.service;

import com.spabelleza.gestion_spa.DTO.UserDTO;
import com.spabelleza.gestion_spa.entity.User;
import com.spabelleza.gestion_spa.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService{

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public User getUserByLoginId(Long loginId) {
        User user = this.userRepository.findUserByLogin_LoginId(loginId).orElse(null);
        return user;
    }

    @Override
    public User getUserById(Long userId) {
        User user = this.userRepository.findById(userId).orElse(null);
        return user;
    }

    @Override
    public List<UserDTO> getUsers(){
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> {
                    String fullName = String.join(" ", user.getUserName(), user.getUserLastName())
                            .replaceAll("\\s+", " ").trim();
                    UserDTO UserDTO = modelMapper.map(user, UserDTO.class);
                    UserDTO.setUserFullName(fullName);
                    return UserDTO;
                }).collect(Collectors.toList());
    }
}
