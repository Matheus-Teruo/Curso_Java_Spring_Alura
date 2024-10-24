package br.com.alura.aula.screenmatch.models;

import br.com.alura.aula.screenmatch.models.request.DadosSerie;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.util.List;
import java.util.OptionalDouble;

@Entity
@Table(name = "series")
public class Serie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(unique = true)
    private String titulo;
    @Enumerated(EnumType.STRING)
    private Genero genero;
    private Integer numeroTemporadas;
    private Double avaliacaoImdb;
    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL)
    private List<Episodios> episodios;

    public Serie() {}

    public Serie(DadosSerie dadosSerie) {
        this.titulo = dadosSerie.titulo();
        this.numeroTemporadas = dadosSerie.numeroTemporadas();
        this.genero = Genero
                .fromString(dadosSerie.genero()
                        .split(",")[0].trim());
        this.avaliacaoImdb = OptionalDouble
                .of(Double.parseDouble(dadosSerie.avaliacaoImdb()))
                .orElseThrow(null);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getNumeroTemporadas() {
        return numeroTemporadas;
    }

    public void setNumeroTemporadas(int numeroTemporadas) {
        this.numeroTemporadas = numeroTemporadas;
    }

    public double getAvaliacaoImdb() {
        return avaliacaoImdb;
    }

    public void setAvaliacaoImdb(double avaliacaoImdb) {
        this.avaliacaoImdb = avaliacaoImdb;
    }

    public List<Episodios> getEpisodios() {
        return episodios;
    }

    public void setEpisodios(List<Episodios> episodios) {
        this.episodios = episodios;
    }

    @Override
    public String toString() {
        return  "titulo='" + titulo + '\'' + "\n" +
                "genero='" + genero + '\'' + "\n" +
                "numeroTemporadas=" + numeroTemporadas + "\n" +
                "avaliacaoImdb=" + avaliacaoImdb;
    }
}
