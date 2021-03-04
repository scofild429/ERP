package com.mypro.business.controller;


import com.mypro.system.common.ActiveUser;
import com.mypro.system.common.Constant;
import com.mypro.system.common.ResultObj;
import com.mypro.business.domain.Customer;
import com.mypro.business.service.CustomerService;
import com.mypro.business.vo.CustomerVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequestMapping("customer")
@RestController
public class CustomerController {
/**
 *@ClassName CustomerController
 *@Description TODO
 *@Auther Silin
 *@Date 02.01.21 14:02
 **/

    @Autowired
    private CustomerService customerService;


    /**
     * query
     */

    @RequestMapping("loadAllCustomer")
    public Object loadAllCustomer(CustomerVo customerVo){
        return this.customerService.queryAllCustomer(customerVo);
    }


    /**
     * add
     */
    @RequestMapping("addCustomer")
    public ResultObj addCustomer(Customer customer){
        try {
            customer.setAvailable(Constant.AVAILABLE_TRUE);
            this.customerService.saveCustomer(customer);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * update
     */
    @RequestMapping("updateCustomer")
    public ResultObj updateCustomer(Customer customer){
        try {
            this.customerService.updateCustomer(customer);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }


    /**
     * delete
     */
    @RequestMapping("deleteCustomer")
    public ResultObj deleteLoginfo(Integer id){
        try {
            this.customerService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }


    /**
     * batchdelete
     */
    @RequestMapping("batchDeleteCustomer")
    public ResultObj batchDeleteLoginfo(Integer[] ids){
        try {
            if (null!=ids&&ids.length>0) {
                List<Integer> idsList = new ArrayList<>();
                for (Integer id : ids) {
                    idsList.add(id);
                }
                this.customerService.removeByIds(idsList);
                return ResultObj.DELETE_SUCCESS;
            }else {
                return new ResultObj(-1,"the passed ID can't be empty");
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }



    @GetMapping("getAllAvailableCustomer")
    public Object getAllAvailableCustomer(){
        return this.customerService.getAllAvailableCustomer();
    }



}
