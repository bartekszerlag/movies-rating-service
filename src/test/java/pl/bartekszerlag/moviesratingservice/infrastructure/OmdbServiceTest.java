package pl.bartekszerlag.moviesratingservice.infrastructure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.client.RestTemplate;
import pl.bartekszerlag.moviesratingservice.domain.MovieDto;
import pl.bartekszerlag.moviesratingservice.domain.MovieNotFoundException;

class OmdbServiceTest {

    private OmdbService omdbService;
    private RestTemplate restTemplate;

    @BeforeEach
    public void setup() {
        restTemplate = Mockito.mock(RestTemplate.class);
        omdbService = new OmdbService(restTemplate);
    }

    @Test
    public void shouldReturnMovieRatingAndVotes() {
        //given
        String response = "{\"Response\": \"True\", \"imdbRating\": 5.0, \"imdbVotes\": \"1000\"}";
        Mockito.when(restTemplate.getForObject(Mockito.any(String.class), Mockito.any(Class.class))).thenReturn(response);

        //when
        MovieDto movieDto = omdbService.getMovieData("title");

        //then
        Assertions.assertEquals(5.0, movieDto.getRating());
        Assertions.assertEquals("1000", movieDto.getVotes());
    }

    @Test
    public void shouldThrowMovieNotFoundException() {
        //given
        String response = "{\"Response\": \"False\", \"imdbRating\": 5.0, \"imdbVotes\": \"1000\"}";
        Mockito.when(restTemplate.getForObject(Mockito.any(String.class), Mockito.any(Class.class))).thenReturn(response);

        //then
        Assertions.assertThrows(MovieNotFoundException.class, () -> omdbService.getMovieData("title"));
    }
}