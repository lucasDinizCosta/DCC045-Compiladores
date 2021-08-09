package visitors;

import ast.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Stack;

import langUtil.*;

public class  InterpretVisitor extends Visitor {

     private Stack<HashMap<String,Object>> env;     
     private HashMap<String,Func> funcs;     
     private Stack<Object> operands;
     
     
     public InterpretVisitor(){
        env = new Stack<HashMap<String,Object>>();
        env.push(new HashMap<String,Object>());
        funcs = new  HashMap<String,Func>();
        operands = new Stack<Object>();

        
     }
     
     public void visit(Program p){
         Node main = null;
         for(Func f : p.getFuncs()){
             funcs.put(f.getID(),f);
             if(f.getID().equals("inicio")){
                 main = f;
             }
         }
         if(main == null){
            throw new RuntimeException( "Não há uma função chamada inicio ! abortando ! ");
         }
         main.accept(this);
     }
     
     public  void visit(Add e){
         try{
            e.getLeft().accept(this);
            e.getRight().accept(this);
            Number esq,dir;
            dir = (Number)operands.pop();
            esq = (Number)operands.pop();
            operands.push( new Integer(esq.intValue() +  dir.intValue() ) ); 
         }catch(Exception x){
             throw new RuntimeException( " (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage() );
         }
     }
     
     public  void visit(Sub e){
        try{
            e.getLeft().accept(this);
            e.getRight().accept(this);
            Number esq,dir;
            dir = (Number)operands.pop();
            esq = (Number)operands.pop();
            operands.push( new Integer(esq.intValue() -  dir.intValue() ) ); 
        }catch(Exception x){
           throw new RuntimeException( " (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage() );
        }
     }
     
     public  void visit(Mul e){
        try{ 
            e.getLeft().accept(this);
            e.getRight().accept(this);
            Number esq,dir;
            dir = (Number)operands.pop();
            esq = (Number)operands.pop();
            operands.push( new Integer(esq.intValue() *  dir.intValue() ) ); 
        }catch(Exception x){
           throw new RuntimeException( " (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage() );
        }
     }
     
     public  void visit(Div e){
        try{  
            e.getLeft().accept(this);
            e.getRight().accept(this);
            Number esq, dir;
            dir = (Number)operands.pop();
            esq = (Number)operands.pop();
            operands.push( new Float(esq.intValue() /  dir.intValue() ) ); 
        }catch(Exception x){
             throw new RuntimeException( " (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage() );
        }
     }
     
     public  void visit(Mod e){
        try{  
            e.getLeft().accept(this);
            e.getRight().accept(this);
            Number esq, dir;
            dir = (Number)operands.pop();
            esq = (Number)operands.pop();
            operands.push( new Integer(esq.intValue() %  dir.intValue() ) ); 
        }catch(Exception x){
             throw new RuntimeException( " (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage() );
        }
     }
     
     public  void visit(And e){
         try{  
            e.getLeft().accept(this);
            e.getRight().accept(this);
            Object esq,dir;
            dir = operands.pop();
            esq = operands.pop();
            operands.push( new Boolean( (Boolean)esq &&  (Boolean)dir ) ); 
        }catch(Exception x){
            throw new RuntimeException( " (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage() );
        }
     }
     
     public  void visit(Lt e){
         try{   
             e.getLeft().accept(this);
             e.getRight().accept(this);
             Object esq,dir;
             dir = operands.pop();
             esq = operands.pop();
             operands.push( new Boolean( (Integer)esq <  (Integer)dir ) ); 
         }catch(Exception x){
           throw new RuntimeException( " (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage() );
         }
     }
     
     public  void visit(Eq e){
         try{   
             e.getLeft().accept(this);
             e.getRight().accept(this);
             operands.push( new Boolean( operands.pop().equals(operands.pop()) ) );
         }catch(Exception x){
           throw new RuntimeException( " (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage() );
        }
     }
     
     
     public  void visit(Not e){
        try{   
             e.getExpr().accept(this);
             operands.push (new Boolean( ! (Boolean)operands.pop() ) );
        }catch(Exception x){
           throw new RuntimeException( " (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage() );
        }
     }
     
     
     // gcd (12,9)
     //
     // > 9
     //   12
     
    public  void visit(Call e){
        try{
            Func f = funcs.get(e.getName());
            if(f != null){
                 for(Expr exp : e.getArgs()){
                      exp.accept(this);
                 }
                 f.accept(this);
               
            }else{
               throw new RuntimeException( " (" + e.getLine() + ", " + e.getCol() + ") Função não definida " +  e.getName());
            }
            
        }catch(Exception x){
           throw new RuntimeException( " (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage() );
        }
     }
     
     public  void visit(True e){ 
         try{
            operands.push(  new Boolean(true));
        }catch(Exception x){
           throw new RuntimeException( " (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage() );
        }
     }
     
     public  void visit(False e){
        try{
             operands.push(  new Boolean(false));
        }catch(Exception x){
           throw new RuntimeException( " (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage() );
        }
     }
     
     public  void visit(NInt e){ 
         try{   
              operands.push( new Integer(e.getValue()) );
         }catch(Exception x){
             throw new RuntimeException( " (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage() );
         }
     }
     
     public  void visit(NFloat e){ 
         try{   
             operands.push( new Float(e.getValue() ));
         }catch(Exception x){
             throw new RuntimeException( " (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage() );
         }
     }
     
     public  void visit(Var e){ 
         try{   
             Object r = env.peek().get(e.getName());
             if(r != null){   
                if(e.getIdx() != null){
                    for(Expr exp : e.getIdx()){
                        exp.accept(this);
                        r = ((ArrayList)r).get( (Integer)operands.pop());
                    }
                }
                operands.push(r);
             }
             else{throw new RuntimeException( " (" + e.getLine() + ", " + e.getCol() + ") variável não declarada " +e.getName() );}
         }catch(Exception x){
             throw new RuntimeException( " (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage() );
         }
     }
     
     public  void visit(Attr e){
         try{   
            e.getExp().accept(this);
            env.peek().put(e.getID().getName(), operands.pop());
         }catch(Exception x){
            throw new RuntimeException( " (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage() );
         }
     }
     
     public  void visit(If e){
        try{
            e.getTeste().accept(this);
            if((Boolean)operands.pop()){
                e.getThen().accept(this);
            }else if(e.getElse() != null){
                e.getElse().accept(this);
            }
        }catch(Exception x){
           throw new RuntimeException( " (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage() );
        }
     }
     
     public  void visit(While e){
        try{
            e.getTeste().accept(this);
            while( (Boolean)operands.pop()){
                e.getBody().accept(this);
                e.getTeste().accept(this);
            }
        }catch(Exception x){
           throw new RuntimeException( " (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage() );
        }
     }
     
     public  void visit(Print e){
         try{
              e.getExpr().accept(this);
              System.out.println(operands.pop().toString());
         }catch(Exception x){
              throw new RuntimeException( " (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage() );
         }
     }
     
     public  void visit(StmtList e){
        
          try{
              e.getCmd1().accept(this);
        
              e.getCmd2().accept(this);
          }catch(Exception x){
              throw new RuntimeException( " (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage() );
          }  
     }

     
    public void visit(Func f){
         HashMap<String,Object> localEnv = new HashMap<String,Object>();
         for(int  i = f.getParams().length-1; i >= 0; i--){
             localEnv.put(f.getParams()[i].getID(),operands.pop());
         } 
         env.push(localEnv);
         f.getBody().accept(this);
         env.pop();
    }
     
    public  void visit(Inst e){
         e.getID();
    }
     
     public  void visit(Return e){
          e.getExpr().accept(this);
   
     }
     
     public  void visit(Param e){   }
     
     public void visit(TyInt t)  {   }
      
     public void visit(TyFloat t){   }
     
     public void visit(TyBool t) {   }
          
     public void visit(TyArr t)  {   }
     
}
