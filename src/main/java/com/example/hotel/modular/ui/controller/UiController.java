package com.example.hotel.modular.ui.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;





@Controller
public class UiController {

    @GetMapping("/")
    public String home() {
        return "redirect:hotel/index";
    }

    @GetMapping("/hotel/index")
    public String hotelindex() {
        return "ui/index";
    }


    @GetMapping("/hotel/user")
    public String hoteluser() {
        return "ui/user";
    }

    @GetMapping("/hotel/room")
    public String hotelroom() {
        return "ui/room";
    }


}
