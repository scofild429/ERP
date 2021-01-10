package com.mypro.system.service;

import com.mypro.system.common.DataGridView;
import com.mypro.system.domain.Dept;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mypro.system.vo.DeptVo;

public interface DeptService extends IService<Dept>{


    DataGridView queryAllDept(DeptVo deptVo);

    Dept saveDept(Dept dept);

    Integer queryDeptMaxOrderNum();

    Dept updateDept(Dept dept);

    Integer queryDetpChildrenCountById(Integer id);
}
