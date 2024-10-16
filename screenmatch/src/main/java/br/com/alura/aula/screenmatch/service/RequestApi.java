package br.com.alura.aula.screenmatch.service;

import br.com.alura.aula.screenmatch.models.Veiculo.Veiculo;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RequestApi {

    public String constroiUrlFilmesESeries(String parametro) {
        return obterDados(ConstantesRequest.BASE_PAGE_URL_SERIES_FILMES +
                parametro +
                ConstantesRequest.APIKEY);
    }

    public String constroiUrlVeiculo(Veiculo veiculo) {
        return obterDados(ConstantesRequest.BASE_PAGE_URL_CARROS +
                veiculo.getTipoVeiculo() +
                ConstantesRequest.URL_MARCAS);
    }

    public String constroiUrlMarca(Veiculo veiculo) {
        return obterDados(constroiUrlVeiculo(veiculo) + "/" +
                veiculo.getCodMarca() +
                ConstantesRequest.URL_MODELOS);
    }

    public String constroiUrlModelo(Veiculo veiculo) {
        return obterDados(constroiUrlMarca(veiculo) + "/" +
                veiculo.getCodModelo() +
                ConstantesRequest.URL_ANOS);
    }

    public String constroiUrlAnos(Veiculo veiculo, String ano) {
        return obterDados(constroiUrlModelo(veiculo) + "/" + ano);
    }

    private String obterDados(String endereco) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();
        try {
            HttpResponse<String> response = HttpClient
                    .newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException | UncheckedIOException e) {
            throw new RuntimeException(e);
        }
    }
}
