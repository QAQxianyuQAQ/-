package com.qaqxianyuqaq.appointmentsystem;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@RestController
@RequestMapping("/users/register")
public class RegisterController {
    @PostMapping
    public BaseResponseUser register(@RequestBody RegisterDTO registerDTO) {
        if(
                registerDTO.getUsername() == null ||
                registerDTO.getUsername().isEmpty()) {
            return new BaseResponseUser(400, null, "用户名不能为空");
        }else if(
                registerDTO.getPassword() == null ||
                registerDTO.getPassword().isEmpty()){
            return new BaseResponseUser(400, null, "密码不能为空");
        }else if(
                registerDTO.getEmail() == null ||
                registerDTO.getEmail().isEmpty() ||
                !registerDTO.getEmail().matches("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$")
        ){
            return new BaseResponseUser(400, null, "邮箱格式不正确");
        }else{

            User user = new User();
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            Lock lock = new ReentrantLock();

            lock.lock();
            user.setId(WareHouse.users.size()+1);
            lock.unlock();

            user.setUsername(registerDTO.getUsername());
            user.setEmail(registerDTO.getEmail());
            user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

            user.setUserType("USER");
            user.setCreateTime(java.time.LocalDateTime.now().toString());
            user.setUpdateTime(java.time.LocalDateTime.now().toString());
            user.setIdDelete(false);

            WareHouse.users.add(user);

            JsonCreator.writeUserToJson(WareHouse.users, "..\\resources\\users.json");

            return new BaseResponseUser(200, user, "注册成功");
        }
    }

}
