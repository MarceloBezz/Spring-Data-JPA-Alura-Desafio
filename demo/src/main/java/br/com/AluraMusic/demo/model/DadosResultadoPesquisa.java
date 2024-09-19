package br.com.AluraMusic.demo.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//CLASSE MÃE
@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosResultadoPesquisa(@JsonAlias("results")DadosMusica resultadoPesquisa) {
    
}
