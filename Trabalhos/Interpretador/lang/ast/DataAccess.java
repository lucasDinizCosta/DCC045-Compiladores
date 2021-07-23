/********************************************************
* Trabalho de Teoria dos Compiladores(DCC045)(2021/1)   *
*                                                       *
* Projeto do Interpretador para a Linguagem Lang        *
* Nome: Lucas Diniz da Costa -- Matricula: 201465524C   *
*                                                       *
*********************************************************/
package lang.ast;

import lang.ast.LValue;
import lang.interpreter.Visitor;

public class DataAccess extends LValue{
    /**
     * ---- Regra
     * lValue: <assoc=left> lvalue DOT ID     # DataAccess
    */
    private LValue lvalue;
    private String id;

    public DataAccess(int line, int column, LValue lvalue, String id){
        super(line, column);
        this.lvalue = lvalue;
        this.id = id;
    }

    public void setLValue(LValue lvalue){
        this.lvalue = lvalue;
    }

    public void setId(String id){
        this.id = id;
    }

    public LValue getLValue(){
        return (this.lvalue);
    }

    public String getId(){
        return (this.id);
    }

    @Override
    public String toString(){
        return lvalue.toString() + '.' + id.toString();
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

}
