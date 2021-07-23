/********************************************************
* Trabalho de Teoria dos Compiladores(DCC045)(2021/1)   *
*                                                       *
* Projeto do Interpretador para a Linguagem Lang        *
* Nome: Lucas Diniz da Costa -- Matricula: 201465524C   *
*                                                       *
*********************************************************/
package lang.ast;

import lang.ast.Type;
import lang.interpreter.Visitor;

public class TypeChar extends Type{
    /**
     * ---- Regra
     * btype: CHAR_TYPE     # BTypeChar
    */

    public TypeChar(int line, int column){
        super(line, column);
    }    

    @Override
    public String toString(){
        return "Char";
    }

    @Override
    public void accept(Visitor v){ 
        v.visit(this);
    }
}
