package com.example.hotel.core.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;


/**
 * @Auther: luhailiang
 * @Date: 2018/9/28 19:53
 * @Description: Md5加密工具类
 */
public class Md5Util {

    public static final String SALT = "Book";

    /**
     * @param str
     * @param salt
     * @return : java.lang.String
     * @author: luhailiang
     * @date: 2019-03-13 07:53
     * @description: Md5加密
     */
    public static String md5(String str, String salt) {
        if (StringUtils.isEmpty(str)) {
            return null;
        } else {
            return new Md5Hash(str, salt).toString();
        }
    }

    public static void main(String args[]) {
        String password = "123456";
//        String password = UUID.randomUUID().toString().replaceAll("-", "");
//        System.out.println(password);
        System.out.println("Md5加密后" + Md5Util.md5(password, Md5Util.SALT));
    }

}
