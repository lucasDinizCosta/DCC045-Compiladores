package lang.ast;

import lang.ast.BinOP;
import lang.ast.Expression;
import lang.interpreter.Visitor;

public class Multiplication extends BinOP{
    /**
     * ---- Regra
     * mexp:<assoc=left> mexp TIMES sexp   # MultiplicationOperation
    */

    public Multiplication(int line, int column, Expression left, Expression right){
        super(line, column, left, right);
    }

    @Override
    public String toString(){
        return (left.toString() + " * " + right.toString());
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
