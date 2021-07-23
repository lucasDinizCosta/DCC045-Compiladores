public class Null extends Expression {
    /**
     * ---- Regra
     * sexp: NULL  # Null
     *
    */
    
    public Null (int line, int column){
        super(line, column);
    }

    @Override
    public String toString(){
        return "null";
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
