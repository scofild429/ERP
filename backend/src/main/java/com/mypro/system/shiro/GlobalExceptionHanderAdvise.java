package com.mypro.system.shiro;


import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHanderAdvise {
/**
 *@ClassName GlobalExceptionHanderAdvise
 *@Description TODO
 *@Auther Silin
 *@Date 05.03.21 18:36
 **/

    @ExceptionHandler(value = {UnauthorizedException.class})
    public Object unauthorized(){
        Map<String , Object> map = new HashMap<>();
        map.put("code", -1);
        map.put("msg", "not authorized, please contact admin");
        return map;
    }

}
