package ast;

/*
 * Esta classe representa uma Operção binária.
 * Expr [opreação] Expr
 */
import java.util.HashMap; 
import visitors.Visitor;

public abstract class BinOP extends Expr {
      
      private Expr l;
      private Expr r;
      
      public BinOP(Expr l, Expr r){
           this.l = l;
           this.r = r;
      }
      
      public void setLeft(Expr n){  l = n; }
      public void setRight(Expr n){ r = n; }
      
      public Expr getLeft(){ return l;}
      public Expr getRight(){ return r;}

}
