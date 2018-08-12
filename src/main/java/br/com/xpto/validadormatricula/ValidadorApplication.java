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

	String matricula;
	Integer base_hexadecimal = 16;
	
	@Autowired
	MatriculaService service;

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {

			String MATRICULAS_SEM_DV = "C:/Users/joyce/Desktop/teste_b2w/matriculas_sem_dv.txt";
			String matriculas_para_validar = "C:/Users/joyce/Desktop/teste_b2w/matriculas_para_validar.txt";
			//service.calculaDigitoVerificador(MATRICULAS_SEM_DV);
			//service.validaDigitoVerificador(matriculas_para_validar);
			String isDigito; 
			String digitoVerificado;
			String digitoAVerificar;
			BufferedReader bufferedReader = new BufferedReader(new FileReader(matriculas_para_validar));
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getProperty("user.dir") +"/matriculas_validadas.txt"));
			while((matricula = bufferedReader.readLine()) != null){
				digitoAVerificar = pegaDigitoVerificador(matricula);
				digitoVerificado = calculaDigitoVerificador(matricula);
				if(digitoAVerificar.equals(digitoVerificado)) {isDigito = " true ";}else {isDigito = " false ";	}
				try {
					escreveEmArquivo(bufferedWriter, matricula  + isDigito + "Digito Correto : " + digitoVerificado);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}		
		};
	};
	
	private String calculaDigitoVerificador(String matricula){	
		return Integer.toHexString(converteMatriculaDecimal(matricula.substring(1, 9)) % base_hexadecimal).toString();
	}
	
	private void escreveEmArquivo(BufferedWriter arquivo,String linha) throws IOException{
		arquivo.write(linha);
		arquivo.newLine();
		arquivo.flush();
	}

	private String pegaDigitoVerificador(String matricula){
		return matricula.substring(matricula.length()-1);
	}

	private Integer converteMatriculaDecimal(String matricula){
		Integer dec = 0;
		for (int i = 0; i < matricula.length(); i++) {		
			dec += Integer.parseInt(Character.toString(matricula.charAt(i)), base_hexadecimal);
		};
		return dec;
	}	
}

