package br.com.projects.seriesexplorers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projects.seriesexplorers.model.Serie;

public interface SerieRepository extends JpaRepository<Serie, Long>{
    
}
