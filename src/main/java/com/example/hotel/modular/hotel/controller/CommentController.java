package com.example.hotel.modular.hotel.controller;


import com.example.hotel.core.common.annotation.LoginRequired;
import com.example.hotel.core.common.page.DataGridDataSource;
import com.example.hotel.core.common.page.JsonData;
import com.example.hotel.core.common.page.PageBean;
import com.example.hotel.modular.hotel.model.Comment;
import com.example.hotel.modular.hotel.model.Customer;
import com.example.hotel.modular.hotel.service.CommentService;
import com.example.hotel.modular.hotel.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/comment")
@CrossOrigin
public class CommentController {

    @Resource
    private CommentService commentService;

    @Resource
    private CustomerService customerService;



    @PostMapping("/save")
    public JsonData saveComment(Comment comment) {
        int count = commentService.saveComment(comment);
        if (count > 0) {
            return JsonData.success(count, "提交成功");
        } else {
            return JsonData.fail("提交失败");
        }

    }

    @PostMapping("/list")
//    @LoginRequired
    public DataGridDataSource<Comment> getUserList(@RequestParam(value = "commentAccount", required = false, defaultValue = "") String commentAccount,
                                                    @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                                    @RequestParam(value = "rows", required = false, defaultValue = "5") Integer rows) {

        PageBean pageBean = new PageBean(page, rows);
        Map<String, Object> map = new HashMap<>();
        map.put("commentAccount", "%" + commentAccount + "%");
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        List<Comment> commentList = commentService.selectCommentList(map);
        for (Comment comment : commentList) {
            Customer customer = customerService.findCustomerByCustomerLoginName(comment.getCommentAccount());
            comment.setCustomer(customer);
        }
        int totalComment= commentService.getTotalComment(map);
        DataGridDataSource<Comment> dataGridDataSource = new DataGridDataSource<>();
        dataGridDataSource.setTotal(totalComment);
        dataGridDataSource.setRows(commentList);
        return dataGridDataSource;
    }


}
