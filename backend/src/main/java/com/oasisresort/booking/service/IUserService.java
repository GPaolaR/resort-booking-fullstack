package com.oasisresort.booking.service;

import com.oasisresort.booking.DTO.UserDTO;
import com.oasisresort.booking.entity.User;

import java.util.List;

public interface IUserService {
    User getUserByLoginId(Long loginId);
    User getUserById(Long usId);
    List<UserDTO> getUsers();
}
