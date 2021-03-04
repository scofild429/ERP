package com.mypro.business.service;

import com.mypro.business.domain.Sales;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mypro.business.vo.SalesVo;
import com.mypro.system.common.DataGridView;

public interface SalesService extends IService<Sales>{


    DataGridView queryAllSales(SalesVo salesVo);

    Sales saveSales(Sales sales);

    Sales updateSales(Sales sales);
}
