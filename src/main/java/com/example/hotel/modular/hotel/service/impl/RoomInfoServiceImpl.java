package com.example.hotel.modular.hotel.service.impl;

import com.example.hotel.modular.hotel.dao.RoomInfoMapper;
import com.example.hotel.modular.hotel.model.RoomInfo;
import com.example.hotel.modular.hotel.service.RoomInfoService;
import com.google.common.base.Preconditions;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Auther: luhailiang
 * @Date: 2021/4/13 21:58
 * @Description:
 */
@Service("roomInfoService")
public class RoomInfoServiceImpl implements RoomInfoService {

    @Resource
    private RoomInfoMapper roomInfoMapper;

    @Override
    public int saveRoomInfo(RoomInfo roomInfo) {
        RoomInfo roomInfo1 = RoomInfo.builder().roomNumber(roomInfo.getRoomNumber())
                .typeId(roomInfo.getTypeId())
                .roomType(roomInfo.getRoomType())
                .roomPrice(roomInfo.getRoomPrice())
                .roomStatus(roomInfo.getRoomStatus())
                .build();
        return roomInfoMapper.insertSelective(roomInfo1);
    }

    @Override
    public int updateRoomInfo(RoomInfo roomInfo) {
        RoomInfo before = roomInfoMapper.selectByPrimaryKey(roomInfo.getRoomId());
        Preconditions.checkNotNull(before, "需更新的房间不存在");
        RoomInfo after = RoomInfo.builder().roomId(roomInfo.getRoomId())
                .roomNumber(roomInfo.getRoomNumber())
                .typeId(roomInfo.getTypeId())
                .roomType(roomInfo.getRoomType())
                .roomPrice(roomInfo.getRoomPrice())
                .roomStatus(roomInfo.getRoomStatus())
                .build();
        return roomInfoMapper.updateByPrimaryKeySelective(after);
    }

    @Override
    public int deleteRoomInfo(Integer roomId) {
        RoomInfo before = roomInfoMapper.selectByPrimaryKey(roomId);
        Preconditions.checkNotNull(before, "需删除的房间不存在");
        return roomInfoMapper.deleteByPrimaryKey(roomId);
    }

    @Override
    public List<RoomInfo> selectRoomInfoList(Map<String, Object> map) {
        return roomInfoMapper.selectRoomInfoList(map);
    }

    @Override
    public int getTotalRoomInfo(Map<String, Object> map) {
        return roomInfoMapper.getTotalRoomInfo(map);
    }
}
