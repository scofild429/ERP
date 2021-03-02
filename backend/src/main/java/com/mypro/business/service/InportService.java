package com.mypro.business.service;

import com.mypro.business.domain.Inport;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mypro.business.vo.InportVo;
import com.mypro.system.common.DataGridView;

public interface InportService extends IService<Inport>{


    Inport saveInport(Inport inport);

    Inport updateInport(Inport inport);

    DataGridView queryAllInport(InportVo inportVo);
}
