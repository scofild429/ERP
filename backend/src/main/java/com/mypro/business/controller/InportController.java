package com.mypro.business.controller;


import com.mypro.business.domain.Inport;
import com.mypro.business.service.InportService;
import com.mypro.business.vo.InportVo;
import com.mypro.system.common.ActiveUser;
import com.mypro.system.common.Constant;
import com.mypro.system.common.ResultObj;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequestMapping("inport")
@RestController
public class InportController {
/**
 *@ClassName InportController
 *@Description TODO
 *@Auther Silin
 *@Date 02.01.21 14:02
 **/

    @Autowired
    private InportService inportService;


    /**
     * query
     */

    @RequestMapping("loadAllInport")
    public Object loadAllInport(InportVo inportVo){
        return this.inportService.queryAllInport(inportVo);
    }


    /**
     * add
     */
    @RequestMapping("addInport")
    public ResultObj addInport(Inport inport){
        try {
            ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getPrincipal();
            inport.setOperateperson(activeUser.getUser().getName());
            inport.setInporttime(new Date());
            this.inportService.saveInport(inport);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * update
     */
    @RequestMapping("updateInport")
    public ResultObj updateInport(Inport inport){
        try {
            this.inportService.updateInport(inport);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }


    /**
     * delete
     */
    @RequestMapping("deleteInport")
    public ResultObj deleteLoginfo(Integer id){
        try {
            this.inportService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }






}
