/********************************************************
* Trabalho de Teoria dos Compiladores(DCC045)(2021/1)   *
*                                                       *
* Projeto do Interpretador para a Linguagem Lang        *
* Nome: Lucas Diniz da Costa -- Matricula: 201465524C   *
*                                                       *
*********************************************************/
package lang.ast;

import lang.ast.Type;

import lang.interpreter.Visitor;
public class TypeArray extends Type{
    /**
     * ---- Regra
     * type: type OPEN_BRACKET CLOSE_BRACKET   # TypeDeclaration   
     * -- tipo de array. Exemplo: Int[]
     */
    private Type type;

    public TypeArray(int line, int column, Type type) {
        super(line, column);
        this.type = type;
    }
    
    public Type getType() {
        return type;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    @Override
    public String toString() {
        return type.toString() + "[]";
    }
}
