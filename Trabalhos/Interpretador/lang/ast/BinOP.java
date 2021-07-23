package lang.ast;

import lang.ast.Expression;

public class BinOP extends Expression{
    // Protected para os filhos poderem acessar
    protected Expression left;      
    protected Expression right;

    public BinOP(int line, int column, Expression left, Expression right) {
        super(line, column);
        this.left = left;
        this.right = right;
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }

    public void setLeft(Expression left) {
        this.left = left;
    }

    public void setRight(Expression right) {
        this.right = right;
    }
}
