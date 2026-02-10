package com.qaqxianyuqaq.appointment_system.exception;

import com.qaqxianyuqaq.appointment_system.pojo.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ServiceException handleException(Exception e) {
        log.error("Exception: {}", e.getMessage());
        return new ServiceException("程序出错",500,null);
    }

}
