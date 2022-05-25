package com.example.hotel.modular.system.service.impl;


import com.example.hotel.modular.system.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Auther: luhailiang
 * @Date: 2019-03-13 21:35
 * @Description:
 */
@Service("mailService")
@Component
@Slf4j
public class MailServiceImpl implements MailService {

    @Resource
    private JavaMailSender mailSender;

    @Value("${mail.fromMail.addr}")
    private String from;

    /**
     * @param to
     * @param subject
     * @param content
     * @return : void
     * @author: luhailiang
     * @date: 2019-03-13 21:36
     * @description: 发送简单邮件
     */
    @Override
    public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        try {
            mailSender.send(message);
            log.info("简单邮件发送成功!");
        } catch (MailException e) {
            log.error("发送简单邮件时发生异常！" + e);
        }
    }
}
