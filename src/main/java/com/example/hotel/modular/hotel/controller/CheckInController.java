package com.example.hotel.modular.hotel.controller;

import com.example.hotel.core.common.page.DataGridDataSource;
import com.example.hotel.core.common.page.JsonData;
import com.example.hotel.core.common.page.PageBean;
import com.example.hotel.modular.hotel.model.CheckIn;
import com.example.hotel.modular.hotel.model.OrderInfo;
import com.example.hotel.modular.hotel.model.RoomInfo;
import com.example.hotel.modular.hotel.service.CheckInService;
import com.example.hotel.modular.hotel.service.OrderInfoService;
import com.example.hotel.modular.hotel.service.RoomInfoService;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: luhailiang
 * @Date: 2021/4/15 21:32
 * @Description:
 */
@RestController
@RequestMapping("/checkin")
@CrossOrigin
public class CheckInController {

    @Resource
    private CheckInService checkInService;

    @Resource
    private RoomInfoService roomInfoService;

    @Resource
    private OrderInfoService orderInfoService;

    @PostMapping("/in")
    public JsonData in(CheckIn checkIn) {
        int count = checkInService.saveCheckIn(checkIn);
        //更新房间状态为已入住
        RoomInfo roomInfo = new RoomInfo();
        roomInfo.setRoomId(checkIn.getRoomId());
        roomInfo.setRoomStatus(1);
        roomInfoService.updateRoomInfo(roomInfo);
        //更新订单状态为已入住
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderId(checkIn.getOrderId());
        orderInfo.setOrderStatus(1);
        orderInfoService.updateOrderInfo(orderInfo);

        if (count > 0) {
            return JsonData.success(count, "入住成功");
        } else {
            return JsonData.fail("入住失败");
        }
    }

    @PostMapping("/list")
//    @LoginRequired
    public DataGridDataSource<CheckIn> getOrderInfoList(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                                        @RequestParam(value = "rows", required = false, defaultValue = "5") Integer rows) {

        PageBean pageBean = new PageBean(page, rows);
        Map<String, Object> map = new HashMap<>();
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        List<CheckIn> checkIns = checkInService.selectCheckInList(map);

        int totalcheckIns = checkInService.getTotalCheckIn(map);
        DataGridDataSource<CheckIn> dataGridDataSource = new DataGridDataSource<>();
        dataGridDataSource.setTotal(totalcheckIns);
        dataGridDataSource.setRows(checkIns);
        return dataGridDataSource;
    }

    @PostMapping("/out")
    public JsonData out(@RequestParam(value = "orderId") Integer orderId,
                        @RequestParam(value = "roomId") Integer roomId) {

        OrderInfo orderInfo1 = orderInfoService.selectOrderInfoById(orderId);
        if (orderInfo1.getOrderStatus() == 3) {
            checkInService.deleteCheckIn(orderId);
            return JsonData.fail("已成功退房，无需重复退房");
        } else {
            //更新房间状态为正常可入住
            RoomInfo roomInfo = new RoomInfo();
            roomInfo.setRoomId(roomId);
            roomInfo.setRoomStatus(0);
            roomInfoService.updateRoomInfo(roomInfo);
            //更新订单状态为已退房
            OrderInfo orderInfo = new OrderInfo();
            orderInfo.setOrderId(orderId);
            orderInfo.setOrderStatus(3);
            checkInService.deleteCheckIn(orderId);
            int count = orderInfoService.updateOrderInfo(orderInfo);

            if (count > 0) {
                return JsonData.success(count, "退房成功");
            } else {
                return JsonData.fail("退房失败");
            }
        }
    }
}
