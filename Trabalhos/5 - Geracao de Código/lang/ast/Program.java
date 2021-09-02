/**********************************************************
* Trabalho de Teoria dos Compiladores(DCC045)(2021/1)     *
*            Linguagem Lang                               *
* Nome: Lucas Diniz da Costa       Matricula: 201465524C  *
*       Luís Henrique Simplício Ribeiro       201635037   *                                               *
***********************************************************/
package lang.ast;

import java.util.ArrayList;
import java.util.List;

import lang.ast.Node;
import lang.interpreter.Visitor;

public class Program extends Node{
    /*********************************************
     *              Regra                        *
     *      Program -> data* func*               *
     *                                           *
     * Armazena a lista de nós do tipo data e do *
     * tipo função                               *
    **********************************************/
    private List<Data> datas;
    private List<Function> functions;

    public Program(int line, int column){
        super(line, column);
        datas = new ArrayList<Data>();
        functions = new ArrayList<Function>();
    }

    public void addFunction(Function function){
        this.functions.add(function);
    }

    public void addData(Data data){
        this.datas.add(data);
    }

    public List<Data> getDatas() {
        return datas;
    }

    public List<Function> getFunctions() {
        return functions;
    }

    @Override
    public String toString(){
        return "Size";
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
