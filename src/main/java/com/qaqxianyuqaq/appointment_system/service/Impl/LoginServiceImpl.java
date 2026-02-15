package com.qaqxianyuqaq.appointment_system.service.Impl;

import com.qaqxianyuqaq.appointment_system.dao.UserMapper;
import com.qaqxianyuqaq.appointment_system.limiter.LoginLimiter;
import com.qaqxianyuqaq.appointment_system.pojo.exception.ServiceException;
import com.qaqxianyuqaq.appointment_system.pojo.user.BaseResponseLoginDTO;
import com.qaqxianyuqaq.appointment_system.pojo.user.LoginDTO;
import com.qaqxianyuqaq.appointment_system.pojo.user.LoginVO;
import com.qaqxianyuqaq.appointment_system.pojo.user.User;
import com.qaqxianyuqaq.appointment_system.service.LoginService;
import com.qaqxianyuqaq.appointment_system.util.JwtUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private JwtUtil jwtUtil;
    @Resource
    private LoginLimiter loginLimiter;
    @Resource
    private HttpServletRequest request;
    //校验输入格式
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
    //登录
    @Override
    public BaseResponseLoginDTO login(LoginDTO loginDTO) {
        //防恶意重复尝试登录
        if (!loginLimiter.tryAcquire(loginDTO.getUsername(), request)) {
            throw new ServiceException("登录尝试次数过多，请10分钟后再试", HttpStatus.TOO_MANY_REQUESTS.value());
        }

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if(this.FormatCheck(loginDTO)){
            ArrayList<User> users = userMapper.findByUsername(loginDTO.getUsername());
            if(
                    !userMapper.findByUsername
                            (loginDTO.getUsername()).isEmpty()
            ){
                for(User user : users) {
                    if(passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
                        return new BaseResponseLoginDTO(302, new LoginVO(
                                jwtUtil.generateToken(
                                        user.getUsername(),
                                        user.getUserType()
                                )
                        ), "登录成功");
                    }
                }
                User user = userMapper.findByEmail(loginDTO.getUsername());
                if(passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
                    return new BaseResponseLoginDTO(302, new LoginVO(
                            jwtUtil.generateToken(
                                    user.getUsername(),
                                    user.getUserType()
                            )
                    ), "登录成功");
                }
                throw new ServiceException("用户不存在或密码错误", HttpStatus.UNAUTHORIZED.value());
            }else{
                throw new ServiceException("用户不存在或密码错误", HttpStatus.UNAUTHORIZED.value());
            }
        }else{
            throw new ServiceException("用户名或密码格式错误", HttpStatus.BAD_REQUEST.value());
        }
    }
}
