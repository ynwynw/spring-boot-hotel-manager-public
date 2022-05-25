package com.example.hotel.core.util;

import com.google.gson.Gson;
import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * @Auther: luhailiang
 * @Date: 2019-11-10 21:52
 * @Description:
 */
public class QueryHelper {

    /**
     * txt|jsonp|xml
     */
    public static String DATATYPE = "jsonp";

    public static String get(String urlString, String token) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5 * 1000);
            conn.setReadTimeout(5 * 1000);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setInstanceFollowRedirects(false);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("token", token);
            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                StringBuilder builder = new StringBuilder();
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(conn.getInputStream(), "utf-8"));
                for (String s = br.readLine(); s != null; s = br
                        .readLine()) {
                    builder.append(s);
                }
                br.close();
                return builder.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String queryIP(String ip) {
        String url = "http://api.ip138.com/query/?ip=" + ip + "&datatype=" + DATATYPE;
        String token = "1129493ffa8e412f46b4d5868079d9cc";
        return get(url, token);
    }

    @Data
    public class JsonRootBean {
        private String ret;
        private String ip;
        private List<String> data;
    }

    public static String getIpLocation(String ip) {
        String jsonString = queryIP(ip);
        //System.out.println(jsonString);
        Gson gson = new Gson();
       JsonRootBean result = gson.fromJson(jsonString, JsonRootBean.class);
        return result.getData().get(0) + result.getData().get(1) + result.getData().get(2);
    }

    public static void main(String args[]) {
        System.out.println(getIpLocation("127.0.0.1"));
        System.out.println(getIpLocation("112.21.45.108"));
    }
}