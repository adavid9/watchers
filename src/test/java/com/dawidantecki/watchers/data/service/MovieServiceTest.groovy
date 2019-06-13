package com.dawidantecki.watchers.data.service

import com.dawidantecki.watchers.configuration.DatabaseConnectionTest
import com.dawidantecki.watchers.data.entity.Movie
import com.dawidantecki.watchers.data.repository.MovieRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class MovieServiceTest extends DatabaseConnectionTest {

    @Autowired
    private MovieService movieService
    @Autowired
    private MovieRepository movieRepository

    def cleanup() {
        movieRepository.deleteAll()
    }

    def "should add movie and save it in the database"() {
        when:
        Movie movie = new Movie("Movie")
        and:
        movieService.addMovie(movie)
        then:
        movie == movieService.findMovie(movie.title)
    }

    def "should delete movie if exists"() {
        when:
        Movie movie = new Movie("Movie")
        and:
        movieService.addMovie(movie)
        then:
        movie == movieService.findMovie(movie.title)
        when:
        movieService.deleteMovie(movie)
        then:
        !movieService.findMovie(movie.title)
    }

    def "should save collection of movies"() {
        given:
        List<Movie> movies = []
        when:
        movies.add(new Movie("title"))
        movies.add(new Movie("title2"))
        and:
        movieService.addMovie(movies)
        then:
        movieService.allMovies().size() == movies.size()
    }

    def "should not add a null movie"() {
        when:
        Movie movie = null
        and:
        movieService.addMovie(movie)
        then:
        movieService.allMovies().isEmpty()
    }

    def "should not add null collection of movies"() {
        given:
        List<Movie> movies = []
        when:
        movies.add(null)
        movies.add(null)
        and:
        movieService.addMovie(movies)
        then:
        movieService.allMovies().isEmpty()
    }

}
