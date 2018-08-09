package br.com.xpto.validadormatricula.services;

import java.io.FileReader;

public interface MatriculaService {
	/**
	 * 
	 * @param arquivo
	 */
	void calculaDigitoVerificador(FileReader arquivo);
	
	/**
	 * 
	 * @param arquivo
	 */
	void validaDigitoVerificador(FileReader arquivo);
	
}
