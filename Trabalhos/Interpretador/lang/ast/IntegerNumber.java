package lang.ast;

import lang.ast.LValue;
import lang.interpreter.Visitor;

public class IntegerNumber extends LValue{
    /**
     * ---- Regra
     * sexp: INT   # IntegerNumber
    */
    private Integer value;
    
    public IntegerNumber(int line, int column, Integer value){
        super(line, column);
        this.value = value;
    }

    public void setValue(Integer value){
        this.value = value;
    }

    public Integer getValue(){
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

