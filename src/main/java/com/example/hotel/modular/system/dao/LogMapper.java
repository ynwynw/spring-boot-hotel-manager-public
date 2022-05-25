package com.example.hotel.modular.system.dao;


import com.example.hotel.modular.system.model.Log;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface LogMapper {
    int deleteByPrimaryKey(Integer logId);

    int insert(Log record);

    int insertSelective(Log record);

    Log selectByPrimaryKey(Integer logId);

    int updateByPrimaryKeySelective(Log record);

    int updateByPrimaryKey(Log record);

    List<Log> selectLogList(Map<String, Object> map);

    int getTotalLog(Map<String, Object> map);
}