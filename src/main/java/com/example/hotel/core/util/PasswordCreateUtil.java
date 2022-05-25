package com.example.hotel.core.util;

import java.util.Random;

/**
 * @Auther: luhailiang
 * @Date: 2019-03-24 21:17
 * @Description: 生成随机密码工具类
 */
public class PasswordCreateUtil {

    /**
     * @param len
     * @return : java.lang.String
     * @author: luhailiang
     * @date: 2019-03-24 21:19
     * @description: 指定长度生成随机密码
     */
    public static String createPassWord(int len) {
        int random = createRandomInt();
        return createPassWord(random, len);
    }

    private static String createPassWord(int random, int len) {
        Random rd = new Random(random);
        final int maxNum = 62;
        StringBuffer sb = new StringBuffer();
        int rdGet;//取得随机数
        char[] str = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
                'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
                'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
                'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
                'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

        int count = 0;
        while (count < len) {
            rdGet = Math.abs(rd.nextInt(maxNum));//生成的数最大为62-1
            if (rdGet >= 0 && rdGet < str.length) {
                sb.append(str[rdGet]);
                count++;
            }
        }
        return sb.toString();
    }

    private static int createRandomInt() {
        //得到0.0到1.0之间的数字，并扩大100000倍
        double temp = Math.random() * 100000;
        //如果数据等于100000，则减少1
        if (temp >= 100000) {
            temp = 99999;
        }
        int tempint = (int) Math.ceil(temp);
        return tempint;
    }

}
