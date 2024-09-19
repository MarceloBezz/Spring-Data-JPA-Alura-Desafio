package br.com.AluraMusic.demo.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosOuvintes(@JsonAlias("listeners") int ouvintes) {
    
}
