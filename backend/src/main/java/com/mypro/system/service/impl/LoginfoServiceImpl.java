package com.mypro.system.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mypro.system.mapper.LoginfoMapper;
import com.mypro.system.domain.Loginfo;
import com.mypro.system.service.LoginfoService;
@Service
public class LoginfoServiceImpl extends ServiceImpl<LoginfoMapper, Loginfo> implements LoginfoService{

}
