package ast;

/*
 * Esta classe representa um comando de atribuição.
 * ID = Expr
 */
 
import java.util.HashMap; 
import visitors.Visitor;

public class Program extends Node {
      
      private Func[] fs;
      
      public Program(Func[] f){
           fs = f;
      }
      
      public Func[] getFuncs(){   return fs; }
      
      
      public String toString(){
          String s = "";
          for(Func f : fs){
             s += f.toString();
          }
          return s;
      }
      
      public void accept(Visitor v){ v.visit(this);}

}
