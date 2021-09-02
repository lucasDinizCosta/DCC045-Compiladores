/**********************************************************
* Trabalho de Teoria dos Compiladores(DCC045)(2021/1)     *
*            Linguagem Lang                               *
* Nome: Lucas Diniz da Costa       Matricula: 201465524C  *
*       Luís Henrique Simplício Ribeiro       201635037   *                                               *
***********************************************************/
package lang.ast;

import lang.ast.Expression;
import lang.ast.Type;

import lang.interpreter.Visitor;

public class NameType extends Type{ 
// public class NameType extends Expression{ 
    /**
     * ---- Regra
     * btype: NAME_TYPE     # BTypeNameType
     * 
    */

    private String val;

    public NameType (int line, int column, String val){
        super(line, column);
        this.val = val;
    }

    public String getID(){
        return this.val;
    }

    @Override
    public String toString(){
        return String.valueOf(val);
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

}
