package ast;

/*
 * Esta classe representa uma valor booleano True.
 * Expr
 */
 
import java.util.HashMap;
import visitors.Visitor;

public class True extends Expr {
      
      public True(){
           
      }
      
      public boolean getValue(){ return true;}
      
      //@Override
      public String toString(){
         return   "true"; 
      }
      
      public void accept(Visitor v){ v.visit(this);}
}
