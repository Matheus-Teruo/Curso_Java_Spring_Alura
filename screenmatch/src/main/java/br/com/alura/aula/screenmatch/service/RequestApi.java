package br.com.alura.aula.screenmatch.service;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RequestApi {

    public String constroiUrl(String parametro) {
        return ConstantesRequest.BASE_PAGE_URL +
                parametro +
                ConstantesRequest.APIKEY;
    }

    public String obterDados(String endereco) {
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
