package ast;

/*
 * Esta classe representa uma expressão de conjunção.
 * Expr + Expr
 */
 
import java.util.HashMap; 
import visitors.Visitor;

public class And extends BinOP {

      public And(Expr l, Expr r){
           super(l,r);
      }
      
      public String toString(){
         String s = getLeft().toString();
         String ss = getRight().toString();
         if(getRight() instanceof Add){
            ss = "(" + ss + ")";
         }
         return   s + " & " + ss;
      }
      
      public void accept(Visitor v){ v.visit(this);}
}
