package br.com.AluraMusic.demo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//CLASSE FILHA
@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosMusica(@JsonAlias("trackmatches")DadosResultadoMusica resultado) {
    
}
