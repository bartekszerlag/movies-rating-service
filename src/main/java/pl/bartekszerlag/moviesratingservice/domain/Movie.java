package pl.bartekszerlag.moviesratingservice.domain;

public class Movie {

    private final String title;
    private final Double rating;
    private final String votes;

    public Movie(String title, Double rating, String votes) {
        this.title = title;
        this.rating = rating;
        this.votes = votes;
    }

    public String getTitle() {
        return title;
    }

    public Double getRating() {
        return rating;
    }

    public String getVotes() {
        return votes;
    }
}