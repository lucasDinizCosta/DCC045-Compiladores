package langUtil;

public class STyInt extends SType {
     
     private static STyInt st = new STyInt();
     
     private STyInt(){
     }
     
     public static STyInt newSTyInt(){ return st; }
     
     public boolean match(SType v){
          return (v instanceof STyErr) ||(v instanceof STyInt);
     }
     
     public String toString(){
         return "Int";
     }   

}
