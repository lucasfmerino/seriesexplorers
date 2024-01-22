package br.com.projects.seriesexplorers.model;

import java.util.OptionalDouble;

import br.com.projects.seriesexplorers.dto.SerieDTO;

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
        this.plot = serieData.seriePlot();
    }
}
