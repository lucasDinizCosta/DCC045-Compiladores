package lang.interpreter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import java.util.Scanner;

import lang.ast.*;

public class InterpretVisitor extends Visitor {

    private Stack<HashMap<String, Object>> env;     // Escopo de variaveis de objetos
    private HashMap<String, Function> funcs;        // Funções da linguagem lang
    private HashMap<String, Data> datas;            // Tipos de dados novos
    private Stack<Object> operands;                 // Operandos
    public HashMap<Integer, Object> parms;          // Parametros de funções
    private boolean retMode, debug;

    public InterpretVisitor() {
        env = new Stack<HashMap<String, Object>>();
        env.push(new HashMap<String, Object>());
        funcs = new HashMap<String, Function>();
        parms = new HashMap<Integer, Object>();
        datas = new HashMap<String, Data>();
        operands = new Stack<Object>();
        retMode = false;
        debug = false;
    }

    // Partem do prog

    @Override
    public void visit(Program p){

    }

    // Partem do data
    
    @Override
    public void visit(Data d){

    }


    // Partem do decl

    @Override
    public void visit(Declaration d){

    }

    // Partem do func

    @Override
    public void visit(Function f){

    }

    // Partem do params

    @Override
    public void visit(Parameters p){

    }

    // Partem do Type

    @Override
    public void visit(TypeArray t){

    }

    // Partem do btype

    @Override
    public void visit(TypeInt t){

    }

    @Override
    public void visit(TypeChar t){

    }

    @Override
    public void visit(TypeBool t){

    }

    @Override
    public void visit(TypeFloat t){

    }

    @Override
    public void visit(NameType n){

    }

    @Override
    public void visit(Type t){

    }


    // Partem do cmd

    @Override
    public void visit(Command c){

    }

    @Override
    public void visit(CommandsList c){

    }

    @Override
    public void visit(If i){

    }

    @Override
    public void visit(IfElse i){

    }

    @Override
    public void visit(Iterate i){

    }

    @Override
    public void visit(Read r){

    }

    @Override
    public void visit(Print i){

    }

    @Override
    public void visit(Return r){

    }

    @Override
    public void visit(Attribution a){

    }

    @Override
    public void visit(FunctionCall f){

    }
    
    // Partem do exp

    @Override
    public void visit(And a){
        
    }


    // Partem do rexp

    @Override
    public void visit(LessThan l){

    }

    @Override
    public void visit(Equality e){

    }

    @Override
    public void visit(Difference n){

    }

    // Partem do aexp

    @Override
    public void visit(Addition a){

    }

    @Override
    public void visit(Subtraction s){

    }

    // Partem do mexp
    @Override
    public void visit(Multiplication m){

    }

    @Override
    public void visit(Division d){

    }

    @Override
    public void visit(Modular m){

    }

    // Partem do sexp

    @Override
    public void visit(Not n){

    }

    @Override
    public void visit(Minus n){

    }

    @Override
    public void visit(BooleanValue b){ // True e False

    }

    @Override
    public void visit(Null n){

    }

    @Override
    public void visit(IntegerNumber i){

    }

    @Override
    public void visit(FloatNumber p){

    }

    @Override
    public void visit(CharLitteral c){

    }

    // Partem do pexp

    @Override
    public void visit(PexpIdentifier i){

    }

    @Override
    public void visit(ExpParenthesis e){

    }
    
    @Override
    public void visit(TypeInstantiate t){

    }

    @Override
    public void visit(FunctionReturn f){

    }

    // Partem do lvalue


    @Override
    public void visit(LValue l){

    }

    @Override
    public void visit(ID i){

    }

    @Override
    public void visit(Identifier i){

    }

    @Override
    public void visit(DataAccess d){

    }

    @Override
    public void visit(ArrayAccess a){

    }

    // Partem do exps


    @Override
    public void visit(FCallParams f){

    }
}