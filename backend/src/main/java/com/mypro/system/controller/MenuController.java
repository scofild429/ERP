package com.mypro.system.controller;


import com.mypro.system.common.Constant;
import com.mypro.system.common.DataGridView;
import com.mypro.system.common.ResultObj;
import com.mypro.system.domain.Menu;
import com.mypro.system.service.MenuService;
import com.mypro.system.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("menu")
public class MenuController {
/**
 *@ClassName MenuController
 *@Description TODO
 *@Auther Silin
 *@Date 03.01.21 16:12
 **/

    @Autowired
    private MenuService menuService;

    /**
     * query all depatment
     * @param menuVo
     * @return
     */

    @RequestMapping("loadAllMenu")
    public Object loadAllMenu(MenuVo menuVo){
        return  this.menuService.queryAllMenu(menuVo);
    }


    @RequestMapping("loadMenu")
    public Object loadMenu(MenuVo menuVo){
        List<Menu> menus = this.menuService.queryAllMenuForList();
        return  new DataGridView(Long.valueOf(menus.size()),menus);
    }


    /**
     * add  menu and right 
     *
     *
     */

    @PostMapping("addMenu")
    public ResultObj addMenu(Menu menu){
        try {
            if (menu.getType().equals("topmenu")){
                menu.setPid(0);
            }
            menu.setSpread(Constant.SPREAD_FALSE);
            menu.setAvailable(Constant.AVAILABLE_TRUE);
            this.menuService.saveMenu(menu);
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
    @GetMapping("queryMenuMaxOrderNum")
    public Object queryMenuMaxOrderNum(){
        Integer maxValue=this.menuService.queryMenuMaxOrderNum();
        return  new DataGridView(maxValue+1);

    }


    /**
     * update  menu and right 
     * @param menu
     * @return
     */
    @PostMapping("updateMenu")
    public ResultObj updateMenu(Menu menu){
        try {
            this.menuService.updateMenu(menu);
            return  ResultObj.UPDATE_SUCCESS;
        }catch ( Exception e){
            e.printStackTrace();
            return  ResultObj.UPDATE_ERROR;
        }
    }


    @GetMapping("getMenuById")
    public Object getMenuById(Integer id){
        return new DataGridView(this.menuService.getById(id));
    }

    /**
     * get the number of child  menu and right 
     * @param id
     * @return
     */
    @GetMapping("getMenuChildrenCountById")
    public Object getMenuChildrenCountById (Integer id){
        Integer count  = this.menuService.queryMenuChildrenCountById(id);
        return new DataGridView(count);
    }

    /**
     * delete
     * @param id
     * @return
     */
    @RequestMapping("deleteMenu")
    public ResultObj deleteMenu(Integer id){
        try {
            this.menuService.removeById(id);
            return  ResultObj.DELETE_SUCCESS;
        }catch ( Exception e){
            e.printStackTrace();
            return  ResultObj.DELETE_ERROR;
        }
    }



}
