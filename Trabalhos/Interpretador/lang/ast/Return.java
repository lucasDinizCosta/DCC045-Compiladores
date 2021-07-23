package lang.ast;

import java.util.ArrayList;
import java.util.List;

import lang.ast.Expression;

import lang.interpreter.Visitor;

public class Return extends Command {
    /**
     * ---- Regra
     * cmd: RETURN exp (COMMA exp)* SEMI  # Return
    */

    private List<Expression> exps = new ArrayList<>();

    public Return (int line, int column, List<Expression> exps){
        super(line, column);
        this.exps = exps;
    }

    public List<Expression> getExps() {
        return exps;
    }

    public void setExps(List<Expression> exps){
        this.exps = exps;
    }

    @Override
    public String toString(){
        StringBuilder bld = new StringBuilder("");
        for (Expression expression : exps) {
            bld.append(expression + "; ");
        }
        return "return" + (bld.length() > 0 ? bld.substring(0, bld.length() - 2) : bld.toString());
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
    
}
