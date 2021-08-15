/********************************************************
* Trabalho de Teoria dos Compiladores(DCC045)(2021/1)   *
*                                                       *
* Projeto do Interpretador para a Linguagem Lang        *
* Nome: Lucas Diniz da Costa -- Matricula: 201465524C   *
*                                                       *
*********************************************************/
package lang.ast;

import lang.ast.Type;
import lang.ast.Expression;
import lang.interpreter.Visitor;

public class TypeInstanciate extends Expression {
    /**
     * ---- Regra
     * pexp: NEW type (OPEN_BRACKET exp CLOSE_BRACKET)?    # TypeInstanciate 
    */
    private Expression exp;         // Array
    private Type type;              // Outros tipos como Int, Float, Char
    private String dataName;        // Caso for tipo data

    public TypeInstanciate (int line, int column, Expression exp, Type type){
        super(line, column);
        this.exp = exp;
        this.type = type;
    }

    // Construtor para um ARRAY DE DATA
    public TypeInstanciate (int line, int column, Expression exp, String dataName){
        super(line, column);
        this.exp = exp;
        this.type = null;
        this.dataName = dataName;
    }

    // Construtor para um ARRAY DE DATA
    public TypeInstanciate (int line, int column, Expression exp, String dataName, Type type){
        super(line, column);
        this.exp = exp;
        this.type = type;
        this.dataName = dataName;
    }


    public TypeInstanciate (int line, int column, Type type){
        super(line, column);
        this.exp = null;
        this.type = type;
    }

    // Construtor para um DATA
    public TypeInstanciate (int line, int column, String dataName){
        super(line, column);
        this.exp = null;
        this.type = null;
        this.dataName = dataName;
    }

    // Construtor para um DATA
    public TypeInstanciate (int line, int column, String dataName, Type type){
        super(line, column);
        this.exp = null;
        this.type = type;
        this.dataName = dataName;
    }

    @Override
    public String toString(){
        if(type != null){
            return " new " + type + (exp != null ? (" [ " + exp + " ] ") : " ");
        }
        else
            return " new " + dataName + (exp != null ? (" [ " + exp + " ] ") : " ");
    }   

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    public Expression getExp() {
        return exp;
    }

    public void setExp(Expression exp) {
        this.exp = exp;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }
}
