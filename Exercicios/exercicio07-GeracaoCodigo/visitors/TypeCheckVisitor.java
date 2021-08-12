package visitors;

import ast.*;
import langUtil.*;

import java.util.Stack;
import java.util.ArrayList;

public class  TypeCheckVisitor extends Visitor{
     
     private STyInt tyint = STyInt.newSTyInt();
     private STyFloat tyfloat = STyFloat.newSTyFloat();
     private STyBool tybool = STyBool.newSTyBool();
     private STyErr tyerr = STyErr.newSTyErr();
     
     
     private ArrayList<String> logError;
     
     private TyEnv<LocalEnv<SType>> env;
     private LocalEnv<SType> temp;
     
     private Stack<SType> stk;
     private boolean retChk;
     
     public TypeCheckVisitor(){
         stk = new Stack<SType>();
         env = new TyEnv<LocalEnv<SType>>();
         logError = new ArrayList<String>();
     }

    public TyEnv<LocalEnv<SType>> getEnv() {return env;}
    
     public int getNumErrors(){ return logError.size(); }
     
     public void printErrors(){ 
          for(String s : logError){
              System.out.println(s);
          }
     }
     
     public void visit(Program p){
         for(Func f : p.getFuncs()){
             STyFun ty;
             SType[] xs = new SType[f.getParams().length + 1];
             for(int i = 0; i < f.getParams().length; i++ ){
                 f.getParams()[i].getTipo().accept(this);
                 xs[i] = stk.pop();
             }
             f.getTipo().accept(this);
             xs[f.getParams().length] = stk.pop();
             ty = new STyFun(xs);
             env.set(f.getID(), new LocalEnv<SType>(f.getID(),ty));
         }
         for(Func f : p.getFuncs()){
             f.accept(this);
         }
         //env.printTable();
     }
     
     private void typeArithmeticBinOp(Node n, String opName){
         SType tyr = stk.pop();
         SType tyl = stk.pop();
         if( (tyr.match(tyint) ) ){
             if(tyl.match(tyint) || tyl.match(tyfloat)){
                stk.push(tyl);
             }else{
                logError.add( n.getLine() + ", " + n.getCol() + ": Operador" + opName +"não se aplica aos tipos " + tyl.toString() + " e " + tyr.toString() );
                stk.push(tyerr);
             }
             
         }else if(tyr.match(tyfloat)){
            if(tyl.match(tyint) || tyl.match(tyfloat) ){
                stk.push(tyl);
            }else{
               logError.add( n.getLine() + ", " + n.getCol() + ": Operador " + opName +" não se aplica aos tipos " + tyl.toString() + " e " + tyr.toString() );
               stk.push(tyerr);
            }
         }else{
             logError.add( n.getLine() + ", " + n.getCol() + ": Operador " + opName +" não se aplica aos tipos " + tyl.toString() + " e " + tyr.toString() );
             stk.push(tyerr);
         }
     }
     
     public void visit(Add e){
         e.getLeft().accept(this);
         e.getRight().accept(this);
         typeArithmeticBinOp(e,"+");

     }
     
     public void visit(Sub e){
         e.getLeft().accept(this);
         e.getRight().accept(this);
         typeArithmeticBinOp(e,"-");
     }
     public void visit(Mul e){
         e.getLeft().accept(this);
         e.getRight().accept(this);
         typeArithmeticBinOp(e,"*");
     }
     public void visit(Div e){
         e.getLeft().accept(this);
         e.getRight().accept(this);
         typeArithmeticBinOp(e,"/");    
     }
     
     public void visit(Mod e){
         e.getLeft().accept(this);
         e.getRight().accept(this);
         SType tyr = stk.pop();
         SType tyl = stk.pop();
         if( tyr.match(tyint) && tyl.match(tyint)  ){
            stk.push(tyint);
         }
         else{
            logError.add( e.getLine() + ", " + e.getCol() + ": Operador % não se aplica aos tipos " + tyl.toString() + " e " + tyr.toString() );
            stk.push(tyerr);
         }
     }
     
     public void visit(And e){
         e.getLeft().accept(this);
         e.getRight().accept(this);
         SType tyr = stk.pop();
         SType tyl = stk.pop();
         if( tyr.match(tybool) && tyl.match(tybool)  ){
            stk.push(tybool);
         }
         else{
            logError.add( e.getLine() + ", " + e.getCol() + ": Operador & não se aplica aos tipos " + tyl.toString() + " e " + tyr.toString() );
            stk.push(tyerr);
         }
     }
     
     public void visit(Lt e){
         e.getLeft().accept(this);
         e.getRight().accept(this);
         SType tyr = stk.pop();
         SType tyl = stk.pop();
         if( (tyr.match(tyint) || tyr.match(tyfloat) ) && (tyl.match(tyint) || tyr.match(tyfloat)) ){
            stk.push(tybool);
         }
         else{
            logError.add( e.getLine() + ", " + e.getCol() + ": Operador < não se aplica aos tipos " + tyl.toString() + " e " + tyr.toString() );
            stk.push(tyerr);
         }
     }
     
     public void visit(Eq e){
         e.getLeft().accept(this);
         e.getRight().accept(this);
         SType tyr = stk.pop();
         SType tyl = stk.pop();
         if( (tyr.match(tyint) || tyr.match(tyfloat) ) && (tyl.match(tyint) || tyr.match(tyfloat)) ){
            stk.push(tybool);
         }
         else{
            logError.add( e.getLine() + ", " + e.getCol() + ": Operador = não se aplica aos tipos " + tyl.toString() + " e " + tyr.toString() );
            stk.push(tyerr);
         }
     }
     
     public void visit(Not e){
         e.getExpr().accept(this);
         SType tyr = stk.pop();
         if(tyr.match(tybool) ){
            stk.push(tybool);
         }else{
            logError.add( e.getLine() + ", " + e.getCol() + ": Operador ! não se aplica ao tipo " + tyr.toString() );
            stk.push(tyerr);
         }
     }
     
     public void visit(True e){  stk.push(tybool);  }
     public void visit(False e){ stk.push(tybool);  }
     public void visit(NInt e){  stk.push(tyint);   }
     public void visit(NFloat e){stk.push(tyfloat); }
     
     public void visit(Var e){ 
          SType t = temp.get(e.getName());
          if(t != null){
             for(Expr x : e.getIdx()){
                if(t instanceof STyArr){
                    t = ((STyArr) t).getArg();
                }else{
                    t = tyerr;
                }
             }
             if(t == tyerr){
                logError.add( e.getLine() + ", " + e.getCol() + ": Atribuição de tipos incompatíveis " + e.getName() );
             }
             stk.push(t);
          }else{
             logError.add( e.getLine() + ", " + e.getCol() + ": Variável não declarada " + e.getName() );
             stk.push(tyerr);
          }
     }
     
     public void visit(Call e){
          LocalEnv<SType> le = env.get(e.getName());
          if(le != null){
               STyFun tf = (STyFun)le.getFuncType();
               if(e.getArgs().length == tf.getTypes().length -1){     
                    int k = 0;
                    boolean r = true;
                    for(Expr x: e.getArgs() ){
                        x.accept(this);
                        if(!tf.getTypes()[k].match(stk.pop())){
                           logError.add( x.getLine() + ", " + x.getCol() + ": " + (k+1) + "\u00BA argumento incompatível com o respectivo parâmetro de " + e.getName() );
                        }
                        k++;
                    }
                    stk.push(tf.getTypes()[tf.getTypes().length-1]);
               }else{
                  logError.add( e.getLine() + ", " + e.getCol() + ": Chamada de função a função " + e.getName() + " incompatível com argumentos. " );
                   stk.push(tyerr);
               }
          }else{
              logError.add( e.getLine() + ", " + e.getCol() + ": Chamada a função não declarada: " + e.getName() );
              stk.push(tyerr);
          }
          
     }
     
     public void visit(Attr e){
        
         if( temp.get(e.getID().getName()) == null && (e.getID().getIdx() == null || e.getID().getIdx().length == 0) ){
             e.getExp().accept(this);
             temp.set(e.getID().getName(),stk.pop());
         }else{
            e.getID().accept(this);
            e.getExp().accept(this);
            if(! stk.pop().match( stk.pop())){
                 logError.add( e.getLine() + ", " + e.getCol() + ": Atribuição ilegal para a variável " + e.getID());
            }
         }
     }
     
     public void visit(If e){
         boolean rt, re;
         re = true;
         e.getTeste().accept(this);
         if(stk.pop().match(tybool)){
             retChk = false;
             e.getThen().accept(this);
             rt = retChk;
             if(e.getElse() != null){
                retChk = false;
                e.getElse().accept(this);
                re = retChk;
             }
             retChk = rt && re;
         }else{
            logError.add( e.getLine() + ", " + e.getCol() + ": Expressão de teste do IF deve ter tipo Bool");
         }
     }
     
     public void visit(While e){
         e.getTeste().accept(this);
         if(stk.pop().match(tybool)){
             e.getBody().accept(this);
         }else{
            logError.add( e.getLine() + ", " + e.getCol() + ": Expressão de teste do IF deve ter tipo Bool");
         }
     }
     
     public void visit(Print e){
         e.getExpr().accept(this);
         stk.pop();
     }
     
     public void visit(StmtList e){
         e.getCmd1().accept(this);
         e.getCmd2().accept(this);
     }
     
     public void visit(Func f){
          retChk = false;
          temp = env.get(f.getID());
          for(Param p:  f.getParams() ){
              p.getTipo().accept(this); 
              temp.set( p.getID(), stk.pop());
          }
          f.getBody().accept(this);
          if(!retChk){
              logError.add( f.getLine() + ", " + f.getCol() + ": Função " + f.getID() + " deve retornar algum valor.");
          }
     }
     
     public void visit(Inst e){
        if(temp.get(e.getID().getName()) != null){
            logError.add( e.getLine() + ", " + e.getCol() + ": Redefinição da variável " + e.getID() );
        }else{
            e.getSize().accept(this);
            if(stk.pop().match(tyint) ){
               e.getTipo().accept(this);
               
               temp.set(e.getID().getName(), new STyArr(stk.pop()) );
            }
        }
        
     }
     
     public void visit(Return e){
          e.getExpr().accept(this);
          if(temp.getFuncType() instanceof STyFun){
               SType[] t = ((STyFun)temp.getFuncType()).getTypes();
               t[t.length-1].match(stk.pop());
          }
          else{
             stk.pop().match(temp.getFuncType());
          }
          retChk = true;
     }
     
     public void visit(Param e){}
     
     public void visit(TyInt t){   stk.push(tyint);   }
     public void visit(TyFloat t){ stk.push(tyfloat); }
     public void visit(TyBool t){  stk.push(tybool);  }
     
     public void visit(TyArr t){ 
         t.getTyArg().accept(this);
         stk.push(new STyArr(stk.pop()) );
     }
}
