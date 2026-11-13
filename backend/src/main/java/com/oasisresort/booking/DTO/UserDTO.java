package com.oasisresort.booking.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long userId;
    private String userFullName;
    // Constructor para crear el DTO con ID y nombre completo
    public UserDTO(Long userId, String userName, String userLastName) {
        this.userId = userId;
        this.userFullName = userName + " " + userLastName;
    }
}
