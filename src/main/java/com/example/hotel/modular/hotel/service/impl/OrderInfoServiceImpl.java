package com.example.hotel.modular.hotel.service.impl;

import com.example.hotel.modular.hotel.dao.OrderInfoMapper;
import com.example.hotel.modular.hotel.model.OrderInfo;
import com.example.hotel.modular.hotel.model.RoomInfo;
import com.example.hotel.modular.hotel.service.OrderInfoService;
import com.google.common.base.Preconditions;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Auther: luhailiang
 * @Date: 2021/4/14 21:41
 * @Description:
 */
@Service("orderInfoService")
public class OrderInfoServiceImpl implements OrderInfoService {
    @Resource
    private OrderInfoMapper orderInfoMapper;

    @Override
    public int saveOrderInfo(OrderInfo orderInfo) {
        OrderInfo orderInfo1 = OrderInfo.builder().name(orderInfo.getName())
                .phone(orderInfo.getPhone())
                .roomTypeId(orderInfo.getRoomTypeId())
                .roomType(orderInfo.getRoomType())
                .orderDate(orderInfo.getOrderDate())
                .orderDays(orderInfo.getOrderDays())
                .orderStatus(orderInfo.getOrderStatus())
                .orderCost(orderInfo.getOrderCost())
                .userId(orderInfo.getUserId())
                .idcard(orderInfo.getIdcard())
                .build();
        return orderInfoMapper.insertSelective(orderInfo1);
    }

    @Override
    public int updateOrderInfo(OrderInfo orderInfo) {
        OrderInfo before = orderInfoMapper.selectByPrimaryKey(orderInfo.getOrderId());
        Preconditions.checkNotNull(before, "需更新的订单不存在");
        OrderInfo after = OrderInfo.builder().orderId(orderInfo.getOrderId())
                .name(orderInfo.getName())
                .phone(orderInfo.getPhone())
                .roomTypeId(orderInfo.getRoomTypeId())
                .roomType(orderInfo.getRoomType())
                .orderDate(orderInfo.getOrderDate())
                .orderDays(orderInfo.getOrderDays())
                .orderStatus(orderInfo.getOrderStatus())
                .orderCost(orderInfo.getOrderCost())
                .userId(orderInfo.getUserId())
                .idcard(orderInfo.getIdcard())
                .build();
        return orderInfoMapper.updateByPrimaryKeySelective(after);
    }

    @Override
    public List<OrderInfo> selectOrderInfoListByUserId(Integer userId) {
        return orderInfoMapper.selectOrderInfoList(userId);
    }

    @Override
    public List<OrderInfo> selectOrderInfoList(Map<String, Object> map) {
        return orderInfoMapper.selectOrderInfoListByMap(map);
    }

    @Override
    public int getTotalOrderInfo(Map<String, Object> map) {
        return orderInfoMapper.getTotalOrderInfo(map);
    }

    @Override
    public OrderInfo selectOrderInfoByNameAndPhone(String name, String phone) {
        return orderInfoMapper.selectOrderInfoByNameAndPhone(name, phone);
    }

    @Override
    public OrderInfo selectOrderInfoById(Integer orderId) {
        return orderInfoMapper.selectByPrimaryKey(orderId);
    }
}
