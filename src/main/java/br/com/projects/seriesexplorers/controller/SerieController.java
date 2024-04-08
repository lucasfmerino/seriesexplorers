package br.com.projects.seriesexplorers.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projects.seriesexplorers.dto.EpisodeResponseDTO;
import br.com.projects.seriesexplorers.dto.SerieResponseDTO;
import br.com.projects.seriesexplorers.service.SerieService;

@RestController
@RequestMapping("/series")
public class SerieController {

    @Autowired
    private SerieService service;

    @GetMapping
    public List<SerieResponseDTO> getSeries() {
        return service.getAllSeries();
    }

    @GetMapping("/top5")
    public List<SerieResponseDTO> getTopSeries() {
        return service.getTop5Series();
    }

    @GetMapping("/lancamentos")
    public List<SerieResponseDTO> getReleases() {
        return service.getReleases();
    }

    @GetMapping("/{id}")
    public SerieResponseDTO getSerie(@PathVariable Long id) {
        return service.getSerieById(id);
    }

    @GetMapping("/{id}/temporadas/todas")
    public List<EpisodeResponseDTO> getAllSeasons(@PathVariable Long id) {
        return service.getAllSeasons(id);
    }

    @GetMapping("/{id}/temporadas/{number}")
    public List<EpisodeResponseDTO> getSeasonByNumber(@PathVariable Long id, @PathVariable Long number) {
        return service.getSeasonByNumber(id, number);
    }
}
