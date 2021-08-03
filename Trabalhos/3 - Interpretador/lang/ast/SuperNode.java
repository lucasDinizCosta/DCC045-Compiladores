/********************************************************
* Trabalho de Teoria dos Compiladores(DCC045)(2021/1)   *
*                                                       *
* Projeto do interpretador para a Linguagem Lang        *
* Nome: Lucas Diniz da Costa -- Matricula: 201465524C   *
*                                                       *
*********************************************************/
package lang.ast;

public abstract class SuperNode{
   
   // The line and column of the node in the input text
   
    public abstract int getLine();
    public abstract int getColumn();
}


