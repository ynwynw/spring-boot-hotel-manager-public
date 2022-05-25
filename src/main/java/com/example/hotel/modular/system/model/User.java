package com.example.hotel.modular.system.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

/**
 * @Auther: luhailiang
 * @Date: 2019-03-12 10:28
 * @Description: UserModel
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder

public class User {


    private Long userId;
    private String userName;
    private String userTrueName;
    private String userPassword;

    private String userPhone;
    private Integer userState;  //约定 1：正常状态  0：禁用状态   默认正常
    @JsonFormat(locale = "zh", timezone = "GMT", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date userCreateTime;
    @JsonFormat(locale = "zh", timezone = "GMT", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date userLastModifyTime;
    //不对应数据库字段
    private String roles;


}
