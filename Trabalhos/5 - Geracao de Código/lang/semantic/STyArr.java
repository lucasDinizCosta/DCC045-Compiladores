/********************************************************
* Trabalho de Teoria dos Compiladores(DCC045)(2021/1)   *
*            Linguagem Lang                             *
* Nome: Lucas Diniz da Costa -- Matricula: 201465524C   *
*                                                       *
*********************************************************/
package lang.semantic;

// Define o tipo array para a analise semantica no TypeCheck
public class STyArr extends SType{
    private SType a;

    public STyArr(SType t) {
        a = t;
    }

    public SType getArg() {
        return a;
    }

    @Override
    public boolean match(SType v) {
        // Verifica se é array e o tipo do array
        return (v instanceof STyErr) || (v instanceof STyArr) && (a.match(((STyArr) v).getArg()));
    }

    @Override
    public String toString() {
        return a.toString() + "[]";
    }
}
