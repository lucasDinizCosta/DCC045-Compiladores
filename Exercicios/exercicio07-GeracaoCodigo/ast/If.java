package ast;

/*
 * Esta classe representa um comando condicional.
 * ?(E) {C} : {C} 
 */
 
import java.util.HashMap; 
import visitors.Visitor;

public class If extends Node {
      
      private Expr teste;
      private Node thn;
      private Node els;
      
      public If(Expr teste, Node thn, Node els){
           this.teste = teste;
           this.thn = thn;
           this.els = els;
      }
      
            
      public If(Expr teste, Node thn){
           this.teste = teste;
           this.thn = thn;
           this.els = null;
      }
      
      public Expr getTeste(){ return teste;}
      public Node getThen(){ return thn;}
      public Node getElse(){ return els;}
      
      public String toString(){
         String s = "?(" + teste.toString() + "){" + thn.toString() + "}";
         String sels =  els != null ? " : {" + els.toString() + "}": "" ;
         s = s + sels;
         return  s.replace('\n', '\0'); 
      }
      
      
      public void accept(Visitor v){ v.visit(this);}
}
