package br.com.AluraMusic.demo.model;

import java.util.List;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
// import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "cantores")
public class Cantor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private int ouvintes;

    @Enumerated(EnumType.STRING)
    private EnumTipo tipo;

    @OneToMany(mappedBy = "nomeArtista", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Musica> musicas;


    public Cantor(DadosArtista cantor, String tipo) {
        this.nome = cantor.cantor().nome();
        this.ouvintes = cantor.cantor().ouvintes().ouvintes();
        this.tipo = EnumTipo.fromString(tipo);
    }

    public Cantor(){}

    @Override
    public String toString() {
        return """
                Nome do cantor: %s - %s
                Número de ouvintes: %d
                """.formatted(nome, tipo, ouvintes);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getOuvintes() {
        return ouvintes;
    }

    public void setOuvintes(int ouvintes) {
        this.ouvintes = ouvintes;
    }

    public EnumTipo getTipo() {
        return tipo;
    }

    public void setTipo(EnumTipo tipo) {
        this.tipo = tipo;
    }

    public List<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(Musica musica) {
        musica.setNomeArtista(this);
        musicas.add(musica);
    }
    
}
