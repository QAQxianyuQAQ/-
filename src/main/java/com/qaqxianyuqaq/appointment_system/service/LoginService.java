package com.qaqxianyuqaq.appointment_system.service;

import com.qaqxianyuqaq.appointment_system.pojo.BaseResponseLoginDTO;
import com.qaqxianyuqaq.appointment_system.pojo.LoginDTO;

public interface LoginService{
    public boolean FormatCheck(LoginDTO loginDTO);
    public BaseResponseLoginDTO login(LoginDTO loginDTO);
}
