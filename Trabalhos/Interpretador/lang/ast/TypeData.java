package lang.ast;

import lang.interpreter.Visitor;

public class TypeData extends Type {

    private String id;

    public TypeData(int line, int column, String  id) {
        super(line, column);
        this.id = id;
        // TODO Auto-generated constructor stub
    }

    public String getId() {
        return id;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    public void setId(String id) {
        this.id = id;
    }
}