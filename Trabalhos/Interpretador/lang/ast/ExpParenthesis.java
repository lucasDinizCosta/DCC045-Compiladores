package lang.ast;

import lang.ast.Expression;
import lang.interpreter.Visitor;

public class ExpParenthesis extends Expression {
    /**
     * ---- Regra
     * pexp: NEW type (OPEN_BRACKET exp CLOSE_BRACKET)?    # TypeInstanciate 
    */
    private Expression exp;

    public ExpParenthesis (int line, int column, Expression exp){
        super(line, column);
        this.exp = exp;
    }

    @Override
    public String toString(){
        return " ( " + exp.toString() + " ) ";
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

}
