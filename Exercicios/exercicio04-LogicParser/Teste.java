import java.io.FileReader;
import java.io.IOException;
import ast.*;
import parser.*;
import java.util.HashMap; 

public class Teste{
     public static void main(String args[]) throws IOException{
          LogicParser p = new LogicParser(args[0]);
          Node result = p.prog(); 
          if(result != null ){
               HashMap<String,Boolean> m = new HashMap<String,Boolean>();
               System.out.println("Aceito");
               System.out.println(result.toString());
               System.out.println("------------------------");
               result.interpret(m);
          }else{
              System.out.println("Rejeitado");
          }
     }
}
