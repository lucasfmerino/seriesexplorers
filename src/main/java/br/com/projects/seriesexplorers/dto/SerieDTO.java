package br.com.projects.seriesexplorers.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public record SerieDTO( @JsonAlias("Title") String serieTitle,
                        @JsonAlias("totalSeasons") Integer serieSeasons,
                        @JsonAlias("imdbRating") String serieRating) {

    // @JsonAlias somente ler
    // @JsonProperty ler e escrever
}
