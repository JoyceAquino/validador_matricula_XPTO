package br.com.xpto.validadormatricula.services.impl;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Service;

import br.com.xpto.validadormatricula.services.MatriculaService;

@Service
public class MatriculaServiceImpl implements MatriculaService {

	private String matricula;
	private Integer base_hexadecimal = 16;

	@Override
	public void calculaDigitoVerificador(String arquivo) throws IOException  {
		BufferedReader bufferedReader = new BufferedReader(new FileReader(arquivo));  
		try {
			while( (matricula = bufferedReader.readLine()) != null ){
				try {
					System.out.println("DIGITO VERIFICADOR : "+ retornaDigitoVerificador(matricula));
				} catch (Exception e) {
					System.out.println("DIGITO NÃO CALCULADO PARA A MATRICULA : " + matricula.toUpperCase() + " EXEÇÃO " + e.getMessage());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		};
	}

	public void validaDigitoVerificador(String arquivo)throws IOException {
		String isDigito; 
		String digitoVerificado;
		String digitoAVerificar;		
		BufferedReader bufferedReader = new BufferedReader(new FileReader(arquivo));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("C:/Users/joyce/Desktop/teste_b2w/matriculas_validadas.txt"));
		while((matricula = bufferedReader.readLine()) != null){
			digitoAVerificar = pegaDigitoVerificador(matricula);
			digitoVerificado = retornaDigitoVerificador(matricula);;
			if(digitoAVerificar.equals(digitoVerificado)) {isDigito = " true ";}else {isDigito = " false ";}
			try {
				escreveEmArquivo(bufferedWriter, matricula  + isDigito );
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		bufferedReader.close();
	}

	private void escreveEmArquivo(BufferedWriter arquivo,String linha) throws IOException{
		arquivo.write(linha);
		arquivo.newLine();
		arquivo.flush();
	}

	private String pegaDigitoVerificador(String matricula){
		return matricula.substring(matricula.length()-1);
	}

	private String retornaDigitoVerificador(String matricula){
		Integer decimal = 0;
		for (int i = 0; i < matricula.length(); i++) {	
			decimal += Integer.parseInt(Character.toString(matricula.charAt(i)), base_hexadecimal);

		};
		return Integer.toHexString(decimal % base_hexadecimal).toString();
	}	
}