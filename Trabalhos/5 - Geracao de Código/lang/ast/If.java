/**********************************************************
* Trabalho de Teoria dos Compiladores(DCC045)(2021/1)     *
*            Linguagem Lang                               *
* Nome: Lucas Diniz da Costa       Matricula: 201465524C  *
*       Luís Henrique Simplício Ribeiro       201635037   *                                               *
***********************************************************/
package lang.ast;

import lang.ast.Command;
import lang.ast.Expression;

import lang.interpreter.Visitor;

public class If extends Command {
    /**
     * ---- Regra
     * cmd: IF OPEN_PARENT exp CLOSE_PARENT cmd   # If
     *
    */
    
    private Expression exp;
    private Command cmd;

    public If (int line, int column, Expression exp, Command cmd){
        super(line, column);
        this.exp = exp;
        this.cmd = cmd;
    }

    @Override
    public String toString(){
        return  " if ( " + exp.toString() + " ) " + cmd.toString();
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    public Expression getExp() {
        return exp;
    }

    public Command getCmd() {
        return cmd;
    }
    
}

