package br.com.alura.aula.screenmatch.principal;

import br.com.alura.aula.screenmatch.models.DadosFilme;
import br.com.alura.aula.screenmatch.models.DadosSerie;
import br.com.alura.aula.screenmatch.models.DadosTemporadas;
import br.com.alura.aula.screenmatch.service.ConstantesRequest;
import br.com.alura.aula.screenmatch.service.RequestApi;
import br.com.alura.aula.screenmatch.service.ConverteDados;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    Scanner scan = new Scanner(System.in);
    RequestApi request = new RequestApi();
    ConverteDados conversor = new ConverteDados();

    public DadosFilme procuraFilme() throws JsonProcessingException {
        System.out.println("Digite o filme que deseja procurar:");
        String json = request.constroiUrlFilmesESeries(scan.next().replace(" ","+"));
        DadosFilme dados = conversor.obterDados(json, DadosFilme.class);
        return dados;
    }

    public DadosSerie procuraSerie() throws JsonProcessingException {
        System.out.println("Digite a série que deseja procurar:");
        String json = request.constroiUrlFilmesESeries(scan.next().replace(" ","+"));
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        return dados;
    }

    public List<DadosTemporadas> procuraTemporadas(DadosSerie serie) throws JsonProcessingException {
        List<DadosTemporadas> listaTemporadas = new ArrayList<>();

        for (int i = 1; i <= serie.numeroTemporadas(); i++) {
            String parametro = serie.titulo().toLowerCase().replace(" ","+") +
                    ConstantesRequest.URL_EPISODIOS + i;
            String json = request.constroiUrlFilmesESeries(parametro);
            DadosTemporadas dados = conversor.obterDados(json, DadosTemporadas.class);
            listaTemporadas.add(dados);
        }
        return listaTemporadas;
    }
}
