package br.com.AluraMusic.demo.Principal;


import java.net.URLEncoder;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.AluraMusic.demo.model.Cantor;
import br.com.AluraMusic.demo.model.DadosArtista;
import br.com.AluraMusic.demo.model.DadosInformacoesMusica;
import br.com.AluraMusic.demo.model.DadosMusica;
import br.com.AluraMusic.demo.model.DadosResultadoPesquisa;
import br.com.AluraMusic.demo.model.EnumTipo;
import br.com.AluraMusic.demo.model.Musica;
import br.com.AluraMusic.demo.repository.CantorRepository;
import br.com.AluraMusic.demo.repository.MusicaRepository;
import br.com.AluraMusic.demo.service.ConexaoAPI;
import br.com.AluraMusic.demo.service.ConversorDados;

public class Principal {
    private ConversorDados conversor = new ConversorDados();
    private ConexaoAPI conexao = new ConexaoAPI();

    
    private CantorRepository cantorRepository;
    private MusicaRepository musicaRepository;

    public Principal(CantorRepository cantorRepository, MusicaRepository musicaRepository) {
        this.cantorRepository = cantorRepository;
        this.musicaRepository = musicaRepository;
    }

    private final String URL = "http://ws.audioscrobbler.com/2.0/?method=";
    private final String API_KEY = "42f875ec4e85f05584b59be050e3efab";

    Scanner scanner = new Scanner(System.in);

    public void exibeMenu() {
        int opcao = -1;
        while(opcao != 0) {
            System.out.println("""
                    ********** Alura Musics **************
                              O seu som é aqui
    
                    O que você deseja fazer hoje?
    
                    1 - Cadastrar um novo cantor
                    2 - Cadastrar uma nova música
                    3 - Listar os cantores cadastrados
                    4 - Listar as músicas cadastradas
                    5 - Buscar músicas por cantor
                    6 - Top 5 cantores
                    7 - Top 5 músicas

                    0 - Sair
                        """);
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                cadastrarCantor();
                break;

                case 2:
                cadastrarMusica();    
                break;

                case 3:
                listarCantores();    
                break;

                case 4:
                listarMusicas();    
                break;

                case 5:
                buscarMusicasPorCantor();    
                break;

                case 6:
                listarTop5Cantores();
                break;

                case 7:
                listarTop5Musicas();
                break;

                case 0:
                System.out.println("Saindo...");
                break;

                default:
                System.out.println("Favor escolher uma opção válida");
                break;
            }

        }
    }

    private void cadastrarCantor() {
        System.out.println("Qual cantor você deseja buscar?");
        var nomeCantor = scanner.nextLine();
        System.out.println("""
                Selecione uma das opções referente à sua busca:
                1 - Solo
                2 - Dupla 
                3 - Banda
                """);
        var tipo = scanner.nextInt();
        scanner.nextLine();

        String enumTipo = "";
        switch (tipo) {
            case 1:
            enumTipo = "Solo";
            break;

            case 2:
            enumTipo = "Dupla";
            break;

            case 3:
            enumTipo = "Banda";
            break;
        
            default:
            System.out.println("Selecione uma opção válida!");
            cadastrarCantor();
            break;
        }

        try {
            String json = conexao.obterDados(URL + "artist.getinfo&artist=" + URLEncoder.encode(nomeCantor) + "&api_key=" + API_KEY + "&format=json");
            DadosArtista cantor = conversor.converteDados(json,DadosArtista.class);
            Cantor novoCantor = new Cantor(cantor, enumTipo);
            System.out.println(novoCantor);
            cantorRepository.save(novoCantor);
        } catch (Exception e) {
            System.out.println("Nome do cantor(a)/banda não encontrado!");
        }
    }

    private void cadastrarMusica() {
        System.out.println("Qual a música desejada?");
        var nomeMusica = scanner.nextLine();
        System.out.println("Qual o cantor(a)/banda que canta a música?");
        var artistaMusica = scanner.nextLine();

        if(cantorRepository.existsByNomeIgnoreCase(artistaMusica)) {
            try {
                String json = conexao.obterDados(URL + "track.search&track=" + URLEncoder.encode(nomeMusica) + "&api_key=" + API_KEY + "&format=json");
                DadosResultadoPesquisa musicas = conversor.converteDados(json,DadosResultadoPesquisa.class);
    
                Optional<DadosInformacoesMusica> musicaFiltrada = musicas.resultadoPesquisa().resultado().musicas().stream()
                .filter(m -> m.nomeArtista().equalsIgnoreCase(artistaMusica))
                .findFirst();
                Musica novaMusica = new Musica(musicaFiltrada);
                
                Cantor artista = cantorRepository.findByNomeIgnoreCase(artistaMusica);
                artista.setMusicas(novaMusica);
    
                cantorRepository.save(artista);    
            } catch (Exception e) {
                System.out.println("Música não encontrada!");
            }
        } else {
            System.out.println("Cantor(a)/banda não encontrado(a)!");
        }

    }

    private void listarCantores() {
        List<Cantor> cantores = cantorRepository.findAll();
        System.out.println("Lista de cantores:");
        cantores.forEach(System.out::println);
    }

    private void listarMusicas() {
        List<Musica> musicas = musicaRepository.findAll();
        System.out.println("Lista de músicas:");
        musicas.forEach(System.out::println);
    }

    private void buscarMusicasPorCantor() {
        System.out.println("Qual o cantor desejado?");
        var nomeCantor = scanner.nextLine();

        Cantor cantorDesejado = new Cantor();
        if(cantorRepository.existsByNomeIgnoreCase(nomeCantor)) {
            cantorDesejado = cantorRepository.findByNomeIgnoreCase(nomeCantor);
            List<Musica> musicas = musicaRepository.findByNomeArtista(cantorDesejado);

            System.out.println("Músicas de " + cantorDesejado.getNome() + ":");
            musicas.forEach(System.out::println);
        } else {
            System.out.println("Cantor(a)/banda não encontrado(a)!");
        }
    }

    private void listarTop5Cantores() {
        List<Cantor> topCantores = cantorRepository.findTop5ByOrderByOuvintesDesc();
        topCantores.forEach(System.out::println);
    }

    private void listarTop5Musicas() {
        List<Musica> topMusicas = musicaRepository.findTop5ByOrderByVisualizacoesDesc();
        topMusicas.forEach(System.out::println);
    }
}
