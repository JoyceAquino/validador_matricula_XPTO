package br.com.xpto.validadormatricula;

import java.io.FileReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.xpto.validadormatricula.services.MatriculaService;

@SpringBootApplication
public class ValidadorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ValidadorApplication.class, args);
	}
	
	@Autowired
	MatriculaService service;
	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {

            FileReader arquivo = new FileReader("C:/Users/joyce/Downloads/teste_b2w/matriculas_sem_dv.txt");
            service.calculaDigitoVerificador(arquivo);
            service.validaDigitoVerificador(arquivo);
	};

	
}}
