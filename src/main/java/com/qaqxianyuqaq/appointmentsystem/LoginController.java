package com.qaqxianyuqaq.appointmentsystem;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/users/login")
public class LoginController {
    @PostMapping
    public BaseResponseLoginDTO login(@RequestBody LoginDTO loginDTO) {
        if(
                loginDTO.getUsername() == null ||
                loginDTO.getUsername().isEmpty()) {
            return new BaseResponseLoginDTO(400, null, "用户名不能为空");
        }else if(
                loginDTO.getPassword() == null ||
                loginDTO.getPassword().isEmpty()){
            return new BaseResponseLoginDTO(400, null, "密码不能为空");
        }

        ObjectMapper objectMapper = new ObjectMapper();
        List<HashMap<String, Object>> userMapList = objectMapper.readValue(
                new File("..\\resources\\users.json"),
                new TypeReference<List<HashMap<String, Object>>>() {}
        );

        if(userMapList == null || userMapList.isEmpty()) {
            return new BaseResponseLoginDTO(400, null, "用户不存在或密码错误");
        }else{
            for(HashMap<String, Object> userMap : userMapList) {
                if(!userMap.containsValue(loginDTO.getUsername())){
                    continue;
                }else{
                    if(userMap.containsValue(loginDTO.getPassword())) {

                        return new BaseResponseLoginDTO(200, new LoginVO(
                                Base64.getEncoder().encodeToString(
                                        loginDTO.getUsername().getBytes()
                                )
                        ), "登录成功");
                    }else{
                        return new BaseResponseLoginDTO(400, null, "用户不存在或密码错误");
                    }
                }
            }
            return new BaseResponseLoginDTO(400, null, "用户不存在或密码错误");
        }
    }
}
