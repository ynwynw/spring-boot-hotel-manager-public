package com.example.hotel.modular.system.service;

/**
 * @Auther: luhailiang
 * @Date: 2019-03-13 21:34
 * @Description: MailService邮件发送
 */
public interface MailService {

    /**
     * @param to
     * @param subject
     * @param content
     * @return : void
     * @author: luhailiang
     * @date: 2019-03-13 21:36
     * @description: 发送简单邮件
     */
     void sendSimpleMail(String to, String subject, String content);
}
