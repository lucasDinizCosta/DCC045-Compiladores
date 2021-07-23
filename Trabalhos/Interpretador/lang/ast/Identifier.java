package lang.ast;

import java.util.ArrayList;
import java.util.List;

import lang.ast.LValue;

import lang.interpreter.Visitor;

public class Identifier extends Lvalue {
    /**
     * ---- Regra
     * lvalue: ID      # Identifier
     *
    */
    
    private String id;

    public Identifier (int line, int column, String id){
        super(line, column);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString(){
        return id.toString();
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

}
