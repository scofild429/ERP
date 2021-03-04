package com.mypro.business.service;

import com.mypro.business.domain.Salesback;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mypro.business.vo.SalesbackVo;
import com.mypro.system.common.DataGridView;

public interface SalesbackService extends IService<Salesback>{


    Salesback saveSalesback(Salesback salesback);

    DataGridView queryAllSalesback(SalesbackVo salesbackVo);
}
