/********************************************************
* Trabalho de Teoria dos Compiladores(DCC045)(2021/1)   *
*                                                       *
* Projeto do Interpretador para a Linguagem Lang        *
* Nome: Lucas Diniz da Costa -- Matricula: 201465524C   *
*                                                       *
*********************************************************/
package lang.ast;

import lang.ast.Command;
import lang.interpreter.Visitor;

import java.util.List;
import java.util.ArrayList;

public class CommandsList extends Command{
    /**
     * ---- Regra: --------
     * cmd: OPEN_BRACES cmd* CLOSE_BRACES      # CommandsList
     * --------------------
     * => Possibilita criar escopo de bloco
     * Exemplo: 
     * func(k :: int){
     *       x = 2;
     *       {
     *           y = 4;
     *           k = 5;
     *       }
     * }
    */

    private List<Command> commands;

    public CommandsList(int line, int column, List<Command> commands){
        super(line, column);
        this.commands = commands;
    }

    public CommandsList(int line, int column){
        super(line, column);
        this.commands = new ArrayList<Command>();
    }

    public List<Command> getCommands(){
        return (this.commands);
    }

    public void setCommands(List<Command> cmd){
        this.commands = cmd;
    }

    public void addCommand(Command cmd){
        commands.add(cmd);
    }

    @Override
    public String toString(){
        String text = " { \n";
        for (Command command : commands) {
            text = text + command.toString() + "\n";
        }
        text = text + " } ";
        return text;
    }
    
    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
