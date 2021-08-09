package ast;

/*
 * Esta classe representa um valor inteiro
 * Expr
 */ 
import java.util.HashMap; 
import visitors.Visitor;

public class NFloat extends Expr {
      
      private float l;
     
      public  NFloat(float v){
           this.l = v;
      }
      
      public float getValue(){ return l;}
      
      //@Override
      public String toString(){
         return   "" + l ; 
      }
      
      public void accept(Visitor v){ v.visit(this);}
}
