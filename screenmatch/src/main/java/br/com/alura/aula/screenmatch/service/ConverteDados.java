package br.com.alura.aula.screenmatch.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.List;

public class ConverteDados{
    private final ObjectMapper mapper = new ObjectMapper();

    public <T> T obterDados(String json, Class<T> classe) throws JsonProcessingException {
        return mapper.readValue(json, classe);
    }

    public <T> List<T> obterLista(String json, Class<T> classe) throws JsonProcessingException {
        CollectionType lista = mapper.getTypeFactory()
                .constructCollectionType(List.class, classe);
        return mapper.readValue(json, lista);
    }
}
