package br.com.alura.aula.screenmatch.models.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosSerie(
        @JsonAlias("Title")
        String titulo,
        @JsonAlias("Genre")
        String genero,
        @JsonAlias("totalSeasons")
        int numeroTemporadas,
        @JsonAlias("imdbRating")
        String avaliacaoImdb
) {
}
