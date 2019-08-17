package com.dawidantecki.watchers.controller.users;

import java.util.Iterator;
import java.util.List;

import com.dawidantecki.watchers.data.entity.Season;
import com.dawidantecki.watchers.data.entity.Series;
import com.dawidantecki.watchers.data.entity.User;
import com.dawidantecki.watchers.data.service.SecurityService;
import com.dawidantecki.watchers.data.service.SeriesService;
import com.dawidantecki.watchers.data.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserSeriesController {

	@Autowired
	private SecurityService securityService;

	@Autowired
	private SeriesService seriesService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/series-list", method = RequestMethod.GET)
	public String seriesList(Model model) {
		User user = userService.findByUsername(securityService.findLoggedInUsername());
		List<Series> series = seriesService.findAll();
		user.getUserSeries().forEach(series::remove);

		model.addAttribute("series", series);
		return "users/series/series-list";
	}

	@RequestMapping(value = "/series-list/{id}", method = RequestMethod.POST)
	public String seriesList(@PathVariable Long id, Model model) {
		User user = userService.findByUsername(securityService.findLoggedInUsername());
		Series series = seriesService.findById(id);

		user.getUserSeries().add(series);
		userService.addUser(user);
		model.addAttribute("msgSuccess", "Series successfully added.");

		return "redirect:/user/series-list";
	}

	@RequestMapping("/series")
	public String userSeries(Model model) {
		User user = userService.findByUsername(securityService.findLoggedInUsername());

		List<Series> userSeries = user.getUserSeries();
		model.addAttribute("series", userSeries);

		return "users/series/userSeries";
	}

	@RequestMapping(value = "/series/{id}", method = RequestMethod.POST)
	public String userSeriesDelete(@PathVariable Long id, Model model) {
		User user = userService.findByUsername(securityService.findLoggedInUsername());
		Series series = seriesService.findById(id);

		user.getUserSeries().remove(series);
		List<Season> seasons = user.getUserSeasons();
		seasons.removeIf(season -> season.getSeries().equals(series));

		user.setUserSeasons(seasons);

		userService.addUser(user);
		model.addAttribute("msgSuccess", "Series successfully removed.");

		return "users/series/userSeries";
	}
}
