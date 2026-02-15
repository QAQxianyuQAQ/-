package com.qaqxianyuqaq.appointment_system.exception;

import com.qaqxianyuqaq.appointment_system.pojo.exception.ServiceException;
import com.qaqxianyuqaq.appointment_system.pojo.user.BaseResponseUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public BaseResponseUser handleServiceException(ServiceException e) {
        log.error("ServiceException: {}", e.getMessage());
        return new BaseResponseUser(e.getCode(), null, e.getMessage());
    }
    @ExceptionHandler(Exception.class)
    public BaseResponseUser handleException(Exception e) {
        log.error("Exception: {}", e.getMessage());
        return new BaseResponseUser(HttpStatus.INTERNAL_SERVER_ERROR.value(), null, "服务器错误");
    }

}
