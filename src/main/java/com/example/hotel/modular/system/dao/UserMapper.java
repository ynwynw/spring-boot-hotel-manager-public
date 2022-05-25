package com.example.hotel.modular.system.dao;


import com.example.hotel.modular.system.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Long userId);


    int insertSelective(User record);

    User selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(User record);


    User selectByUserName(String userName);

    List<User> selectUserList(Map<String, Object> map);

    int getTotalUser(Map<String, Object> map);


    int countByPhone(@Param("userPhone") String userPhone, @Param("userId") Long userId);

    int countByName(@Param("userName") String userName, @Param("userId") Long userId);

    int insertUserRoles(Map<String, Object> map);

    int countByUserId(Long userId);

    int countByUserTrueName(@Param("userTrueName") String userTrueName, @Param("userId") Long userId);
}