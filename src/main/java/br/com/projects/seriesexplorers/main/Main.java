package br.com.projects.seriesexplorers.main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
// import java.util.stream.Collectors;
import java.util.stream.Collectors;

import br.com.projects.seriesexplorers.dto.SeasonDTO;
import br.com.projects.seriesexplorers.dto.SerieDTO;
import br.com.projects.seriesexplorers.model.Episode;
import br.com.projects.seriesexplorers.model.Serie;
import br.com.projects.seriesexplorers.repository.SerieRepository;
import br.com.projects.seriesexplorers.service.ConsumeAPI;
import br.com.projects.seriesexplorers.service.DataConverter;
import br.com.projects.seriesexplorers.utils.Params;

public class Main {

    private Scanner sc = new Scanner(System.in);
    private ConsumeAPI consume = new ConsumeAPI();
    private DataConverter converter = new DataConverter();

    private static final String BASE_URL = "https://www.omdbapi.com/?t=";
    private static final String API_KEY = Params.apiKey;
    // private List<SerieDTO> seriesData = new ArrayList<>();
    private SerieRepository serieRepository;
    private List<Serie> series = new ArrayList<>();

    public Main(SerieRepository serieRepository) {
        this.serieRepository = serieRepository;
    }

    public void displayMenu() {

        var menu = """

                1 - Buscar Séries para cadastro
                2 - Busca Episódios
                3 - Listar séries buscadas
                4 - Buscar série por título

                0 - Sair
                """;

        var option = "";
        while (!option.equals("0")) {

            System.out.println(menu);
            option = sc.nextLine();

            switch (option) {
                case "1":
                    searchWebSerie();
                    break;

                case "2":
                    searchEpisodeBySerie();
                    break;

                case "3":
                    listSearchedSeries();
                    break;

                case "4":
                    findSerieByTitle();
                    break;

                case "0":
                    System.out.println("Desligando...");
                    break;

                default:
                    System.out.println("Opção Inválida");
                    ;
            }
        }
    }

    private void searchWebSerie() {
        SerieDTO serieData = getSerieData();
        Serie serie = new Serie(serieData);
        // seriesData.add(serieData);
        serieRepository.save(serie);
        System.out.println(serieData);
    }

    private SerieDTO getSerieData() {
        System.out.println("Digite o nome da série: ");
        var serieTitle = sc.nextLine();
        var json = consume.getApiData(BASE_URL + serieTitle.replace(" ", "+") + API_KEY);
        SerieDTO serieData = converter.getData(json, SerieDTO.class);
        return serieData;
    }

    private void searchEpisodeBySerie() {
        listSearchedSeries();
        System.out.println("Digite o nome da série: ");
        var serieName = sc.nextLine();

        Optional<Serie> serie = series.stream()
                .filter(s -> s.getTitle().toLowerCase().contains(serieName.toLowerCase()))
                .findFirst();

        if (serie.isPresent()) {
            var serchedSerie = serie.get();
            List<SeasonDTO> seasons = new ArrayList<>();
            for (int i = 1; i <= serchedSerie.getTotalSeasons(); i++) {
                var json = consume
                        .getApiData(BASE_URL + serchedSerie.getTitle().replace(" ", "+") + "&season=" + i + API_KEY);
                SeasonDTO seasonData = converter.getData(json, SeasonDTO.class);
                seasons.add(seasonData);
            }
            seasons.forEach(System.out::println);

            List<Episode> episodes = seasons.stream()
                    .flatMap(d -> d.seasonEpisodes().stream()
                            .map(e -> new Episode(d.seasonNumber(), e)))
                    .collect(Collectors.toList());

            serchedSerie.setEpisodes(episodes);
            serieRepository.save(serchedSerie);
        } else {
            System.out.println("Série não encontrada.");
        }
    }

    // private void searchEpisodeBySerieBeckup() {
    // SerieDTO serieDTO = getSerieData();
    // List<SeasonDTO> seasons = new ArrayList<>();
    // for (int i = 1; i <= serieDTO.serieSeasons(); i++) {
    // var json = consume.getApiData(BASE_URL + serieDTO.serieTitle().replace(" ",
    // "+") + "&season=" + i + API_KEY);
    // SeasonDTO seasonData = converter.getData(json, SeasonDTO.class);
    // seasons.add(seasonData);
    // }
    // seasons.forEach(System.out::println);
    // }

    // private void listSearchedSeries() {
    // List<Serie> series = new ArrayList<>();
    // series = seriesData.stream()
    // .map(serieData -> new Serie(serieData))
    // .collect(Collectors.toList());
    // series.stream()
    // .sorted(Comparator.comparing(Serie::getGenre))
    // .forEach(System.out::println);
    // }

    private void listSearchedSeries() {
        series = serieRepository.findAll();
        series.stream()
                .sorted(Comparator.comparing(Serie::getGenre))
                .forEach(System.out::println);
    }

    private void findSerieByTitle() {
        System.out.println("Digite o nome da série: ");
        var serieTitle = sc.nextLine();
        Optional<Serie> serchedSerie = serieRepository.findByTitleContainingIgnoreCase(serieTitle);

        if (serchedSerie.isPresent()) {
            System.out.println("Dados da série: " + serchedSerie.get());
        } else {
            System.out.println("Série não encontrada");
        }
    }
}
