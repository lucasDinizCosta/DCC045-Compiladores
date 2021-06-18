
/******************************************************
* Trabalho de Teoria dos Compiladores(DCC045)(2021/1) *
*                                                     *
* Nome: Lucas Diniz da Costa -- Matricula: 201465524C *
******************************************************/

import java.io.FileReader;
import java.io.IOException;

public class Lang {
    public static void main(String args[]) throws IOException {
        // Instancia o analisador léxico gerado pelo Jflex
        Lexer lx = new Lexer(new FileReader(args[0]));

        // Instancia um token
        Token t = lx.nextToken();

        /// Itera sobre os tokens da entrada até chegar ao final do arquivo
        while (t != null) {
            System.out.println(t.toString());
            t = lx.nextToken();
        }
        System.out.println("Total de tokens lidos " + lx.readedTokens());
    }
}
