/********************************************************
* Trabalho de Teoria dos Compiladores(DCC045)(2021/1)   *
*                                                       *
* Projeto do Interpretador para a Linguagem Lang        *
* Nome: Lucas Diniz da Costa -- Matricula: 201465524C   *
*                                                       *
*********************************************************/
package lang.ast;

import lang.ast.Command;
import lang.ast.LValue;

import lang.interpreter.Visitor;

public class Read extends Command {
    /**
     * ---- Regra
     * cmd: READ lvalue SEMI  # Read
    */
    
    private LValue lvalue;

    public Read (int line, int column, LValue lvalue){
        super(line, column);
        this.lvalue = lvalue;
    }

    @Override
    public String toString(){
        return " read " + lvalue.toString() + " ; ";
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    public LValue getLValue() {
        return lvalue;
    }
}