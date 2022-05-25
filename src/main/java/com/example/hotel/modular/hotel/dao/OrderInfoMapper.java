package com.example.hotel.modular.hotel.dao;

import com.example.hotel.modular.hotel.model.OrderInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderInfoMapper {
    int deleteByPrimaryKey(Integer orderId);


    int insertSelective(OrderInfo record);

    OrderInfo selectByPrimaryKey(Integer orderId);

    int updateByPrimaryKeySelective(OrderInfo record);

    List<OrderInfo> selectOrderInfoList(Integer userId);

    List<OrderInfo> selectOrderInfoListByMap(Map<String, Object> map);
    int getTotalOrderInfo(Map<String, Object> map);

    OrderInfo selectOrderInfoByNameAndPhone(@Param("name")String name, @Param("phone")String phone);
}