package com.qaqxianyuqaq.appointment_system.controller;

import com.qaqxianyuqaq.appointment_system.pojo.user.BaseResponseLoginDTO;
import com.qaqxianyuqaq.appointment_system.pojo.user.BaseResponseUser;
import com.qaqxianyuqaq.appointment_system.pojo.user.LoginDTO;
import com.qaqxianyuqaq.appointment_system.pojo.user.RegisterDTO;
import com.qaqxianyuqaq.appointment_system.service.LoginService;
import com.qaqxianyuqaq.appointment_system.service.RegisterService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/users")
public class UserController {
    @Resource
    private LoginService loginService;
    @Resource
    private RegisterService registerService;
    @PostMapping("/login")
    public BaseResponseLoginDTO login(@RequestBody LoginDTO loginDTO) {
        return loginService.login(loginDTO);
    }

    @PostMapping("/register")
    public BaseResponseUser register(@RequestBody RegisterDTO registerDTO) {
        return registerService.register(registerDTO);
    }
}
