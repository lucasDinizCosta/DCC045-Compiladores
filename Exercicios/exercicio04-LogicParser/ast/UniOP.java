package ast;

public abstract class UniOP extends Expr {
    private Expr r;

    public UniOP(int lin, int col, Expr r) {
        super(lin, col);
        this.r = r;
    }

    public void setRight(Expr n) {
        r = n;
    }

    public Expr getRight() {
        return r;
    }
}
