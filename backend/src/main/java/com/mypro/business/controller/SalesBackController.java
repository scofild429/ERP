package com.mypro.business.controller;


import com.mypro.business.domain.Salesback;
import com.mypro.business.service.SalesbackService;
import com.mypro.business.vo.SalesbackVo;
import com.mypro.system.common.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("salesback")
@RestController
public class SalesBackController {
/**
 *@ClassName SalesbackController
 *@Description TODO
 *@Auther Silin
 *@Date 02.01.21 14:02
 **/

    @Autowired
    private SalesbackService salesbackService;


    @RequestMapping("loadAllSalesback")
    public Object loadAllSalesback(SalesbackVo salesbackVo){
        return this.salesbackService.queryAllSalesback(salesbackVo);
    }


    /**
     * add
     */
    @RequestMapping("addSalesback")
    public ResultObj addSalesback(Salesback salesback){
        try {
            this.salesbackService.saveSalesback(salesback);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
//
//    /**
//     * update
//     */
//    @RequestMapping("updateSalesback")
//    public ResultObj updateSalesback(Salesback salesback){
//        try {
//            this.salesbackService.updateSalesback(salesback);
//            return ResultObj.UPDATE_SUCCESS;
//        }catch (Exception e){
//            e.printStackTrace();
//            return ResultObj.UPDATE_ERROR;
//        }
//    }



}
