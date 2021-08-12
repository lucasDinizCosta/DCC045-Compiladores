package ast;

/*
 * Esta classe representa um tipo.
 */
public abstract class Tipo extends Node {
      
      public Tipo(){
      
      }
      
      public abstract boolean match(Tipo t);
}
