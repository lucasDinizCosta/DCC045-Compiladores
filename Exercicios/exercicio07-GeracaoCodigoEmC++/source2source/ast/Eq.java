package ast;

/*
 * Esta classe representa uma operação de comparação pela igualdade.
 * Expr + Expr
 */
 
import java.util.HashMap; 
import visitors.Visitor;

public class Eq extends BinOP {

      public Eq(Expr l, Expr r){
           super(l,r);
      }
      
      public String toString(){
         String s = getLeft().toString();
         String ss = getRight().toString();
         if(getRight() instanceof Add){
            ss = "(" + ss + ")";
         }
         return   s + " = " + ss;
      }
      
      public void accept(Visitor v){ v.visit(this);}
            
}
