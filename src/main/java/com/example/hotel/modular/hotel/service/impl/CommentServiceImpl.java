package com.example.hotel.modular.hotel.service.impl;

import com.example.hotel.modular.hotel.dao.CommentMapper;
import com.example.hotel.modular.hotel.model.Comment;
import com.example.hotel.modular.hotel.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Service("commentService")
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentMapper commentMapper;

    @Override
    public int saveComment(Comment comment) {
        return commentMapper.insertSelective(comment);
    }

    @Override
    public List<Comment> selectCommentList(Map<String, Object> map) {
        return commentMapper.selectCommentList(map);
    }

    @Override
    public int getTotalComment(Map<String, Object> map) {
        return commentMapper.getTotalComment(map);
    }
}
