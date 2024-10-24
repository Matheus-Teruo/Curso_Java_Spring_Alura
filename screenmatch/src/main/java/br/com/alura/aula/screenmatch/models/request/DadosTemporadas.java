package br.com.alura.aula.screenmatch.models.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosTemporadas(
        @JsonAlias("Title")
        String titulo,
        @JsonAlias("Season")
        String temporada,
        @JsonAlias("Episodes")
        List<DadosEpisodio> episodios
) {
}
