package com.mypro.business.service;

import com.mypro.business.domain.Customer;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mypro.business.vo.CustomerVo;
import com.mypro.system.common.DataGridView;

public interface CustomerService extends IService<Customer>{


    DataGridView queryAllCustomer(CustomerVo customerVo);

    Customer saveCustomer(Customer customer);

    Customer updateCustomer(Customer customer);
}
