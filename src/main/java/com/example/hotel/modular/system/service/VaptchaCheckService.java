package com.example.hotel.modular.system.service;

/**
 * @Auther: luhailiang
 * @Date: 2019-03-25 16:10
 * @Description:
 */
public interface VaptchaCheckService {

    /**
     * @param token
     * @param ip
     * @return : boolean
     * @author: luhailiang
     * @date: 2019-03-25 16:12
     * @description: 人机验证结果校验
     */
    boolean vaptchaCheck(String token, String ip) throws Exception;
}
