package ast;

import visitors.Visitor;

/*
 * Esta classe representa um tipo Inteiro.
 * Expr
 */
public  class TyBool extends Tipo {
      
      public TyBool(){}
      
      public boolean match(Tipo t){
         return t instanceof TyBool; 
      }
      
      public String toString(){ return "Bool"; }
      
      public void accept(Visitor v){ v.visit(this);}
}
