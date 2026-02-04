package com.qaqxianyuqaq.appointment_system.service;

import com.qaqxianyuqaq.appointment_system.pojo.user.BaseResponseUser;
import com.qaqxianyuqaq.appointment_system.pojo.user.RegisterDTO;

public interface RegisterService{
    boolean FormatCheck(RegisterDTO RegisterDTO);
    BaseResponseUser register(RegisterDTO registerDTO);
}
