package br.com.alura.aula.screenmatch;

import br.com.alura.aula.screenmatch.principal.Principal;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws JsonProcessingException {
		Principal principal = new Principal();
		Scanner leitor = new Scanner(System.in);
		int codMenu = -1;
		String menu = """
				1 - Procura Filme
				2 - Procura Serie
				3 - Procura Temporada
				4 - Procura Episódios
				
				0 - Sair
				""";

		while (codMenu != 0) {
			System.out.println(menu);
			codMenu = leitor.nextInt();

			switch (codMenu) {
				case 1:
					var dadosFilme = principal.procuraFilme();
					System.out.println(dadosFilme);
					break;
				case 2:
					var dadosSerie = principal.procuraSerie();
					System.out.println(dadosSerie);
					break;
				case 3:
					var dadosSerieTemporada = principal.procuraSerie();
					var dadosTemporadas = principal.procuraTemporadas(dadosSerieTemporada);
					dadosTemporadas.forEach(temporada -> System.out.println(
							"Titulo:" + temporada.titulo() +
									"Temporada:" + temporada.temporada()
					));
					break;
				case 4:
					var dadosSerieEpisodios = principal.procuraSerie();
					var dadosTemporadasEp = principal.procuraTemporadas(dadosSerieEpisodios);
					var dadosEpisodios = dadosTemporadasEp.stream()
							.flatMap(temporada -> temporada.episodios().stream())
							.toList();
					dadosEpisodios.forEach(System.out::println);
					break;
				case 0:
					System.out.println("Finalizando programa");
					break;
				default:
					System.out.println("Comando não aceito");
					break;
			}
		}
	}
}
