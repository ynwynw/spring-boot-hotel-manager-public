package com.example.hotel.modular.hotel.service;

import com.example.hotel.modular.hotel.model.RoomType;

import java.util.List;
import java.util.Map;

/**
 * @Auther: luhailiang
 * @Date: 2021/4/13 18:30
 * @Description:
 */
public interface RoomTypeService {
    int saveRoomType(RoomType roomType);

    int updateRoomType(RoomType roomType);

    int deleteRoomType(Integer typeId);

    List<RoomType> selectRoomTypeList(Map<String, Object> map);

    int getTotalRoomType(Map<String, Object> map);

    RoomType findByTypeId(Integer typeId);


}
