package br.com.AluraMusic.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.AluraMusic.demo.model.Cantor;

public interface CantorRepository extends JpaRepository<Cantor, Long> {
    List<Cantor> findAll();
    boolean existsByNomeIgnoreCase(String nome);
    Cantor findByNomeIgnoreCase(String nome);
    List<Cantor> findTop5ByOrderByOuvintesDesc();
}