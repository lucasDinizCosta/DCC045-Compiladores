package ast;

/*
 * Esta classe representa uma operação de divisão.
 * Expr + Expr
 */
 
import java.util.HashMap; 
import visitors.Visitor;

public class Div extends BinOP {

      public Div(Expr l, Expr r){
           super(l,r);
      }
      
      public String toString(){
         String s = getLeft().toString();
         String ss = getRight().toString();
         if(getRight() instanceof Add){
            ss = "(" + ss + ")";
         }
         return   s + " / " + ss;
      }
      
      public void accept(Visitor v){ v.visit(this);}
}
