package com.mypro.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mypro.system.common.DataGridView;
import com.mypro.system.vo.NoticeVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mypro.system.domain.Notice;
import com.mypro.system.mapper.NoticeMapper;
import com.mypro.system.service.NoticeService;
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService{


    @Autowired
    private NoticeMapper noticeMapper;



    @Override
    public DataGridView queryAllNotice(NoticeVo noticeVo) {
        IPage<Notice> page = new Page<>(noticeVo.getPage(), noticeVo.getLimit());
        QueryWrapper<Notice> qw = new QueryWrapper<>();
        qw.like(StringUtils.isNotBlank(noticeVo.getTitle()),"title",noticeVo.getTitle());
        qw.like(StringUtils.isNotBlank(noticeVo.getOpername()),"opername", noticeVo.getOpername());
        qw.ge(noticeVo.getStartTime()!=null,"createtime",noticeVo.getStartTime());
        qw.le(noticeVo.getEndTime()!=null,"createtime",noticeVo.getEndTime());
        qw.orderByDesc("createtime");

        this.noticeMapper.selectPage(page, qw);
        return new DataGridView(page.getTotal(), page.getRecords());
    }
}
