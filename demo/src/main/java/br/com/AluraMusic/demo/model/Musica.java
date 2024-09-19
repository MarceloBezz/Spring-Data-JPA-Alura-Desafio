package br.com.AluraMusic.demo.model;

import java.util.Optional;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "musicas")
public class Musica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @ManyToOne
    private Cantor nomeArtista;

    private int visualizacoes;
    
    public Musica(Optional<DadosInformacoesMusica> musicaFiltrada) {
        this.titulo = musicaFiltrada.get().nome();
        this.visualizacoes = musicaFiltrada.get().ouvintes();
        
    }

    public Musica() {
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Cantor getNomeArtista() {
        return nomeArtista;
    }

    public void setNomeArtista(Cantor nomeArtista) {
        this.nomeArtista = nomeArtista;
    }

    public int getVisualizacoes() {
        return visualizacoes;
    }

    public void setVisualizacoes(int visualizacoes) {
        this.visualizacoes = visualizacoes;
    }

    @Override
    public String toString() {
        return """
                Nome da música: %s
                Cantor(a)/banda: %s
                Visualizações: %d
                """.formatted(titulo, nomeArtista.getNome(), visualizacoes);
    }
}
