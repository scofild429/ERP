package com.mypro.system.service;

import com.mypro.system.common.DataGridView;
import com.mypro.system.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mypro.system.vo.UserVo;

public interface UserService extends IService<User>{

    User queryUserByLoginName(String  loginname);


    DataGridView queryAllUser(UserVo userVo);

    User saveUser(User user);

    User updateUser(User user);

    Integer queryUserMaxOrderNum();

    void savaUserRole(Integer uid, Integer[] rids);
}
