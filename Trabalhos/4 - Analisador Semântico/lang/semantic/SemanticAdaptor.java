/********************************************************
* Trabalho de Teoria dos Compiladores(DCC045)(2021/1)   *
*            Linguagem Lang                             *
* Nome: Lucas Diniz da Costa -- Matricula: 201465524C   *
*                                                       *
*********************************************************/
package lang.semantic;

import lang.ast.SuperNode;

// Adaptador para classe de semantic. a Função parseFile deve retornar null caso o parser resulte em erro. 
public interface SemanticAdaptor {
    public abstract SuperNode parseFile(String path);
}
