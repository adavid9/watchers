package com.dawidantecki.watchers.data.service;

import com.dawidantecki.watchers.configuration.DatabaseConnectionTest;
import com.dawidantecki.watchers.data.entity.Movie;
import com.dawidantecki.watchers.data.repository.MovieRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MovieServiceTest extends DatabaseConnectionTest {

    @Autowired
    private MovieService movieService;
    @Autowired
    private MovieRepository movieRepository;

    private ArrayList<Movie> movies;

    @Before
    public void setUp() {
        movies = moviesList();
    }

    @After
    public void cleanUp() {
        movieRepository.deleteAll();
    }

    @Test
    public void should_save_movie() {
        Movie movie = movies.get(0);

        movieService.addMovie(movie);

        assertEquals(movie, movieService.findMovie(movie.getId()));
    }

    @Test
    public void should_save_movies() {
        movieService.addMovie(movies);
        movies.forEach(movie -> assertEquals(movie, movieService.findMovie(movie.getId())));
    }

    @Test
    public void should_not_thrown_exception_while_null_movie() {
        movieService.addMovie((Movie) null);
        assertEquals(0, movieService.allMovies().size());
    }

    @Test
    public void should_delete_movie() {
        Movie movie = movies.get(0);
        movieService.addMovie(movie);
        movieService.deleteMovie(movie);
        assertNull(movieService.findMovie(movie.getId()));
    }

    @Test
    public void should_delete_collection_of_movies() {
        movieService.addMovie(movies);
        movieService.deleteMovie(movies);
        movies.forEach(movie -> assertNull(movieService.findMovie(movie.getId())));
    }

    @Test
    public void should_delete_movie_by_id() {
        Movie movie = movies.get(0);
        movieService.addMovie(movie);
        movieService.deleteMovie(movie.getId());
        assertNull(movieService.findMovie(movie.getId()));
    }

    @Test
    public void should_delete_movie_by_title() {
        Movie movie = movies.get(0);
        movieService.addMovie(movie);
        movieService.deleteMovie(movie.getTitle());
        assertNull(movieService.findMovie(movie.getTitle()));
    }

    @Test
    public void should_be_only_one() {
        movies.add(new Movie("Sharks"));
        movies.add(new Movie("Sharks"));

        movieService.addMovie(movies);
        List<Movie> allMovies = movieService.allMovies();
        for (Movie m1 : allMovies) {
            int sameMovies = 0;
            for (Movie m2 : allMovies) {
                if (m1.getTitle().equals(m2.getTitle())) sameMovies++;
                if (sameMovies > 1) fail("Movie with already existing title cannot be saved");
            }
        }
    }

    @Test
    public void should_find_movie_by_id() {
        Movie movie = movies.get(0);
        movieService.addMovie(movie);
        assertEquals(movie, movieService.findMovie(movie.getId()));
    }

    @Test
    public void should_find_movie_by_title() {
        Movie movie = movies.get(0);
        movieService.addMovie(movie);
        assertEquals(movie, movieService.findMovie(movie.getTitle()));
    }

    private ArrayList<Movie> moviesList() {
        ArrayList<Movie> movies = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            movies.add(new Movie("title" + i));
        }
        return movies;
    }
}