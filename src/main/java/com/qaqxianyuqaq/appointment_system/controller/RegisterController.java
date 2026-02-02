package com.qaqxianyuqaq.appointment_system.controller;

import com.qaqxianyuqaq.appointment_system.pojo.BaseResponseUser;
import com.qaqxianyuqaq.appointment_system.pojo.RegisterDTO;
import com.qaqxianyuqaq.appointment_system.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/register")
public class RegisterController {
    @Autowired
    private RegisterService registerService;
    @PostMapping
    public BaseResponseUser register(@RequestBody RegisterDTO registerDTO) {
            return registerService.register(registerDTO);
    }

}
