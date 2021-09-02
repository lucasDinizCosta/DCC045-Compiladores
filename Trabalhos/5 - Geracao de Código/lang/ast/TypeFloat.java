/**********************************************************
* Trabalho de Teoria dos Compiladores(DCC045)(2021/1)     *
*            Linguagem Lang                               *
* Nome: Lucas Diniz da Costa       Matricula: 201465524C  *
*       Luís Henrique Simplício Ribeiro       201635037   *                                               *
***********************************************************/
package lang.ast;

import lang.ast.Type;
import lang.interpreter.Visitor;

public class TypeFloat extends Type{
    /**
     * ---- Regra
     * btype: FLOAT_TYPE    # BTypeFloat
    */
    public TypeFloat(int line, int column){
        super(line, column);
    }    

    @Override
    public String toString(){
        return "Float";
    }

    @Override
    public void accept(Visitor v){ 
        v.visit(this);
    }
}
