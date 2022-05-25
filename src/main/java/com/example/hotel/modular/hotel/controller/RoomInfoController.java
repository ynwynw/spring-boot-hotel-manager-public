package com.example.hotel.modular.hotel.controller;

import com.example.hotel.core.common.page.DataGridDataSource;
import com.example.hotel.core.common.page.JsonData;
import com.example.hotel.core.common.page.PageBean;
import com.example.hotel.modular.hotel.model.RoomInfo;
import com.example.hotel.modular.hotel.model.RoomType;
import com.example.hotel.modular.hotel.service.RoomInfoService;
import com.example.hotel.modular.hotel.service.RoomTypeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: luhailiang
 * @Date: 2021/4/13 18:45
 * @Description:
 */
@RestController
@RequestMapping("/roominfo")
@CrossOrigin
public class RoomInfoController {

    @Resource
    private RoomInfoService roomInfoService;

    @Resource
    private RoomTypeService roomTypeService;


    @PostMapping("/save")
//    @LoginRequired
    public JsonData saveRoomInfo(RoomInfo roomInfo) {
        RoomType roomType = roomTypeService.findByTypeId(roomInfo.getTypeId());
        RoomInfo save = new RoomInfo();
        save.setRoomNumber(roomInfo.getRoomNumber());
        save.setTypeId(roomInfo.getTypeId());
        save.setRoomType(roomType.getTypeName());
        save.setRoomPrice(roomType.getPrice());
        int count = roomInfoService.saveRoomInfo(save);
        if (count > 0) {
            return JsonData.success(count, "添加成功");
        } else {
            return JsonData.fail("添加失败");
        }

    }

    @PutMapping("/update")
//    @LoginRequired
    public JsonData updateRoomInfo(RoomInfo roomInfo) {
        RoomType roomType = roomTypeService.findByTypeId(roomInfo.getTypeId());
        RoomInfo update = new RoomInfo();
        update.setRoomId(roomInfo.getRoomId());
        update.setRoomNumber(roomInfo.getRoomNumber());
        update.setTypeId(roomInfo.getTypeId());
        update.setRoomType(roomType.getTypeName());
        update.setRoomPrice(roomType.getPrice());
        int count = roomInfoService.updateRoomInfo(update);
        if (count > 0) {
            return JsonData.success(count, "修改成功");
        } else {
            return JsonData.fail("修改失败");
        }

    }

    @DeleteMapping("/delete")
//    @LoginRequired
    public JsonData deleteRoomType(@RequestParam(value = "roomId") Integer roomId) {
        int count = roomInfoService.deleteRoomInfo(roomId);
        if (count > 0) {
            return JsonData.success(count, "删除成功");
        } else {
            return JsonData.fail("删除失败");
        }

    }


    @PostMapping("/list")
//    @LoginRequired
    public DataGridDataSource<RoomInfo> getRoomTypeList(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                                        @RequestParam(value = "rows", required = false, defaultValue = "5") Integer rows) {

        PageBean pageBean = new PageBean(page, rows);
        Map<String, Object> map = new HashMap<>();
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        List<RoomInfo> roomInfoList = roomInfoService.selectRoomInfoList(map);

        int totalroomInfo = roomInfoService.getTotalRoomInfo(map);
        DataGridDataSource<RoomInfo> dataGridDataSource = new DataGridDataSource<>();
        dataGridDataSource.setTotal(totalroomInfo);
        dataGridDataSource.setRows(roomInfoList);
        return dataGridDataSource;
    }


    @PostMapping("/listByRoomTypeId")
//    @LoginRequired
    public DataGridDataSource<RoomInfo> getRoomTypeListByRoomTypeId(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                                                    @RequestParam(value = "rows", required = false, defaultValue = "5") Integer rows,
                                                                    @RequestParam(value = "typeId") Integer typeId) {

        PageBean pageBean = new PageBean(page, rows);
        Map<String, Object> map = new HashMap<>();
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        map.put("typeId", typeId);
        map.put("roomStatus", 0);
        List<RoomInfo> roomInfoList = roomInfoService.selectRoomInfoList(map);

        int totalroomInfo = roomInfoService.getTotalRoomInfo(map);
        DataGridDataSource<RoomInfo> dataGridDataSource = new DataGridDataSource<>();
        dataGridDataSource.setTotal(totalroomInfo);
        dataGridDataSource.setRows(roomInfoList);
        return dataGridDataSource;
    }


}
