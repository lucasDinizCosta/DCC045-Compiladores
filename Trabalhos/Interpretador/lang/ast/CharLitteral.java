package lang.ast;

import lang.ast.LValue;
import lang.interpreter.Visitor;

public class CharLitteral extends LValue{
    /**
     * ---- Regra
     * sexp: CHAR  # CharLitteral
    */

    private char value; 
    
    public CharLitteral(int line, int column, char value){
        super(line, column);
        this.value = value;
    }

    public void setValue(char value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }

    @Override
    public String toString(){
        return " "+ value + " ";
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
