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
    private String id;              // Atributo do objeto
    private String dataId;          // Nome do objeto

    public DataAccess (int line, int column, LValue lvalue, String id, String dataId){
        super(line, column);
        this.lvalue = lvalue;
        this.id = id;
        this.dataId = dataId;
    }

    public LValue getLValue() {
        return lvalue;
    }

    public String getId() {
        return id;
    }

    public String getDataId() {
        return dataId;
    }

    @Override
    public String toString(){
        return lvalue.toString() + "." + id.toString();
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

}
