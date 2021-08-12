package ast;

/*
 * Esta classe representa um valor booleano False.
 * Expr
 */
 
import java.util.HashMap; 
import visitors.Visitor;

public class False extends Expr {
      
      public False(){
           
      }
      
      public boolean getValue(){ return false;}
      
      //@Override
      public String toString(){
         return   "false"; 
      }
      
      public void accept(Visitor v){ v.visit(this);}

}
