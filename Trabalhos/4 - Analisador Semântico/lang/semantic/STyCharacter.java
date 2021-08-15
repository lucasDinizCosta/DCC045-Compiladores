/********************************************************
* Trabalho de Teoria dos Compiladores(DCC045)(2021/1)   *
*            Linguagem Lang                             *
* Nome: Lucas Diniz da Costa -- Matricula: 201465524C   *
*                                                       *
*********************************************************/
package lang.semantic;

// Define o tipo char para a analise semantica no TypeCheckVisitor
public class STyCharacter extends SType {

    private static STyCharacter st = new STyCharacter();

    private STyCharacter(){
    }

    public static STyCharacter newSTyCharacter() {
        return st;
    }

    public boolean match(SType v) {
        return (v instanceof STyErr) || (v instanceof STyCharacter);
    }

    public String toString() {
        return "Char";
    }

}
