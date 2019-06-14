package com.dawidantecki.watchers.controller;

import com.dawidantecki.watchers.data.entity.Episode;
import com.dawidantecki.watchers.data.entity.Season;
import com.dawidantecki.watchers.data.entity.Series;
import com.dawidantecki.watchers.data.service.SeasonService;
import com.dawidantecki.watchers.data.service.SeriesService;
import com.dawidantecki.watchers.util.DateParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedList;
import java.util.List;

@Controller
public class SeasonController {

    private SeasonService seasonService;
    private SeriesService seriesService;

    public SeasonController(SeasonService seasonService, SeriesService seriesService) {
        this.seasonService = seasonService;
        this.seriesService = seriesService;
    }

    @RequestMapping(value = "/addSeason", method = RequestMethod.GET)
    public String create() {
        return "admin/season/create";
    }

    @RequestMapping(value = "/addSeason", method = RequestMethod.POST)
    public String create(@RequestParam("name") String name, @RequestParam("release_date") String release_date,
                         @RequestParam("seriesTitle") String seriesTitle, Model model) {

        Season foundSeason = seasonService.findByName(name);
        if (foundSeason != null) {
            model.addAttribute("msgError", "Season already exists");
            return "admin/season/create";
        }

        if (name == null || name.trim().length() == 0) {
            model.addAttribute("msgError", "Incorrect season name");
            return "admin/season/create";
        }

        Season season = new Season(name);

        if (release_date != null) {
            season.setRelease_date(DateParser.parseDate(release_date));
        }

        Series series = null;
        if (seriesTitle != null) {
            series = seriesService.findByTitle(seriesTitle);
        }

        if (series != null) {
            season.setSeries(series);
        } else {
            model.addAttribute("msgError", "No series found");
            return "admin/season/create";
        }

        seasonService.addSeason(season);
        model.addAttribute("msgSuccess", "Successfully saved");
        return "admin/season/create";
    }

    @RequestMapping(value = "/deleteSeason", method = RequestMethod.GET)
    public String delete(Model model) {
        List<Season> seasons = seasonService.findAll();
        model.addAttribute("seasons", seasons);

        return "admin/season/delete";
    }

    @RequestMapping(value = "/deleteSeason", method = RequestMethod.POST)
    public String delete(@RequestParam("name") String name, Model model) {
        Season foundSeason = seasonService.findByName(name);
        if (foundSeason == null) {
            model.addAttribute("msgError", "There is no season");
        } else {
            seasonService.deleteSeason(foundSeason);
            model.addAttribute("msgSuccess", "Season has been deleted");
        }

        List<Season> seasons = seasonService.findAll();
        model.addAttribute("seasons", seasons);

        return "admin/season/delete";
    }

    @RequestMapping(value = "/deleteSeason/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable("id") Long id, Model model) {
        Season foundSeason = seasonService.findById(id);
        if (foundSeason == null) {
            model.addAttribute("msgError", "There is no season");
        } else {
            seasonService.deleteSeason(foundSeason);
            model.addAttribute("msgSuccess", "Season has been deleted");
        }

        model.addAttribute("seasons", seasonService.findAll());
        return "admin/season/delete";
    }

    @RequestMapping(value = "/readSeason", method = RequestMethod.GET)
    public String read(Model model) {
        model.addAttribute("seasons", seasonService.findAll());

        return "admin/season/read";
    }

    @RequestMapping(value = "/readSeason/{id}", method = RequestMethod.POST)
    public String read(@PathVariable("id") Long id, Model model) {
        Season foundSeason = seasonService.findById(id);
        if (foundSeason == null) {
            model.addAttribute("msgError", "Season not found");
        } else {
            List<Episode> episodes = new LinkedList<>();
            Series series = foundSeason.getSeries();
            foundSeason.getEpisodes().forEach(episodes::add);

            model.addAttribute("series", series);
            model.addAttribute("season", foundSeason);
            model.addAttribute("episodes", episodes);

        }
        return "admin/season/readOne";
    }

    @RequestMapping(value = "/updateSeason", method = RequestMethod.GET)
    public String update(Model model) {
        List<Season> seasons = seasonService.findAll();
        model.addAttribute("seasons", seasons);

        return "admin/season/update";
    }

    @RequestMapping(value = "/updateSeason/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") Long id, Model model) {
        Season season = seasonService.findById(id);
        if (season == null) {
            model.addAttribute("msgError", "Season not found");
            return "admin/season/update";
        }

        model.addAttribute("season", season);
        return "admin/season/updateSeason";
    }

    @RequestMapping(value = "/updateSeason", method = RequestMethod.POST)
    public String update(@RequestParam("id") String id, @RequestParam("name") String name,
                         @RequestParam("release_date") String release_date, Model model) {
        Season season = seasonService.findById(Long.parseLong(id));
        if (season == null) {
            model.addAttribute("msgError", "Season not found");
            return "admin/season/update";
        }

        season.setName(name);
        season.setRelease_date(DateParser.parseDate(release_date));
        seasonService.addSeason(season);

        model.addAttribute("msgSuccess", "Successfully updated");
        return "admin/season/updateSeason";
    }
}
