package com.mypro.system.service;

import com.mypro.system.common.DataGridView;
import com.mypro.system.domain.Loginfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mypro.system.vo.LoginfoVo;

public interface LoginfoService extends IService<Loginfo>{


    DataGridView queryAllLoginfo(LoginfoVo loginfoVo);
}
