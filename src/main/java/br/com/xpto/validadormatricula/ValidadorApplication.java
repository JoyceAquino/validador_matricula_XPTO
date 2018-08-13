package br.com.xpto.validadormatricula;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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

	String matriculas_sem_dv = System.getProperty("user.dir") +"/matriculas_sem_dv.txt";
	String matriculas_para_validar = System.getProperty("user.dir") +"/matriculas_para_validar.txt";

	@Autowired
	MatriculaService service;

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {

			service.calculaDigitoVerificador(matriculas_sem_dv);
			service.validaDigitoVerificador(matriculas_para_validar);
	
		};
	}}

