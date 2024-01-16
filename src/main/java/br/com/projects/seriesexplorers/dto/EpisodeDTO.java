package br.com.projects.seriesexplorers.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EpisodeDTO(
    @JsonAlias("Title") String episodeTitle,
    @JsonAlias("Episode") Integer episodeNumber,
    @JsonAlias("imdbRating") String episodeRating,
    @JsonAlias("Released") String episodeReleaseDate) {
}
