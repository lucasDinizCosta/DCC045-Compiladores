/**********************************************************
* Trabalho de Teoria dos Compiladores(DCC045)(2021/1)     *
*            Linguagem Lang                               *
* Nome: Lucas Diniz da Costa       Matricula: 201465524C  *
*       Luís Henrique Simplício Ribeiro       201635037   *                                               *
***********************************************************/
package lang.ast;

import lang.ast.Expression;
import lang.interpreter.Visitor;
public class Null extends Expression {
    /**
     * ---- Regra
     * sexp: NULL  # Null
     *
    */

    private String value = null;
    
    public Null (int line, int column){
        super(line, column);
    }

    public String getValue(){
        return value;
    }

    @Override
    public String toString(){
        return "null";
        // return value.toString();
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
