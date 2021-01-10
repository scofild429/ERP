package com.mypro.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mypro.system.common.DataGridView;
import com.mypro.system.vo.DeptVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mypro.system.domain.Dept;
import com.mypro.system.mapper.DeptMapper;
import com.mypro.system.service.DeptService;
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService{

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public DataGridView queryAllDept(DeptVo deptVo) {
        QueryWrapper<Dept> qw = new QueryWrapper<>();
        qw.like(StringUtils.isNotBlank(deptVo.getTitle()), "title", deptVo.getTitle());
        qw.orderByAsc("ordernum");
        List<Dept> depts = this.deptMapper.selectList(qw);
        return  new DataGridView(Long.valueOf(depts.size()), depts);
    }

    @Override
    public Dept saveDept(Dept dept) {
        this.deptMapper.insert(dept);
        return dept;
    }

    @Override
    public Integer queryDeptMaxOrderNum() {
        return this.deptMapper.queryDeptMaxOrderNum();
    }

    @Override
    public Dept updateDept(Dept dept) {
        this.deptMapper.updateById(dept);
        return dept;
    }

    @Override
    public Integer queryDetpChildrenCountById(Integer id) {
        return  this.deptMapper.queryDeptChildrenCountById(id);
    }

    @Override
    public Dept getById(Serializable id){
        return super.getById(id);
    }


    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }
}
