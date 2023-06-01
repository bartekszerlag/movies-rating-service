package pl.bartekszerlag.moviesratingservice.infrastructure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.bartekszerlag.moviesratingservice.domain.Movie;
import pl.bartekszerlag.moviesratingservice.domain.MovieDto;

class MovieServiceTest {

    private MovieService movieService;
    private OmdbService omdbService;

    @BeforeEach
    public void setup() {
        omdbService = Mockito.mock(OmdbService.class);
        movieService = new MovieService(omdbService);
    }

    @Test
    public void shouldReturnMovie() {
        //given
        String title = "title";
        Mockito.when(omdbService.getMovieData(title)).thenReturn(new MovieDto(5.0, "1000"));

        //when
        Movie movie = movieService.fetchMovie(title);

        //then
        Assertions.assertEquals(title, movie.getTitle());
        Assertions.assertEquals(5.0, movie.getRating());
        Assertions.assertEquals("1000", movie.getVotes());
    }
}