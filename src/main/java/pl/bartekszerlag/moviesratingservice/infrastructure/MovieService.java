package pl.bartekszerlag.moviesratingservice.infrastructure;

import org.springframework.stereotype.Service;
import pl.bartekszerlag.moviesratingservice.domain.Movie;
import pl.bartekszerlag.moviesratingservice.domain.MovieDto;

@Service
public
class MovieService {

    private final OmdbService omdbService;

    public MovieService(OmdbService externalApiService) {
        this.omdbService = externalApiService;
    }

    public Movie fetchMovie(String title) {
        MovieDto movieDto = omdbService.getMovieData(title);

        return new Movie(title, movieDto.getRating(), movieDto.getVotes());
    }
}