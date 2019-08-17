package com.dawidantecki.watchers.controller.users;

import java.util.ArrayList;
import java.util.List;

import com.dawidantecki.watchers.data.entity.Episode;
import com.dawidantecki.watchers.data.entity.Season;
import com.dawidantecki.watchers.data.entity.User;
import com.dawidantecki.watchers.data.service.EpisodeService;
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
public class UserEpisodeController {

	@Autowired
	private SecurityService securityService;

	@Autowired
	private EpisodeService episodeService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/episodes-list", method = RequestMethod.GET)
	public String episodeList(Model model) {
		User user = userService.findByUsername(securityService.findLoggedInUsername());

		List<Season> seasons = user.getUserSeasons();
		List<Episode> episodes = new ArrayList<>();
		seasons.forEach((season) -> episodes.addAll(season.getEpisodes()));

		user.getUserEpisodes().forEach(episodes::remove);
		model.addAttribute("episodes", episodes);

		return "users/episodes/episode-list";
	}

	@RequestMapping(value = "/episodes-list/{id}", method = RequestMethod.POST)
	public String episodeList(@PathVariable Long id, Model model) {
		User user = userService.findByUsername(securityService.findLoggedInUsername());
		Episode episode = episodeService.findById(id);

		user.getUserEpisodes().add(episode);
		userService.addUser(user);

		model.addAttribute("msgSuccess", "Episode successfully added.");

		return "redirect:/user/episode-list";
	}

	@RequestMapping(value = "/episodes", method = RequestMethod.GET)
	public String userEpisodes(Model model) {
		User user = userService.findByUsername(securityService.findLoggedInUsername());

		List<Episode> episodes = user.getUserEpisodes();
		model.addAttribute("episodes", episodes);

		return "users/episodes/userEpisodes";
	}

	@RequestMapping(value = "/episodes/{id}", method = RequestMethod.POST)
	public String userEpisodes(@PathVariable Long id, Model model) {
		User user = userService.findByUsername(securityService.findLoggedInUsername());
		Episode episode = episodeService.findById(id);

		user.getUserEpisodes().remove(episode);
		userService.addUser(user);

		model.addAttribute("msgSuccess", "Episode successfully removed.");
		return "users/episodes/userEpisodes";
	}
}
