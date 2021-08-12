package ast;

/*
 * Esta classe representa um comando de atribuição.
 * ID = Expr
 */
 
import java.util.HashMap; 
import visitors.Visitor;

public class Attr extends Node {
      
      private Var id;
      private Expr e; 
      
      public Attr(Var id, Expr e){
           this.id = id;
           this.e  = e;
      }
      
      public Var getID(){ return id;} 
      public Expr getExp(){   return e; }
      
      public String toString(){
          return id.toString() + " = " + e.toString() + ";";
      }
      
      public void accept(Visitor v){ v.visit(this);}

}
