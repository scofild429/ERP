package com.mypro.system.controller;


import com.mypro.system.common.Constant;
import com.mypro.system.common.DataGridView;
import com.mypro.system.common.ResultObj;
import com.mypro.system.domain.Role;
import com.mypro.system.service.RoleService;
import com.mypro.system.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("role")
public class RoleController {
/**
 *@ClassName RoleController
 *@Description TODO
 *@Auther Silin
 *@Date 03.01.21 16:12
 **/

    @Autowired
    private RoleService roleService;

    /**
     * query all role
     * @param roleVo
     * @return
     */
    @RequestMapping("loadAllRole")
    public Object loadAllRole(RoleVo roleVo){
        return  this.roleService.queryAllRole(roleVo);
    }

    /**
     * query all available role without page
     * @param roleVo
     * @return
     */
    @RequestMapping("loadAllAvailableRoleNoPage")
    public Object loadAllAvailableRoleNoPage(RoleVo roleVo)
    {
        roleVo.setAvailable(Constant.AVAILABLE_TRUE);
        return  this.roleService.queryAllAvailableRoleNoPage(roleVo);

    }



    /**
     * add role
     *
     *
     */

    @PostMapping("addRole")
    public ResultObj addRole(Role role){
        try {
            role.setCreatetime(new Date());
            role.setAvailable(Constant.AVAILABLE_TRUE);
            this.roleService.saveRole(role);
            return  ResultObj.ADD_SUCCESS;
        }catch ( Exception e){
            e.printStackTrace();
            return  ResultObj.ADD_ERROR;
        }
    }


    /**
     * update role
     * @param role
     * @return
     */
    @PostMapping("updateRole")
    public ResultObj updateRole(Role role){
        try {
            this.roleService.updateRole(role);
            return  ResultObj.UPDATE_SUCCESS;
        }catch ( Exception e){
            e.printStackTrace();
            return  ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * delete
     * @param id
     * @return
     */
    @RequestMapping("deleteRole")
    public ResultObj deleteRole(Integer id){
        try {
            this.roleService.removeById(id);
            return  ResultObj.DELETE_SUCCESS;
        }catch ( Exception e){
            e.printStackTrace();
            return  ResultObj.DELETE_ERROR;
        }
    }

    /**
     * batchdelete
     */
    @RequestMapping("batchDeleteRole")
    public ResultObj batchDeleteRole(Integer[] ids){
        try {
            if (null!=ids&&ids.length>0) {
                List<Integer> idsList = new ArrayList<>();
                for (Integer id : ids) {
                    idsList.add(id);
                }
                this.roleService.removeByIds((idsList));
                //this.loginfoService.removeByIds(idsList);
                return ResultObj.DELETE_SUCCESS;
            }else {
                return new ResultObj(-1,"the passed ID can't be empty");
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     *find all menu rights accoding to Rid
     */
    @RequestMapping("queryMenuIdsByRid")
    public  Object queryMenuIdsByRid(Integer id){
        List<Integer> mids = this.roleService.queryMenuIdsByRid(id);
        return  new DataGridView(mids);
    }


    /**
     * 保存角色和菜单权限之间的关系
     */
    @RequestMapping("saveRoleMenu")
    public ResultObj saveRoleMenu(Integer rid,Integer[] mids){
        try {
            this.roleService.saveRoleMenu(rid,mids);
            return ResultObj.DISPATCH_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DISPATCH_ERROR;
        }
    }


}
