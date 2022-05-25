package com.example.hotel.modular.hotel.service;


import com.example.hotel.modular.hotel.model.Customer;


import java.util.List;
import java.util.Map;

public interface CustomerService {


    List<Customer> selectCustomerList(Map<String, Object> map);

    int getTotalCustomer(Map<String, Object> map);

    int saveCustomer(Customer customer);

    int updateCustomer(Customer customer);

    int deleteCustomer(Integer customerId);

    Customer findCustomerByCustomerLoginName(String customerLoginName);
}
