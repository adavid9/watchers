package com.dawidantecki.watchers.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WelcomeController {

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String home() {
        return "welcome";
    }

    @RequestMapping(value = "admin/adminStart", method = RequestMethod.GET)
    public String adminPage() {
        return "adminStart";
    }

    @RequestMapping(value = "/userStart", method = RequestMethod.GET)
    public String userPage() {
        return "userStart";
    }
}
