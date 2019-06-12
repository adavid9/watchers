package com.dawidantecki.watchers.controller;

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

import java.util.List;

@Controller
public class SeriesController {

    private SeriesService seriesService;
    private SeasonService seasonService;

    public SeriesController(SeriesService seriesService, SeasonService seasonService) {
        this.seriesService = seriesService;
        this.seasonService = seasonService;
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
}
