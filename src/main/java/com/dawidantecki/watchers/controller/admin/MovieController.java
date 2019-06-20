package com.dawidantecki.watchers.controller.admin;

import com.dawidantecki.watchers.data.entity.Movie;
import com.dawidantecki.watchers.data.service.MovieService;
import com.dawidantecki.watchers.util.DateParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class MovieController {

    private MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @RequestMapping(value = "/addMovie", method = RequestMethod.GET)
    public String create() {
        return "admin/movies/create";
    }

    @RequestMapping(value = "/addMovie", method = RequestMethod.POST)
    public String create(@RequestParam("title") String title, @RequestParam("description") String description,
                         @RequestParam("release_date") String release_date, @RequestParam("country") String country,
                         @RequestParam("category") String category, Model model) {
        Movie foundMovie = movieService.findMovie(title);
        if (foundMovie != null) {
            model.addAttribute("msgError", "Movie already exists");
            return "admin/movies/create";
        }

        if (title == null || title.trim().length() == 0) {
            model.addAttribute("msgError", "Wrong title");
            return "admin/movies/create";
        }

        Date release = DateParser.parseDate(release_date);
        Movie movie = new Movie(title, description, release, country, category);

        movieService.addMovie(movie);
        model.addAttribute("msgSuccess", "Movie created successfully.");

        return "admin/movies/create";
    }

    @RequestMapping(value = "/deleteMovie", method = RequestMethod.GET)
    public String delete(Model model) {
        List<Movie> movies = movieService.allMovies();
        model.addAttribute("movies", movies);

        return "admin/movies/delete";
    }

    @RequestMapping(value = "/deleteMovie", method = RequestMethod.POST)
    public String delete(@RequestParam("title") String title, Model model) {
        Movie movie = movieService.findMovie(title);
        if (movie == null) {
            model.addAttribute("msgError", "Movie not found");
        } else {
            movieService.deleteMovie(movie);
            model.addAttribute("msgSuccess", "Movie removed");
        }

        return "admin/movies/delete";
    }

    @RequestMapping(value = "/deleteMovie/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable("id") Long id, Model model) {
        Movie movie = movieService.findMovie(id);
        if (movie == null) {
            model.addAttribute("msgError", "Movie not found");
        } else {
            movieService.deleteMovie(movie);
            model.addAttribute("msgSuccess", "Movie removed");
        }

        return "admin/movies/delete";
    }

    @RequestMapping(value = "/readMovie", method = RequestMethod.GET)
    public String read(Model model) {
        List<Movie> movies = movieService.allMovies();
        model.addAttribute("movies", movies);

        return "admin/movies/read";
    }

    @RequestMapping(value = "/updateMovie", method = RequestMethod.GET)
    public String update(Model model) {
        List<Movie> movies = movieService.allMovies();
        model.addAttribute("movies", movies);

        return "admin/movies/update";
    }

    @RequestMapping(value = "/updateMovie/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") Long id, Model model) {
        Movie movie = movieService.findMovie(id);
        if (movie == null) {
            model.addAttribute("msgError", "No movie found");
        }

        model.addAttribute("movie", movie);

        return "admin/movies/updateMovie";
    }

    @RequestMapping(value = "/updateMovie", method = RequestMethod.POST)
    public String updateMovie(@RequestParam("id") String id, @RequestParam("title") String title,
                              @RequestParam("description") String description, @RequestParam("release_date") String release_date,
                              @RequestParam("country") String country, @RequestParam("category") String category,
                              Model model) {
        Movie movie = movieService.findMovie(Long.parseLong(id));
        if (movie == null) {
            model.addAttribute("msgError", "Movie not found");
            return "admin/movies/update";
        }

        movie.setTitle(title);
        movie.setDescription(description);
        movie.setRelease_date(DateParser.parseDate(release_date));
        movie.setCountry(country);
        movie.setCategory(category);

        movieService.addMovie(movie);
        model.addAttribute("msgSuccess", "Updated successfully");

        return "admin/movies/updateMovie";
    }
}
