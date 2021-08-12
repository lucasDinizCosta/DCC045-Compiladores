package ast;

import visitors.Visitor;

/*
 * Esta classe representa um tipo Inteiro.
 * Expr
 */
 
 
public  class TyArr extends Tipo {
      private Tipo argType;
      
      public TyArr(Tipo t){
         argType = t;
      }
      
      public Tipo getTyArg(){
         return argType;
      }
      
      public boolean match(Tipo t){
        if(t instanceof TyArr){
            return argType.match(((TyArr)t).getTyArg());
        }
        return false;
      }
      
      public void accept(Visitor v){ v.visit(this);}
}
