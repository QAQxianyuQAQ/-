package com.qaqxianyuqaq.appointment_system.service.Impl;

import com.qaqxianyuqaq.appointment_system.dao.UserMapper;
import com.qaqxianyuqaq.appointment_system.pojo.exception.ServiceException;
import com.qaqxianyuqaq.appointment_system.pojo.user.BaseResponseUser;
import com.qaqxianyuqaq.appointment_system.pojo.user.RegisterDTO;
import com.qaqxianyuqaq.appointment_system.pojo.user.User;
import com.qaqxianyuqaq.appointment_system.service.RegisterService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {
    @Resource
    private UserMapper userMapper;
    // 格式检查
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
    // 注册
    public BaseResponseUser register(RegisterDTO registerDTO) {
        if(this.FormatCheck(registerDTO)){
            User user = new User();
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            user.setId(userMapper.count()+1);
            user.setUsername(registerDTO.getUsername());
            user.setEmail(registerDTO.getEmail());
            user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
            user.setUserType("users");
            user.setCreateTime(java.time.LocalDateTime.now());
            user.setUpdateTime(java.time.LocalDateTime.now());
            user.setIdDelete(false);

            userMapper.insert(user);
            return new BaseResponseUser(200, user, "注册成功");
        }else{
            throw new ServiceException("用户名（邮箱）或密码格式错误", HttpStatus.BAD_REQUEST.value());
        }
    }
}
