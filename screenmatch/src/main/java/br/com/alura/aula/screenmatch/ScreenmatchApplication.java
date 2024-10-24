package br.com.alura.aula.screenmatch;

import br.com.alura.aula.screenmatch.principal.Principal;
import br.com.alura.aula.screenmatch.repository.SerieRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

    public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Autowired
	private SerieRepository serieRepository;

	@Override
	public void run(String... args) throws JsonProcessingException {
		Principal principal = new Principal(serieRepository);
		Scanner leitor = new Scanner(System.in);
		int codMenu = -1;
		String menu = """
				1 - Procura Filme
				2 - Procura Serie
				3 - Procura Temporada
				4 - Procura Episódios
				5 - Query Serie
				
				0 - Sair
				""";

		while (codMenu != 0) {
			System.out.println(menu);
			codMenu = leitor.nextInt();
			leitor.nextLine();

			switch (codMenu) {
				case 1:
					var dadosFilme = principal.procuraFilme();
					System.out.println(dadosFilme);
					principal.busca = " ";
					break;
				case 2:
					var dadosSerie = principal.procuraSerie();
					System.out.println(dadosSerie);
					principal.busca = " ";
					break;
				case 3:
					var dadosTemporadas = principal.procuraTemporadas();
					dadosTemporadas.forEach(temporada -> System.out.println(
							"Titulo:" + temporada.titulo() +
									"Temporada:" + temporada.temporada()
					));
					principal.busca = " ";
					break;
				case 4:
					var dadosEpisodios = principal.procuraEpisodios();
					dadosEpisodios.forEach(System.out::println);
					principal.busca = " ";
					break;
				case 5:
					principal.buscaSerie();
					principal.busca = " ";
					break;
				case 0:
					System.out.println("Finalizando programa");
					principal.busca = " ";
					break;
				default:
					System.out.println("Comando não aceito");
					break;
			}
		}
	}
}
