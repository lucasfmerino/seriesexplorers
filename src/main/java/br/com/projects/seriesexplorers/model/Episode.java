package br.com.projects.seriesexplorers.model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import br.com.projects.seriesexplorers.dto.EpisodeDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "T_EPISODE")
public class Episode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer season;

    private String title;

    private Integer number;

    private Double rating;

    private LocalDate releaseDate;

    @ManyToOne
    private Serie serie;

    // public Episode(EpisodeDTO episodeDto) {
    //     this.season = Integer.parseInt(episodeDto.espisodeSeason());
    //     this.title = episodeDto.episodeTitle();
    //     this.number = episodeDto.episodeNumber();
    //     this.rating = Double.valueOf(episodeDto.episodeRating());
    //     this.releaseDate = LocalDate.parse(episodeDto.episodeReleaseDate());
    // }

    public Episode(Integer seasonNumber, EpisodeDTO episodeDto) {
        this.season = seasonNumber;
        this.title = episodeDto.episodeTitle();
        this.number = episodeDto.episodeNumber();
        try {
            this.rating = Double.valueOf(episodeDto.episodeRating());
        } catch (NumberFormatException e) {
            this.rating = 0.0;
        }
        try {
            this.releaseDate = LocalDate.parse(episodeDto.episodeReleaseDate());
        } catch(DateTimeParseException e) {
            this.releaseDate = null;
            // this.releaseDate = LocalDate.parse("2007-12-03");
        }

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    @Override
    public String toString() {
        return "Season: " + season +
                "\n Episode Number" + number +
                "\n Title: " + title +
                "\n Rating: " + rating +
                "\n Release Date: " + releaseDate +
                "\n";
    }

}
