package br.com.alura.aula.screenmatch.repository;

import br.com.alura.aula.screenmatch.models.Episodios;
import br.com.alura.aula.screenmatch.models.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SerieRepository extends JpaRepository<Serie, Integer> {
    @Query("SELECT s FROM Serie s WHERE s.titulo ILIKE %:trechoSerie%")
    List<Serie> procuraSerie(String trechoSerie);

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s.titulo = :serieName ")
    List<Episodios> procuraEpisodios(String serieName);
}