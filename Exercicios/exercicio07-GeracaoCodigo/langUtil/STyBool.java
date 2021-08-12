package langUtil;

public class STyBool extends SType {
     
      private static STyBool st = new STyBool();;
     
     private STyBool(){
     }
     
     public static STyBool newSTyBool(){ return st; }
     
     public boolean match(SType v){
          return (v instanceof STyErr) || (v instanceof STyBool);
     }
     
     public String toString(){
         return "Bool";
     }
     

}
