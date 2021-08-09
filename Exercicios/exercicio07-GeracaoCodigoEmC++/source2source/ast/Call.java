package ast;

/*
 * Esta classe representa uma variável.
 * Expr
 */
 
import java.util.HashMap; 
import visitors.Visitor;

public class Call extends Expr {
      
      private String l;
      private Expr[] args;
     
      public Call(String name, Expr[] xs){
           this.l = name;
           args = xs;
      }
      
      public String getName(){ return l;}
      public Expr[] getArgs(){ return args;}
      
      
      //@Override
      public String toString(){
         String s = l + "(";
         if(args.length >0){
            s += args[0].toString();
            for(int i =1; i< args.length; i++){
               s += "," + args[i].toString();
            }
         }
         s += ")";
         return s;
      }
      
      public void accept(Visitor v){ v.visit(this);}

}
