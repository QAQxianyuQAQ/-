package com.qaqxianyuqaq.appointment_system.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO implements UserDTO{
    private String username;
    private String password;
}
