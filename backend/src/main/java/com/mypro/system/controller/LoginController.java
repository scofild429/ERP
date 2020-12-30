package com.mypro.system.controller;

import com.mypro.system.common.ResultObj;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin
@RequestMapping("login")
public class LoginController {
//    user login
    @RequestMapping("doLogin")
    @ResponseBody
    public ResultObj doLogin(String loginname, String  password){
        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken loginToken = new UsernamePasswordToken(loginname, password);
            subject.login(loginToken);
            // ActiveUser activeUser = (ActiveUser) subject.getPrincipal();
            // get the sessionid of shiro, naming token
            String token = subject.getSession().getId().toString();
            //login daily
            return new ResultObj(200, "success login", token);
        }catch (AuthenticationException e){
            e.printStackTrace();
            return  new ResultObj(-1,"username or password is wrong");
        }
    }


}
