package com.dawidantecki.watchers.controller;

import com.dawidantecki.watchers.data.entity.Role;
import com.dawidantecki.watchers.data.entity.User;
import com.dawidantecki.watchers.data.entity.enums.RoleName;
import com.dawidantecki.watchers.data.service.RoleService;
import com.dawidantecki.watchers.data.service.SecurityService;
import com.dawidantecki.watchers.data.service.UserService;
import com.dawidantecki.watchers.validators.CustomFieldValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private UserService userService;
    private SecurityService securityService;
    private RoleService roleService;

    @Autowired
    public UserController(UserService userService, SecurityService securityService, RoleService roleService) {
        this.userService = userService;
        this.securityService = securityService;
        this.roleService = roleService;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration() {
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@RequestParam("username") String username, @RequestParam("email") String email,
                               @RequestParam("question") String question, @RequestParam("answer") String answer,
                               @RequestParam("password") String password, @RequestParam("confirmPassword") String confirmPassword,
                               @RequestParam("roleName") String roleName, Model model) {

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

        if (answer.equals("")) {
        	model.addAttribute("msgError", "You need to specify an answer.");
        	return "registration";
		}

        Role role;
        if (roleName != null) {
            role = roleService.findByName(RoleName.valueOf(roleName.toUpperCase()));
        } else {
			role = new Role(RoleName.valueOf(roleName.toUpperCase()));
		}

        if (!CustomFieldValidator.isEmailValid(email)) {
            model.addAttribute("msgError", "Email is invalid");
            return "registration";
        }

        User user = new User(username, email, question, answer,
				password, confirmPassword);
        user.getRoles().add(role);

        userService.addUser(user);
        securityService.autoLogin(username, password);


        // for now every registered user is (ROLE_USER) based so no need to determine if an admin is registered
        return "redirect:/userStart";
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
