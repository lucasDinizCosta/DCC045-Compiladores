/********************************************************
* Trabalho de Teoria dos Compiladores(DCC045)(2021/1)   *
*                                                       *
* Projeto do Interpretador para a Linguagem Lang        *
* Nome: Lucas Diniz da Costa -- Matricula: 201465524C   *
*                                                       *
*********************************************************/
package lang.ast;

import lang.interpreter.Visitor;

public class TypeData extends Type {

    private String id;

    public TypeData(int line, int column, String  id) {
        super(line, column);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    public void setId(String id) {
        this.id = id;
    }
}