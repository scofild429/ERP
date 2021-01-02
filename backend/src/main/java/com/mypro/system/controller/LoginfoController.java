package com.mypro.system.controller;

import com.mypro.system.domain.Loginfo;
import com.mypro.system.service.LoginfoService;
import com.mypro.system.vo.LoginfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("loginfo")
@CrossOrigin
public class LoginfoController {

/**
 * @ClassName LoginfoController
 * @Description TODO
 * @Auther Silin
 * @Date 02.01.21 08:59
 **/

    @Autowired
    private LoginfoService loginfoService;

    @RequestMapping("loadAllLoginfo")
    public Object loadAllLoginfo(LoginfoVo loginfoVo){
        return this.loginfoService.queryAllLoginfo(loginfoVo);

    }



}
