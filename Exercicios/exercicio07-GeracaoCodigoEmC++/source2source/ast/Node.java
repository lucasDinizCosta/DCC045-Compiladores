package ast;

import java.util.HashMap;
import visitors.Visitable;

public abstract class Node extends beaver.Symbol implements Visitable{
      
      public Node(){

      }
      
      public int getLine(){ return super.getLine(getStart()); }
      public int getCol(){ return super.getColumn(getStart());}  

}
