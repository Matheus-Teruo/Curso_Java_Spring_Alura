package br.com.alura.aula.screenmatch.principal;

import br.com.alura.aula.screenmatch.models.DadosSeries;
import br.com.alura.aula.screenmatch.models.DadosTemporadas;
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

    public DadosSeries procuraSerie() throws JsonProcessingException {
        System.out.println("Digite a série que deseja procurar:");
        String json = request.constroiUrlFilmesESeries(scan.next().replace(" ","+"));

        DadosSeries dados = conversor.obterDados(json, DadosSeries.class);
        System.out.println(dados);
        return dados;
    }

    public List<DadosTemporadas> procuraTemporadas(DadosSeries serie) throws JsonProcessingException {
        List<DadosTemporadas> listaTemporadas = new ArrayList<>();

        for (int i = 1; i <= serie.numeroTemporadas(); i++) {
            String parametro = serie.titulo().replace(" ","+") + "&season=" + i;
            String json = request.constroiUrlFilmesESeries(parametro);
            DadosTemporadas dados = conversor.obterDados(json, DadosTemporadas.class);

            listaTemporadas.add(dados);
        }
        System.out.println(listaTemporadas);
        return listaTemporadas;
    }
}
