package com.example.hotel.modular.hotel.service.impl;

import com.example.hotel.modular.hotel.dao.CheckInMapper;
import com.example.hotel.modular.hotel.model.CheckIn;
import com.example.hotel.modular.hotel.service.CheckInService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Auther: luhailiang
 * @Date: 2021/4/15 21:21
 * @Description:
 */
@Service("checkInService")
public class CheckInServiceImpl implements CheckInService {

    @Resource
    private CheckInMapper checkInMapper;

    @Override
    public int saveCheckIn(CheckIn checkIn) {
        CheckIn checkIn1 = CheckIn.builder().orderId(checkIn.getOrderId())
                .roomId(checkIn.getRoomId())
                .roomNumber(checkIn.getRoomNumber())
                .persons(checkIn.getPersons())
                .ids(checkIn.getIds())
                .checkInTime(checkIn.getCheckInTime())
                .build();
        return checkInMapper.insertSelective(checkIn1);
    }

    @Override
    public List<CheckIn> selectCheckInList(Map<String, Object> map) {
        return checkInMapper.selectCheckInList(map);
    }

    @Override
    public int getTotalCheckIn(Map<String, Object> map) {
        return checkInMapper.getTotalCheckIn(map);
    }
    @Override
    public void deleteCheckIn(Integer orderId){
        checkInMapper.deleteCheckIn(orderId);
    }
}
