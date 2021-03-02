package com.mypro.business.service;

import com.mypro.business.domain.Outport;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mypro.business.vo.OutportVo;
import com.mypro.system.common.DataGridView;

public interface OutportService extends IService<Outport>{


    Outport saveOutport(Outport outport);

    DataGridView queryAllOutport(OutportVo outportVo);
}
