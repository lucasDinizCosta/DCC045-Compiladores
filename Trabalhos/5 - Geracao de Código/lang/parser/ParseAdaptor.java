/**********************************************************
* Trabalho de Teoria dos Compiladores(DCC045)(2021/1)     *
*            Linguagem Lang                               *
* Nome: Lucas Diniz da Costa       Matricula: 201465524C  *
*       Luís Henrique Simplício Ribeiro       201635037   *                                               *
***********************************************************/
package lang.parser;

import java.io.*;
import lang.ast.SuperNode;
import lang.parser.*;
import java.util.List;

// Adaptador para classe de parser. a Função parseFile deve retornar null caso o parser resulte em erro. 

public interface ParseAdaptor{
   public abstract SuperNode parseFile(String path);
}



