package br.com.projects.seriesexplorers.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.projects.seriesexplorers.dto.SeasonDTO;
import br.com.projects.seriesexplorers.dto.SerieDTO;
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

        var menu = """
                1 - Buscar Séries
                2 - Busca Episódios

                0 - Sair
                """;

        System.out.println(menu);
        var option = sc.nextInt();
        sc.nextLine();

        switch (option) {
            case 1:
                searchWebSerie();
                break;
        
            case 2:
                searchEpisodeBySerie();
                break;

            case 3:
                System.out.println("Desligando...");
                break;

            default:
                System.out.println("Opção Inválida");;
        }
    }

    private void searchWebSerie() {
        SerieDTO serieData = getSerieData();
        System.out.println(serieData);
    }

    private SerieDTO getSerieData() {
        System.out.println("Digite o nome da série: ");
        var serieTitle = sc.nextLine();
        var json = consume.getApiData(BASE_URL + serieTitle.replace(" ", "+") + API_KEY);
        SerieDTO serieData = converter.getData(json,  SerieDTO.class);
        return serieData;
    }

    private void searchEpisodeBySerie() {
        SerieDTO serieDTO = getSerieData();
        List<SeasonDTO> seasons = new ArrayList<>();
        for (int i = 1; i <= serieDTO.serieSeasons(); i++) {
            var json = consume.getApiData(BASE_URL + serieDTO.serieTitle().replace(" ", "+") + "&season=" + i + API_KEY);
            SeasonDTO seasonData = converter.getData(json, SeasonDTO.class);
            seasons.add(seasonData);
        }
        seasons.forEach(System.out::println);
    }
}
