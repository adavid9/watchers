package com.dawidantecki.watchers.controller;

import com.dawidantecki.watchers.data.entity.User;
import com.dawidantecki.watchers.data.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/forgot-password")
public class ForgotPasswordController {

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String forgotPassword() {
		return "users/account/forgot-password";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String forgotPassword(@RequestParam("username") String username, @RequestParam("question")
			String question, @RequestParam("answer") String answer, Model model) {
		User user = userService.findByUsername(username);
		if (user == null) {
			model.addAttribute("msgError", "User with username ["+ username +"] not found.");
			return "users/account/forgot-password";
		}
		boolean isValid = validateQuestionAnswer(user, question, answer);
		if (!isValid) {
			model.addAttribute("msgError", "Question or Answer does not match to user");
			return "users/account/forgot-password";
		} else {
			model.addAttribute("user", user);
			return "users/account/changePassword";
		}
	}

	private boolean validateQuestionAnswer(User user, String question, String answer) {
		return user.getQuestion().equals(question) && user.getAnswer().equals(answer);
	}
}
