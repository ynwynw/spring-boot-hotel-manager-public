package com.example.hotel.modular.hotel.controller;


import com.example.hotel.core.common.annotation.LoginRequired;
import com.example.hotel.core.common.page.DataGridDataSource;
import com.example.hotel.core.common.page.JsonData;
import com.example.hotel.core.common.page.PageBean;
import com.example.hotel.core.util.Md5Util;
import com.example.hotel.modular.hotel.model.Customer;
import com.example.hotel.modular.hotel.model.CustomerLogin;
import com.example.hotel.modular.hotel.service.CustomerService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;


@RestController
@RequestMapping("/customer")
@CrossOrigin
public class CustomerController {

    @Resource
    private CustomerService customerService;


    @PostMapping("/list")
    @LoginRequired
    public DataGridDataSource<Customer> getUserList(@RequestParam(value = "customerLoginName", required = false, defaultValue = "") String customerLoginName,
                                                    @RequestParam(value = "customerName", required = false, defaultValue = "") String customerName,
                                                    @RequestParam(value = "customerPhone", required = false, defaultValue = "") String customerPhone,
                                                    @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                                    @RequestParam(value = "rows", required = false, defaultValue = "5") Integer rows) {

        PageBean pageBean = new PageBean(page, rows);
        Map<String, Object> map = new HashMap<>();
        map.put("customerLoginName", "%" + customerLoginName + "%");
        map.put("customerName", "%" + customerName + "%");
        map.put("customerPhone", "%" + customerPhone + "%");
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        List<Customer> customerList = customerService.selectCustomerList(map);

        int totalCustomer = customerService.getTotalCustomer(map);
        DataGridDataSource<Customer> dataGridDataSource = new DataGridDataSource<>();
        dataGridDataSource.setTotal(totalCustomer);
        dataGridDataSource.setRows(customerList);
        return dataGridDataSource;
    }


    @PostMapping("/save")
//    @LoginRequired
    public JsonData saveCustomer(Customer customer) {
        int count = customerService.saveCustomer(customer);
        if (count > 0) {
            return JsonData.success(count, "注册成功");
        } else {
            return JsonData.fail("注册失败");
        }

    }

    @PutMapping("/update")
//    @LoginRequired
    public JsonData updateCustomer(Customer customer) {
        int count = customerService.updateCustomer(customer);
        if (count > 0) {
            Customer customerInfo = customerService.findCustomerByCustomerLoginName(customer.getCustomerLoginName());
            return JsonData.success(customerInfo, "修改成功");
        } else {
            return JsonData.fail("修改失败");
        }

    }


    @DeleteMapping("/delete")
    @LoginRequired
    public JsonData deleteCustomer(@RequestParam(value = "customerId") Integer customerId) {

        int count = customerService.deleteCustomer(customerId);
        if (count > 0) {
            return JsonData.success(count, "删除成功");
        } else {
            return JsonData.fail("删除失败");

        }
    }

    @PostMapping("/login")
    public JsonData customerLogin(CustomerLogin customerLogin){
        if (StringUtils.isEmpty(customerLogin.getCustomerLoginName())) {
            return JsonData.fail("用户名不能为空！");
        }
        if (StringUtils.isEmpty(customerLogin.getCustomerLoginPassword())) {
            return JsonData.fail("密码不能为空！");
        }
        Customer customer= customerService.findCustomerByCustomerLoginName(customerLogin.getCustomerLoginName());
        if(customer==null){
            return JsonData.fail("用户不存在！");
        }
        if (Md5Util.md5(customerLogin.getCustomerLoginPassword(), Md5Util.SALT).equals(customer.getCustomerLoginPassword())) {
            customer.setCustomerLoginPassword("");
            Map<String,Object> customerMap = new HashMap<>();
            customerMap.put("user",customer);
            return JsonData.success(customerMap);
        }else{
            return JsonData.fail("用户名或密码错误！");
        }

    }





}
