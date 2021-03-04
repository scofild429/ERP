package com.mypro.business.controller;


import com.mypro.business.domain.Sales;
import com.mypro.business.service.SalesService;
import com.mypro.business.vo.SalesVo;
import com.mypro.system.common.ActiveUser;
import com.mypro.system.common.ResultObj;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RequestMapping("sales")
@RestController
public class SalesController {
/**
 *@ClassName SalesController
 *@Description TODO
 *@Auther Silin
 *@Date 02.01.21 14:02
 **/

    @Autowired
    private SalesService salesService;


    /**
     * query
     */

    @RequestMapping("loadAllSales")
    public Object loadAllSales(SalesVo salesVo){
        return this.salesService.queryAllSales(salesVo);
    }


    /**
     * add
     */
    @RequestMapping("addSales")
    public ResultObj addSales(Sales sales){
        try {
            ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getPrincipal();
            sales.setOperateperson(activeUser.getUser().getName());
            sales.setSalestime(new Date());
            this.salesService.saveSales(sales);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * update
     */
    @RequestMapping("updateSales")
    public ResultObj updateSales(Sales sales){
        try {
            this.salesService.updateSales(sales);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }


    /**
     * delete
     */
    @RequestMapping("deleteSales")
    public ResultObj deleteLoginfo(Integer id){
        try {
            this.salesService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }






}
