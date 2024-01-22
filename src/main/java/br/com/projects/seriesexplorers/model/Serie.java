package br.com.projects.seriesexplorers.model;

import java.util.OptionalDouble;

import br.com.projects.seriesexplorers.dto.SerieDTO;
// import br.com.projects.seriesexplorers.service.IntegrationGPT;

public class Serie {
    
    private String title;
    private Integer totalSeasons;
    private Double rating;
    private Category genre;
    private String actors;
    private String poster;
    private String plot;

    public Serie(SerieDTO serieData) {
        this.title = serieData.serieTitle();
        this.totalSeasons = serieData.serieSeasons();
        this.rating = OptionalDouble.of(Double.valueOf(serieData.serieRating())).orElse(0);
        this.genre = Category.fromPortuguese(serieData.serieGenre().split(",")[0].trim());
        this.actors = serieData.serieActors();
        this.poster = serieData.seriePoster();
        // this.plot = IntegrationGPT.translate(serieData.seriePlot()).trim();
        this.plot = serieData.seriePlot();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getTotalSeasons() {
        return totalSeasons;
    }

    public void setTotalSeasons(Integer totalSeasons) {
        this.totalSeasons = totalSeasons;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Category getGenre() {
        return genre;
    }

    public void setGenre(Category genre) {
        this.genre = genre;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    @Override
    public String toString() {
        return "Genre: " + genre + '\n' +
        "Title: " + title + '\n' +
        "Total Seasons: " + totalSeasons + '\n' +
        "Rating: " + rating + '\n' +
        "Actors: " + actors + '\n' +
        "Poster: " + poster + '\n' +
        "Plot: " + plot + '\n';
    }
}