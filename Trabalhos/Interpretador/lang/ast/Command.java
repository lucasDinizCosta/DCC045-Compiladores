package lang.ast;

import lang.ast.Node;
import lang.interpreter.Visitor;

public abstract class Command extends Node {
    // Classe abstrata para tratar os nós derivados de 'cmd' na gramática
    
    public Command(int line, int column) {
        super(line, column);
    }
    
    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}