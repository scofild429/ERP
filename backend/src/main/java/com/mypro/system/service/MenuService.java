package com.mypro.system.service;

import com.mypro.system.common.DataGridView;
import com.mypro.system.domain.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mypro.system.vo.MenuVo;

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

    DataGridView queryAllMenu(MenuVo menuVo);

    Menu saveMenu(Menu menu);

    Integer queryMenuMaxOrderNum();

    Menu updateMenu(Menu menu);

    Integer queryMenuChildrenCountById(Integer id);

    List<String> queryPermissionCodesByUserId(Integer id);
}
