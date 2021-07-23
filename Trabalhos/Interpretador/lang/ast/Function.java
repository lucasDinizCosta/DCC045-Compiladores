package lang.ast;

import java.util.ArrayList;
import java.util.List;

import lang.ast.Node;
import lang.ast.Type;
import lang.ast.Command;
import lang.ast.Parameters;

import lang.interpreter.Visitor;

public class Function extends Node{
    /**
     * ---- Regra
     * func: ID OPEN_PARENT params? CLOSE_PARENT (COLON type (COMMA type)*)? 
     * OPEN_BRACES cmd* CLOSE_BRACES    # Function
    */
    private String id;
    private Parameters parameters;
    private List<Type> returnTypes;     // Tipos de retorno da função
    private List<Command> commands;     // Corpo da função

    public Function(int line, int column, ID id, Parameters parameters){
        super(line, column);
        this.id = id;
        this.parameters = parameters;
        this.returnTypes = new ArrayList<Type>();
        this.commands = new ArrayList<Command>();
    }
      
    public String getId() {
        return id;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public List<Type> getReturnTypes() {
        return returnTypes;
    }

    public List<Command> getCommands() {
        return commands;
    }

    public void addCommand(Command cmd){
        this.commands.add(cmd);
    }

    public void addReturnTypes(Type returnType){
        this.returnTypes.add(returnType);
    }

    public String toString(){
        return "";
    }

    public void setCommands(List<Command> commands) {
        this.commands = commands;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }

    public void setReturnTypes(List<Type> returnTypes) {
        this.returnTypes = returnTypes;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

}
