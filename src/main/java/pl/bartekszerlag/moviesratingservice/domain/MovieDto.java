package pl.bartekszerlag.moviesratingservice.domain;

public class MovieDto {
    private final Double rating;
    private final String votes;

    public MovieDto(Double rating, String votes) {
        this.rating = rating;
        this.votes = votes;
    }

    public Double getRating() {
        return rating;
    }

    public String getVotes() {
        return votes;
    }
}