package br.com.projects.seriesexplorers.dto;

import br.com.projects.seriesexplorers.model.Category;

public record SerieResponseDTO(
        Long id,
        String title,
        Integer totalSeasons,
        Double rating,
        Category genre,
        String actors,
        String poster,
        String plot) {
}
