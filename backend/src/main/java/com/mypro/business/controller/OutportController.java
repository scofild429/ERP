package com.mypro.business.controller;


import com.mypro.business.domain.Outport;
import com.mypro.business.service.OutportService;
import com.mypro.business.vo.OutportVo;
import com.mypro.system.common.ActiveUser;
import com.mypro.system.common.ResultObj;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RequestMapping("outport")
@RestController
public class OutportController {
/**
 *@ClassName OutportController
 *@Description TODO
 *@Auther Silin
 *@Date 02.01.21 14:02
 **/

    @Autowired
    private OutportService outportService;


    @RequestMapping("loadAllOutport")
    public Object loadAllOutport(OutportVo outportVo){
        return this.outportService.queryAllOutport(outportVo);
    }


    /**
     * add
     */
    @RequestMapping("addOutport")
    public ResultObj addOutport(Outport outport){
        try {
            this.outportService.saveOutport(outport);
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
//    @RequestMapping("updateOutport")
//    public ResultObj updateOutport(Outport outport){
//        try {
//            this.outportService.updateOutport(outport);
//            return ResultObj.UPDATE_SUCCESS;
//        }catch (Exception e){
//            e.printStackTrace();
//            return ResultObj.UPDATE_ERROR;
//        }
//    }



}
