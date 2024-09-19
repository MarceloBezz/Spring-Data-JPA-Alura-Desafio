package br.com.AluraMusic.demo.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosCantor(@JsonAlias("name") String nome,
                         @JsonAlias("stats")DadosOuvintes ouvintes) {
    
}
