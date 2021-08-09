package ast;

/*
 * Esta classe representa um comando de Impressão.
 * Expr
 */
import java.util.HashMap; 
import visitors.Visitor;
 
public class Return extends Node {
      
      private Expr s;
      
      public Return( Expr s ){
           this.s = s;
      }
      
      public Expr getExpr(){ return s;}
      
      //@Override
      public String toString(){
         return   "^"  + s.toString() + ";"; 
      }
      
      public void accept(Visitor v){ v.visit(this); }
}
