/********************************************************
* Trabalho de Teoria dos Compiladores(DCC045)(2021/1)   *
*                                                       *
* Projeto do Interpretador para a Linguagem Lang        *
* Nome: Lucas Diniz da Costa -- Matricula: 201465524C   *
*                                                       *
*********************************************************/
package lang.ast;

import lang.ast.Type;
import lang.ast.Expression;
import lang.interpreter.Visitor;

public class TypeInstanciate extends Expression {
    /**
     * ---- Regra
     * pexp: NEW type (OPEN_BRACKET exp CLOSE_BRACKET)?    # TypeInstanciate 
    */
    private Expression exp;
    private Type type;

    public TypeInstanciate (int line, int column, Expression exp, Type type){
        super(line, column);
        this.exp = exp;
        this.type = type;
    }

    @Override
    public String toString(){
        return " new " + type + (exp != null ? (" [ " + exp + " ] ") : " ");
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    public Expression getExp() {
        return exp;
    }

    public void setExp(Expression exp) {
        this.exp = exp;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
