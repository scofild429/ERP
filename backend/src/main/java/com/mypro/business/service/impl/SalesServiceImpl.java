package com.mypro.business.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mypro.business.domain.Sales;
import com.mypro.business.mapper.SalesMapper;
import com.mypro.business.service.SalesService;
@Service
public class SalesServiceImpl extends ServiceImpl<SalesMapper, Sales> implements SalesService{

}
