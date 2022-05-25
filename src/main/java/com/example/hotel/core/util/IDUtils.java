package com.example.hotel.core.util;

import java.util.Random;

/**
 * @Auther: luhailiang
 * @Date: 2019-04-09 14:57
 * @Description: ID生成工具类
 */
public class IDUtils {


    /**
     * 生成用户ID(同时作为借书证编号)
     * @return
     */
    public static long genUserId() {
        //取当前时间的长整形值包含毫秒
        long millis = System.currentTimeMillis();
        //long millis = System.nanoTime();
        //加上两位随机数
        Random random = new Random();
        int end2 = random.nextInt(99);
        //如果不足两位前面补0
        String str = millis + String.format("%02d", end2);
        long id = new Long(str);
        return id;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++)
            System.out.println(genUserId());
    }
}
