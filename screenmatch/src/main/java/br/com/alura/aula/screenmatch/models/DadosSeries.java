package br.com.alura.aula.screenmatch.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosSeries(
        @JsonAlias("Title")
        String titulo,
        @JsonAlias("totalSeasons")
        int numeroTemporadas,
        @JsonAlias("imdbRating")
        String avaliacaoImdb
) {
}
