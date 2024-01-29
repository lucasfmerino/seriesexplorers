package br.com.projects.seriesexplorers.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projects.seriesexplorers.model.Category;
import br.com.projects.seriesexplorers.model.Serie;

public interface SerieRepository extends JpaRepository<Serie, Long>{

    Optional<Serie> findByTitleContainingIgnoreCase(String serieName);

    List<Serie> findByActorsContainingIgnoreCaseAndRatingGreaterThanEqual(String actorName, Double rating);

    List<Serie> findTop5ByOrderByRatingDesc();

    List<Serie> findByGenre(Category category);
}
