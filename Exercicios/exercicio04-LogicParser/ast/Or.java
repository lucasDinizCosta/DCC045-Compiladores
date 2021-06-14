package ast;

import java.util.HashMap; 
public class Or extends BinOP {
    public Or(int lin, int col, Expr l, Expr r){
        super(lin,col,l,r);
   }
   
   public String toString(){
      String s = getLeft().toString();
      String ss = getRight().toString();
      if(getRight() instanceof Or){
         ss = "(" + ss + ")";
      }
      return   s + " ∨ " + ss;
   }
   
   public Boolean interpret(HashMap<String,Boolean> m){
       
       return (getLeft().interpret(m) || getRight().interpret(m));
   }
}
