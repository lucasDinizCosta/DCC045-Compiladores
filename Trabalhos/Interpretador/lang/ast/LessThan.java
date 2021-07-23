package lang.ast;

import lang.ast.Expression;
import lang.ast.BinOP;

import lang.interpreter.Visitor;

public class LessThan extends BinOP {
    /**
     * ---- Regra
     * rexp: aexp LESS_THAN aexp   # LessThan
    */
    
    public LessThan(int line, int column, Expression left, Expression right) {
        super(line, column, left, right);
    }

    @Override
    public String toString(){
        return left.toString() + " < " + right.toString();
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

}

