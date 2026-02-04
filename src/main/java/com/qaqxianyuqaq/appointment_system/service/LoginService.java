package com.qaqxianyuqaq.appointment_system.service;

import com.qaqxianyuqaq.appointment_system.pojo.user.BaseResponseLoginDTO;
import com.qaqxianyuqaq.appointment_system.pojo.user.LoginDTO;

public interface LoginService{
    public boolean FormatCheck(LoginDTO loginDTO);
    public BaseResponseLoginDTO login(LoginDTO loginDTO);
}
