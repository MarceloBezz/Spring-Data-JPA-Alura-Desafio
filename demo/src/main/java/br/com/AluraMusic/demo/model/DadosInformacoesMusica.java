package br.com.AluraMusic.demo.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//CLASSE FINAL COM AS MÚSICAS
@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosInformacoesMusica(
    @JsonAlias("name")String nome,
    @JsonAlias("artist")String nomeArtista,
    @JsonAlias("listeners")int ouvintes) {
}
