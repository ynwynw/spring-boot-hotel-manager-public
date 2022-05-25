package com.example.hotel.core.util;


import com.example.hotel.core.common.thread.RequestHolder;
import nl.bitwalker.useragentutils.Browser;
import nl.bitwalker.useragentutils.OperatingSystem;
import nl.bitwalker.useragentutils.UserAgent;

/**
 * @Auther: luhailiang
 * @Date: 2019-10-30 21:36
 * @Description:
 */
public class UserAgentUtil {

    /**
     * @return : java.lang.String
     * @author: luhailiang
     * @date: 2019-10-30 22:02
     * @description: 获取系统类型
     */
    public static String getSystemType() {
        String ua = RequestHolder.getCurrentRequest().getHeader("User-Agent");
        //转成UserAgent对象
        UserAgent userAgent = UserAgent.parseUserAgentString(ua);
        //获取系统信息
        OperatingSystem os = userAgent.getOperatingSystem();
        //系统名称
        String system = os.getName();
        return system;
    }


    /**
     * @return : java.lang.String
     * @author: luhailiang
     * @date: 2019-10-30 22:02
     * @description: 获取浏览器类型
     */
    public static String getBrowserType() {
        String ua = RequestHolder.getCurrentRequest().getHeader("User-Agent");
        //转成UserAgent对象
        UserAgent userAgent = UserAgent.parseUserAgentString(ua);
        //获取浏览器信息
        Browser browser = userAgent.getBrowser();
        //浏览器名称
        String browserName = browser.getName();
        return browserName;
    }
}
