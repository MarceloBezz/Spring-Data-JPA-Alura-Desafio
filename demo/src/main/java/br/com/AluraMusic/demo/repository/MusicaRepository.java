package br.com.AluraMusic.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.AluraMusic.demo.model.Cantor;
import br.com.AluraMusic.demo.model.Musica;
import java.util.List;


public interface MusicaRepository extends JpaRepository<Musica, Long> {
    List<Musica> findByNomeArtista(Cantor nomeArtista);

    List<Musica> findTop5ByOrderByVisualizacoesDesc();
}
