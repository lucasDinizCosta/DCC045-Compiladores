/********************************************************
* Trabalho de Teoria dos Compiladores(DCC045)(2021/1)   *
*                                                       *
* Projeto do Interpretador para a Linguagem Lang        *
* Nome: Lucas Diniz da Costa -- Matricula: 201465524C   *
*                                                       *
*********************************************************/
package lang.ast;

import lang.ast.Command;
import lang.ast.Expression;

import lang.interpreter.Visitor;

public class Iterate extends Command {
    /**
     * ---- Regra
     * cmd: ITERATE OPEN_PARENT exp CLOSE_PARENT cmd  # Iterate
    */
    private Iterate it;
    private Expression exp;
    private Command cmd;

    public Iterate (int line, int column, Iterate it, Expression exp, Command cmd){
        super(line, column);
        this.it = it;
        this.exp = exp;
        this.cmd = cmd;
    }

    @Override
    public String toString(){
        return it.toString() + " ( " + exp.toString() + " ) " + cmd.toString();
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    public Iterate getIterate() {
        return it;
    }

    public void setIterate(Iterate it) {
        this.it = it;
    }

    public Expression getExp() {
        return exp;
    }

    public void setExp(Expression exp) {
        this.exp = exp;
    }

    public Command getCmd() {
        return cmd;
    }

    public void setCmd(Command cmd) {
        this.cmd = cmd;
    }

}
