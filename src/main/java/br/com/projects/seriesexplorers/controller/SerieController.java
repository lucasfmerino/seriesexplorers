package br.com.projects.seriesexplorers.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
