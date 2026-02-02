package com.qaqxianyuqaq.appointment_system.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO implements UserDTO{
    private String username;
    private String password;
    private String email;
}
