package com.qaqxianyuqaq.appointmentsystem;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/users/login")
public class LoginController {
    @PostMapping
    public BaseResponseLoginDTO login(LoginDTO loginDTO) {


        return null;
    }
}
