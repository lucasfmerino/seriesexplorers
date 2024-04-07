package br.com.projects.seriesexplorers.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projects.seriesexplorers.dto.SerieResponseDTO;
import br.com.projects.seriesexplorers.repository.SerieRepository;

@RestController
public class SerieController {

    @Autowired
    private SerieRepository serieRepository;

    @GetMapping("/series")
    public List<SerieResponseDTO> getSeries() {
        return serieRepository.findAll()
                .stream()
                .map(s -> new SerieResponseDTO(
                    s.getId(),
                    s.getTitle(),
                    s.getTotalSeasons(),
                    s.getRating(),
                    s.getGenre(),
                    s.getActors(),
                    s.getPoster(),
                    s.getPlot()))
                .collect(Collectors.toList());
    }
}
