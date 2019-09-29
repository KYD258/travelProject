package com.qf.config;

import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyExceptionHandle {

    @ExceptionHandler(value = UnauthenticatedException.class)
    public String ex(){
        return "unauth";

    }

    @ExceptionHandler(value = ArithmeticException.class)
    public String ar(){
        return "arith";
    }
}
