package com.mypro.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mypro.system.mapper.UserMapper;
import com.mypro.system.domain.User;
import com.mypro.system.service.UserService;
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

    private Log log = LogFactory.getLog(UserServiceImpl.class);

    @Override
    public User queryUserByLoginName(String loginname) {
        UserMapper userMapper = this.getBaseMapper();
        QueryWrapper<User> qw = new QueryWrapper<>();
        if (StringUtils.isBlank(loginname)){
            log.error("ername can't be empty");
            return  null;
        }
        qw.eq("loginname",loginname);
        User user = userMapper.selectOne(qw);
        return user;
    }
}
