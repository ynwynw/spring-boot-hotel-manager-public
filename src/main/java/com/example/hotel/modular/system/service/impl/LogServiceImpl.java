package com.example.hotel.modular.system.service.impl;


import com.example.hotel.modular.system.dao.LogMapper;
import com.example.hotel.modular.system.model.Log;
import com.example.hotel.modular.system.service.LogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Auther: luhailiang
 * @Date: 2019-10-30 22:04
 * @Description:
 */
@Service("logService")
public class LogServiceImpl implements LogService {

    @Resource
    private LogMapper logMapper;

    @Override
    public void saveLog(Log log) {
        logMapper.insertSelective(log);
    }

    @Override
    public List<Log> selectLogList(Map<String, Object> map) {
        return logMapper.selectLogList(map);
    }

    @Override
    public int getTotalLog(Map<String, Object> map) {
        return logMapper.getTotalLog(map);
    }
}
