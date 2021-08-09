package ast;

/*
 * Esta classe representa um comando de Impressão.
 * Expr
 */
import java.util.HashMap; 
import visitors.Visitor;
 
public class Param extends Node {
      
      private String s;
      private Tipo t;
      
      public Param( String s, Tipo t ){
           this.s = s;
           this.t = t;
      }
      
      public String getID(){ return s;}
      public Tipo getTipo(){ return t;}
      
      //@Override
      public String toString(){
         return   s.toString() + ":" + t.toString(); 
      }
      
      public void accept(Visitor v){ v.visit(this); }
}
