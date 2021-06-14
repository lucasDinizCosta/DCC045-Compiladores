package ast;

import java.util.HashMap;

public abstract class Node {
      
      private int line,col;
      
      public Node(int l, int c){
           line = l;
           col = c;
      }
      
      public int getLine(){ return line;}
      public int getCol(){ return col;}  
      
      public abstract Boolean interpret(HashMap<String,Boolean> m);
      
}
