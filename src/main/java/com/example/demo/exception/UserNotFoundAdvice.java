package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class UserNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    Map<String,String> getException(UserNotFoundException userNotFoundException){
        Map<String,String> exceptions=new HashMap<>();
        exceptions.put("errorMassage",userNotFoundException.getMessage());
        return exceptions;
    }
}
