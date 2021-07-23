package lang.ast;

import java.util.ArrayList;
import java.util.List;

import lang.ast.Command;
import lang.ast.Expression;
import lang.ast.LValue;
import lang.ast.FCallParams;

import lang.interpreter.Visitor;

public class FunctionCall extends Command{
    /**
     * ---- Regra
     * cmd: ID OPEN_PARENT exps? CLOSE_PARENT (LESS_THAN lvalue 
     * (COMMA lvalue)* GREATER_THAN)? SEMI   # FunctionCall
     * 
     * Exemplo: divmod(5, 2)<q, r>;     // Será retornada 2 valores e armazenados na variavel q e r
    */
    private String id;
    private FCallParams functionCallParams;
    private List<LValue> lvalues = new ArrayList<>();
    private List<Expression> exps = new ArrayList<>();

    public FunctionCall (int line, int column, String id){
        super(line, column);
        this.id = id;
    }

    public FunctionCall (int line, int column, String id, FCallParams params){
        super(line, column);
        this.id = id;
        this.functionCallParams = params;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public FCallParams getFCallParams() {
        return functionCallParams;
    }

    public void setFCallParams(FCallParams fCallParams) {
        this.functionCallParams = fCallParams;
    }

    public List<LValue> getLValues() {
        return lvalues;
    }

    public void setLValues(List<LValue> lvalues) {
        this.lvalues = lvalues;
    }

    public List<Expression> getExps() {
        return exps;
    }

    public void setExps(List<Expression> exps) {
        this.exps = exps;
    }

    public void addExp(Expression exp){
        exps.add(exp);
    }

    public void addLValue(LValue lvalue){
        lvalues.add(lvalue);
    }

    @Override
    public String toString(){
        StringBuilder bld = new StringBuilder("");
        for (LValue lvalue : lvalues) {
            bld.append(lvalue + ", ");
        }
        if(exps != null){
            if(bld.length() > 0){
                return id.toString() + " ( " +  exps.toString() + " ) " + 
                " < " + bld.substring(0, bld.length() - 2) + " > " + " ; ";
            }
            return id.toString() + " ( " +  exps.toString() + " ) " + "" + " ; ";
        }
        else{
            if(bld.length() > 0){
                return id.toString() + " ( " + "" + " ) " + 
                " < " + bld.substring(0, bld.length() - 2) + " > " + " ; ";
            }
            return id.toString() + " ( " +  "" + " ) " + "" + " ; ";
        }
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
