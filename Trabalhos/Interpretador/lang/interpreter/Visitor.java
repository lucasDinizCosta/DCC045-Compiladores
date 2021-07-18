package lang.interpreter;

import ast.*;

public abstract  class  Visitor {
    public abstract void visit(Command c);
    public abstract void visit(Addition a);
    public abstract void visit(Declaration d);
    public abstract void visit(Data d);
    public abstract void visit(Parameters p);
    public abstract void visit(ID i);
    public abstract void visit(NAME_TYPE n);
    public abstract void visit(Type t);
    public abstract void visit(TypeBool t);
    public abstract void visit(TypeArray t);
    public abstract void visit(TypeChar t);
    public abstract void visit(TypeInt t);
    public abstract void visit(TypeFloat t);
    public abstract void visit(TypeData t);
    public abstract void visit(Subtraction s);
    public abstract void visit(Multiplication m);
    public abstract void visit(Division d);
    public abstract void visit(Equal e);
    public abstract void visit(LessThan l);
    public abstract void visit(DivisionRest d);
    public abstract void visit(NotEqual n);
    public abstract void visit(Not n);
    public abstract void visit(Minus n);
    public abstract void visit(Booleano b);
    public abstract void visit(Null n);
    public abstract void visit(Inteiro i);
    public abstract void visit(PontoFlutuante p);
    public abstract void visit(Caractere c);
    public abstract void visit(ExpParenthesis e);
    public abstract void visit(TypeInstantiate t);
    public abstract void visit(Lvalue l);
    public abstract void visit(Identifier i);
    public abstract void visit(PIdentifier i);
    public abstract void visit(ArrayAccess a);
    public abstract void visit(DataAccess d);
    public abstract void visit(TempPexp t);
    public abstract void visit(FCallParams f);
    public abstract void visit(Conjunction c);
    public abstract void visit(Imprimir i);
    public abstract void visit(Program p);
    public abstract void visit(Function f);
    public abstract void visit(Read r);
    public abstract void visit(Return r);
    public abstract void visit(Attribution a);
    public abstract void visit(Iterate i);
    public abstract void visit(If i);
    public abstract void visit(IfElse i);
    public abstract void visit(CommandsList c);
    public abstract void visit(FunctionCall f);
}

