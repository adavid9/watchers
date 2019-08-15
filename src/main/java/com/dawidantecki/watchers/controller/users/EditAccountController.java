package com.dawidantecki.watchers.controller.users;

import javax.servlet.http.HttpSession;

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

@Controller
public class EditAccountController {

    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @RequestMapping(value = "/changePassword", method = RequestMethod.GET)
    public String changePassword(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        // lookup for user
        User user = userService.findByUsername(username);
        if (user != null) {
            model.addAttribute("user", user);
        } else {
            model.addAttribute("msgError", "User not found");
        }

        return "users/account/changePassword";
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    public String changePassword(@RequestParam("id") String id, @RequestParam("password") String password,
                                 @RequestParam("confirmPassword") String confirmPassword, Model model, HttpSession session) {
        User user = userService.findById(Long.parseLong(id));
        if (user == null) {
            model.addAttribute("msgError", "User not found.");
            return "users/account/changePassword";
        }

        model.addAttribute("user", user);

        if (passwordEncoder.matches(password, user.getPassword())) {
            model.addAttribute("msgError", "Password cannot be the same as old password.");
            return "users/account/changePassword";
        }

        if (password.length() < 3) {
            model.addAttribute("msgError", "The password cannot be less than 3 characters.");
            return "users/account/changePassword";
        }

        // check if password and confirmPassword are same
        if (!password.equals(confirmPassword)) {
            model.addAttribute("msgError", "The password cannot be different than confirmPassword.");
            return "users/account/changePassword";
        }

        user.setPassword(password);

        userService.addUser(user);
        // whenever the user change password, redirect him to the login page
		// to force him to log in with the new password.
		session.invalidate();
		return "redirect:/login";
    }
}
