package com.mypro.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mypro.system.domain.Role;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {
    void deleteRoleMenuByRid(Serializable id);

    void deleteRoleMenuByMid(Serializable id);

    void deleteRoleUserByRid(Serializable id);

    void deleteRoleUserByUid(Serializable id);

    List<Integer> queryMenuIdsByRid(Integer id);

    void insertRoleMenu(@Param("rid") Integer rid, @Param("mid") Integer mid);

    List<Integer> queryRoleIdsByUserIds(Integer userId);

    List<Integer> queryMenuIdsByRids(@Param("roleIds") List<Integer> roleIds);
};