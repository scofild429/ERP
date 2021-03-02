package com.mypro.business.service;

import com.mypro.business.domain.Provider;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mypro.business.vo.ProviderVo;
import com.mypro.system.common.DataGridView;

public interface ProviderService extends IService<Provider>{


    DataGridView queryAllProvider(ProviderVo providerVo);

    Provider saveProvider(Provider provider);

    Provider updateProvider(Provider provider);

    DataGridView getAllAvailableProvider();
}
