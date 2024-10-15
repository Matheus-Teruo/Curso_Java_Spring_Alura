package br.com.alura.aula.screenmatch.models.Veiculo;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.core.annotation.AliasFor;

@JsonIgnoreProperties(ignoreUnknown = true)
public record VeiculoSelecionado(
        @JsonAlias("Valor")
        String valor,
        @JsonAlias("Marca")
        String marca,
        @JsonAlias("Modelo")
        String modelo,
        @JsonAlias("AnoModelo")
        String anoModelo,
        @JsonAlias("Combustivel")
        String tipoCombustivel
) {
}
