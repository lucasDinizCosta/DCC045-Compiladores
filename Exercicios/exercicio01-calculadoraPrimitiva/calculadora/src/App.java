import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import java.util.regex.*;  

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * Exercício - Calculadora primitiva - Disciplina de Teoria dos Compiladores (2021/1)
 * 		Universidade Federal de Juiz de Fora (UFJF)
 * 
 * Curso: Ciência da Computação
 * 
 * Aluno: Lucas Diniz da Costa 	--- Matricula: 201465524C
 */

public class App {
	public void calcPrimitiva(ArrayList<String> elementos){
		/**
		 * A calculadora tem apenas dois tipos de comando:
		 * 1) Comando de atribuição;
		 * 2) Comando de impressão na tela.
		 */

		// REMOVE OS ESPACOS EM CADA LINHA PRA FACILITAR A LEITURA
		elementos = this.removeEspacos(elementos);

		System.out.println("Calculadora primitiva");
		HashMap<String, Integer> variaveis = new HashMap<String, Integer>();
		int posicaoElemento = 0;
		String operando;
		String linha;
		Boolean erro = false;
		String anterior, atual;

		/**
		 * Sobre erros de sintaxe:
		 * Optei por utilizar o regex para filtar alguns padroes de digitação, dos comandos de
		 * atribuição e impressão, e outros possíveis erros seriam tratados via código.
		 * 
		 * Sobre os comandos:
		 * 
		 * No comando de atribuição trabalha-se com 2 pilhas:
		 * => A primeira recebe os numeros
		 * => A segunda recebe os sinais
		 * 
		 * quando o sinal de multiplicação é encontrado na leitura, o numero lido é multiplicado
		 * pelo valor tirado da pilha de numeros e depois adicionado novamente.
		 * Com o sinal de soma, ele apenas adiciona o numero na pilha de numeros.
		 * Após o processo, será desempilhado a pilha de numeros e feita a operação de soma
		 * da pilha de sinal, assim, soma-se os valores ao final.
		 * 
		 */

		Stack<String> pilhaNumeros = new Stack<String>();
		Stack<String> pilhaSinais = new Stack<String>();

		do {
			linha = elementos.get(posicaoElemento);
			anterior = "";
			atual = "";
			operando = "";
			if(linha.length() == 2){	// Comando de impressao na tela curto
				for(int index = 0; index < linha.length(); index++){
					char simbolo = linha.charAt(index);
					if(index > 0){
						atual = String.valueOf(simbolo);
						anterior = String.valueOf(linha.charAt(index - 1));
						if(simbolo == ';'){
							if(variaveis.get(operando) != null){
								System.out.println("O valor de " + operando + " eh: " + 
								variaveis.get(operando));
							}
							else{
								System.out.println("\tErro:\nlinha "+(posicaoElemento+1)+": Variavel \'" + operando + "\' nao instanciada");
								erro = true;
							}
						}
						else{
							System.out.println("\tErro:\nlinha "+(posicaoElemento+1)+": Falta o \';\' no final da linha");
							erro = true;
						}
					}
					else{	// Primeiro caractere
						atual = String.valueOf(simbolo);
						anterior = String.valueOf(simbolo);
						if(simbolo >= 'a' && simbolo <= 'z'){
							operando = String.valueOf(simbolo);
						}
						else{
							System.out.println("\tErro:\nlinha "+(posicaoElemento+1)+": formato incorreto");
							erro = true;
							break;
						}
					}
				}
			}
			else if(linha.length() > 2){	
				if(verificaExpressao(linha) == 1){	// Comando de atribuição 
					operando = String.valueOf(linha.charAt(0));
					pilhaNumeros.clear();
					pilhaSinais.clear();
					String expressao = linha.substring(linha.indexOf('=') + 1, linha.length() - 1);
					for(int i = 0; i < expressao.length(); i++){
						char simbolo = expressao.charAt(i);
						if(i > 0){
							atual = String.valueOf(simbolo);
							anterior = String.valueOf(expressao.charAt(i - 1));
							char caractereAnterior = expressao.charAt(i - 1);//anterior.charAt(anterior.length() - 1);
							if(simbolo >= 'a' && simbolo <= 'z'){
								// =======> TRATAMENTO DE ERROS
								// VARIAVEL NULA
								if(variaveis.get(atual) == null){
									System.out.println("\tErro:\nlinha "+(posicaoElemento+1)+": Variavel \'" + atual + "\' nao instanciada");
									erro = true;
									break;
								}
								// operacao invalida no codigo fonte ====> Duas variaveis juntas sem operador
								if(caractereAnterior >= 'a' && caractereAnterior <= 'z'){
									System.out.println("\tErro:\nlinha "+(posicaoElemento+1)+": operacao invalida em \'"+caractereAnterior+simbolo+"\'");
									erro = true;
									break;
								}

								if(caractereAnterior == '*'){
									Integer numeroAnterior = Integer.parseInt(pilhaNumeros.pop());
									Integer numeroAtual = variaveis.get(atual);
									pilhaSinais.pop();
									// Adiciona a multiplicação de volta na pilha
									pilhaNumeros.push((numeroAnterior * numeroAtual) + "");
								}
								else if(caractereAnterior == '+'){
									pilhaNumeros.push(String.valueOf(variaveis.get(atual)));
								}
								else{
									System.out.println("\tErro:\nlinha "+(posicaoElemento+1)+": Formato numerico incorreto");
									erro = true;
									break;
								}
							}
							else if(simbolo >= '0' && simbolo <= '9'){
								// Caractere anterior e o atual são numeros
								if(caractereAnterior >= '0' && caractereAnterior <= '9'){
									// Concatena o algarismo dos numeros
									pilhaNumeros.push(pilhaNumeros.pop() + simbolo);
								}
								else{
									if(caractereAnterior == '*'){
										// Verifica caracteres posteriores pra ver se o numero tem mais de 1 algarismo
										if(i < expressao.length() - 2){
											for(int j = i+1; j < expressao.length(); j++){
												char simboloPosterior = expressao.charAt(j);
												if(simboloPosterior >= '0' && simboloPosterior <= '9'){
													// anterior = simboloPosterior + "";//atual.charAt(atual.length() - 1) + "";
													atual = atual + simboloPosterior;
												}
												else{
													i = j - 1;
													break;
												}
											}
										}
										Integer numeroAnterior = Integer.parseInt(pilhaNumeros.pop());
										Integer numeroAtual = Integer.parseInt(atual);
										pilhaSinais.pop();
										// Adiciona a multiplicação de volta na pilha
										pilhaNumeros.push((numeroAnterior * numeroAtual) + "");
									}
									else if(caractereAnterior == '+'){
										pilhaNumeros.push(atual);
									}
									else{
										System.out.println("\tErro:\nlinha "+(posicaoElemento+1)+": Formato numerico incorreto");
										erro = true;
										break;
									}
								}
							}
							else if(simbolo == '*' || simbolo == '+'){
								if(caractereAnterior == '*' || caractereAnterior == '+'){
									System.out.println("\tErro:\nlinha "+(posicaoElemento+1)+": operacao invalida em \'"+caractereAnterior+simbolo+"\'");
									erro = true;
									break;
								}
								pilhaSinais.push(atual);
							}
						}
						else{	// Primeiro caractere
							atual = String.valueOf(simbolo);
							anterior = String.valueOf(simbolo);
							if(simbolo >= 'a' && simbolo <= 'z'){
								if(variaveis.get(atual) == null){
									System.out.println("\tErro:\nlinha "+(posicaoElemento+1)+": Variavel \'" + atual + "\' nao instanciada");
									erro = true;
									break;
								}
								else{
									pilhaNumeros.push(String.valueOf(variaveis.get(atual)));
								}
							}
							else if(simbolo == '+' || simbolo == '*'){
								System.out.println("\tErro:\nlinha "+(posicaoElemento + 1)+": Formato incorreto de atribuição");
								erro = true;
								break;
							}
							else{
								pilhaNumeros.push(atual);
							}
						}
					}
					if(!erro){
						// Percorre as pilhas pra terminar o calculo da soma
						while(pilhaNumeros.size() > 1){
							Integer numeroAnterior = Integer.parseInt(pilhaNumeros.pop());
							Integer numeroAtual = Integer.parseInt(pilhaNumeros.pop());
							pilhaSinais.pop();

							// Adiciona a multiplicação de volta na pilha
							pilhaNumeros.push((numeroAnterior + numeroAtual) + "");
							
						}

						if(pilhaNumeros.size() == 1){
							variaveis.put(operando, Integer.parseInt(pilhaNumeros.pop()));
						}
					}
				}
				else if(verificaExpressao(linha) == 2){		// Comando de impressao extenso que trata para mais de 2 caracteres
					pilhaNumeros.clear();
					pilhaSinais.clear();
					String expressao = linha.substring(0, linha.length() - 1);
					for(int i = 0; i < expressao.length(); i++){
						char simbolo = expressao.charAt(i);
						if(i > 0){
							atual = String.valueOf(simbolo);
							anterior = String.valueOf(expressao.charAt(i - 1));
							char caractereAnterior = expressao.charAt(i - 1);//anterior.charAt(anterior.length() - 1);
							if(simbolo >= 'a' && simbolo <= 'z'){
								// =======> TRATAMENTO DE ERROS
								// VARIAVEL NULA
								if(variaveis.get(atual) == null){
									System.out.println("\tErro:\nlinha "+(posicaoElemento+1)+": Variavel \'" + atual + "\' nao instanciada");
									erro = true;
									break;
								}
								// operacao invalida no codigo fonte ====> Duas variaveis juntas sem operador
								if(caractereAnterior >= 'a' && caractereAnterior <= 'z'){
									System.out.println("\tErro:\nlinha "+(posicaoElemento+1)+": operacao invalida em \'"+caractereAnterior+simbolo+"\'");
									erro = true;
									break;
								}

								if(caractereAnterior == '*'){
									Integer numeroAnterior = Integer.parseInt(pilhaNumeros.pop());
									Integer numeroAtual = variaveis.get(atual);
									pilhaSinais.pop();
									// Adiciona a multiplicação de volta na pilha
									pilhaNumeros.push((numeroAnterior * numeroAtual) + "");
								}
								else if(caractereAnterior == '+'){
									pilhaNumeros.push(String.valueOf(variaveis.get(atual)));
								}
								else{
									System.out.println("\tErro:\nlinha "+(posicaoElemento+1)+": Formato numerico incorreto");
									erro = true;
									break;
								}
							}
							else if(simbolo >= '0' && simbolo <= '9'){
								// Caractere anterior e o atual são numeros
								if(caractereAnterior >= '0' && caractereAnterior <= '9'){
									// Concatena o algarismo dos numeros
									pilhaNumeros.push(pilhaNumeros.pop() + simbolo);
								}
								else{
									if(caractereAnterior == '*'){
										// Verifica caracteres posteriores pra ver se o numero tem mais de 1 algarismo
										if(i < expressao.length() - 2){
											for(int j = i+1; j < expressao.length(); j++){
												char simboloPosterior = expressao.charAt(j);
												if(simboloPosterior >= '0' && simboloPosterior <= '9'){
													// anterior = simboloPosterior + "";//atual.charAt(atual.length() - 1) + "";
													atual = atual + simboloPosterior;
												}
												else{
													i = j - 1;
													break;
												}
											}
										}
										Integer numeroAnterior = Integer.parseInt(pilhaNumeros.pop());
										Integer numeroAtual = Integer.parseInt(atual);
										pilhaSinais.pop();
										// Adiciona a multiplicação de volta na pilha
										pilhaNumeros.push((numeroAnterior * numeroAtual) + "");
									}
									else if(caractereAnterior == '+'){
										pilhaNumeros.push(atual);
									}
									else{
										System.out.println("\tErro:\nlinha "+(posicaoElemento+1)+": Formato numerico incorreto");
										erro = true;
										break;
									}
								}
							}
							else if(simbolo == '*' || simbolo == '+'){
								if(caractereAnterior == '*' || caractereAnterior == '+'){
									System.out.println("\tErro:\nlinha "+(posicaoElemento+1)+": operacao invalida em \'"+caractereAnterior+simbolo+"\'");
									erro = true;
									break;
								}
								pilhaSinais.push(atual);
							}
						}
						else{	// Primeiro caractere
							atual = String.valueOf(simbolo);
							anterior = String.valueOf(simbolo);
							if(simbolo >= 'a' && simbolo <= 'z'){
								if(variaveis.get(atual) == null){
									System.out.println("\tErro:\nlinha "+(posicaoElemento+1)+": Variavel \'" + atual + "\' nao instanciada");
									erro = true;
									break;
								}
								else{
									pilhaNumeros.push(String.valueOf(variaveis.get(atual)));
								}
							}
							else if(simbolo == '+' || simbolo == '*'){
								System.out.println("\tErro:\nlinha "+(posicaoElemento + 1)+": Formato incorreto de atribuição");
								erro = true;
								break;
							}
							else{
								pilhaNumeros.push(atual);
							}
						}
					}
					if(!erro){
						// Percorre as pilhas pra terminar o calculo da soma
						while(pilhaNumeros.size() > 1){
							Integer numeroAnterior = Integer.parseInt(pilhaNumeros.pop());
							Integer numeroAtual = Integer.parseInt(pilhaNumeros.pop());
							pilhaSinais.pop();

							// Adiciona a multiplicação de volta na pilha
							pilhaNumeros.push((numeroAnterior + numeroAtual) + "");
							
						}

						if(pilhaNumeros.size() == 1){
							System.out.println("O valor impresso eh: " + Integer.parseInt(pilhaNumeros.pop()));
						}
					}
				}
				else{
					System.out.println("\tErro:\nlinha "+(posicaoElemento+1)+": Formato incorreto");
					erro = true;
				}
			}
			else{
				erro = true;
			}
			posicaoElemento++;
		}while((posicaoElemento < elementos.size()) && !erro);
		if(erro){
			System.out.println("Erro presente no codigo fonte");
		}
		imprimirHashMap(variaveis);
	}

	public void imprimirKeys(HashMap<String, Integer> lista){
		System.out.println("\nImprime keys do Hashmap: \n");
		for (String string : lista.keySet()) {
			System.out.println(string);
		}
	}

	public void imprimirHashMap(HashMap<String, Integer> lista){
		System.out.println("\nImprime Hashmap: \n");
		System.out.println(lista);
	}

    public ArrayList<String> lerArquivo(String caminhoArquivo){
		try{
			Path caminhoLeitura = Paths.get(caminhoArquivo); //Caminho do arquivo de texto
			if(caminhoLeitura.toFile().exists() == true){	
				Charset utf8charset = Charset.forName("UTF-8");
				FileInputStream caminhoDoArquivo = new FileInputStream(caminhoLeitura.toFile().getPath());
				InputStreamReader tradutorEncode = new InputStreamReader(caminhoDoArquivo, utf8charset); 
				BufferedReader bufferLeitura = new BufferedReader(tradutorEncode);	
				String linha = bufferLeitura.readLine();
				ArrayList<String> linhas = new ArrayList<String>();
				System.out.println("-------------------");
				System.out.println("\nLeitura do arquivo de texto.\n");
                while(linha != null){
                    System.out.println(linha);
					linhas.add(linha);
					linha = bufferLeitura.readLine();
				}
				bufferLeitura.close();
                System.out.println("\nBanco de dados importado com sucesso.\n");
				System.out.println("-------------------");
				return linhas;
			}
			else {
                System.out.println("\nBanco de dados não lido, caminho/arquivo não encontrado\n");
			}
		}catch(Exception erro){		//Tratamento de erros
			erro.printStackTrace();
			System.out.println("\nErro na leitura de arquivos\n");
		}
		return null;
	}

	public ArrayList<String> removeEspacos(ArrayList<String> lista){
		ArrayList<String> lista2 = new ArrayList<String>();
		for (String string : lista) {
			lista2.add(string.replaceAll(" ", ""));
		}
		return lista2;
	}

	public void imprimeLista(ArrayList<String> codigo){
		System.out.println("Imprime lista: ");
		for (String string : codigo) {
			System.out.println(string);
		}
		System.out.println("-----------\n");
	}

	public int verificaExpressao(String linha){
		if(Pattern.matches("^[a-z]\\s*;$", linha)){	  // Comando de impressao
			return 0;
		}
		else if(Pattern.matches(
			"^[a-z]\\s*(((?:=\\s*(([0-9]{1,})|(([a-z]){1}))\\s*){1})|(=\\s*(([0-9]*)|([a-z]){1})(?:\\s*[+*]\\s*(([0-9]*)|([a-z]{1}))\\s*)+));$", linha
		)){	// Padrao de atribuição
			// Regex captura as expressoes que forem  "a = t;" ou "a = 525;" ou "a = 85 + b + c * 52;"
			return 1;
		}
		else if(Pattern.matches(
			"^\\s*(([0-9]*)|([a-z]){1})(?:\\s*[+*]\\s*(([0-9]*)|([a-z]{1}))\\s*)+;$", linha
		)){	// Comando de impressao mais extenso
			return 2;
		}
		else{
			return -1;
		}
	}

    public static void main(String[] args) throws Exception {
        App main = new App();
		ArrayList<String> codigo = main.lerArquivo("./Arquivos/codigo.txt");
		main.calcPrimitiva(codigo);
    }
}
