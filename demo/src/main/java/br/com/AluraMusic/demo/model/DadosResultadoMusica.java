package br.com.AluraMusic.demo.model;

import java.util.List;
// import org.hibernate.mapping.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//CLASSE NETA
@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosResultadoMusica(@JsonAlias("track")List<DadosInformacoesMusica> musicas) {
    
}
