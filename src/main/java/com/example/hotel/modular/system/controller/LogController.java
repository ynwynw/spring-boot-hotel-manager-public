package com.example.hotel.modular.system.controller;


import com.example.hotel.core.common.annotation.LoginRequired;
import com.example.hotel.core.common.page.DataGridDataSource;
import com.example.hotel.core.common.page.PageBean;
import com.example.hotel.modular.system.model.Log;
import com.example.hotel.modular.system.service.LogService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: luhailiang
 * @Date: 2019-10-31 08:24
 * @Description:
 */
@RestController
@RequestMapping("/log")
public class LogController {

    @Resource
    private LogService logService;

    @PostMapping("/list")
    @LoginRequired
    public DataGridDataSource<Log> getLogList(@RequestParam(value = "logUserName", required = false, defaultValue = "") String logUserName,
                                              @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                              @RequestParam(value = "rows", required = false, defaultValue = "10") Integer rows) {
        PageBean pageBean = new PageBean(page, rows);
        Map<String, Object> map = new HashMap<>();
        map.put("logUserName", "%" + logUserName + "%");
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        List<Log> logList = logService.selectLogList(map);
        int totalLog = logService.getTotalLog(map);
        DataGridDataSource<Log> dataGridDataSource = new DataGridDataSource<>();
        dataGridDataSource.setRows(logList);
        dataGridDataSource.setTotal(totalLog);
        return dataGridDataSource;
    }
}
