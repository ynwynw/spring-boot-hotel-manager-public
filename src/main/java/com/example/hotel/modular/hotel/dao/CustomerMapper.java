package com.example.hotel.modular.hotel.dao;

import com.example.hotel.modular.hotel.model.Customer;
import com.example.hotel.modular.system.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface CustomerMapper {
    int deleteByPrimaryKey(Integer customerId);


    int insertSelective(Customer record);

    Customer selectByPrimaryKey(Integer customerId);

    int updateByPrimaryKeySelective(Customer record);


    List<Customer> selectCustomerList(Map<String, Object> map);

    int getTotalCustomer(Map<String, Object> map);

    int countByPhone(@Param("customerPhone")String customerPhone, @Param("customerId")Integer customerId);

    int countByLoginName(@Param("customerLoginName")String customerLoginName, @Param("customerId")Integer customerId);

    Customer selectByCustomerLoginName(String customerLoginName);
}