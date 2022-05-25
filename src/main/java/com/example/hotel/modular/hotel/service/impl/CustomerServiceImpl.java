package com.example.hotel.modular.hotel.service.impl;


import com.example.hotel.core.common.exception.ParamException;
import com.example.hotel.core.util.Md5Util;
import com.example.hotel.modular.hotel.dao.CustomerMapper;
import com.example.hotel.modular.hotel.model.Customer;
import com.example.hotel.modular.hotel.service.CustomerService;
import com.google.common.base.Preconditions;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

    @Resource
    private CustomerMapper customerMapper;

    @Override
    public List<Customer> selectCustomerList(Map<String, Object> map) {
        return customerMapper.selectCustomerList(map);
    }

    @Override
    public int getTotalCustomer(Map<String, Object> map) {
        return customerMapper.getTotalCustomer(map);
    }

    @Override
    public int saveCustomer(Customer customer) {
        if (checkCustomerLoginNameExist(customer.getCustomerLoginName(), customer.getCustomerId())) {
            throw new ParamException("登录账户已被占用");
        }
        if (checkCustomerPhoneExist(customer.getCustomerPhone(), customer.getCustomerId())) {
            throw new ParamException("手机号已被占用");
        }
        Customer customer1=Customer.builder()
                .customerLoginName(customer.getCustomerLoginName())
                .customerLoginPassword(Md5Util.md5(customer.getCustomerLoginPassword(), Md5Util.SALT))
                .customerName(customer.getCustomerName())
                .customerPhone(customer.getCustomerPhone())
                .customerCardNumber(customer.getCustomerCardNumber())
                .build();
        return customerMapper.insertSelective(customer1);
    }

    private boolean checkCustomerPhoneExist(String customerPhone, Integer customerId) {
        return customerMapper.countByPhone(customerPhone, customerId) > 0;
    }

    private boolean checkCustomerLoginNameExist(String customerLoginName, Integer customerId) {
        return customerMapper.countByLoginName(customerLoginName,customerId)>0;
    }


    @Override
    public int updateCustomer(Customer customer) {
        if (checkCustomerLoginNameExist(customer.getCustomerLoginName(), customer.getCustomerId())) {
            throw new ParamException("登录账户已被占用");
        }
        if (checkCustomerPhoneExist(customer.getCustomerPhone(), customer.getCustomerId())) {
            throw new ParamException("手机号已被占用");
        }
        Customer before = customerMapper.selectByPrimaryKey(customer.getCustomerId());
        Preconditions.checkNotNull(before, "需更新的客户不存在");
        Customer after=Customer.builder()
                .customerId(customer.getCustomerId())
                .customerLoginName(customer.getCustomerLoginName())
                .customerLoginPassword(Md5Util.md5(customer.getCustomerLoginPassword(), Md5Util.SALT))
                .customerName(customer.getCustomerName())
                .customerPhone(customer.getCustomerPhone())
                .customerCardNumber(customer.getCustomerCardNumber())
                .build();
        return customerMapper.updateByPrimaryKeySelective(after);
    }

    @Override
    public int deleteCustomer(Integer customerId) {
        Customer before = customerMapper.selectByPrimaryKey(customerId);
        Preconditions.checkNotNull(before, "需删除的客户不存在");
        return customerMapper.deleteByPrimaryKey(customerId);
    }

    @Override
    public Customer findCustomerByCustomerLoginName(String customerLoginName) {
        return customerMapper.selectByCustomerLoginName(customerLoginName);
    }
}
