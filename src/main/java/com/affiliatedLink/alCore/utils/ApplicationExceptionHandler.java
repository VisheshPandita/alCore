package com.affiliatedLink.alCore.utils;

import com.affiliatedLink.alCore.exception.UserNotFoundException;
import com.affiliatedLink.alCore.exception.LinkNotFoundException;
import com.affiliatedLink.alCore.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handelInvalidArgument(MethodArgumentNotValidException ex){
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        return errorMap;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(UserNotFoundException.class)
    public Map<String, String> handelCreatorNotFound(UserNotFoundException ex){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("Error message", ex.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ProductNotFoundException.class)
    public Map<String, String> handelProductNotFound(ProductNotFoundException ex){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("Error message", ex.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(LinkNotFoundException.class)
    public Map<String, String> handelLinkNotFound(LinkNotFoundException ex){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("Error message", ex.getMessage());
        return errorMap;
    }
}
