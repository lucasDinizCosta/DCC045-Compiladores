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

public class App {
	public class operacaoIndice{
		// public Integer valor = 0;
		public char op;
		public int id = 0;

		public operacaoIndice(char op, int id){
			// this.valor = valor;
			this.op = op;
			this.id = id;
		}
	}

	public void calcPrimitiva(ArrayList<String> elementos){
		/**
		 * A calculadora tem apenas dois tipos de comando:
		 * 1) Comando de atribuição;
		 * 2) Comando de impressão na tela.
		 */
		System.out.println("Calculadora primitiva");
		HashMap<String, Integer> variaveis = new HashMap<String, Integer>();
		int posicaoElemento = 0;
		int numeroLinha = 1;
		String operando;
		String linha;
		String operacaoAtribuicao;
		Boolean erro = false;
		String anterior, atual;
		List<operacaoIndice> ordemOperacao = new ArrayList<operacaoIndice>();
		Stack<Integer> pilhaNumeros = new Stack<Integer>();
		Stack<String> pilhaSinais = new Stack<String>();

		// DEBUG
		// variaveis.put("c", 1);

		do {
			linha = elementos.get(posicaoElemento);
			anterior = "";
			atual = "";
			operando = "";
			operacaoAtribuicao = "";
			if(linha.length() == 2){	// Comando de impressao na tela
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
								System.out.println("Variavel \'" + operando + "\' nao instanciada");
								erro = true;
							}
						}
						else{
							System.out.println("Erro: Falta o \';\' no final da linha");
							erro = true;
						}
					}
					else{	// Primeiro caractere
						/**
						 * a = 3;
						 * b = 4;
						 * c = a*a + b*b;
						 * c;
						 */
						atual = String.valueOf(simbolo);
						anterior = String.valueOf(simbolo);
						if(simbolo >= 'a' && simbolo <= 'z'){
							// System.out.println(simbolo);
							operando = String.valueOf(simbolo);
							//variaveis.put(String.valueOf(simbolo), 1);
						}
						else{
							erro = true;
							break;
						}
					}
				}
			}
			else if(linha.length() > 2){
				if(verificaExpressao(linha) == 1){
					System.out.println(linha);
				}
				else{
					System.out.println("Erro na linha");
					erro = true;
				}

				posicaoElemento++;
			}
			else{
				erro = true;
			}
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

	public Integer calcular(String num1, String num2, char op){
		if(op == '*'){
			return (Integer.parseInt(num1) * Integer.parseInt(num2));
		}
		else if(op == '+'){
			return (Integer.parseInt(num1) + Integer.parseInt(num2));
		}
		return 0;
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

	public void testeRegex(ArrayList<String> codigo){
		for (String string : codigo) {  
			boolean b3 = Pattern.matches("^[a-z]\\s*;$", string);  
			// boolean b3 = Pattern.matches("^[a-z]\\s*=\\s*(([a-z]{1}|[0-9]*){1}\\s*[+*]\\s*([a-z]{1}|[0-9]*)\\s*)*;$", string);  
			System.out.println(string + " --- " + b3);
		}
	}

	public int verificaExpressao(String linha){
		if(Pattern.matches("^[a-z]\\s*;$", linha)){	  // Comando de impressao
			return 0;
		}
		else if(Pattern.matches("^[a-z]\\s*=\\s*[0-9a-z]*( ){0,}([+*]( ){0,}[0-9a-z]*( ){0,})*;$", linha)){
			return 1;
		}
		else{
			return -1;
		}
	}

    public static void main(String[] args) throws Exception {
        App main = new App();
		ArrayList<String> codigo = main.lerArquivo("./Arquivos/codigo.txt"); // ArrayList<String> codigo = new ArrayList<String>();
		codigo = main.removeEspacos(codigo);
		// main.testeRegex(codigo);
		main.calcPrimitiva(codigo);
    }
}
