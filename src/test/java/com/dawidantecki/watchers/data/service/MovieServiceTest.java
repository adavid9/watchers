package com.dawidantecki.watchers.data.service;

import com.dawidantecki.watchers.data.entity.Movie;
import com.dawidantecki.watchers.data.repository.MovieRepository;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService;

    @Test
    public void when_find_movie_by_id_then_movie_should_be_returned() {
        final Optional<Movie> expected = Optional.of(Movie.builder()
                .title("Movie")
                .build()
        );
        when(movieRepository.findById(anyLong())).thenReturn(expected);

        final Movie actual = movieService.findMovie(1L);

        assertEquals(expected.get(), actual);
    }

    @Test
    public void when_find_movie_by_title_then_movie_should_be_returned() {
        final Movie expected = Movie.builder()
                .title("Movie")
                .build();
        when(movieRepository.findByTitle(anyString())).thenReturn(expected);

        final Movie actual = movieService.findMovie("Movie");

        assertEquals(expected, actual);
    }

    @Test
    public void when_find_all_movies_then_movies_should_be_returned() {
        final List<Movie> expected = Lists.newArrayList(
                Movie.builder()
                        .title("Movie_1")
                        .build(),
                Movie.builder()
                        .title("Movie_2")
                        .build()
        );
        when(movieRepository.findAll()).thenReturn(expected);

        final List<Movie> actual = movieService.allMovies();

        assertEquals(expected, actual);
    }

    @Test
    public void when_delete_movie_then_movie_should_be_deleted() {
        final Movie movie = Movie.builder()
                .title("Movie")
                .build();

        movieService.deleteMovie(movie);

        verify(movieRepository).delete(eq(movie));
    }

    @Test
    public void when_add_movie_then_movie_should_be_saved() {
        final Movie movie = Movie.builder()
                .title("Movie")
                .build();

        movieService.addMovie(movie);

        verify(movieRepository).save(eq(movie));
    }
}