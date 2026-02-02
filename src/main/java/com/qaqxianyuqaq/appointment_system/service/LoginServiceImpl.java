package com.qaqxianyuqaq.appointment_system.service;

import com.qaqxianyuqaq.appointment_system.dao.UserMapper;
import com.qaqxianyuqaq.appointment_system.pojo.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginServiceImpl implements LoginService{
    @Autowired
    private UserMapper userMapper;
    @Override
    public boolean FormatCheck(UserDTO userDTO) {
        return false;
    }
}
