package com.mypro.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mypro.system.domain.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends BaseMapper<User> {
    Integer queryUserMaxOrderNum();

    void saveUserRole(@Param("uid") Integer uid,@Param("rid") Integer rid);
}