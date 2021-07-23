package lang.ast;

import lang.ast.Command;
import lang.ast.Expression;

import lang.interpreter.Visitor;

public class Print extends Command {
    /**
     * ---- Regra
     * cmd: PRINT exp SEMI    # Print
    */
    
    private Expression exp;

    public Print (int line, int column, Expression exp){
        super(line, column);
        this.exp = exp;
    }

    public Expression getExpression() {
        return exp;
    }   

    @Override
    public String toString(){
        return " print " + exp.toString() + " ; ";
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}