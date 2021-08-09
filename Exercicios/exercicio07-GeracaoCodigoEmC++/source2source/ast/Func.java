package ast;

/*
 * Esta classe representa um comando de atribuição.
 * ID = Expr
 */
 
import java.util.HashMap; 
import visitors.Visitor;

public class Func extends Node {
      
      private String id;
      private Tipo t;
      private Param[] ps;
      private Node body;
      
      public Func(String id, Param[] p, Tipo t, Node n){
           this.id = id;
           this.t  = t;
           this.ps = p;
           body = n;
      }
      
      public String getID(){ return id;} 
      public Tipo getTipo(){   return t; }
      public Param[] getParams(){   return ps; }
      public Node getBody(){ return body;}
      
      
      public String toString(){
          String s = id.toString() + "(";
          if(ps.length > 0){
             s += ps[0].toString();
             for(int i = 1; i < ps.length; i++){
                 s += ps[i].toString();
             }
          }
          s += ") : " + t.toString() + "{" + body.toString() + "}";
          return s;
      }
      
      public void accept(Visitor v){ v.visit(this);}

}
