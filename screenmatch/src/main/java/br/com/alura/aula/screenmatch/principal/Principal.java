package br.com.alura.aula.screenmatch.principal;

import br.com.alura.aula.screenmatch.models.Episodios;
import br.com.alura.aula.screenmatch.models.Serie;
import br.com.alura.aula.screenmatch.models.request.DadosFilme;
import br.com.alura.aula.screenmatch.models.request.DadosSerie;
import br.com.alura.aula.screenmatch.models.request.DadosTemporadas;
import br.com.alura.aula.screenmatch.repository.SerieRepository;
import br.com.alura.aula.screenmatch.service.ConstantesRequest;
import br.com.alura.aula.screenmatch.service.ConverteDados;
import br.com.alura.aula.screenmatch.service.RequestApi;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

    Scanner scan = new Scanner(System.in);
    RequestApi request = new RequestApi();
    ConverteDados conversor = new ConverteDados();
    Serie serie;
    public String busca = " ";;
    private SerieRepository serieRepository;

    public Principal(SerieRepository serieRepository){
        this.serieRepository = serieRepository;
    }

    public DadosFilme procuraFilme() throws JsonProcessingException {
        System.out.println("Digite o filme que deseja procurar:");
        if (busca.equals(" ")) {
            busca = scan.nextLine();
        }
        String json = request.constroiUrlFilmesESeries(busca.replace(" ","+"));
        return conversor.obterDados(json, DadosFilme.class);
    }

    public Serie procuraSerie() throws JsonProcessingException {
        System.out.println("Digite a série que deseja procurar:");
        if (busca.equals(" ")) {
            busca = scan.nextLine();
        }
        List<Serie> querySerie = serieRepository.procuraSerie(busca);
        if (querySerie.isEmpty()){
            System.out.println("Busca serie");
            String json = request.constroiUrlFilmesESeries(busca.replace(" ","+"));
            DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
            serie = new Serie(dados);
            serieRepository.save(serie);
        } else {
            System.out.println("Query Serie");
            serie = querySerie.getFirst();
        }
        return serie;
    }

    public List<DadosTemporadas> procuraTemporadas() throws JsonProcessingException {
        List<DadosTemporadas> listaTemporadas = new ArrayList<>();
        System.out.println("temporada = " + busca);
        Serie serie = procuraSerie();

        System.out.println("Busca Temporada");
        for (int i = 1; i <= serie.getNumeroTemporadas(); i++) {
            String parametro = serie.getTitulo().toLowerCase().replace(" ","+") +
                    ConstantesRequest.URL_EPISODIOS + i;
            String json = request.constroiUrlFilmesESeries(parametro);
            DadosTemporadas dados = conversor.obterDados(json, DadosTemporadas.class);
            listaTemporadas.add(dados);
        }

        return listaTemporadas;
    }

    public List<Episodios> procuraEpisodios() throws JsonProcessingException {
        Serie serie = procuraSerie();
        List<Episodios> queryEpisodios = serieRepository.procuraEpisodios(serie.getTitulo());
        if (queryEpisodios.isEmpty()) {
            System.out.println("Busca Episódio");
            List<DadosTemporadas> listaTemporadas = procuraTemporadas();
            List<Episodios> listaEpisodios = listaTemporadas.stream()
                    .flatMap(temporada -> temporada.episodios().stream()
                            .map(episodio -> new Episodios(episodio, serie, temporada)))
                    .collect(Collectors.toList());
            System.out.println("lista");

            serie.setEpisodios(listaEpisodios);
            listaEpisodios.forEach(System.out::println);
            serieRepository.save(serie);

            return listaEpisodios;
        } else {
            System.out.println("Query Episódio");
            return queryEpisodios;
        }
    }

    public void buscaSerie() {
        System.out.println("Digite a série que deseja procurar:");
        if (busca.equals(" ")) {
            busca = scan.nextLine();
        }
        List<Serie> querySerie = serieRepository.procuraSerie(busca);
        querySerie.forEach(System.out::println);
    }
}
