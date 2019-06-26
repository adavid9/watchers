package com.dawidantecki.watchers.controller.users;

import com.dawidantecki.watchers.data.entity.Movie;
import com.dawidantecki.watchers.data.entity.User;
import com.dawidantecki.watchers.data.service.MovieService;
import com.dawidantecki.watchers.data.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class UserMoviesController {

    private MovieService movieService;
    private UserService userService;

    public UserMoviesController(MovieService movieService, UserService userService) {
        this.movieService = movieService;
        this.userService = userService;
    }

    // Find all available movies that user can add to his movie list.
    @RequestMapping(value = "/availableMovies", method = RequestMethod.GET)
    public String availableMovies(Model model) {
        User user = getLoggedInUser();
        List<Movie> movies = movieService.allMovies();
        // do not display movies that user already added
        user.getUser_movies().forEach(movies::remove);
        model.addAttribute("movies", movies);

        return "users/movies/availableMovies";
    }

    @RequestMapping(value = "/availableMovies/{id}", method = RequestMethod.POST)
    public String availableMovies(@PathVariable("id") Long id, Model model) {
        Movie movie = movieService.findMovie(id);
        User user = getLoggedInUser();
        if (movie == null) {
            model.addAttribute("msgError", "Movie not found!");
        } else {
            movie.getUsers().add(user);
            movieService.addMovie(movie);
            model.addAttribute("msgSuccess", "Movie successfully added!");
        }

        return "users/movies/availableMovies";
    }

    @RequestMapping(value = "/myMoviesList", method = RequestMethod.GET)
    public String userMovies(Model model) {
        User user = getLoggedInUser();
        Set<Movie> movies = user.getUser_movies();

        model.addAttribute("movies", movies);
        return "users/movies/userMoviesList";
    }

    @RequestMapping(value = "/myMoviesList/{id}", method = RequestMethod.POST)
    public String deleteUserMovie(@PathVariable("id") Long id, Model model) {
        Movie movie = movieService.findMovie(id);
        User user = getLoggedInUser();
        if (movie == null) {
            model.addAttribute("msgError", "Movie not found!");
        } else {
            movie.getUsers().remove(user);
            movieService.addMovie(movie);
            model.addAttribute("msgSuccess", "Movie successfully deleted!");
        }

        return "users/movies/userMoviesList";
    }

    @RequestMapping(value = "/topMovies", method = RequestMethod.GET)
    public String top10Movies(Model model) {
        List<Movie> movies = movieService.allMovies().stream()
                .sorted(Comparator.comparingDouble(Movie::getRate).reversed())
                .limit(10)
                .collect(Collectors.toList());

        model.addAttribute("movies", movies);
        return "users/movies/topMovies";
    }

    // This method is used to find logged in user
    private User getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        return userService.findByUsername(username);
    }
}
