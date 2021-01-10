package com.mypro.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mypro.system.domain.Dept;

public interface DeptMapper extends BaseMapper<Dept> {
    Integer queryDeptMaxOrderNum();

    Integer queryDeptChildrenCountById(Integer id);

    }