package com.dawidantecki.watchers.controller;

import com.dawidantecki.watchers.data.entity.User;
import com.dawidantecki.watchers.data.service.SecurityService;
import com.dawidantecki.watchers.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

// TODO: Improve the UserController ( this is just the first attempt to the registration and login system )
@Controller
public class UserController {

    private UserService userService;
    private SecurityService securityService;

    @Autowired
    public UserController(UserService userService, SecurityService securityService) {
        this.userService = userService;
        this.securityService = securityService;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration() {
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@RequestParam("username") String username, @RequestParam("password")
                               String password, @RequestParam("confirmPassword") String confirmPassword,
                               Model model) {

        User foundUser = userService.findByUsername(username);
        if (foundUser != null) {
            model.addAttribute("msgError", "That user already exists");
            return "registration";
        }

        if (username.equals("") || username.length() < 2) {
            model.addAttribute("msgError", "Wrong username");
            return "registration";
        }

        if (!password.equals(confirmPassword)) {
            model.addAttribute("msgError", "Passwords can not be different");
            return "registration";
        }

        User user = new User(username, password, confirmPassword);

        userService.addUser(user);
        securityService.autoLogin(username, password);

        return "redirect:/welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("msgError", "Your username and password is invalid");
        if (logout != null)
            model.addAttribute("msgError", "You have been logged out successfully");

        return "login";
    }
}
