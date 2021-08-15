/********************************************************
* Trabalho de Teoria dos Compiladores(DCC045)(2021/1)   *
*            Linguagem Lang                             *
* Nome: Lucas Diniz da Costa -- Matricula: 201465524C   *
*                                                       *
*********************************************************/
package lang.semantic;


// Define o tipo de erro semantico no TypeCheckVisitor
public class STyErr extends SType {

    private static STyErr st = new STyErr();

    private STyErr() {
    }

    public static STyErr newSTyErr() {
        return st;
    }

    @Override
    public boolean match(SType v) {
        return true;
    }

    @Override
    public String toString() {
        return "TyError";
    }

}
