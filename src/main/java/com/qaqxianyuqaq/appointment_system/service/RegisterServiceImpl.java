package com.qaqxianyuqaq.appointment_system.service;

import com.qaqxianyuqaq.appointment_system.dao.UserMapper;
import com.qaqxianyuqaq.appointment_system.pojo.BaseResponseUser;
import com.qaqxianyuqaq.appointment_system.pojo.RegisterDTO;
import com.qaqxianyuqaq.appointment_system.pojo.User;
import jakarta.annotation.Resource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService{
    @Resource
    private UserMapper userMapper;
    @Override
    public boolean FormatCheck(RegisterDTO registerDTO) {
        if(
                registerDTO.getUsername() == null ||
                        registerDTO.getUsername().isEmpty()) {
            return false;
        }else if(
                registerDTO.getPassword() == null ||
                        registerDTO.getPassword().isEmpty()){
            return false;
        }else if(
                registerDTO.getEmail() == null ||
                        registerDTO.getEmail().isEmpty() ||
                        !registerDTO.getEmail().matches("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$")
        ){
            return false;
        }
        return true;
    }

    public BaseResponseUser register(RegisterDTO registerDTO) {
        if(this.FormatCheck(registerDTO)){
            User user = new User();
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            user.setId(userMapper.count()+1);
            user.setUsername(registerDTO.getUsername());
            user.setEmail(registerDTO.getEmail());
            user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
            user.setUserType("users");
            user.setCreateTime(java.time.LocalDateTime.now().toString());
            user.setUpdateTime(java.time.LocalDateTime.now().toString());
            user.setIdDelete(false);

            userMapper.insert(user);
            return new BaseResponseUser(200, user, "注册成功");
        }else{
            return new BaseResponseUser(400, null, "用户名或密码或邮箱格式错误");
        }
    }
}
