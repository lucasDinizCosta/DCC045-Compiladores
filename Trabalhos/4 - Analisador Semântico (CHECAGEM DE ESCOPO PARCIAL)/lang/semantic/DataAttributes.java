/********************************************************
* Trabalho de Teoria dos Compiladores(DCC045)(2021/1)   *
*            Linguagem Lang                             *
* Nome: Lucas Diniz da Costa -- Matricula: 201465524C   *
*                                                       *
*********************************************************/
package lang.semantic;

import java.util.ArrayList;
import java.util.List;
import lang.semantic.*;


// Classe que armazenas os tipos data no ====> TypeCheckVisitor
public class DataAttributes {

    private String nomeData;
    private ArrayList<String> nomeVariaveis = new ArrayList<String>();
    private ArrayList<SType> tipos = new ArrayList<SType>();

    public DataAttributes(String nomeData, ArrayList<String> nomeVariaveis, ArrayList<SType> tipos){
        this.nomeData = nomeData;
        this.nomeVariaveis = nomeVariaveis;
        this.tipos = tipos;
    }

    public String getNomeData(){
        return this.nomeData;
    }

    public ArrayList<String> getVariaveis(){
        return this.nomeVariaveis;
    }

    public ArrayList<SType> getTipos(){
        return this.tipos;
    }

    public void addAttribute(String nome, SType tipo){
        nomeVariaveis.add(nome);
        tipos.add(tipo);
    }

    @Override
    public String toString(){
        String texto = "data ";
        texto += nomeData + " {\n";
        if(this.tipos.size() != 0 && this.nomeVariaveis.size() != 0){
            for(int i = 0; i < this.tipos.size(); i++){
                texto += "\t" + this.nomeVariaveis.get(i).toString() + " :: ";
                texto += this.tipos.get(i).toString() + ";\n";
            }
        }
        texto += " } ";
        return texto;
    }
    
    public void printTable(){
        System.out.println(toString());
    }
    
}
