package br.com.projects.seriesexplorers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.projects.seriesexplorers.dto.EpisodeDTO;
import br.com.projects.seriesexplorers.dto.SeasonDTO;
import br.com.projects.seriesexplorers.dto.SerieDTO;
import br.com.projects.seriesexplorers.service.ConsumeAPI;
import br.com.projects.seriesexplorers.service.DataConverter;
import br.com.projects.seriesexplorers.utils.Params;

@SpringBootApplication
public class SeriesexplorersApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SeriesexplorersApplication.class, args);
	}

	private static String apiKey = Params.apiKey;

	@Override
	public void run(String... args) throws Exception {

		var consume = new ConsumeAPI();
		DataConverter converter = new DataConverter();

		// Pegar dados da série
		var jsonSerie = consume.getApiData("https://www.omdbapi.com/?t=gilmore+girls&apikey=" + apiKey);

		SerieDTO serieData = converter.getData(jsonSerie, SerieDTO.class);

		System.out.println();
		System.out.println(serieData);

		// Pegar dados do episódio
		var jsonEpisode = consume
				.getApiData("https://www.omdbapi.com/?t=gilmore+girls&season=1&episode=2&apikey=" + apiKey);

		EpisodeDTO episodeData = converter.getData(jsonEpisode, EpisodeDTO.class);

		System.out.println();
		System.out.println(episodeData);

		// Pegar dados de todas as temporadas
		List<SeasonDTO> seasons = new ArrayList<>();
		for (int i = 1; i <= serieData.serieSeasons(); i++) {
			var jsonSeason = consume
					.getApiData("https://www.omdbapi.com/?t=gilmore+girls&season=" + i + "&apikey=" + apiKey);
			SeasonDTO seasonData = converter.getData(jsonSeason, SeasonDTO.class);
			seasons.add(seasonData);
		}

		seasons.forEach(System.out::println);

	}

}
