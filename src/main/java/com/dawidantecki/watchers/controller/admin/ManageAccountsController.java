package com.dawidantecki.watchers.controller.admin;

import com.dawidantecki.watchers.data.entity.User;
import com.dawidantecki.watchers.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class ManageAccountsController {

    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @RequestMapping(value = "/accounts", method = RequestMethod.GET)
    public String accounts(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.findByUsername(username);
        List<User> users = userService.findAll();
        if (user != null)
            users.remove(user); // do not display current logged in account
        model.addAttribute("users", users);

        return "admin/account/accounts";
    }

    @RequestMapping(value = "/deleteAccount/{id}", method = RequestMethod.GET)
    public String deleteAccountDetails(@PathVariable("id") Long id, Model model) {
        User account = userService.findById(id);
        if (account == null) {
            model.addAttribute("msgError", "Account not found.");
            return "admin/account/accounts";
        }

        model.addAttribute("account", account);
        return "admin/account/selected-account-delete";
    }

    @RequestMapping(value = "/deleteAccount/{id}", method = RequestMethod.POST)
    public String deleteAccount(@PathVariable("id") Long id, Model model) {
        User account = userService.findById(id);
        if (account == null) {
            model.addAttribute("msgError", "Account not found.");
            return "admin/account/accounts";
        }

        userService.deleteUser(account);
        return "redirect:/admin/accounts";
    }

    @RequestMapping(value = "/changeUserPassword/{id}", method = RequestMethod.GET)
    public String changeUserPassword(@PathVariable("id") Long id, Model model) {
        User account = userService.findById(id);
        if (account == null) {
            model.addAttribute("msgError", "Account not found.");
            return "admin/account/accounts";
        }

        model.addAttribute("account", account);
        return "admin/account/selected-account-password";
    }

    @RequestMapping(value = "/changeUserPassword", method = RequestMethod.POST)
    public String changeUserPassword(@RequestParam("id") String id, @RequestParam("password") String password,
                                     @RequestParam("confirmPassword") String confirmPassword, Model model) {
        User account = userService.findById(Long.parseLong(id));
        if (account == null) {
            model.addAttribute("msgError", "Account not found.");
            return "admin/account/accounts";
        } else {
            model.addAttribute("account", account);
        }

        if (password == null || password.trim().length() == 0) {
            model.addAttribute("msgError", "Password is incorrect.");
            return "admin/account/selected-account-password";
        }

        if (password.length() < 3) {
            model.addAttribute("msgError", "Password must have at least 3 characters.");
            return "admin/account/selected-account-password";
        }

        if (passwordEncoder.matches(password, account.getPassword())) {
            model.addAttribute("msgError", "Password can not be same as previous one.");
            return "admin/account/selected-account-password";
        }

        // validate if password is the same as confirmPassword
        if (!confirmPassword.equals(password)) {
            model.addAttribute("msgError", "Password cannot be different than confirm password.");
            return "admin/account/selected-account-password";
        }

        account.setPassword(password);
        userService.addUser(account);

        return "redirect:/admin/accounts";
    }
}
