package com.qaqxianyuqaq.appointment_system.service;

import com.qaqxianyuqaq.appointment_system.dao.UserMapper;
import com.qaqxianyuqaq.appointment_system.pojo.BaseResponseLoginDTO;
import com.qaqxianyuqaq.appointment_system.pojo.LoginDTO;
import com.qaqxianyuqaq.appointment_system.pojo.LoginVO;
import jakarta.annotation.Resource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class LoginServiceImpl implements LoginService{
    @Resource
    private UserMapper userMapper;
    @Override
    public boolean FormatCheck(LoginDTO loginDTO) {
        if(
                loginDTO.getUsername() == null ||
                        loginDTO.getUsername().isEmpty()) {
            return false;
        }else if(
                loginDTO.getPassword() == null ||
                        loginDTO.getPassword().isEmpty()){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public BaseResponseLoginDTO login(LoginDTO loginDTO) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if(this.FormatCheck(loginDTO)){
            if(
                    userMapper.findByUsernameAndPassword
                            (loginDTO.getUsername(), passwordEncoder.encode(loginDTO.getPassword())) != null
            ){
                return new BaseResponseLoginDTO(200, new LoginVO(
                        Base64.getEncoder().encodeToString(
                                loginDTO.getUsername().getBytes()
                        )
                ), "登录成功");
            }else{
                return new BaseResponseLoginDTO(400, null, "用户不存在或密码错误");
            }
        }else{
            return new BaseResponseLoginDTO(400, null, "用户名或密码格式错误");
        }
    }
}
