package lang.ast;

import lang.ast.Expression;

import lang.interpreter.Visitor;

public class Minus extends Expression {
    /**
     * ---- Regra
     * sexp: <assoc=right> MINUS sexp   # Minus
    */
    
    private Expression exp;

    public Minus(int line, int column, Expression exp) {
        super(line, column);
        this.exp = exp;
    }

    @Override
    public String toString(){
        return " - " + exp.toString();
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
