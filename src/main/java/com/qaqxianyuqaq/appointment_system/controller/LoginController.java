package com.qaqxianyuqaq.appointment_system.controller;

import com.qaqxianyuqaq.appointment_system.pojo.user.BaseResponseLoginDTO;
import com.qaqxianyuqaq.appointment_system.pojo.user.LoginDTO;
import com.qaqxianyuqaq.appointment_system.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/users/login")
public class LoginController {
    @Autowired
    private LoginService loginService;
    @PostMapping
    public BaseResponseLoginDTO login(@RequestBody LoginDTO loginDTO) {
        return loginService.login(loginDTO);
    }
}
