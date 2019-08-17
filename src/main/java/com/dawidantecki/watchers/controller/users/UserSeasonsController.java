package com.dawidantecki.watchers.controller.users;

import java.util.ArrayList;
import java.util.List;

import com.dawidantecki.watchers.data.entity.Episode;
import com.dawidantecki.watchers.data.entity.Season;
import com.dawidantecki.watchers.data.entity.Series;
import com.dawidantecki.watchers.data.entity.User;
import com.dawidantecki.watchers.data.service.SeasonService;
import com.dawidantecki.watchers.data.service.SecurityService;
import com.dawidantecki.watchers.data.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserSeasonsController {

	@Autowired
	private SecurityService securityService;

	@Autowired
	private SeasonService seasonService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/seasons-list", method = RequestMethod.GET)
	public String seasonsList(Model model) {
		User user = userService.findByUsername(securityService.findLoggedInUsername());

		List<Series> series = user.getUserSeries();
		List<Season> seasons = new ArrayList<>();
		series.forEach((s) -> seasons.addAll(s.getSeasons()));

		user.getUserSeasons().forEach(seasons::remove);

		model.addAttribute("seasons", seasons);
		return "users/seasons/seasons-list";
	}

	@RequestMapping(value = "/seasons-list/{id}", method = RequestMethod.POST)
	public String seasonList(@PathVariable Long id, Model model) {
		User user = userService.findByUsername(securityService.findLoggedInUsername());
		Season season = seasonService.findById(id);

		user.getUserSeasons().add(season);
		userService.addUser(user);

		model.addAttribute("msgSuccess", "Season successfully added.");
		return "users/seasons/seasons-list";
	}

	@RequestMapping(value = "/seasons", method = RequestMethod.GET)
	public String userSeasons(Model model) {
		User user = userService.findByUsername(securityService.findLoggedInUsername());

		List<Season> seasons = user.getUserSeasons();
		model.addAttribute("seasons", seasons);

		return "users/seasons/userSeasons";
	}

	@RequestMapping(value = "/seasons/{id}", method = RequestMethod.POST)
	public String userSeasonsDelete(@PathVariable Long id, Model model) {
		User user = userService.findByUsername(securityService.findLoggedInUsername());
		Season season = seasonService.findById(id);

		user.getUserSeasons().remove(season);
		List<Episode> episodes = user.getUserEpisodes();
		episodes.removeIf((episode) -> episode.getSeason().equals(season));
		user.setUserEpisodes(episodes);

		userService.addUser(user);

		model.addAttribute("msgSuccess", "Season successfully removed.");
		return "users/seasons/userSeasons";
	}
}
