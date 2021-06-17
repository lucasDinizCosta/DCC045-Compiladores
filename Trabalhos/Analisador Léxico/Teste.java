/******************************************************
* Trabalho de Teoria dos Compiladores(DCC045)(2021/1) *
*                                                     *
* Nome: Lucas Diniz da Costa -- Matricula: 201465524C *
******************************************************/

import java.io.FileReader;
import java.io.IOException;

public class Teste {
     public static void main(String args[]) throws IOException {
          Lexer lx = new Lexer(new FileReader(args[0]));
          Token t = lx.nextToken();
          while (t != null) {
               System.out.println(t.toString());
               t = lx.nextToken();
          }
          System.out.println("Total de tokens lidos " + lx.readedTokens());
     }
}
