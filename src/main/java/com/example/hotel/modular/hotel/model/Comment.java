package com.example.hotel.modular.hotel.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Comment {
    private Integer commentId;

    private String commnetContent;

    private String commentAccount;

    private Integer commentGrade;

    @JsonFormat(locale = "zh", timezone = "GMT", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date commentCreateTime;

    @JsonFormat(locale = "zh", timezone = "GMT", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date commentLastModifyTime;

    //不对应数据库
    private Customer customer;

}