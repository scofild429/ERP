package com.mypro.business.service;

import com.mypro.business.domain.Goods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mypro.business.vo.GoodsVo;
import com.mypro.system.common.DataGridView;

public interface GoodsService extends IService<Goods>{


    DataGridView queryAllGoods(GoodsVo goodsVo);

    Goods saveGoods(Goods goodsr);

    Goods updateGoods(Goods goodsr);

    DataGridView getAllAvailableGoods();

    DataGridView getGoodsByProviderId(Integer providerid);
}
