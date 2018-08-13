# Validador de matricula XPTO.

A Aplicação calcula e valida o digito verificador para uma série de matrículas da empresa XPTO.

O primeiro arquivo de entrada (matriculas_sem_dv.txt) contém a informação de 100 matrículas, 
a aplicação usa essas matrículas para calcular um digito verificador válido, usando os parâmetros fornecidos pela XPTO
e retorna o arquivo de saída (matriculas_com_dv.txt) com os digitos válidos calculados.

O segundo arquivo de entrada (matriculas_para_validar.txt) contém uma sequencia de matriculas com os digitos verificadores já existentes
o arquivo de saída verifica se os digitos das matriculas do arquivo de entrada são verdadeiros ou falsos.

Para usar esse projeto

1 - Clone o respositorio para sua estação de trabalho 
    
      >  git clone https://github.com/JoyceAquino/validador_matricula_XPTO.git
      

2 - Importe para o seu IDE Java (Eclipse, Netbeans, Spring Tool Suite ...)

3 - Execute o projeto com a opções > Run as JavaAplication

Os arquivos de entrada e saída estão e são gerados no repositório do próprio projeto para evitar problemas de acesso aos diretórios.
Caso seja necessário mudar o diretório de saída para os arquivos gerados, será necessário conceder o acesso aos mesmos.

## Estrutura do Projeto.
O projeto possui três pacotes 

Pacote principal 
### br.com.xpto.validadormatricula 
Onde chamo o serviço de matrícula e seus respectivos métodos.
			
        service.calculaDigitoVerificador(matriculas_sem_dv);
        service.validaDigitoVerificador(matriculas_para_validar);
      
Os demais pacotes são 

### br.com.xpto.validadormatricula.services 
Onde são declarados os servicos publicos relacionados a matrícula, que ficam acessíveis para a aplicação executar mediante a passagem dos parâmetros solicitados, conforma na classe principal.
 
     /**
       * Recebe o arquivo com a lista de matriculas sem digito verificador calculado,
       * Lê cada matricula do arquivo e calcula o digito verificador válido.
       * Gera um novo arquivo com cada matricula e seus digitos válidos calculados.
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
  
### br.com.xpto.validadormatricula.services.impl 
Onde os métodos do serviço de matrícula são implementados.
 
#### Implementação dos métodos públicos

    void calculaDigitoVerificador(String caminhoArquivo) throws IOException;
    void validaDigitoVerificador(String caminhoArquivo) throws IOException;
  
  
#### Implementação dos métodos privados

    /*
    * Escreve uma no linha no arquivo de saída 
    */
      private void escreveEmArquivo(BufferedWriter arquivo,String linha) throws IOException{
      }
	
    /*
    * Pega o digito verificador da matrícula pela ultima posição da string.
    */
    private String pegaDigitoVerificador(String matricula){...}
	
    /*
    * Calcula o digito verificador lendo cada caracter da string de matrícula
    * Converte os mesmos para decimal e soma todos eles.
    * Usa o resto da divisão (totalDosDigitos % base_hexadecimal), convertendo o resultado novamente para Hexadecimal
    * O Retorno da função é o DigitoVerificador. 
    */
    private String retornaDigitoVerificador(String matricula){...}
	
  Dúvidas : joycesaquino@gmail.com
  
  Sugestões : https://github.com/JoyceAquino/validador_matricula_XPTO/issues/new
