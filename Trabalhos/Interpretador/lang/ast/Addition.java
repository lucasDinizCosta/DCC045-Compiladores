package lang.ast;

import lang.ast.BinOP;
import lang.ast.Expression;

import lang.interpreter.Visitor;

public class Addition extends BinOP{
    /**
     * ---- Regra
     * aexp: aexp PLUS mexp    # AdditionOperation
    */

    public Addition(int line, int column, Expression left, Expression right){
        super(line, column, left, right);
    }

    @Override
    public String toString(){
        return (this.left.toString() + " + " + this.right.toString());
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
