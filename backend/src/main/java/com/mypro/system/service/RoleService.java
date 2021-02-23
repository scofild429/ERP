package com.mypro.system.service;

import com.mypro.system.common.DataGridView;
import com.mypro.system.domain.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mypro.system.vo.RoleVo;

import java.util.List;

public interface RoleService extends IService<Role>{


    DataGridView queryAllRole(RoleVo roleVo);

    Role saveRole(Role role);

    Role updateRole(Role role);

    List<Integer> queryMenuIdsByRid(Integer id);

    void saveRoleMenu(Integer rid, Integer[] mids);

    DataGridView queryAllAvailableRoleNoPage(RoleVo roleVo);
}
