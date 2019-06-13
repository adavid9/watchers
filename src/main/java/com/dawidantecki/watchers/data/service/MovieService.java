package com.dawidantecki.watchers.data.service;

import com.dawidantecki.watchers.data.entity.Movie;
import com.dawidantecki.watchers.data.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class MovieService {

    private MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public void addMovie(Movie movie) {
        if (movie == null)
            return;
        movieRepository.save(movie);
    }

    public void addMovie(Collection<Movie> movies) {
        if ((movies != null) && (movies.size() > 0)) {
            movies.forEach(x -> {
                if (x != null)
                    addMovie(x);
            });
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

    public void deleteMovie(String title) {
        Movie movie = movieRepository.findByTitle(title);
        if (movie == null)
            return;

        movieRepository.delete(movie);
    }

    public void deleteMovie(long id) {
        Movie movie = movieRepository.findById(id).orElse(null);
        if (movie == null)
            return;

        movieRepository.delete(movie);
    }

    public void deleteMovie(Movie movie) {
        if (movie == null)
            return;
        movieRepository.delete(movie);
    }
}
