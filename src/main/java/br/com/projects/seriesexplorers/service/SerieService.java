package br.com.projects.seriesexplorers.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projects.seriesexplorers.dto.SerieResponseDTO;
import br.com.projects.seriesexplorers.repository.SerieRepository;

@Service
public class SerieService {

    @Autowired
    private SerieRepository serieRepository;
    
    public List<SerieResponseDTO> getAllSeries() {
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
