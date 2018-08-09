package br.com.xpto.validadormatricula.services.impl;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.stereotype.Service;

import br.com.xpto.validadormatricula.services.MatriculaService;

@Service
public class MatriculaServiceImpl implements MatriculaService {
	
	private String matricula;
	private String digitoVerificador;
	private Integer base_hexadecimal = 16;
		
	@Override
	public void calculaDigitoVerificador(FileReader arquivo)  {
        BufferedReader bufferedReader = new BufferedReader(arquivo);  
        try {
			while( (matricula = bufferedReader.readLine()) != null ){
				try {
					System.out.println("DIGITO VERIFICADOR : "+ Integer.toHexString(converteMatriculaDecimal(matricula) % base_hexadecimal).toUpperCase());
				} catch (Exception e) {
					System.out.println("DIGITO NÃO CALCULADO PARA A MATRICULA : " + matricula.toUpperCase() + " EXEÇÃO " + e.getMessage());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		};
		
	}

	@Override
	public void validaDigitoVerificador(FileReader arquivo) {
		 BufferedReader bufferedReader = new BufferedReader(arquivo);  
	        try {
				while((matricula = bufferedReader.readLine()) != null ){
					try {
						digitoVerificador = pegaDigitoVerificador(matricula);
						System.out.println("DIGITO CALCULADO : "+ Integer.toHexString(converteMatriculaDecimal(matricula) % base_hexadecimal).toUpperCase() + " - DIGITO LIDO : "+ digitoVerificador.toUpperCase());
	
					} catch (Exception e) {
						System.out.println("DIGITO NÃO CALCULADO PARA A MATRICULA : " + matricula.toUpperCase() + " CARACTER NÃO LIDO " + e.getMessage());
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			};
			
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


