package lang.semantic;

public class LocalAmbiente<A> extends TyEnv<A>{
    private String id; 
    private SType t;
    
    public LocalAmbiente(String id, SType t){
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
