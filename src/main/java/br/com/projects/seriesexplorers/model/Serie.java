package br.com.projects.seriesexplorers.model;

import java.util.OptionalDouble;

import br.com.projects.seriesexplorers.dto.SerieDTO;
import jakarta.persistence.Column;
// import br.com.projects.seriesexplorers.service.IntegrationGPT;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "T_SERIE")
public class Serie {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String title;

    private Integer totalSeasons;

    private Double rating;

    @Enumerated(EnumType.STRING)
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
