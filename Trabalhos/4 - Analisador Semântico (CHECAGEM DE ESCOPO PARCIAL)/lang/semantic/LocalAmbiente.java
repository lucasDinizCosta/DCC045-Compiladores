/********************************************************
* Trabalho de Teoria dos Compiladores(DCC045)(2021/1)   *
*            Linguagem Lang                             *
* Nome: Lucas Diniz da Costa -- Matricula: 201465524C   *
*                                                       *
*********************************************************/
package lang.semantic;

// Armazena um ambiente para cada função no Typecheck
public class LocalAmbiente<A> extends TyEnv<A>{
    private String id; 
    private SType t;
    
    public LocalAmbiente(String id, SType t){
       this.t = t;
       this.id = id;
    }
    
    public String getFuncID(){ return id;}
    
    public SType getFuncType(){ return t;}

    @Override
    public String toString(){
        String s = "--------------- (" + id + "," + t.toString() + ") ---------------\n";
        s += super.toString();
        return s;
    }
}
