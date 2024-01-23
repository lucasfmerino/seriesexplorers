package br.com.projects.seriesexplorers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.projects.seriesexplorers.main.Main;
import br.com.projects.seriesexplorers.repository.SerieRepository;

@SpringBootApplication
public class SeriesexplorersApplication implements CommandLineRunner {

	@Autowired
	private SerieRepository serieRepository;

	public static void main(String[] args) {
		SpringApplication.run(SeriesexplorersApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Main main = new Main(serieRepository);
		main.displayMenu();

	}

}
