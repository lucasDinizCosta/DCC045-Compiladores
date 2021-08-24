/********************************************************
* Trabalho de Teoria dos Compiladores(DCC045)(2021/1)   *
*            Linguagem Lang                             *
* Nome: Lucas Diniz da Costa -- Matricula: 201465524C   *
*                                                       *
*********************************************************/
package lang.semantic;

// Define o tipo Null para a analise semantica no typeCheckVisitor
public class STyNull extends SType{
    private static STyNull st = new STyNull();

    private STyNull() {
    }

    public static STyNull newSTyNull() {
        return st;
    }

    @Override
    public boolean match(SType v) {
        return (v instanceof STyErr) || (v instanceof STyNull);
    }

    @Override
    public String toString() {
        return "Null";
    }
}
