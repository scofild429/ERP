package com.mypro.business.controller;


import com.mypro.business.domain.Provider;
import com.mypro.business.service.ProviderService;
import com.mypro.business.vo.ProviderVo;
import com.mypro.system.common.Constant;
import com.mypro.system.common.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("provider")
@RestController
public class ProviderController {
/**
 *@ClassName ProviderController
 *@Description TODO
 *@Auther Silin
 *@Date 02.01.21 14:02
 **/

    @Autowired
    private ProviderService providerService;


    /**
     * query
     */

    @RequestMapping("loadAllProvider")
    public Object loadAllProvider(ProviderVo providerVo){
        return this.providerService.queryAllProvider(providerVo);
    }


    /**
     * add
     */
    @RequestMapping("addProvider")
    public ResultObj addProvider(Provider provider){
        try {
            provider.setAvailable(Constant.AVAILABLE_TRUE);
            this.providerService.saveProvider(provider);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * update
     */
    @RequestMapping("updateProvider")
    public ResultObj updateProvider(Provider provider){
        try {
            this.providerService.updateProvider(provider);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }


    /**
     * delete
     */
    @RequestMapping("deleteProvider")
    public ResultObj deleteLoginfo(Integer id){
        try {
            this.providerService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }


    /**
     * batchdelete
     */
    @RequestMapping("batchDeleteProvider")
    public ResultObj batchDeleteLoginfo(Integer[] ids){
        try {
            if (null!=ids&&ids.length>0) {
                List<Integer> idsList = new ArrayList<>();
                for (Integer id : ids) {
                    idsList.add(id);
                }
                this.providerService.removeByIds(idsList);
                return ResultObj.DELETE_SUCCESS;
            }else {
                return new ResultObj(-1,"the passed ID can't be empty");
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }


    @GetMapping("getAllAvailableProvider")
    public  Object getAllAvailableProvider(){
        return this.providerService.getAllAvailableProvider();
    }





}
