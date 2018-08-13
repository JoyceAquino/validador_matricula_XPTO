package br.com.xpto.validadormatricula.services;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface MatriculaService {
	
	/**
	 * Recebe o arquivo com a lista de matriculas sem digito verificador calculado
	 * e gera um novo arquivo com os digitos validos calculados.
	 * @param arquivo
	 * @throws FileNotFoundException 
	 */
	void calculaDigitoVerificador(String caminhoArquivo) throws IOException;
	
	/**
	 * Recebe o arquivo com a lista de matriculas com digito verificador para validar
	 * e gera um novo arquivo com os digitos verificados como verdadeiros ou falsos.
	 * @param arquivo
	 * @throws IOException 
	 */
	void validaDigitoVerificador(String caminhoArquivo) throws IOException;
	
}
