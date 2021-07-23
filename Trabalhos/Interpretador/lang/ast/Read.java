package lang.ast;

import lang.ast.Command;
import lang.ast.Expression;

import lang.interpreter.Visitor;

public class Read extends Command {
    /**
     * ---- Regra
     * cmd: READ lvalue SEMI  # Read
    */
    
    private Lvalue lvalue;

    public Read (int line, int column, Lvalue lvalue){
        super(line, column);
        this.lvalue = lvalue;
    }

    @Override
    public String toString(){
        return " read " + lvalue.toString() + " ; ";
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    public Lvalue getLvalue() {
        return lvalue;
    }
}