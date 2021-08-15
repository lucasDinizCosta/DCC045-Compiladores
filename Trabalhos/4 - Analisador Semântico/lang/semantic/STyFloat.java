/********************************************************
* Trabalho de Teoria dos Compiladores(DCC045)(2021/1)   *
*            Linguagem Lang                             *
* Nome: Lucas Diniz da Costa -- Matricula: 201465524C   *
*                                                       *
*********************************************************/
package lang.semantic;

// Define o tipo float para a analise semantica no TypeCheckVisitor
public class STyFloat extends SType{
    private static STyFloat st = new STyFloat();

    private STyFloat(){
    }

    public static STyFloat newSTyFloat() {
        return st;
    }

    @Override
    public boolean match(SType v) {
        return (v instanceof STyErr) || (v instanceof STyFloat);
    }

    @Override
    public String toString() {
        return "Float";
    }
}
