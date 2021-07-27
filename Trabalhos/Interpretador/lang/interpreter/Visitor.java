/********************************************************
* Trabalho de Teoria dos Compiladores(DCC045)(2021/1)   *
*                                                       *
* Projeto do Interpretador para a Linguagem Lang        *
* Nome: Lucas Diniz da Costa -- Matricula: 201465524C   *
*                                                       *
*********************************************************/
package lang.interpreter;

import lang.ast.*;

public abstract class Visitor {

    // Partem do prog
    public abstract void visit(Program p);

    // Partem do data
    public abstract void visit(Data d);

    // Partem do decl
    public abstract void visit(Declaration d);

    // Partem do func
    public abstract void visit(Function f);

    // Partem do params
    public abstract void visit(Parameters p);

    // Partem do Type
    public abstract void visit(TypeArray t);

    // Partem do btype
    public abstract void visit(TypeInt t);
    public abstract void visit(TypeChar t);
    public abstract void visit(TypeBool t);
    public abstract void visit(TypeFloat t);
    public abstract void visit(Type t);
    public abstract void visit(NameType i);

    // Partem do cmd
    public abstract void visit(Command c);
    public abstract void visit(CommandsList c);
    public abstract void visit(If i);
    public abstract void visit(IfElse i);
    public abstract void visit(Iterate i);
    public abstract void visit(Read r);
    public abstract void visit(Print i);
    public abstract void visit(Return r);
    public abstract void visit(Attribution a);
    public abstract void visit(FunctionCall f);
    
    // Partem do exp
    public abstract void visit(And a);

    // Partem do rexp
    public abstract void visit(LessThan l);
    public abstract void visit(Equality e);
    public abstract void visit(Difference n);

    // Partem do aexp
    public abstract void visit(Addition a);
    public abstract void visit(Subtraction s);

    // Partem do mexp
    public abstract void visit(Multiplication m);
    public abstract void visit(Division d);
    public abstract void visit(Modular m);

    // Partem do sexp
    public abstract void visit(Not n);
    public abstract void visit(Minus n);
    public abstract void visit(BooleanValue b); // True e False
    public abstract void visit(Null n);
    public abstract void visit(IntegerNumber i);
    public abstract void visit(FloatNumber p);
    public abstract void visit(CharLitteral c);

    // Partem do pexp
    public abstract void visit(PexpIdentifier i);
    public abstract void visit(ExpParenthesis e);
    public abstract void visit(TypeInstanciate t);
    public abstract void visit(FunctionReturn f);

    // Partem do lvalue
    public abstract void visit(LValue l);
    public abstract void visit(ID i);
    public abstract void visit(Identifier i);
    public abstract void visit(DataAccess d);
    public abstract void visit(ArrayAccess a);

    // Partem do exps
    public abstract void visit(FCallParams f);
    
}

