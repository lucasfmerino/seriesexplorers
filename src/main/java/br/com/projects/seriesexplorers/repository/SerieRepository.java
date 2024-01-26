package br.com.projects.seriesexplorers.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projects.seriesexplorers.model.Serie;

public interface SerieRepository extends JpaRepository<Serie, Long>{
    Optional<Serie> findByTitleContainingIgnoreCase(String serieName);
}
