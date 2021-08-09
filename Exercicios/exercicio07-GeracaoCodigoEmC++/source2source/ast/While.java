package ast;

/*
 * Esta classe representa um comando condicional.
 * ?(E) {C} : {C} 
 */
 
import java.util.HashMap; 
import visitors.Visitor;

public class While extends Node {
      
      private Expr teste;
      private Node body;
      
      public While(Expr teste, Node body){
           this.teste = teste;
           this.body = body;
      }
            
      public String toString(){
         String s = "[" + teste.toString() + "]{" + body.toString() + "}";
         return  s.replace('\n', '\0'); 
      }
      
      public Expr getTeste(){ return teste;}
      public Node getBody(){ return body;}
      
      public void accept(Visitor v){ v.visit(this);}
}
