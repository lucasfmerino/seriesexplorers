package br.com.projects.seriesexplorers.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)  // Ignorar dados não mapeados
public record SerieDTO( @JsonAlias("Title") String serieTitle,
                        @JsonAlias("totalSeasons") Integer serieSeasons,
                        @JsonAlias("imdbRating") String serieRating) {
}

// @JsonAlias somente ler
// @JsonProperty ler e escrever