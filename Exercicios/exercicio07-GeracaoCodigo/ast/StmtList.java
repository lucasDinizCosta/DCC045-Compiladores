package ast;

/*
 * Esta classe representa uma lista de comandos
 * Stmt ; StmtList
 */
import java.util.HashMap; 
import visitors.Visitor;

public class StmtList extends Node {
      
      private Node cmd1;
      private Node cmd2; 
      
      public StmtList(Node c1, Node c2){
           this.cmd1 =  c1;
           this.cmd2 =  c2;
      }
      
      public StmtList(Node cmd1){
           this.cmd1 =  cmd1;
           this.cmd2 =  null;
      }
      
      public Node getCmd1(){ return cmd1;} 
      public Node getCmd2(){ return cmd2; }
      
      //@Override
      public String toString(){
         if(cmd2 !=  null && !(cmd2 instanceof StmtList)){
              return   cmd1.toString() + "\n" + cmd2.toString() ;
         }
         return  cmd1.toString() + "\n" + (cmd2 != null ? cmd2.toString()  : "") ;
      }
      
      public void accept(Visitor v){ v.visit(this);}
}

