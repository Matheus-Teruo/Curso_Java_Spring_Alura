package br.com.alura.aula.screenmatch;

import br.com.alura.aula.screenmatch.models.DadosSeries;
import br.com.alura.aula.screenmatch.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		DadosSeries dados = principal.procuraSerie();
		principal.procuraTemporadas(dados);
	}
}
