package com.example.hotel.modular.hotel.service;


import com.example.hotel.modular.hotel.model.Comment;

import java.util.List;
import java.util.Map;

public interface CommentService {

    int saveComment(Comment comment);

    List<Comment> selectCommentList(Map<String, Object> map);

    int getTotalComment(Map<String, Object> map);
}
