package com.mypro.system.service;

import com.mypro.system.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

public interface UserService extends IService<User>{

    User queryUserByLoginName(String  loginname);


}
