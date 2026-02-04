package com.qaqxianyuqaq.appointment_system.service;

import com.qaqxianyuqaq.appointment_system.dao.UserMapper;
import com.qaqxianyuqaq.appointment_system.pojo.user.BaseResponseLoginDTO;
import com.qaqxianyuqaq.appointment_system.pojo.user.LoginDTO;
import com.qaqxianyuqaq.appointment_system.pojo.user.LoginVO;
import com.qaqxianyuqaq.appointment_system.pojo.user.User;
import com.qaqxianyuqaq.appointment_system.util.JwtUtil;
import jakarta.annotation.Resource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
                    !userMapper.findByUsername
                            (loginDTO.getUsername()).isEmpty()
            ){
                for(User user : userMapper.findByUsername(loginDTO.getUsername())) {
                    if(passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
                        return new BaseResponseLoginDTO(302, new LoginVO(
                                JwtUtil.generateToken(
                                        user.getUsername(),
                                        user.getUserType()
                                )
                        ), "登录成功");
                    }
                }
                return new BaseResponseLoginDTO(400, null, "用户不存在或密码错误");
            }else{
                return new BaseResponseLoginDTO(400, null, "用户不存在或密码错误");
            }
        }else{
            return new BaseResponseLoginDTO(400, null, "用户名或密码格式错误");
        }
    }
}
