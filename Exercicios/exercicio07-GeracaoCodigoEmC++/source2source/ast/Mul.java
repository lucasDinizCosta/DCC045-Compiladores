package ast;

/*
 * Esta classe representa uma expressão de Multiplicação.
 * Expr * Expr
 */
import java.util.HashMap; 
import visitors.Visitor;

public class Mul extends BinOP {
      public Mul(Expr l, Expr r){
           super(l,r);
      }
      
      //@Override
      public String toString(){
         String s = getLeft().toString();
         if(getLeft() instanceof Mul || getLeft() instanceof Add){
            s = "(" + s + ")";
         }
         String ss = getRight().toString();
         if( getRight() instanceof Add){
            ss = "(" + ss+ ")";
         }
         return   s + " * " + ss;
      }
      
      public void accept(Visitor v){ v.visit(this);}
}
