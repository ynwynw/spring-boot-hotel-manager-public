package com.example.hotel.modular.hotel.service.impl;

import com.example.hotel.modular.hotel.dao.RoomTypeMapper;
import com.example.hotel.modular.hotel.model.RoomType;
import com.example.hotel.modular.hotel.service.RoomTypeService;
import com.google.common.base.Preconditions;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Auther: luhailiang
 * @Date: 2021/4/13 18:33
 * @Description:
 */
@Service("roomTypeService")
public class RoomTypeServiceImpl implements RoomTypeService {

    @Resource
    private RoomTypeMapper roomTypeMapper;

    @Override
    public int saveRoomType(RoomType roomType) {
        RoomType roomType1 = RoomType.builder().typeName(roomType.getTypeName())
                .area(roomType.getArea())
                .bedNum(roomType.getBedNum())
                .bedSize(roomType.getBedSize())
                .price(roomType.getPrice())
                .window(roomType.getWindow())
                .rest(roomType.getRest())
                .build();
        return roomTypeMapper.insertSelective(roomType1);
    }

    @Override
    public int updateRoomType(RoomType roomType) {
        RoomType before = roomTypeMapper.selectByPrimaryKey(roomType.getTypeId());
        Preconditions.checkNotNull(before, "需更新的房间类型不存在");
        RoomType after = RoomType.builder().typeId(roomType.getTypeId())
                .typeName(roomType.getTypeName())
                .area(roomType.getArea())
                .bedNum(roomType.getBedNum())
                .bedSize(roomType.getBedSize())
                .price(roomType.getPrice())
                .window(roomType.getWindow())
                .rest(roomType.getRest())
                .build();
        return roomTypeMapper.updateByPrimaryKeySelective(after);
    }

    @Override
    public int deleteRoomType(Integer typeId) {
        RoomType before = roomTypeMapper.selectByPrimaryKey(typeId);
        Preconditions.checkNotNull(before, "需删除的房型不存在");
        return roomTypeMapper.deleteByPrimaryKey(typeId);
    }

    @Override
    public List<RoomType> selectRoomTypeList(Map<String, Object> map) {
        return roomTypeMapper.selectRoomTypeList(map);
    }

    @Override
    public int getTotalRoomType(Map<String, Object> map) {
        return roomTypeMapper.getTotalRoomType(map);
    }

    @Override
    public RoomType findByTypeId(Integer typeId) {
        return roomTypeMapper.selectByPrimaryKey(typeId);
    }
}
