/**********************************************************
* Trabalho de Teoria dos Compiladores(DCC045)(2021/1)     *
*            Linguagem Lang                               *
* Nome: Lucas Diniz da Costa       Matricula: 201465524C  *
*       Luís Henrique Simplício Ribeiro       201635037   *                                               *
***********************************************************/
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

    @Override
    public String getId() {
        // TODO Auto-generated method stub
        return null;
    }
}
