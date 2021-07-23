package lang.ast;

import lang.ast.BinOP;
import lang.interpreter.Visitor;

public class And extends BinOP{
    /**
     * ---- Regra
     * exp:<assoc=left> exp AND exp    # AndOperation
    */

    public And(int line, int column, Expression left, Expression right){
        super(line, column);
        this.left = left;
        this.right = right;
    }

    @Override
    public void toString(){
        return (left.toString() + " && " + right.toString());
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
