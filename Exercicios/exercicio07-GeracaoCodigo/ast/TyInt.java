package ast;

import visitors.Visitor;

/*
 * Esta classe representa um tipo Inteiro.
 * Expr
 */
public class TyInt extends Tipo {
      
      public TyInt(){}
      
      public boolean match(Tipo t){
         return t instanceof TyInt; 
      }
      
      public String toString(){ return "Int"; }
      
      public void accept(Visitor v){ v.visit(this);}
}
