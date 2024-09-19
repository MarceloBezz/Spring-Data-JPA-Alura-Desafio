package br.com.AluraMusic.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.AluraMusic.demo.Principal.Principal;
import br.com.AluraMusic.demo.repository.MusicaRepository;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private br.com.AluraMusic.demo.repository.CantorRepository cantorRepository;

	@Autowired
	private MusicaRepository musicaRepository;
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(cantorRepository, musicaRepository);
		principal.exibeMenu();
	}

}
