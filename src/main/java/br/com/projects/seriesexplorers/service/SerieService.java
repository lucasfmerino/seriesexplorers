package br.com.projects.seriesexplorers.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projects.seriesexplorers.dto.EpisodeResponseDTO;
import br.com.projects.seriesexplorers.dto.SerieResponseDTO;
import br.com.projects.seriesexplorers.model.Serie;
import br.com.projects.seriesexplorers.repository.SerieRepository;

@Service
public class SerieService {

    @Autowired
    private SerieRepository serieRepository;

    public List<SerieResponseDTO> getAllSeries() {
        return dataConverter(serieRepository.findAll());
    }

    public List<SerieResponseDTO> getTop5Series() {
        return dataConverter(serieRepository.findTop5ByOrderByRatingDesc());
    }

    public List<SerieResponseDTO> getReleases() {
        return dataConverter(serieRepository.lastReleases());
    }

    public SerieResponseDTO getSerieById(Long id) {
        Optional<Serie> serie = serieRepository.findById(id);

        if (serie.isPresent()) {
            Serie s = serie.get();
            return new SerieResponseDTO(
                    s.getId(),
                    s.getTitle(),
                    s.getTotalSeasons(),
                    s.getRating(),
                    s.getGenre(),
                    s.getActors(),
                    s.getPoster(),
                    s.getPlot());
        }
        return null;
    }

    public List<EpisodeResponseDTO> getAllSeasons(Long id) {
        Optional<Serie> serie = serieRepository.findById(id);

        if (serie.isPresent()) {
            Serie s = serie.get();
            return s.getEpisodes().stream().map(e -> new EpisodeResponseDTO(
                    e.getSeason(), e.getNumber(), e.getTitle()))
                    .collect(Collectors.toList());
        }
        return null;
    }

    public List<EpisodeResponseDTO> getSeasonByNumber(Long id, Long number) {
        return serieRepository.getEpisodesBySeason(id, number).stream().map(e -> new EpisodeResponseDTO(
            e.getSeason(), e.getNumber(), e.getTitle()))
            .collect(Collectors.toList());
    }

    private List<SerieResponseDTO> dataConverter(List<Serie> series) {
        return series.stream()
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
