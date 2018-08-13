package br.com.xpto.validadormatricula.services.impl;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.xpto.validadormatricula.services.MatriculaService;

@Service
public class MatriculaServiceImpl implements MatriculaService {
	
	private static final Logger log = LoggerFactory.getLogger(MatriculaServiceImpl.class);
	
	private String matricula;
	private Integer base_hexadecimal = 16;

	public void calculaDigitoVerificador(String arquivo) throws IOException  {
		log.info("Calculando o digito verificador !");
		BufferedReader bufferedReader = new BufferedReader(new FileReader(arquivo));  
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getProperty("user.dir") +"/matriculas_com_dv.txt"));
		try {
			while( (matricula = bufferedReader.readLine()) != null ){
				try {
					log.info("Escrevendo no arquivo: " +  matricula + "-" + retornaDigitoVerificador(matricula));
					escreveEmArquivo(bufferedWriter, matricula + "-" + retornaDigitoVerificador(matricula));
				} catch (Exception e) {
				}
			}
			log.info("Operação finalizada ! O Arquivo de saída está salvo no diretório " + System.getProperty("user.dir") +". Nome do Arquivo : matriculas_com_dv.txt");
		} catch (IOException e) {
			e.printStackTrace();
		};
		bufferedReader.close();
	}
	
	
	public void validaDigitoVerificador(String arquivo)throws IOException {
		log.info("Validando o digito verificador !");
		String digitoValido; 
		String digitoVerificado;
		String digitoAVerificar;		
		BufferedReader bufferedReader = new BufferedReader(new FileReader(arquivo));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getProperty("user.dir") +"/matriculas_validadas.txt"));
		while((matricula = bufferedReader.readLine()) != null){
			digitoAVerificar = pegaDigitoVerificador(matricula);
			digitoVerificado = retornaDigitoVerificador(matricula.substring(1, 9));;
			if(digitoAVerificar.equals(digitoVerificado)) {digitoValido = " true ";}else {digitoValido = " false ";}
			try {
				log.info(matricula.toUpperCase() + digitoValido + "Digito calculado : " + digitoVerificado.toUpperCase());
				escreveEmArquivo(bufferedWriter, matricula  + digitoValido);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		log.info("Operação finalizada ! O Arquivo de saída está salvo no diretório " + System.getProperty("user.dir") +". Nome do Arquivo : matriculas_validadas.txt");
		bufferedReader.close();
	}
	
	/*
	 * Escreve uma no linha no arquivo de saída 
	 */
	private void escreveEmArquivo(BufferedWriter arquivo,String linha) throws IOException{
		arquivo.write(linha);
		arquivo.newLine();
		arquivo.flush();
	}
	
	/*
	 * Pega o digito verificador da matrícula pela ultima posição da string.
	 */
	private String pegaDigitoVerificador(String matricula){
		return matricula.substring(matricula.length()-1);
	}
	
	/*
	 * Calcula o digito verificador lendo cada caracter da string de matrícula
	 * Converte os mesmos para decimal e soma todos eles.
	 * Usa o resto da divisão (totalDosDigitos % base_hexadecimal), convertendo o resultado novamente para Hexadecimal
	 * O Retorno da função é o DigitoVerificador. 
	 */
	private String retornaDigitoVerificador(String matricula){
		Integer totalDosDigitos = 0;
		for (int i = 0; i < matricula.length(); i++) {	
			totalDosDigitos += Integer.parseInt(Character.toString(matricula.charAt(i)), base_hexadecimal);
		};
		return Integer.toHexString(totalDosDigitos % base_hexadecimal).toString();
	}	
}