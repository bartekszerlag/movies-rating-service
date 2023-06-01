package pl.bartekszerlag.moviesratingservice.infrastructure;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.bartekszerlag.moviesratingservice.domain.MovieDto;
import pl.bartekszerlag.moviesratingservice.domain.MovieNotFoundException;

@Service
class OmdbService {

    private final Logger logger = LoggerFactory.getLogger(OmdbService.class);
    private final RestTemplate restTemplate;

    @Value("${omdb.uri}")
    private String omdbUri;

    @Value("${omdb.key}")
    private String omdbKey;

    OmdbService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    MovieDto getMovieData(String title) {
        String url = String.format("%s?apikey=%s&t=%s", omdbUri, omdbKey, title);
        String response = restTemplate.getForObject(url, String.class);
        Double rating = null;
        String votes = null;

        try {
            String validResponse = new ObjectMapper().readTree(response).path("Response").asText();

            if (validResponse.equals("True")) {
                rating = new ObjectMapper().readTree(response).path("imdbRating").asDouble();
                votes = new ObjectMapper().readTree(response).path("imdbVotes").asText();
            } else {
                throw new MovieNotFoundException("Not found movie: " + title);
            }
        } catch (JsonProcessingException e) {
            logger.info("--- Fetching series rating failed ---");
        }

        return new MovieDto(rating, votes);
    }
}