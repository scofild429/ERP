package com.mypro.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mypro.system.common.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.function.Consumer;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mypro.system.domain.Menu;
import com.mypro.system.mapper.MenuMapper;
import com.mypro.system.service.MenuService;
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService{

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> queryAllMenuForList() {
        QueryWrapper<Menu> qw=new QueryWrapper<>();
        qw.eq("available", Constant.AVAILABLE_TRUE);
        qw.and(new Consumer<QueryWrapper<Menu>>() {
            @Override
            public void accept(QueryWrapper<Menu> menuQueryWrapper) {
                menuQueryWrapper.eq("type",Constant.MENU_TYPE_TOP)
                        .or().eq("type",Constant.MENU_TYPE_LEFT);
            }
        });
        qw.orderByAsc("ordernum");
        return this.menuMapper.selectList(qw);
    }

    @Override
    public List<Menu> queryMenuForListByUserId(Integer id) {
        QueryWrapper<Menu> qw=new QueryWrapper<>();
        qw.eq("available", Constant.AVAILABLE_TRUE);
        qw.and(new Consumer<QueryWrapper<Menu>>() {
            @Override
            public void accept(QueryWrapper<Menu> menuQueryWrapper) {
                menuQueryWrapper.eq("type",Constant.MENU_TYPE_TOP)
                        .or().eq("type",Constant.MENU_TYPE_LEFT);
            }
        });
        qw.orderByAsc("ordernum");
        return this.menuMapper.selectList(qw);
    }
}
