package com.mypro.system.controller;


import com.mypro.system.common.Constant;
import com.mypro.system.common.DataGridView;
import com.mypro.system.common.MD5Utils;
import com.mypro.system.common.ResultObj;
import com.mypro.system.domain.User;
import com.mypro.system.service.UserService;
import com.mypro.system.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("user")
public class UserController {
/**
 *@ClassName UserController
 *@Description TODO
 *@Auther Silin
 *@Date 03.01.21 16:12
 **/

    @Autowired
    private UserService userService;

    /**
     * query all user
     * @param userVo
     * @return
     */

    @RequestMapping("loadAllUser")
    public Object loadAllUser(UserVo userVo)
    {
        return  this.userService.queryAllUser(userVo);

    }

    /**
     * add user
     */

    @PostMapping("addUser")
    public ResultObj addUser(User user){
        try {
            user.setSalt(MD5Utils.createUUID());
            user.setType(Constant.USER_TYPE_NORMAL);
            user.setPwd(MD5Utils.md5(Constant.DEFAULT_PWD, user.getSalt(), 2));
            user.setAvailable(Constant.AVAILABLE_TRUE);
            user.setImgpath(Constant.DEFAULT_TITLE_IMAGE);
            this.userService.saveUser(user);
            return  ResultObj.ADD_SUCCESS;
        }catch ( Exception e){
            e.printStackTrace();
            return  ResultObj.ADD_ERROR;
        }
    }

    /**
     * update user
     * @param user
     * @return
     */
    @PostMapping("updateUser")
    public ResultObj updateUser(User user){
        try {
            this.userService.updateUser(user);
            return  ResultObj.UPDATE_SUCCESS;
        }catch ( Exception e){
            e.printStackTrace();
            return  ResultObj.UPDATE_ERROR;
        }
    }


    /**
     * reset user password
     * @param id
     * @return
     */
    @PostMapping("resetUserPwd")
    public ResultObj resetUserPwd(Integer id){
        try {
            User user = new User();
            user.setId(id);
            user.setSalt(MD5Utils.createUUID());
            user.setPwd(MD5Utils.md5(Constant.DEFAULT_PWD, user.getSalt(), 2));
            this.userService.updateUser(user);
            this.userService.updateUser(user);
            return  ResultObj.RESET_SUCCESS;
        }catch ( Exception e){
            e.printStackTrace();
            return  ResultObj.RESET_ERROR;
        }
    }



    /**
     * delete
     * @param id
     * @return
     */
    @RequestMapping("deleteUser")
    public ResultObj deleteUser(Integer id){
        try {
            this.userService.removeById(id);
            return  ResultObj.DELETE_SUCCESS;
        }catch ( Exception e){
            e.printStackTrace();
            return  ResultObj.DELETE_ERROR;
        }
    }


    /**
     * get the max order nummer
     * @return
     */
    @GetMapping("queryUserMaxOrderNum")
    public Object queryUserMaxOrderNum(){
        Integer maxValue=this.userService.queryUserMaxOrderNum();
        return  new DataGridView(maxValue+1);

    }

    /**
     * save the collection between user and role
     */
    @RequestMapping("saveUserRole")
    public ResultObj saveUserRole(Integer uid, Integer[] rids){
        try {
            this.userService.savaUserRole(uid, rids);
            return ResultObj.DISPATCH_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DISPATCH_ERROR;
        }
    }


}
