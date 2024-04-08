package br.com.projects.seriesexplorers.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.projects.seriesexplorers.model.Category;
import br.com.projects.seriesexplorers.model.Episode;
import br.com.projects.seriesexplorers.model.Serie;

public interface SerieRepository extends JpaRepository<Serie, Long>{

    Optional<Serie> findByTitleContainingIgnoreCase(String serieName);

    List<Serie> findByActorsContainingIgnoreCaseAndRatingGreaterThanEqual(String actorName, Double rating);

    List<Serie> findTop5ByOrderByRatingDesc();

    List<Serie> findByGenre(Category category);

    List<Serie> findByTotalSeasonsLessThanEqualAndRatingGreaterThanEqual(int totalSeasons, double rating);

    // @Query(value = "SELECT * FROM t_serie WHERE t_serie.total_seasons <= 4 and t_serie.rating >= 7", nativeQuery = true)
    @Query("SELECT s FROM Serie s WHERE s.totalSeasons <= :totalSeasons and s.rating >= :rating")
    List<Serie> findBySeasonsAndRating(int totalSeasons, double rating);

    @Query("SELECT e FROM Serie s JOIN s.episodes e WHERE e.title ILIKE %:segment%")
    List<Episode> episodesBySegment(String segment);

    @Query("SELECT e FROM Serie s JOIN s.episodes e WHERE s = :serie ORDER BY e.rating DESC LIMIT 5")
    List<Episode> topEpisodesBySerie(Serie serie);

    @Query("SELECT e FROM Serie s JOIN s.episodes e WHERE s = :serie AND YEAR(e.releaseDate) >= :releaseYear")
    List<Episode> episodesBySerieAndYear(Serie serie, int releaseYear);

    // List<Serie> findTop5ByOrderByEpisodesReleaseDateDesc();
    @Query("SELECT s FROM Serie s JOIN s.episodes e GROUP BY s ORDER BY MAX(e.releaseDate) DESC LIMIT 5")
    List<Serie> lastReleases();

}
