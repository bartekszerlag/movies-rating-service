package pl.bartekszerlag.moviesratingservice.infrastructure.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.bartekszerlag.moviesratingservice.domain.Movie;
import pl.bartekszerlag.moviesratingservice.domain.MovieNotFoundException;
import pl.bartekszerlag.moviesratingservice.infrastructure.MovieService;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MovieService movieService;

    @Test
    public void shouldFetchMovie_statusCode200() throws Exception {
        String title = "title";
        when(movieService.fetchMovie(title)).thenReturn(new Movie("title", 5.0, "1000"));

        mockMvc
                .perform(MockMvcRequestBuilders.get("/rating/{title}", title))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void shouldNotFoundMovie_statusCode404() throws Exception {
        String title = "title";
        when(movieService.fetchMovie(title)).thenThrow(MovieNotFoundException.class);

        mockMvc
                .perform(MockMvcRequestBuilders.get("/rating/{title}", title))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}