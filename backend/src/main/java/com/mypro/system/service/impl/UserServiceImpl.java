package com.mypro.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mypro.system.common.AppUtils;
import com.mypro.system.common.Constant;
import com.mypro.system.common.DataGridView;
import com.mypro.system.domain.Dept;
import com.mypro.system.mapper.RoleMapper;
import com.mypro.system.service.DeptService;
import com.mypro.system.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ReactiveAdapterRegistry;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mypro.system.mapper.UserMapper;
import com.mypro.system.domain.User;
import com.mypro.system.service.UserService;

import javax.crypto.spec.PSource;
import java.io.Serializable;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

    private Log log = LogFactory.getLog(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;


    @Override
    public User queryUserByLoginName(String loginname) {
        QueryWrapper<User> qw = new QueryWrapper<>();
        if (StringUtils.isBlank(loginname)){
            log.error("ername can't be empty");
            return  null;
        }
        qw.eq("loginname", loginname);
        User user = userMapper.selectOne(qw);
        return user;
    }

    @Override
    public DataGridView queryAllUser(UserVo userVo) {
        IPage<User> page = new Page<>(userVo.getPage(), userVo.getLimit());
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("type", Constant.USER_TYPE_NORMAL);
        qw.eq(null != userVo.getDeptid(),"deptid", userVo.getDeptid());
        qw.like(StringUtils.isNotBlank(userVo.getName()),"name",userVo.getName());
        qw.like(StringUtils.isNotBlank(userVo.getAddress()),"address", userVo.getAddress());
        qw.like(StringUtils.isNotBlank(userVo.getRemark()),"remark",userVo.getRemark());

        this.userMapper.selectPage(page, qw);
        List<User> records = page.getRecords();

        DeptService deptService = AppUtils.getContext().getBean(DeptService.class);
        for (User recode :records){
            if (null != recode.getDeptid()){
                Dept dept = deptService.getById(recode.getId());
                recode.setDeptname(dept.getTitle());
            }
        }
        return new DataGridView(page.getTotal(), records);
    }

    @Override
    public User saveUser(User user) {
        this.userMapper.insert(user);
        return user;
    }

    @Override
    public User updateUser(User user) {
        this.userMapper.updateById(user);
        return user;
    }

    @Override
    public Integer queryUserMaxOrderNum() {
        return this.userMapper.queryUserMaxOrderNum();
    }

    @Override
    public void savaUserRole(Integer uid, Integer[] rids) {
        roleMapper.deleteRoleUserByUid(uid);
        if (null != rids && rids.length > 0){
            for (Integer rid :rids){
                this.userMapper.saveUserRole(uid, rid);
            }
        }

    }


    @Override
    public boolean removeById(Serializable id) {
        roleMapper.deleteRoleUserByUid(id);
        return super.removeById(id);
    }



}
