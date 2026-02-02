package com.qaqxianyuqaq.appointment_system.service;

import com.qaqxianyuqaq.appointment_system.pojo.BaseResponseUser;
import com.qaqxianyuqaq.appointment_system.pojo.RegisterDTO;

public interface RegisterService{
    boolean FormatCheck(RegisterDTO RegisterDTO);
    BaseResponseUser register(RegisterDTO registerDTO);
}
