package langUtil;

public class STyArr extends SType {
     
     private SType a;
     
     public STyArr(SType t){
         a = t;
     }
     
     public SType getArg(){return a;}
          
     public boolean match(SType v){
          return (v instanceof STyErr) || (v instanceof STyArr) && (a.match( ((STyArr)v).getArg() ) );
     }
     
     public String toString(){
         return a.toString() + "[]";
     }
     

}
