package ast;

/*
 * Esta classe representa uma expressão negação.
 * Expr + Expr
 */
 
import java.util.HashMap; 
import visitors.Visitor;

public class Not extends Expr {
      
      Expr e; 
      
      public Not(Expr l){
           e = l;
      }
      
      public Expr getExpr(){ return e; }
      public String toString(){
         String s = e.toString();
         if(! (e instanceof NInt || e instanceof NFloat || e instanceof Var || e instanceof True || e instanceof False)){
            s = "!" + s;
         }
         return   s ;
      }
      
      public void accept(Visitor v){ v.visit(this);}
}
