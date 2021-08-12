package ast;

/*
 * Esta classe representa um comando de Impressão.
 * Expr
 */
import java.util.HashMap; 
import visitors.Visitor;
 
public class Inst extends Node {
      
      private Var i ;
      private Tipo t;
      private Expr s;
      
      public Inst( Var i, Tipo t, Expr s ){
           this.i = i;
           this.t = t;
           this.s = s;
      }
      
      public Tipo getTipo(){ return t;}
      public Var getID(){ return i;}
      public Expr getSize(){ return s;}
      
      //@Override
      public String toString(){
         return   "$" + i.toString() + " " + t.toString() + " " + s.toString() + ";"; 
      }
      
      public void accept(Visitor v){ v.visit(this); }
}
