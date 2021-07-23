package lang.ast;

import lang.ast.Expression;
import lang.interpreter.Visitor;

public class BooleanValue extends Expression{
    /**
     * ---- Regra:
     * sexp: TRUE  # True
     * E também a regra:
     * sexp: FALSE  # False
     * -- Attribution é um tipo de comando
    */

    private Boolean value;

    public BooleanValue (int line, int column, Boolean value){
        super(line, column);
        this.value = value;
    }

    public void setValue(Boolean v) {
        this.value = v;
    }

    public Boolean getValue() {
        return value;
    }

    @Override
    public String toString(){
        return " " + value.toString() + " ";
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

}
