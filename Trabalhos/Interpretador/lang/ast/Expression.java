package lang.ast;

import lang.ast.Node;

public abstract class Expression extends Node {
    // Classe abstrata para tratar os nós derivados de 'exp' na gramática
    // Utilizado também para expressões que trabalham com um operador e dois operandos   

    public Expression(int line, int column) {
        super(line, column);
    }
}