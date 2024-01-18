package br.com.projects.seriesexplorers.main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import br.com.projects.seriesexplorers.dto.EpisodeDTO;
import br.com.projects.seriesexplorers.dto.SeasonDTO;
import br.com.projects.seriesexplorers.dto.SerieDTO;
import br.com.projects.seriesexplorers.model.Episode;
import br.com.projects.seriesexplorers.service.ConsumeAPI;
import br.com.projects.seriesexplorers.service.DataConverter;
import br.com.projects.seriesexplorers.utils.Params;

public class Main {

    private Scanner sc = new Scanner(System.in);
    private ConsumeAPI consume = new ConsumeAPI();
    private DataConverter converter = new DataConverter();

    private static final String BASE_URL = "https://www.omdbapi.com/?t=";
    private static final String API_KEY = Params.apiKey;

    public void displayMenu() {

        System.out.println("Digite o nome da série: ");
        var title = sc.nextLine();

        var serieJson = consume.getApiData(BASE_URL + title.replace(" ", "+") + API_KEY);

        SerieDTO serieData = converter.getData(serieJson, SerieDTO.class);

        System.out.println();
        System.out.println(serieData);

        List<SeasonDTO> seasons = new ArrayList<>();

        for (int i = 1; i <= serieData.serieSeasons(); i++) {
            var seasonJson = consume.getApiData(BASE_URL + title.replace(" ", "+") + "&season=" + i + API_KEY);
            SeasonDTO seasonData = converter.getData(seasonJson, SeasonDTO.class);
            seasons.add(seasonData);
        }

        System.out.println();
        seasons.forEach(System.out::println);
        System.out.println();

        // for (int i = 0; i < serieData.serieSeasons(); i++) {
        //     List<EpisodeDTO> episodes = seasons.get(i).seasonEpisodes();
        //     for (int j = 0; j < episodes.size(); j++) {
        //         System.out.println(episodes.get(j).episodeTitle());
        //     }
        //     System.out.println();
        // }

        seasons.forEach(s -> s.seasonEpisodes().forEach(e -> System.out.println(e.episodeTitle())));


        List<EpisodeDTO> allSeasonEpisodes = seasons.stream()
            .flatMap(s -> s.seasonEpisodes().stream())
            .collect(Collectors.toList());

        System.out.println("\nTop 10 episódios:");
        allSeasonEpisodes.stream()
            .filter(e -> !e.episodeRating().equalsIgnoreCase("N/A"))
            // .peek(e -> System.out.println("Filter (N/A): " + e))
            .sorted(Comparator.comparing(EpisodeDTO::episodeRating).reversed())
            // .peek(e -> System.out.println("Order: " + e))
            .limit(10)
            // .peek(e -> System.out.println("Limit: " + e))
            .map(e -> e.episodeTitle().toUpperCase())
            // .peek(e -> System.out.println("Map toUpperCase: " + e))
            .forEach(System.out::println);


        List<Episode> episodes = seasons.stream()
            .flatMap(s -> s.seasonEpisodes().stream()
                .map(dto -> new Episode(s.seasonNumber(), dto))
            ).collect(Collectors.toList());

        episodes.forEach(System.out::println);

        // BUSCA POR FILTRO
        System.out.println("Digite o título ou um fragmento de títula para realizar a busca: ");
        var searchInput = sc.nextLine();
        Optional<Episode> searchEpisode = episodes.stream()
            .filter(e -> e.getTitle().toUpperCase().contains(searchInput.toUpperCase()))
            .findFirst();
        
        if(searchEpisode.isPresent()) {
            System.out.println("Episódio encontrado!");
            System.out.println("Temporada: " + searchEpisode.get().getSeason() + ", episoódio " + searchEpisode.get().getTitle());
        } else {
            System.out.println("Episódio não encontrado");
        }

        // BUSCA POR ANO
        System.out.println("A partir de que ano você deseja ver os episódios?");
        var year = sc.nextInt();
        sc.nextLine();

        LocalDate inputDate = LocalDate.of(year, 1, 1);

        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        episodes.stream()
            .filter(e -> e.getReleaseDate() != null && e.getReleaseDate().isAfter(inputDate))
            .forEach(e -> System.out.println(
                "Temporada: " + e.getSeason() +
                "Episódio: " + e.getTitle() +
                "Data de Lançamento: " + e.getReleaseDate().format(df)
            ));
    }
}
