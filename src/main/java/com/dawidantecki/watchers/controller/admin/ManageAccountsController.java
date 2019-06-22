package com.dawidantecki.watchers.controller.admin;

import com.dawidantecki.watchers.data.entity.User;
import com.dawidantecki.watchers.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class ManageAccountsController {

    @Autowired
    private UserService userService;

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
        return "admin/account/selected-account";
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
}
