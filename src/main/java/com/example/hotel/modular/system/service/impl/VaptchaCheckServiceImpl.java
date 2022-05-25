package com.example.hotel.modular.system.service.impl;

import com.example.hotel.core.common.page.VaptchaMessage;
import com.example.hotel.modular.system.service.VaptchaCheckService;
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: luhailiang
 * @Date: 2019-03-25 16:10
 * @Description:
 */
@Service("vaptchaCheckService")
@Slf4j
public class VaptchaCheckServiceImpl implements VaptchaCheckService {

    @Value(value = "${vaptcha.id}")
    private String VAPTCHA_ID;

    @Value(value = "${vaptcha.secretkey}")
    private String VAPTCHA_SECRETKEY;

    /**
     * @param token
     * @param ip
     * @return : boolean
     * @author: luhailiang
     * @date: 2019-03-25 16:12
     * @description: 人机验证结果校验
     */
    @Override
    public boolean vaptchaCheck(String token, String ip) throws Exception {
        String body = "";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://api.vaptcha.com/v2/validate");
        List<NameValuePair> nvps = new ArrayList<>();
        nvps.add(new BasicNameValuePair("id", VAPTCHA_ID));
        nvps.add(new BasicNameValuePair("secretkey", VAPTCHA_SECRETKEY));
        nvps.add(new BasicNameValuePair("scene", ""));
        nvps.add(new BasicNameValuePair("token", token));
        nvps.add(new BasicNameValuePair("ip", ip));

        httpPost.setEntity(new UrlEncodedFormEntity(nvps));

        CloseableHttpResponse r = httpClient.execute(httpPost);
        HttpEntity entity = r.getEntity();

        if (entity != null) {
            body = EntityUtils.toString(entity, "utf-8");
            log.info(body);
        }
        r.close();
        httpClient.close();
        Gson gson = new Gson();
        VaptchaMessage message = gson.fromJson(body, VaptchaMessage.class);
        if (message.getSuccess() == 1) {
            return true;
        } else {
            return false;
        }
    }
}
