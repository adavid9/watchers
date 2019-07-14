package com.dawidantecki.watchers.controller;

import com.dawidantecki.watchers.component.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WelcomeController {

    private Messages messages;

    @Autowired
    public WelcomeController(Messages messages) {
        this.messages = messages;
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String home(Model model) {
        String welcome = "welcome.message";
        String description = "description.message";

        model.addAttribute("msg", messages.get(welcome));
        model.addAttribute("description", messages.get(description));
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
