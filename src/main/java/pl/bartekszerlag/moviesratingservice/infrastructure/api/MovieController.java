package pl.bartekszerlag.moviesratingservice.infrastructure.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import pl.bartekszerlag.moviesratingservice.domain.Movie;
import pl.bartekszerlag.moviesratingservice.domain.MovieNotFoundException;
import pl.bartekszerlag.moviesratingservice.infrastructure.MovieService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
class MovieController {

    private final MovieService movieService;

    MovieController(MovieService service) {
        this.movieService = service;
    }

    @GetMapping("/rating/{title}")
    ResponseEntity<Movie> fetchMovie(@PathVariable String title) {
        try {
            return ResponseEntity.ok(movieService.fetchMovie(title));
        } catch (MovieNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}