package com.mypro.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mypro.business.domain.Goods;
import com.mypro.business.domain.Provider;
import com.mypro.business.service.GoodsService;
import com.mypro.business.service.ProviderService;
import com.mypro.business.vo.InportVo;
import com.mypro.system.common.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mypro.business.domain.Inport;
import com.mypro.business.mapper.InportMapper;
import com.mypro.business.service.InportService;
@Service
public class InportServiceImpl extends ServiceImpl<InportMapper, Inport> implements InportService{

    @Autowired
    private InportMapper inportMapper;

    @Autowired
    private ProviderService providerService;

    @Autowired
    private GoodsService goodsService;

    @Override
    public Inport saveInport(Inport inport) {
        this.inportMapper.insert(inport);
        //update the product number in Lage
        Integer goodsId = inport.getGoodsid();
        Goods goods = this.goodsService.getById(goodsId);
        goods.setNumber(goods.getNumber()+inport.getNumber());
        this.goodsService.updateGoods(goods);
        return null;
    }

    @Override
    public Inport updateInport(Inport inport) {
        Inport oldObj = this.inportMapper.selectById(inport.getId());
        Goods goods = this.goodsService.getById(oldObj.getGoodsid());
        goods.setNumber(goods.getNumber()-oldObj.getNumber()+inport.getNumber());
        this.goodsService.updateGoods(goods);
        this.inportMapper.updateById(inport);
        return  inport;
    }

    @Override
    public DataGridView queryAllInport(InportVo inportVo) {
        IPage<Inport> page=new Page<>(inportVo.getPage(),inportVo.getLimit());
        QueryWrapper<Inport> qw=new QueryWrapper<>();
        qw.eq(inportVo.getGoodsid()!=null,"goodsid",inportVo.getGoodsid());
        qw.eq(inportVo.getProviderid()!=null,"providerid",inportVo.getProviderid());
        qw.ge(inportVo.getStartTime()!=null,"inporttime",inportVo.getStartTime());
        qw.le(inportVo.getEndTime()!=null,"inporttime",inportVo.getEndTime());
        qw.orderByDesc("inporttime");
        this.inportMapper.selectPage(page,qw);
        List<Inport> records = page.getRecords();
        for (Inport record : records) {
            if(null!=record.getGoodsid()){
                Goods goods = this.goodsService.getById(record.getGoodsid());
                record.setGoodsname(goods.getGoodsname());
                record.setSize(goods.getSize());
            }
            if(null!= record.getProviderid()){
                Provider provider = this.providerService.getById(record.getProviderid());
                record.setProvidername(provider.getProvidername());
            }
        }
        return new DataGridView(page.getTotal(),records);
    }

    @Override
    public boolean removeById(Serializable id) {
        Inport inport = this.inportMapper.selectById(id);
        Goods goods = this.goodsService.getById(inport.getGoodsid());
        goods.setNumber(goods.getNumber()-inport.getNumber());
        this.goodsService.updateGoods(goods);
        return super.removeById(id);
    }
}
