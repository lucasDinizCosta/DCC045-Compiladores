package lang.ast;

import lang.ast.Command;
import lang.interpreter.Visitor;

import java.util.List;
import java.util.ArrayList;

public class CommandsList extends Command{
    /**
     * ---- Regra
     * cmd: OPEN_BRACES cmd* CLOSE_BRACES      # CommandsList
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
        String text = " { ";
        for (Command command : commands) {
            text = text + command.toString();
        }
        text = text + " } ";
        return text;
    }
    
    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
