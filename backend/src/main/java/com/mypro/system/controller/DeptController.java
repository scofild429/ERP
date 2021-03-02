package com.mypro.system.controller;


import com.mypro.system.common.Constant;
import com.mypro.system.common.DataGridView;
import com.mypro.system.common.ResultObj;
import com.mypro.system.domain.Dept;
import com.mypro.system.service.DeptService;
import com.mypro.system.vo.DeptVo;
import lombok.var;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("dept")
public class DeptController {
/**
 *@ClassName DeptController
 *@Description TODO
 *@Auther Silin
 *@Date 03.01.21 16:12
 **/

    @Autowired
    private DeptService deptService;

    /**
     * query all depatment
     * @param deptVo
     * @return
     */

    @RequestMapping("loadAllDept")
    public Object loadAllDept(DeptVo deptVo){
        return  this.deptService.queryAllDept(deptVo);
    }


    /**
     * add department
     *
     *
     */

    @PostMapping("addDept")
    @RequiresPermissions("dept:add")
    public ResultObj addDept(Dept dept){
        try {
            dept.setSpread(Constant.SPREAD_FALSE);
            dept.setAvailable(Constant.AVAILABLE_TRUE);
            this.deptService.saveDept(dept);
            return  ResultObj.ADD_SUCCESS;
        }catch ( Exception e){
            e.printStackTrace();
            return  ResultObj.ADD_ERROR;
        }
    }


    /**
     * get the max order nummer
     * @return
     */
    @GetMapping("queryDeptMaxOrderNum")
    public Object queryDeptMaxOrderNum(){
        Integer maxValue=this.deptService.queryDeptMaxOrderNum();
        return  new DataGridView(maxValue+1);

    }


    /**
     * update department
     * @param dept
     * @return
     */
    @PostMapping("updateDept")
    @RequiresPermissions("dept:update")
    public ResultObj updateDept(Dept dept){
        try {
            this.deptService.updateDept(dept);
            return  ResultObj.UPDATE_SUCCESS;
        }catch ( Exception e){
            e.printStackTrace();
            return  ResultObj.UPDATE_ERROR;
        }
    }


    @GetMapping("getDeptById")
    public Object getDeptById(Integer id){
        return new DataGridView(this.deptService.getById(id));
    }

    /**
     * get the number of child department
     * @param id
     * @return
     */
    @GetMapping("getDeptChildrenCountById")
    public Object getDeptChildrenCountById (Integer id){
        Integer count  = this.deptService.queryDetpChildrenCountById(id);
        return new DataGridView(count);
    }

    /**
     * delete
     * @param id
     * @return
     */
    @RequestMapping("deleteDept")
    @RequiresPermissions("dept:delete")
    public ResultObj deleteDept(Integer id){
        try {
            this.deptService.removeById(id);
            return  ResultObj.DELETE_SUCCESS;
        }catch ( Exception e){
            e.printStackTrace();
            return  ResultObj.DELETE_ERROR;
        }
    }



}
