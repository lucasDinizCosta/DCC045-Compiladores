import java.io.IOException;

public class Teste {
    /** 
     * Pra compilar pelo terminal: javac -cp . Teste.java
     * Pra rodar pelo terminal: java -cp . Foo sample1.txt
     * Para limpar as compilações anteriores: rm *.class
    */
    
    public static void main (String args[]) throws IOException{
        // Cria o analisador léxico com o primeiro argumento passado como parametro(nome do arquivo)
        Scanner lex = new Scanner(args[0]);

        // Faz a leitura do próximo Token
        Token token = lex.nextToken();
        // Enquanto ainda tiver Token será impresso o Token
        while(token.getToken() != Token.EOF){
            System.out.println("Token: " + token.getToken() + " lexeme: " + token.getLexeme());
            token = lex.nextToken();
        }
    }
}
