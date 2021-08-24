/********************************************************
* Trabalho de Teoria dos Compiladores(DCC045)(2021/1)   *
*            Linguagem Lang                             *
* Nome: Lucas Diniz da Costa -- Matricula: 201465524C   *
*                                                       *
*********************************************************/
package lang.semantic;
// Define o tipo data para a analise semantica no TypeCheckVisitor
public class STyData extends SType{
    private String name;

    @Override
    public boolean match(SType v) {
        return (v instanceof STyErr) || (v instanceof STyData);
    }
    
    @Override
    public String toString(){
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public STyData(String name) {
        this.name = name;
    }
}
