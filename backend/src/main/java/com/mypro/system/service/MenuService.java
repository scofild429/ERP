package com.mypro.system.service;

import com.mypro.system.domain.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface MenuService extends IService<Menu>{


    /**
     * query all menu
     * @return
     */
    List<Menu> queryAllMenuForList();


    /**
     * query according to ID
     * @param id
     * @return
     */
    List<Menu> queryMenuForListByUserId(Integer id);
}
