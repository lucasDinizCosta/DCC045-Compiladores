package lang.semantic;

import java.util.ArrayList;
import java.util.TreeMap;

public class TyEnv<A>{

    private TreeMap<String,A> typeEnv;
    private ArrayList<A> typeEnvFuncoes;

    public TyEnv(){
       typeEnv = new TreeMap<String,A>();
       typeEnvFuncoes = new ArrayList<A>();
    }
    
    public void set(String id, A t){
        typeEnv.put(id,t);  
    }
    
    public A get(String id){
         return typeEnv.get(id);
    }

    public void add(A t){
        typeEnvFuncoes.add(t);  
    }

    // Retorna todas as funções que tem o nome passado
    // Para tratar sobrecarga depois
    
    public ArrayList<A> getFuncoes(String id){
        ArrayList<A> sobrecargaFuncoes = new ArrayList<A>();
        for(int i = 0; i < typeEnvFuncoes.size(); i++){
            LocalAmbiente funcao = (LocalAmbiente)typeEnvFuncoes.get(i);
            if(funcao.getFuncID().equals(id)){  // Se o nome da função for igual, coloca na lista
                sobrecargaFuncoes.add(typeEnvFuncoes.get(i));
            }
        }
         return sobrecargaFuncoes;
    }

    public void printTable(){
        System.out.println(toString());
    }

    @Override
    public String toString(){
        String s = "";
        return s;
        /*
        Object[] x = (typeEnv.keySet().toArray()); 
        for(int i = 0; i < x.length; i++){
           s += ((String)x[i]) + " : " + (typeEnv.get(x[i])).toString() + "\n";
        }
        return s;*/
    }
    
}
