/********************************************************
* Trabalho de Teoria dos Compiladores(DCC045)(2021/1)   *
*                                                       *
* Projeto do Interpretador para a Linguagem Lang        *
* Nome: Lucas Diniz da Costa -- Matricula: 201465524C   *
*                                                       *
*********************************************************/
package lang.ast;

import lang.ast.Expression;
import lang.interpreter.Visitor;

public class ID extends Expression{
    private String value;

    public ID(int line, int column, String value){
        super(line, column);
        this.value = value;
    }

    @Override
    public String toString(){
        return String.valueOf(value);
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
