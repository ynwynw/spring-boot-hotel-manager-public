package com.example.hotel.modular.hotel.service;

import com.example.hotel.modular.hotel.model.RoomInfo;

import java.util.List;
import java.util.Map;

/**
 * @Auther: luhailiang
 * @Date: 2021/4/13 21:56
 * @Description:
 */
public interface RoomInfoService {
    int saveRoomInfo(RoomInfo roomInfo);

    int updateRoomInfo(RoomInfo roomInfo);

    int deleteRoomInfo(Integer roomId);

    List<RoomInfo> selectRoomInfoList(Map<String, Object> map);

    int getTotalRoomInfo(Map<String, Object> map);


}
