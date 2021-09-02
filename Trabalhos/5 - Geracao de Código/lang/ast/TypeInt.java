/**********************************************************
* Trabalho de Teoria dos Compiladores(DCC045)(2021/1)     *
*            Linguagem Lang                               *
* Nome: Lucas Diniz da Costa       Matricula: 201465524C  *
*       Luís Henrique Simplício Ribeiro       201635037   *                                               *
***********************************************************/
package lang.ast;

import lang.ast.Type;
import lang.interpreter.Visitor;

public class TypeInt extends Type{
    /**
     * ---- Regra
     * btype: INT_TYPE     # BTypeInt
    */
    public TypeInt(int line, int column){
        super(line, column);
    }    

    @Override
    public String toString(){
        return "Int";
    }

    @Override
    public void accept(Visitor v){ 
        v.visit(this);
    }
}
