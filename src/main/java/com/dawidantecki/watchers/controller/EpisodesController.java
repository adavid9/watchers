package com.dawidantecki.watchers.controller;

import com.dawidantecki.watchers.data.entity.Episode;
import com.dawidantecki.watchers.data.entity.Season;
import com.dawidantecki.watchers.data.entity.Series;
import com.dawidantecki.watchers.data.service.EpisodeService;
import com.dawidantecki.watchers.data.service.SeasonService;
import com.dawidantecki.watchers.util.DateParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class EpisodesController {

    private EpisodeService episodeService;
    private SeasonService seasonService;

    @Autowired
    public EpisodesController(EpisodeService episodeService, SeasonService seasonService) {
        this.episodeService = episodeService;
        this.seasonService = seasonService;
    }

    @RequestMapping(value = "/addEpisode", method = RequestMethod.GET)
    public String create() {
        return "admin/episodes/create";
    }

    @RequestMapping(value = "/addEpisode", method = RequestMethod.POST)
    public String create(@RequestParam("title") String title, @RequestParam("description") String description,
                         @RequestParam("release_date") String release_date, @RequestParam("seasonName") String seasonName,
                         Model model) {
        Episode foundEpisode = episodeService.findByTitle(title);
        if (foundEpisode != null) {
            model.addAttribute("msgError", "Episode already exists");
            return "admin/episodes/create";
        }

        if (title == null || title.trim().length() == 0) {
            model.addAttribute("msgError", "Wrong title");
            return "admin/episodes/create";
        }

        Date date = null;
        if (release_date != null)
            date = DateParser.parseDate(release_date);

        Season season = null;
        if (seasonName != null) {
            season = seasonService.findByName(seasonName);
        }

        if (season == null) {
            model.addAttribute("msgError", "Season not found");
            return "admin/episodes/create";
        }

        Episode episode = new Episode(title, description, date, season);
        episodeService.addEpisode(episode);
        model.addAttribute("msgSuccess", "Episode successfully created");

        return "admin/episodes/create";
    }

    @RequestMapping(value = "/deleteEpisode", method = RequestMethod.GET)
    public String delete(Model model) {
        model.addAttribute("episodes", episodeService.findAll());

        return "admin/episodes/delete";
    }

    @RequestMapping(value = "/deleteEpisode", method = RequestMethod.POST)
    public String delete(@RequestParam("title") String title, Model model) {
        Episode episode = episodeService.findByTitle(title);
        if (episode == null) {
            model.addAttribute("msgError", "Episode not found");
        } else {
            episodeService.deleteEpisodeById(episode.getId());
            model.addAttribute("msgSuccess", "Episode removed");
        }

        model.addAttribute("episodes", episodeService.findAll());
        return "admin/episodes/delete";
    }

    @RequestMapping(value = "/deleteEpisode/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable("id") Long id, Model model) {
        Episode episode = episodeService.findById(id);
        if (episode == null) {
            model.addAttribute("msgError", "Episode not found");
        } else {
            episodeService.deleteEpisodeById(episode.getId());
            model.addAttribute("msgSuccess", "Episode removed");
        }

        model.addAttribute("episodes", episodeService.findAll());
        return "admin/episodes/delete";
    }

    @RequestMapping(value = "/readEpisode", method = RequestMethod.GET)
    public String read(Model model) {
        model.addAttribute("episodes", episodeService.findAll());
        return "admin/episodes/read";
    }

    @RequestMapping(value = "/readEpisode/{id}", method = RequestMethod.POST)
    public String read(@PathVariable("id") Long id, Model model) {
        Episode episode = episodeService.findById(id);
        if (episode == null) {
            model.addAttribute("msgError", "Episode not found");
        } else {
            Season season = episode.getSeason();
            Series series = null;
            if (season != null)
                series = season.getSeries();

            model.addAttribute("episode", episode);
            model.addAttribute("season", season);
            model.addAttribute("series", series);
        }

        return "admin/episodes/readOne";
    }
}
