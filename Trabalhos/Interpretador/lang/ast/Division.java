package lang.ast;

import lang.ast.BinOP;
import lang.ast.Expression;
import lang.interpreter.Visitor;

public class Division extends BinOP{
    /**
     * ---- Regra
     * mexp: <assoc=left> mexp SLASH sexp   # DivisionOperation
    */

    public Division(int line, int column, Expression left, Expression right){
        super(line, column);
        this.left = left;
        this.right = right;
    }

    @Override
    public void toString(){
        return (left.toString() + " / " + right.toString());
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
