package br.com.alura.aula.screenmatch.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosTemporadas(
        @JsonAlias("Title")
        String titulo,
        @JsonAlias("totalEpisodes")
        int numeroEpisodios,
        @JsonAlias("imdbRating")
        String avaliacaoImdb
) {
}
