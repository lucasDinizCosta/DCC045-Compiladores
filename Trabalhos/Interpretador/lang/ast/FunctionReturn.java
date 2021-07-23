package lang.ast;

import lang.ast.Type;
import lang.ast.Expression;
import lang.ast.FCallParams;
import lang.interpreter.Visitor;

public class FunctionReturn extends Expression {
    /**
     * ---- Regra
     * pexp: ID OPEN_PARENT exps? CLOSE_PARENT OPEN_BRACKET exp CLOSE_BRACKET  # FunctionReturn 
     * // Como retorna 2 valores, logo precisa do funcao(parametros)[indice] Exemplo: fat(num−1)[0]
    */
    private String id;
    private FCallParams exps;
    private Expression exp;
    private Type type;

    public FunctionReturn (int line, int column, String id, FCallParams exps, Expression exp){
        super(line, column);
        this.id = id;
        this.exps = exps;
        this.exp = exp;
    }

    @Override
    public String toString(){
        return id + " ( " + (exps != null ? exps : "") + " ) [ " + exp + " ] ";
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

}