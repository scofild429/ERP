package com.mypro.system.service;

import com.mypro.system.common.DataGridView;
import com.mypro.system.domain.Notice;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mypro.system.vo.NoticeVo;

public interface NoticeService extends IService<Notice>{
    DataGridView queryAllNotice(NoticeVo noticeVo);
}
