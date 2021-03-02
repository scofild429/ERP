package com.mypro.business.controller;


import com.mypro.business.domain.Goods;
import com.mypro.business.service.GoodsService;
import com.mypro.business.vo.GoodsVo;
import com.mypro.system.common.Constant;
import com.mypro.system.common.DataGridView;
import com.mypro.system.common.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("goods")
@RestController
public class GoodsController {
/**
 *@ClassName GoodsController
 *@Description TODO
 *@Auther Silin
 *@Date 02.01.21 14:02
 **/

    @Autowired
    private GoodsService goodsService;


    /**
     * query
     */

    @RequestMapping("loadAllGoods")
    public Object loadAllGoods(GoodsVo goodsVo){
        return this.goodsService.queryAllGoods(goodsVo);
    }


    /**
     * add
     */
    @RequestMapping("addGoods")
    public ResultObj addGoods(Goods goods){
        try {
            goods.setAvailable(Constant.AVAILABLE_TRUE);
            this.goodsService.saveGoods(goods);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * update
     */
    @RequestMapping("updateGoods")
    public ResultObj updateGoods(Goods goods){
        try {
            this.goodsService.updateGoods(goods);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }


    /**
     * delete
     */
    @RequestMapping("deleteGoods")
    public ResultObj deleteLoginfo(Integer id){
        try {
            this.goodsService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }


    /**
     * batchdelete
     */
    @RequestMapping("batchDeleteGoods")
    public ResultObj batchDeleteLoginfo(Integer[] ids){
        try {
            if (null!=ids&&ids.length>0) {
                List<Integer> idsList = new ArrayList<>();
                for (Integer id : ids) {
                    idsList.add(id);
                }
                this.goodsService.removeByIds(idsList);
                return ResultObj.DELETE_SUCCESS;
            }else {
                return new ResultObj(-1,"the passed ID can't be empty");
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    @GetMapping("getAllAvailableGoods")
    public Object getAllAvailableGoods(){
        return this.goodsService.getAllAvailableGoods();
    }

    @GetMapping("getGoodsByProviderId")
    public Object ggetGoodsByProviderId(Integer providerid){
        return this.goodsService.getGoodsByProviderId(providerid);
    }

    /**
     * 根据商品ID查询商品信息
     */
    @GetMapping("getGoodsByGoodId")
    public Object getGoodsByGoodId(Integer goodsid){
        return new DataGridView(this.goodsService.getById(goodsid));
    }




}
