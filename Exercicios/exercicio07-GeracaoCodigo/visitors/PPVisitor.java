package visitors;

import ast.*;
import java.util.ArrayList;

public class  PPVisitor extends Visitor {
     private ArrayList<String> lines ;
     private String buff;
     private String idnt;
     private int priority;
     
     public PPVisitor(){
        lines = new ArrayList<String>();
        buff = "";
        idnt = "";
        priority = 0;
     }
     /*
      * !   = 0
      * *,/ = 1 
      * +.- = 2
      * &   = 3
      * <   = 4
      * =   = 5
      */
     
     public String getStr(){
        String str = "";
        for(String l : lines){
            str += l + "\n";
        }
        return str;
     }
     
     private void incIdnt(){
        idnt += "    ";
     }
     
     private void decIdnt(){
        idnt = idnt.substring(0,idnt.length() - 4);
     }
     
     private void closeLine(){
         lines.add(idnt + buff);
         buff = "";
     }
     
     public void visit(Program p){
         for(Func f : p.getFuncs()){
              f.accept(this);
         }
     }
     
     public  void visit(Add e){
        int x = priority;
        if(priority < 2){ buff += "(";}
        priority = 2;
        e.getLeft().accept(this);
        buff += "+";
        priority = 2;
        e.getRight().accept(this);
        priority = x;
        if(priority < 2){ buff += ")";}
     }
     
     public  void visit(Sub e){
        int x = priority;
        if(priority < 2){ buff += "(";}
        priority = 2;
        e.getLeft().accept(this);
        buff += "-";
        priority = 2;
        e.getRight().accept(this);
        priority = x;
        if(priority < 2){ buff += ")";}
     }
     
     public  void visit(Mul e){
        int x = priority;
        if(priority < 1){ buff += "(";}
        priority = 1;
        e.getLeft().accept(this);
        buff += "*";
        priority = 1;
        e.getRight().accept(this);
        priority = x;
        if(priority < 1){ buff += ")";}
     }
     
     public  void visit(Div e){
        int x = priority;
        if(priority < 1){ buff += "(";}
        priority = 1;
        e.getLeft().accept(this);
        buff += "-";
        priority = 1;
        e.getRight().accept(this);
        priority = x;
        if(priority < 1){ buff += ")";}
     }
     
     public void visit(Mod e){
         int x = priority;
         if(priority < 1){ buff += "(";}
         priority = 1;
         e.getLeft().accept(this);
         buff += "%";
         priority = 1;
         e.getRight().accept(this);
         priority = x;
         if(priority < 1){ buff += ")";} 
     }
     
     public  void visit(And e){
        int x = priority;
        if(priority < 3){ buff += "(";}
        priority = 3;
        e.getLeft().accept(this);
        buff += "-";
        priority = 3;
        e.getRight().accept(this);
        priority = x;
        if(priority < 3){ buff += ")";}
     }
     
     public  void visit(Lt e){
        int x = priority;
        if(priority < 4){ buff += "(";}
        priority = 4;
        e.getLeft().accept(this);
        buff += "<";
        priority = 4;
        e.getRight().accept(this);
        priority = x;
        if(priority < 4){ buff += ")";}
     }
     
     public  void visit(Eq e){
        int x = priority;
        if(priority < 5){ buff += "(";}
        priority = 5;
        e.getLeft().accept(this);
        buff += "=";
        priority = 5;
        e.getRight().accept(this);
        priority = x;
        if(priority < 5){ buff += ")";}
     }
     
     public  void visit(Not e){
        int x = priority;
        if(priority < 0){ buff += "(";}
        buff += "!";
        priority = 0;
        e.getExpr().accept(this);
        if(priority < 0){ buff += ")";}
        priority = x;
     }
     
     public  void visit(True e){   buff += "true";      }
     public  void visit(False e){  buff += "false";     }
     public  void visit(NInt e){   buff += e.getValue();}
     public  void visit(NFloat e){ buff += e.getValue();}
     
     public  void visit(Var e){
           buff += e.getName();
           if(e.getIdx() != null){
                 for(Expr ex : e.getIdx()){
                     buff += "[";
                     priority = 10;
                     ex.accept(this);
                     buff += "]";
                 }
           }
           
     }
      
    public void visit(Call e){
         int x = priority;
         buff += e.getName() + "(";
         if(e.getArgs().length > 0){
             priority = 10;
             e.getArgs()[0].accept(this);
             for(int i = 1; i< e.getArgs().length; i++){
                 buff += ",";
                 priority = 10;
                 e.getArgs()[i].accept(this);
             }
         }
         buff +=  ")";
         priority =x;
    }
     
     public  void visit(Attr e){
         priority = 10;
        e.getID().accept(this);
        buff += " <- ";
        e.getExp().accept(this);
        buff += " ; ";
        closeLine();
     }
     
     public  void visit(If e){
         priority = 10;
        buff += "?(";
        e.getTeste().accept(this);
        buff += "){";
        
        closeLine();
        incIdnt();
        priority = 10;
        e.getThen().accept(this);
        closeLine();
        decIdnt();
        buff += "}";
        

        if(e.getElse() != null){
            buff += ": {";
            closeLine();    
            incIdnt();
            priority = 10;
            e.getElse().accept(this);
            decIdnt();
            buff += "}";
        }
        closeLine();
     }
     
     public  void visit(While e){
           buff += "?[";
           priority = 10;
           e.getTeste().accept(this);
           buff += "]{";
           closeLine();
           incIdnt();
           priority = 10;
           e.getBody().accept(this);
           decIdnt();
           buff += "}";
           closeLine();
     }
     
     public  void visit(Print e){
          buff += "#";
          priority = 10;
          e.getExpr().accept(this);
          buff += ";";
          closeLine();
     }
     
     public  void visit(StmtList e){
          e.getCmd1().accept(this);
          e.getCmd2().accept(this);
     }
     
     public  void visit(Func f){
          buff += f.getID()+"(";
          if(f.getParams().length >0){
              f.getParams()[0].accept(this);
              buff += ", ";
              for(int i =0; i < f.getParams().length; i++){
                  f.getParams()[0].accept(this);
              }
          }
          buff += ") : ";
          f.getTipo().accept(this);
          buff += "{";
          closeLine();
          incIdnt();
          f.getBody().accept(this);
          decIdnt();
          buff += "}";
          closeLine();
     }
     
     public  void visit(Inst e){
          buff +=  "$ " + e.getID() + " " +  e.getTipo() + " "+ e.getSize() +";";
          closeLine();
     }
     
     public  void visit(Return e){
         buff +=  "@ " ;
         e.getExpr().accept(this);
         closeLine();
     }
     
     public  void visit(Param e){
         buff += e.getID() + ":";
         e.getTipo().accept(this);
     }
     
     
     public void visit(TyInt t)  { buff += "Int";   }
     public void visit(TyFloat t){ buff += "Float"; }
     public void visit(TyBool t) { buff += "Bool";  }
     public void visit(TyArr t)  { 
         t.getTyArg().accept(this);
         buff += "[]";  
     }
     
}
