package ast;

import visitors.Visitor;

/*
 * Esta classe representa um tipo Float.
 * Expr
 */
public  class TyFloat extends Tipo {
      
      public TyFloat(){}
      
      public boolean match(Tipo t){
         return t instanceof TyFloat; 
      }
      
      public String toString(){ return "Float"; }
      
      public void accept(Visitor v){ v.visit(this);}
}
