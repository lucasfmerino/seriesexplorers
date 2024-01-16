package br.com.projects.seriesexplorers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SeriesexplorersApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(SeriesexplorersApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("test command line runner");
	}

}
