/**********************************************************
* Trabalho de Teoria dos Compiladores(DCC045)(2021/1)     *
*            Linguagem Lang                               *
* Nome: Lucas Diniz da Costa       Matricula: 201465524C  *
*       Luís Henrique Simplício Ribeiro       201635037   *                                               *
***********************************************************/
package lang.ast;

import lang.ast.LValue;
import lang.interpreter.Visitor;

public class PexpIdentifier extends LValue {
    /**
     * ---- Regra
     * pexp: lvalue    # PexpIdentifier       // Chama lValue e o ID
    */
    
    private String id;

    public PexpIdentifier (int line, int column, String id){
        super(line, column);
        this.id = id;
    }

    @Override
    public String toString(){
        return id.toString();
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
