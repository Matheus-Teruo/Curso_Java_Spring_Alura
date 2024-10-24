package br.com.alura.aula.screenmatch.models;

import br.com.alura.aula.screenmatch.models.request.DadosEpisodio;
import br.com.alura.aula.screenmatch.models.request.DadosTemporadas;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.OptionalDouble;
import java.util.OptionalInt;

@Entity
@Table(name = "episodios")
public class Episodios {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String titulo;
    private LocalDate dataLancamento;
    private Integer episodio;
    private Integer temporada;
    private Double avaliacao;
    @ManyToOne
    private Serie serie;

    public Episodios() {}

    public Episodios(DadosEpisodio dadosEpisodio, Serie serie, DadosTemporadas dadosTemporada) {
        this.titulo = dadosEpisodio.titulo();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.dataLancamento = LocalDate
                .parse(dadosEpisodio.dataLancamento(), formatter);
        this.episodio = OptionalInt
                .of(Integer.parseInt(dadosEpisodio.numeroEpisodio()))
                .orElseThrow(null);
        this.temporada = OptionalInt
                .of(Integer.parseInt(dadosTemporada.temporada()))
                .orElseThrow(null);
        this.avaliacao = OptionalDouble
                .of(Double.parseDouble(dadosEpisodio.avaliacao()))
                .orElseThrow(null);
        this.serie = serie;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public int getEpisodio() {
        return episodio;
    }

    public void setEpisodio(int episodio) {
        this.episodio = episodio;
    }

    public double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    @Override
    public String toString() {
        return  "titulo='" + titulo + '\'' +
                ", dataLancamento=" + dataLancamento +
                ", episodio=" + episodio +
                ", avaliacao=" + avaliacao;
    }
}
