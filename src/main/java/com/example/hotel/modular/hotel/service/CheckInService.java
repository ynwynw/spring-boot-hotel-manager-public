package com.example.hotel.modular.hotel.service;

import com.example.hotel.modular.hotel.model.CheckIn;

import java.util.List;
import java.util.Map;

/**
 * @Auther: luhailiang
 * @Date: 2021/4/15 21:20
 * @Description:
 */
public interface CheckInService {
    int saveCheckIn(CheckIn checkIn);

    List<CheckIn> selectCheckInList(Map<String, Object> map);

    int getTotalCheckIn(Map<String, Object> map);

    void deleteCheckIn(Integer orderId);
}
