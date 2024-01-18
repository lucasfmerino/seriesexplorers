package br.com.projects.seriesexplorers.main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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

        System.out.println("\nTop 5 episódios:");
        allSeasonEpisodes.stream()
            .filter(e -> !e.episodeRating().equalsIgnoreCase("N/A"))
            .sorted(Comparator.comparing(EpisodeDTO::episodeRating).reversed())
            .limit(5)
            .forEach(System.out::println);

        List<Episode> episodes = seasons.stream()
            .flatMap(s -> s.seasonEpisodes().stream()
                .map(dto -> new Episode(s.seasonNumber(), dto))
            ).collect(Collectors.toList());

        episodes.forEach(System.out::println);
    }
}
