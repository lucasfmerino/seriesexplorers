package br.com.projects.seriesexplorers.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SeasonDTO(
    @JsonAlias("Season") Integer seasonNumber,
    @JsonAlias("Episodes") List<EpisodeDTO> seasonEpisodes) {
}
