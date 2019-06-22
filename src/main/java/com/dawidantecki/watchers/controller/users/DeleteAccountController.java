package com.dawidantecki.watchers.controller.users;

import com.dawidantecki.watchers.data.entity.User;
import com.dawidantecki.watchers.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class DeleteAccountController {

    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @RequestMapping(value = "/deleteAccount", method = RequestMethod.GET)
    public String deleteAccount(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.findByUsername(username);
        if (user == null) {
            model.addAttribute("msgError", "Account not found.");
        } else {
            model.addAttribute("user", user);
        }

        return "users/account/deleteAccount";
    }

    @RequestMapping(value = "/deleteAccount", method = RequestMethod.POST)
    public String deleteAccount(@RequestParam("id") String id, @RequestParam("password") String password,
                                @RequestParam(value = "isSure", required = false) String isSure, Model model,
                                HttpSession session) {
        User user = userService.findById(Long.parseLong(id));
        if (user == null) {
            model.addAttribute("msgError", "Account not found.");
            return "users/account/deleteAccount";
        }

        model.addAttribute("user", user);
        if (!passwordEncoder.matches(password, user.getPassword())) {
            model.addAttribute("msgError", "Cannot delete account, password does not match.");
            return "users/account/deleteAccount";
        }

        boolean isOkay = false;
        if (isSure != null && isSure.equals("on"))
            isOkay = true;

        if (!isOkay) {
            model.addAttribute("msgError", "Cannot delete account, please select the checkbox");
            return "users/account/deleteAccount";
        }

        userService.deleteUser(user);
        session.invalidate();
        return "redirect:/login";
    }
}
