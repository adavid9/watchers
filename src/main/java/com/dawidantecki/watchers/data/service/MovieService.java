package com.dawidantecki.watchers.data.service;

import com.dawidantecki.watchers.data.entity.Movie;
import com.dawidantecki.watchers.data.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(final MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public void addMovie(Movie movie) {
        if (movie == null)
            return;
        Movie m = movieRepository.findByTitle(movie.getTitle());
        if (m != null) {
            m.setTitle(movie.getTitle());
            m.setDescription(movie.getDescription());
            m.setRelease_date(movie.getRelease_date());
            m.setCountry(movie.getCountry());
            m.setCategory(movie.getCategory());
            m.setRate(movie.getRate());
            m.setUsers(movie.getUsers());
            movieRepository.save(m);
        } else {
            movieRepository.save(movie);
        }
    }

    public Movie findMovie(long id) {
        return movieRepository.findById(id).orElse(null);
    }

    public Movie findMovie(String title) {
        return movieRepository.findByTitle(title);
    }

    public List<Movie> allMovies() {
        return movieRepository.findAll();
    }

    public void deleteMovie(Movie movie) {
        if (movie == null)
            return;
        movieRepository.delete(movie);
    }
}
