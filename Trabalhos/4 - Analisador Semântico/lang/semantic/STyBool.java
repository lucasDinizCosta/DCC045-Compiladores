/********************************************************
* Trabalho de Teoria dos Compiladores(DCC045)(2021/1)   *
*            Linguagem Lang                             *
* Nome: Lucas Diniz da Costa -- Matricula: 201465524C   *
*                                                       *
*********************************************************/
package lang.semantic;
// Define o tipo booleano para a analise Semantica no TypeCheckVisitor
public class STyBool extends SType{
    private static STyBool st = new STyBool();

    private STyBool(){
    }

    public static STyBool newSTyBool() {
        return st;
    }

    public boolean match(SType v) {
        return (v instanceof STyErr) || (v instanceof STyBool);
    }

    public String toString() {
        return "Bool";
    }
}
