package langUtil;

import java.util.Stack;

import java.util.TreeMap;



public class LocalEnv<A> extends TyEnv<A> {
    private String id; 
    private SType t;
    
    public LocalEnv(String id, SType t){
       this.t = t;
       this.id = id;
    }
    
    public String getFuncID(){ return id;}
    
    public SType getFuncType(){ return t;}

     public String toString(){
         String s = "--------------- (" + id + "," + t.toString() + ") ---------------\n";
         s += super.toString();
         return s;
     }
    
       
}
