package br.com.projects.seriesexplorers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.projects.seriesexplorers.service.ConsumeAPI;
import br.com.projects.seriesexplorers.utils.Params;

@SpringBootApplication
public class SeriesexplorersApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(SeriesexplorersApplication.class, args);
	}

	private static String apiKey = Params.apiKey;

	@Override
	public void run(String... args) throws Exception {
		
		var consume = new ConsumeAPI();

		// Pegar dados da série
		var json = consume.getData("https://www.omdbapi.com/?t=gilmore+girls&apikey=" + apiKey);

		System.out.println(json);


		// Pegar imagem random
		json = consume.getData("https://coffee.alexflipnote.dev/random.json");
		System.out.println(json);
	}

}
