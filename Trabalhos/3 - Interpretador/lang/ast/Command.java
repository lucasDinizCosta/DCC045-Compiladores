/********************************************************
* Trabalho de Teoria dos Compiladores(DCC045)(2021/1)   *
*                                                       *
* Projeto do Interpretador para a Linguagem Lang        *
* Nome: Lucas Diniz da Costa -- Matricula: 201465524C   *
*                                                       *
*********************************************************/
package lang.ast;

import lang.ast.Node;
import lang.interpreter.Visitor;

public abstract class Command extends Node {
    // Classe abstrata para tratar os nós derivados de 'cmd' na gramática
    
    public Command(int line, int column) {
        super(line, column);
    }
    
    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}