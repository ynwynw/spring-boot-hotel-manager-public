package com.example.hotel.modular.hotel.dao;

import com.example.hotel.modular.hotel.model.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CommentMapper {
    int deleteByPrimaryKey(Integer commentId);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer commentId);

    int updateByPrimaryKeySelective(Comment record);


    List<Comment> selectCommentList(Map<String, Object> map);

    int getTotalComment(Map<String, Object> map);
}