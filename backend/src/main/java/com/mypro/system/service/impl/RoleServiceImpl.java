package com.mypro.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mypro.system.common.DataGridView;
import com.mypro.system.vo.RoleVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mypro.system.mapper.RoleMapper;
import com.mypro.system.domain.Role;
import com.mypro.system.service.RoleService;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService{

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public DataGridView queryAllRole(RoleVo roleVo) {
        IPage<Role> page = new Page<>(roleVo.getPage(), roleVo.getLimit());
        QueryWrapper<Role> qw = new QueryWrapper<>();
        qw.eq(roleVo.getAvailable() != null, "available",roleVo.getAvailable());
        qw.like(StringUtils.isNotBlank(roleVo.getName()),"name", roleVo.getName());
        qw.like(StringUtils.isNotBlank(roleVo.getRemark()),"remark", roleVo.getRemark());
        this.roleMapper.selectPage(page, qw);
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    @Override
    public Role saveRole(Role role) {
        this.roleMapper.insert(role);
        return role;
    }

    @Override
    public Role updateRole(Role role) {
        this.roleMapper.updateById(role);
        return role;
    }

    @Override
    public List<Integer> queryMenuIdsByRid(Integer id) {
        return  this.roleMapper.queryMenuIdsByRid(id);
    }

    @Override
    public void saveRoleMenu(Integer rid, Integer[] mids) {
        this.roleMapper.deleteRoleMenuByRid(rid);
        if (null != mids && mids.length>0){
            for (Integer mid:mids){
                this.roleMapper.insertRoleMenu(rid, mid);
            }
        }
    }

    @Override
    public DataGridView queryAllAvailableRoleNoPage(RoleVo roleVo) {
        QueryWrapper<Role> qw = new QueryWrapper<>();
        qw.eq(roleVo.getAvailable() != null, "available",roleVo.getAvailable());
        List<Role> roles = this.roleMapper.selectList(qw);

        List<Integer> roleIds = this.roleMapper.queryRoleIdsByUserIds(roleVo.getUserId());
        List<Map<String, Object>> lists = new ArrayList<>();

        for (Role role: roles){
            Boolean LAY_CHECKED = false;
            for (Integer roleId: roleIds){
                if (role.getId().equals(roleId)){
                    LAY_CHECKED = true;
                    break;
                }
            }
            Map<String, Object> map = new HashMap<>();
            map.put("id", role.getId());
            map.put("name", role.getName());
            map.put("remark", role.getRemark());
            map.put("LAY_CHECKED",LAY_CHECKED);
            lists.add(map);
        }
        return new DataGridView(Long.valueOf(lists.size()), lists);
    }

    @Override
    public boolean removeById(Serializable id) {
        // delete the connection between role and menu ie. role and user
        roleMapper.deleteRoleMenuByRid(id);
        roleMapper.deleteRoleUserByRid(id);
        return super.removeById(id);
    }
}

