package com.example.hotel.modular.hotel.dao;

import com.example.hotel.modular.hotel.model.CheckIn;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CheckInMapper {
    int deleteByPrimaryKey(Integer checkInId);


    int insertSelective(CheckIn record);

    CheckIn selectByPrimaryKey(Integer checkInId);

    int updateByPrimaryKeySelective(CheckIn record);

    List<CheckIn> selectCheckInList(Map<String, Object> map);

    int getTotalCheckIn(Map<String, Object> map);

    void deleteCheckIn(Integer orderId);
}