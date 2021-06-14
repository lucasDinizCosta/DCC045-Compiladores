package ast;
import java.util.HashMap;

public class Not extends UniOP{
    public Not(int lin, int col, Expr r){
        super(lin,col,r);
   }
   
   public String toString(){
      String ss = getRight().toString();
      if(getRight() instanceof Bool){
         ss = "(" + ss + ")";
      }
      return  " ¬ " + ss;
   }
   
   public Boolean interpret(HashMap<String,Boolean> m){
       return (!getRight().interpret(m));
   }
}
