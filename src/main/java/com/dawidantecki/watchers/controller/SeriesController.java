package com.dawidantecki.watchers.controller;

import com.dawidantecki.watchers.data.entity.Episode;
import com.dawidantecki.watchers.data.entity.Season;
import com.dawidantecki.watchers.data.entity.Series;
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
public class SeriesController {

    private SeriesService seriesService;

    public SeriesController(SeriesService seriesService) {
        this.seriesService = seriesService;
    }

    @RequestMapping(value = "/addSeries", method = RequestMethod.GET)
    public String create() {
        return "admin/series/create";
    }

    @RequestMapping(value = "/addSeries", method = RequestMethod.POST)
    public String create(@RequestParam("title") String title, @RequestParam("description") String description,
                         @RequestParam("country") String country, @RequestParam("director") String director,
                         @RequestParam("release_date") String release_date, Model model) {

        Series series = seriesService.findByTitle(title);
        if (series != null) {
            model.addAttribute("msgError", "Series already exists");
            return "admin/series/create";
        }

        if (title.equals("")) {
            model.addAttribute("msgError", "Wrong title");
            return "admin/series/create";
        }

        Series newSeries = new Series(title, description, country, director);
        if (release_date != null)
            newSeries.setRelease_date(DateParser.parseDate(release_date));

        seriesService.addSeries(newSeries);
        model.addAttribute("msgSuccess", "Successfully saved!");
        return "admin/series/create";
    }

    @RequestMapping(value = "/deleteSeries", method = RequestMethod.GET)
    public String delete(Model model) {
        List<Series> series = seriesService.findAll();
        model.addAttribute("series", series);
        return "admin/series/delete";
    }

    @RequestMapping(value = "/deleteSeries/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable("id") Long id, Model model) {
        Series foundSeries = seriesService.findById(id);
        if (foundSeries == null) {
            model.addAttribute("msg", "Series not found");
        } else {
            seriesService.deleteSeries(foundSeries);
            model.addAttribute("msg", "Series deleted successfully");
        }

        List<Series> series = seriesService.findAll();
        model.addAttribute("series", series);

        return "admin/series/delete";
    }

    @RequestMapping(value = "/deleteSeries", method = RequestMethod.POST)
    public String delete(@RequestParam("title") String title, Model model) {
        Series foundSeries = seriesService.findByTitle(title);

        if (foundSeries == null) {
            model.addAttribute("msgError", "Series not found");
        } else {
            seriesService.deleteSeries(foundSeries);
            model.addAttribute("msgSuccess", "Series deleted successfully");
        }

        List<Series> series = seriesService.findAll();
        model.addAttribute("series", series);

        return "admin/series/delete";
    }

    @RequestMapping(value = "/readSeries", method = RequestMethod.GET)
    public String read(Model model) {
        model.addAttribute("series", seriesService.findAll());
        return "admin/series/read";
    }

    @RequestMapping(value = "/readSeries/{id}", method = RequestMethod.POST)
    public String read(@PathVariable("id") Long id, Model model) {
        Series foundSeries = seriesService.findById(id);
        if (foundSeries == null) {
            model.addAttribute("msgError", "There is no series");
        } else {
            List<Season> seasons = foundSeries.getSeasons();
            List<Episode> episodes = new LinkedList<>();
            if (seasons != null && seasons.size() > 0) {
                seasons.forEach(x -> episodes.addAll(x.getEpisodes()));
            }
            model.addAttribute("series", foundSeries);
            model.addAttribute("seasons", seasons);
            model.addAttribute("episodes", episodes);
        }

        return "admin/series/readOne";
    }

    @RequestMapping(value = "/updateSeries", method = RequestMethod.GET)
    public String update(Model model) {
        List<Series> series = seriesService.findAll();
        model.addAttribute("series", series);

        return "admin/series/update";
    }

    @RequestMapping(value = "/updateSeries/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") Long id, Model model) {
        Series series = seriesService.findById(id);
        if (series == null) {
            model.addAttribute("msgError", "No series found");
        }

        model.addAttribute("series", series);
        return "admin/series/updateSeries";
    }

    @RequestMapping(value = "/updateSeries", method = RequestMethod.POST)
    public String update(@RequestParam("id") String id, @RequestParam("title") String title,
                         @RequestParam("description") String description, @RequestParam("country") String country,
                         @RequestParam("director") String director, @RequestParam("release_date") String release_date,
                         Model model) {
        Series series = seriesService.findById(Long.parseLong(id));
        if (series == null) {
            model.addAttribute("msgError", "Series not found");
            return "admin/series/update";
        }

        series.setTitle(title);
        series.setDescription(description);
        series.setCountry(country);
        series.setDirector(director);
        series.setRelease_date(DateParser.parseDate(release_date));

        seriesService.addSeries(series);
        model.addAttribute("msgSuccess", "Successfully updated");

        return "admin/series/updateSeries";
    }

}
