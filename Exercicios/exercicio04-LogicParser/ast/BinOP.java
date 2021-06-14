package ast;

/*
 * Esta classe representa uma Operação binária.
 * Expr [operação] Expr
 */

public abstract class BinOP extends Expr {
      
      private Expr l;
      private Expr r;
      
      public BinOP(int lin, int col, Expr l, Expr r){
           super(lin,col);
           this.l = l;
           this.r = r;
      }
      
      public void setLeft(Expr n){  l = n; }
      public void setRight(Expr n){ r = n; }
      
      public Expr getLeft(){ return l;}
      public Expr getRight(){ return r;}
      
}
