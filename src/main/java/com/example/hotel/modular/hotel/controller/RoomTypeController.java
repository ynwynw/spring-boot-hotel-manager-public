package com.example.hotel.modular.hotel.controller;

import com.example.hotel.core.common.annotation.LoginRequired;
import com.example.hotel.core.common.page.DataGridDataSource;
import com.example.hotel.core.common.page.JsonData;
import com.example.hotel.core.common.page.PageBean;
import com.example.hotel.modular.hotel.model.RoomType;
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
@RequestMapping("/roomtype")
@CrossOrigin
public class RoomTypeController {

    @Resource
    private RoomTypeService roomTypeService;


    @PostMapping("/save")
//    @LoginRequired
    public JsonData saveRoomType(RoomType roomType) {
        int count = roomTypeService.saveRoomType(roomType);
        if (count > 0) {
            return JsonData.success(count, "添加成功");
        } else {
            return JsonData.fail("添加失败");
        }

    }

    @PutMapping("/update")
//    @LoginRequired
    public JsonData updateRoomType(RoomType roomType) {
        int count = roomTypeService.updateRoomType(roomType);
        if (count > 0) {
            return JsonData.success(count, "修改成功");
        } else {
            return JsonData.fail("修改失败");
        }

    }

    @DeleteMapping("/delete")
//    @LoginRequired
    public JsonData deleteRoomType(@RequestParam(value = "typeId") Integer typeId) {
        int count = roomTypeService.deleteRoomType(typeId);
        if (count > 0) {
            return JsonData.success(count, "删除成功");
        } else {
            return JsonData.fail("删除失败");
        }

    }


    @PostMapping("/list")
//    @LoginRequired
    public DataGridDataSource<RoomType> getRoomTypeList(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                                        @RequestParam(value = "rows", required = false, defaultValue = "5") Integer rows) {

        PageBean pageBean = new PageBean(page, rows);
        Map<String, Object> map = new HashMap<>();
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        List<RoomType> roomTypeList = roomTypeService.selectRoomTypeList(map);

        int totalroomType = roomTypeService.getTotalRoomType(map);
        DataGridDataSource<RoomType> dataGridDataSource = new DataGridDataSource<>();
        dataGridDataSource.setTotal(totalroomType);
        dataGridDataSource.setRows(roomTypeList);
        return dataGridDataSource;
    }

    @GetMapping("/listAll")
    public JsonData listAllRoomType() {
        Map<String, Object> map = new HashMap<>();
        List<RoomType> roomTypeList = roomTypeService.selectRoomTypeList(map);
        return JsonData.success(roomTypeList);
    }
}
