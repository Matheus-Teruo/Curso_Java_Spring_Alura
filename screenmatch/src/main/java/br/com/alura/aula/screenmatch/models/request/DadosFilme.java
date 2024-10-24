package br.com.alura.aula.screenmatch.models.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosFilme(
        @JsonAlias("Title")
        String titulo,
        @JsonAlias("Released")
        String anoLancamento,
        @JsonAlias("imdbRating")
        String avaliacaoImdb,
        @JsonAlias("Genre")
        String genero,
        @JsonAlias("Actors")
        String atores,
        @JsonAlias("Plot")
        String sinopse,
        @JsonAlias("Poster")
        String poster
) {
}
