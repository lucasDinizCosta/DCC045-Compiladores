package visitors;

import ast.*;

public abstract  class  Visitor {
     public abstract void visit(Program p);
     
     public abstract void visit(Add e);
     public abstract void visit(Sub e);
     public abstract void visit(Mul e);
     public abstract void visit(Div e);
     public abstract void visit(Mod e);
     
     public abstract void visit(And e);
     public abstract void visit(Lt e);
     public abstract void visit(Eq e);
     public abstract void visit(Not e);
     
     public abstract void visit(True e);
     public abstract void visit(False e);
     public abstract void visit(NInt e);
     public abstract void visit(NFloat e);
     public abstract void visit(Var e);
     public abstract void visit(Call e);
     
     public abstract void visit(Attr e);
     public abstract void visit(If e);
     public abstract void visit(While e);
     public abstract void visit(Print e);
     public abstract void visit(StmtList e);
     public abstract void visit(Func f);
     
     public abstract void visit(Inst e);
     public abstract void visit(Return e);
     public abstract void visit(Param e);
     
     public abstract void visit(TyInt t);
     public abstract void visit(TyFloat t);
     public abstract void visit(TyBool t);
     public abstract void visit(TyArr t);
}
