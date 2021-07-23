package lang.ast;

import lang.ast.LValue;
import lang.interpreter.Visitor;

public class FloatNumber extends LValue{
    /**
     * ---- Regra
     * sexp: FLOAT  # FloatNumber
    */
    private Float value;
    
    public FloatNumber(int line, int column, Float value){
        super(line, column);
        this.value = value;
    }

    public void setValue(Float value){
        this.value = value;
    }

    public Float getValue(){
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