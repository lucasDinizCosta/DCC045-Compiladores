package lang.semantic;

import java.util.ArrayList;
import java.util.Stack;

import lang.interpreter.Visitor;
import lang.ast.*;
import lang.semantic.*;

public class TypeCheckVisitor extends Visitor {
    private STyInt tyInt = STyInt.newSTyInt();
    private STyFloat tyFloat = STyFloat.newSTyFloat();
    private STyCharacter tyChar = STyCharacter.newSTyCharacter();
    private STyBool tyBool = STyBool.newSTyBool();
    private STyNull tyNull = STyNull.newSTyNull();
    private STyErr tyerr = STyErr.newSTyErr();

    // Armazena as mensagens de erro
    private ArrayList<String> logError;

    private TyEnv<LocalAmbiente<SType>> env;

    //
    private LocalAmbiente<SType> temp;

    // Pilha de tipos da linguagem lang
    private Stack<SType> stk;

    // Retorno de função => Se o retorno for acionado não deve ser feito os outros
    // comandos da função
    private boolean retChk;

    public TypeCheckVisitor() {
        stk = new Stack<SType>();
        env = new TyEnv<LocalAmbiente<SType>>();
        logError = new ArrayList<String>();
    }

    // Retorna a quantidade de erros
    public int getNumErrors() {
        return logError.size();
    }

    public void printErrors() {
        System.out.println("-------------- Type errors -----------");
        for (String s : logError) {
            System.out.println(s);
        }
        System.out.println("--------------------------------------");
    }

    // Partem do prog
    public void visit(Program p){

    }

    // Partem do data
    public void visit(Data d){

    }

    // Partem do decl
    public void visit(Declaration d){

    }

    // Partem do func
    public void visit(Function f){

    }

    // Partem do params
    public void visit(Parameters p){

    }

    // Partem do Type
    public void visit(TypeArray t){

    }

    // Partem do btype
    public void visit(TypeInt t){

    }

    public void visit(TypeChar t){

    }

    public void visit(TypeBool t){

    }

    public void visit(TypeFloat t){

    }

    public void visit(Type t){

    }

    public void visit(NameType i){  // TypeData

    }

    // Partem do cmd
    public void visit(Command c){

    }

    public void visit(CommandsList c){
    
    }    

    public void visit(If i){

    }

    public void visit(IfElse i){

    }

    public void visit(Iterate i){

    }

    public void visit(Read r){

    }

    public void visit(Print i){

    }

    public void visit(Return r){

    }

    public void visit(Attribution a){

    }

    public void visit(FunctionCall f){

    }
    
    // Partem do exp
    public void visit(And a){

    }

    // Partem do rexp
    public void visit(LessThan l){

    }

    public void visit(Equality e){

    }

    public void visit(Difference n){

    }

    // Partem do aexp
    public void visit(Addition a){

    }

    public void visit(Subtraction s){

    }

    // Partem do mexp
    public void visit(Multiplication m){

    }

    public void visit(Division d){

    }

    public void visit(Modular m){

    }


    // Partem do sexp
    public void visit(Not n){

    }

    public void visit(Minus n){

    }

    public void visit(BooleanValue b){ // True e False

    } 

    public void visit(Null n){

    }

    public void visit(IntegerNumber i){

    }

    public void visit(FloatNumber p){

    }

    public void visit(CharLitteral c){

    }

    // Partem do pexp
    public void visit(PexpIdentifier i){

    }

    public void visit(ExpParenthesis e){

    }

    public void visit(TypeInstanciate t){

    }

    public void visit(FunctionReturn f){

    }


    // Partem do lvalue
    public void visit(LValue l){

    }

    public void visit(ID i){

    }

    public void visit(Identifier i){

    }

    public void visit(DataAccess d){

    }

    public void visit(ArrayAccess a){

    }


    // Partem do exps
    public void visit(FCallParams f){

    }
}
