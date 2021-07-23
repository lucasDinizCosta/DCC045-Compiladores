package lang.ast;

import lang.ast.LValue;
import lang.ast.Expression;
import lang.interpreter.Visitor;

public class ArrayAccess extends LValue{
    /**
     * ---- Regra
     * lvalue: <assoc=left> lvalue OPEN_BRACKET exp CLOSE_BRACKET # ArrayAccess
    */
    private LValue lvalue;
    private Expression exp;

    public ArrayAccess(int line, int column, LValue lvalue, Expression exp){
        super(line, column);
        this.lvalue = lvalue;
        this.exp = exp;
    }

    public LValue getLValue() {
        return lvalue;
    }

    public Expression getExp() {
        return exp;
    }

    @Override
    public String toString(){
        return lvalue.toString() + " [ " + exp.toString() + " ] ";
    }

    @Override
    public String getId() {
        return lvalue.getId();
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
