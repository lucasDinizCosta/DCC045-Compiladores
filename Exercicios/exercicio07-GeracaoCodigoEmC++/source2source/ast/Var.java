package ast;

/*
 * Esta classe representa uma variável.
 * Expr
 */
 
import java.util.HashMap; 
import visitors.Visitor;

public class Var extends Expr {
      
      private Expr[] idx;
      private String name;
     
      public Var(String name, Expr[] idxs){
           idx = idxs;
           this.name = name;
      }
      
      public Var(String name){
           this.name = name;
           idx = null;
      }
      
      public Expr[] getIdx(){ return idx;}
      
      public String getName(){ return name;}
      
      //@Override
      public String toString(){
         String s = name;
          if(idx != null){  
            for(Expr e : idx ){
               s += "["; 
               s += e.toString();
               s += "]";
            }
          }  
         return  s; 
      }
      
      public void accept(Visitor v){ v.visit(this);}

}
