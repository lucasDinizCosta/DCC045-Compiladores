package langUtil;

public class STyFloat extends SType {
     
     private static STyFloat st = new STyFloat();
     
     private STyFloat(){
     }
     
     public static STyFloat newSTyFloat(){ return st; }
     
     public boolean match(SType v){
          return (v instanceof STyErr) || (v instanceof STyInt);
     }
     
     public String toString(){
         return "Float";
     }
     

}
