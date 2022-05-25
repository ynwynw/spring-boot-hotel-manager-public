package com.example.hotel.core.common.page;

import lombok.Data;

/**
 * @Auther: luhailiang
 * @Date: 2018/10/19 13:46
 * @Description: Vaptcha人机验证服务器端验证返回消息封装
 */
@Data
public class VaptchaMessage {

    private Integer success; //验证结果，1为通过，0为失败

    private Integer score; //可信度，区间[0, 100]

    private String msg; //信息描述


}
