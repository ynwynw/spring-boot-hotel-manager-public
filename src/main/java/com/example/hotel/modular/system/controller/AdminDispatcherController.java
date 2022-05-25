package com.example.hotel.modular.system.controller;


import com.example.hotel.core.common.annotation.LoginRequired;
import com.example.hotel.core.common.annotation.Operation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;


/**
 * @Auther: luhailiang
 * @Date: 2019-03-12 14:46
 * @Description:
 */
@Controller
public class AdminDispatcherController {

//    @GetMapping("/")
//    public String home() {
//        return "redirect:login";
//    }

    @GetMapping("/admin/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    @LoginRequired
    @Operation(value = "退出登录")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/admin/login";
    }

    @GetMapping("/admin/index")
    @LoginRequired
//    @Operation(value = "访问后台主页")
    public String admin() {
        return "admin/system/index";
    }

    @GetMapping("/admin/user")
    @LoginRequired
//    @Operation(value = "访问用户管理界面")
    public String adminUser() {
        return "admin/system/user";
    }

    @GetMapping("/admin/role")
    @LoginRequired
//    @Operation(value = "访问角色管理界面")
    public String adminRole() {
        return "admin/system/role";
    }

    @GetMapping("/admin/permission")
    @LoginRequired
//    @Operation(value = "访问应用管理界面")
    public String adminPermission() {
        return "admin/system/permission";
    }

    @GetMapping("/admin/log")
    @LoginRequired
//    @Operation(value = "访问应用管理界面")
    public String adminLog() {
        return "admin/system/log";
    }

    @GetMapping("/403")
    public String error403() {
        return "error/403";
    }

    @GetMapping("/404")
    public String error404() {
        return "error/404";
    }

    @GetMapping("/500")
    public String error500() {
        return "error/500";
    }



}
