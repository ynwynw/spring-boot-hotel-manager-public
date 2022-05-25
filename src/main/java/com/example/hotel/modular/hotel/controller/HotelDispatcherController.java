package com.example.hotel.modular.hotel.controller;


import com.example.hotel.core.common.annotation.LoginRequired;
import com.example.hotel.core.common.annotation.Operation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;


@Controller
public class HotelDispatcherController {



    @GetMapping("/admin/customer")
    @LoginRequired
    public String customer() {
        return "admin/hotel/customer";
    }


    @GetMapping("/admin/comment")
    @LoginRequired
    public String comment() {
        return "admin/hotel/comment";
    }

    @GetMapping("/admin/roomtype")
    @LoginRequired
    public String roomtype() {
        return "admin/hotel/roomtype";
    }

    @GetMapping("/admin/roominfo")
    @LoginRequired
    public String roominfo() {
        return "admin/hotel/roominfo";
    }

    @GetMapping("/admin/orderinfo")
    @LoginRequired
    public String orderinfo() {
        return "admin/hotel/orderinfo";
    }

    @GetMapping("/admin/checkin")
    @LoginRequired
    public String checkin() {
        return "admin/hotel/checkin";
    }

    @GetMapping("/admin/home")
    @LoginRequired
    public String home() {
        return "admin/hotel/home";
    }

}
