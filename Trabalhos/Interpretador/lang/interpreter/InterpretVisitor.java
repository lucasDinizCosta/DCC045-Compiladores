/********************************************************
* Trabalho de Teoria dos Compiladores(DCC045)(2021/1)   *
*                                                       *
* Projeto do Interpretador para a Linguagem Lang        *
* Nome: Lucas Diniz da Costa -- Matricula: 201465524C   *
*                                                       *
*********************************************************/
package lang.interpreter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import java.util.Scanner;

import lang.ast.*;
import lang.interpreter.Visitor;

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
        Node main = null;

        if (p.getDatas() != null) {
            for (Data data : p.getDatas()) {
                datas.put(data.getId(), data);
            }
        }

        for (Function f : p.getFunctions()) {
            funcs.put(f.getId(), f);
            if (f.getId().equals("main")) {
                main = f;
            }
        }

        if (main == null) {
            throw new RuntimeException("Não há uma função chamada main ! abortando !");
        }

        main.accept(this);
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
        HashMap<String, Object> localEnv = new HashMap<String, Object>();
        if (f.getParameters() != null) {
            Parameters params = f.getParameters();
            params.accept(this);
            for (int i = f.getParameters().size() - 1; i >= 0; i--) {
                localEnv.put(params.getSingleId(i), operands.pop());
            }
        }
        env.push(localEnv);
        for (Command command : f.getCommands()) {
            command.accept(this);
        }
        env.pop();
        retMode = false;
    }

    // Partem do params

    @Override
    public void visit(Parameters p){
        try {
            for (Type type : p.getType()) {
                type.accept(this);
                int pos = 0;
                for (String id : p.getId()) {
                    // salva a variável no escopo da funcao, de acordo com valor do parametro
                    // passado
                    env.peek().put(id, parms.get(pos));
                    pos++;
                }

            }
        } catch (Exception x) {
            throw new RuntimeException(x.getMessage());
        }
    }

    // Partem do Type

    @Override
    public void visit(TypeArray t){

    }

    // Partem do btype

    @Override
    public void visit(TypeInt t){
        try {
            for (Integer key : parms.keySet()) {
                operands.push(parms.get(key));
            }
        } catch (Exception x) {
            throw new RuntimeException(x.getMessage());
        }
    }

    @Override
    public void visit(TypeChar t){
        try {
            for (Integer key : parms.keySet()) {
                operands.push(parms.get(key));
            }
        } catch (Exception x) {
            throw new RuntimeException(x.getMessage());
        }
    }

    @Override
    public void visit(TypeBool t){
        try {
            for (Integer key : parms.keySet()) {
                operands.push(parms.get(key));
            }
        } catch (Exception x) {
            throw new RuntimeException("Erro!");
        }
    }

    @Override
    public void visit(TypeFloat t){
        try {
            for (Integer key : parms.keySet()) {
                operands.push(parms.get(key));
            }
        } catch (Exception x) {
            throw new RuntimeException(x.getMessage());
        }
    }

    @Override
    public void visit(NameType n){
        try {
            Object r = env.peek().get(n.getID());
            if (r != null || (r == null && env.peek().containsKey(n.getID()))) {
                operands.push(r);
            } else {
                throw new RuntimeException("Erro!");
            }
        } catch (Exception x) {
            throw new RuntimeException(x.getMessage());
        }
    }

    @Override
    public void visit(Type t){

    }


    // Partem do cmd

    @Override
    public void visit(Command c){
        try {
            c.accept(this);
        } catch (Exception x) {
            throw new RuntimeException(x.getMessage());
        }
    }

    @Override
    public void visit(CommandsList c){
        if (retMode) {
            return;
        }
        try {
            for (Command command : c.getCommands()) {
                command.accept(this);
                if (retMode) {
                    return;
                }
            }
        } catch (Exception x) {
            throw new RuntimeException(x.getMessage());
        }
    }

    @Override
    public void visit(If i){
        try {
            i.getExp().accept(this);
            if ((boolean) operands.pop()) {
                i.getCmd().accept(this);
            }

        } catch (Exception x) {
            throw new RuntimeException(x.getMessage());
        }
    }

    @Override
    public void visit(IfElse i){
        try {
            i.getExp().accept(this);
            if ((boolean) operands.pop()) {
                i.getCmd().accept(this);
            } else {
                i.getElseCmd().accept(this);
            }

        } catch (Exception x) {
            throw new RuntimeException(x.getMessage());
        }
    }

    @Override
    public void visit(Iterate i){
        try {
            i.getExp().accept(this);
            Object obj = operands.pop();
            if (obj instanceof Boolean)
                while ((Boolean) obj) {
                    i.getExp().accept(this);
                    i.getCmd().accept(this);
                    obj = operands.pop();
                }
            else if (obj instanceof Integer) {
                for (int j = 0; j < (Integer) obj; j++) {
                    i.getCmd().accept(this);
                }
            }
        } catch (Exception x) {
            throw new RuntimeException(x.getMessage());
        }
    }

    @Override
    public void visit(Read r){
        try {
            LValue lvalue = r.getLValue();
            Scanner sc = new Scanner(System.in);    // Scanner para fazer a leitura de entrada pelo teclado
            String input = sc.nextLine();
            if (lvalue instanceof Identifier) {
                env.peek().put(((Identifier) lvalue).getId(), input);
            }
            else if (lvalue instanceof DataAccess) {
                Object obj = env.peek().get(((Identifier) ((DataAccess) lvalue).getLValue()).getId());
                ((HashMap<String, Object>) obj).put(((DataAccess) lvalue).getId(), input);
            }
        } catch (Exception x) {
            throw new RuntimeException(x.getMessage());
        }
    }

    @Override
    public void visit(Print i){
        try {
            i.getExpression().accept(this);
            Object obj = operands.pop();
            System.out.print(obj);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void visit(Return r){
        for (Expression exp : r.getExps()) {
            exp.accept(this);
        }
        retMode = true;
    }

    @Override
    public void visit(Attribution a){
        try {
            a.getExp().accept(this);
            LValue lvalue = a.getLValue();
            if (lvalue instanceof DataAccess) {
                String str = a.getLValue().toString();
                Object obj = operands.pop();
                env.peek().put(str, obj);
            }
            else if (lvalue instanceof Identifier) {
                env.peek().put(((Identifier) lvalue).getId(), operands.pop());
            }
        } catch (Exception x) {
            throw new RuntimeException(x.getMessage());
        }
    }

    @Override
    public void visit(FunctionCall f){
        try {
            Function function = funcs.get(f.getId());
            if (f != null) {
                for (Expression exp : f.getExps()) {
                    exp.accept(this);
                }
                if (f.getFCallParams() != null) {
                    int tempID = 0;
                    for (Expression exp : f.getFCallParams().getExps()) {
                        exp.accept(this);
                        Object obj = (Object) operands.pop();
                        parms.put(tempID, obj);
                        tempID++;
                    }
                }
                function.accept(this);
                if (f.getLValues() != null) {
                    List<LValue> ret = f.getLValues();
                    int it = 0;
                    for (LValue l : ret) {
                        env.peek().put(ret.get(it).getId(), operands.pop());
                        it++;
                    }

                    // for(int i = ret.size() - 1; i >= 0 ; i++){
                    // env.peek().put(ret.get(i).getId(),operands.pop());
                    // }

                }
            }

        } catch (Exception x) {
            throw new RuntimeException(x.getMessage());
        }
    }
    
    // Partem do exp

    @Override
    public void visit(And a){
        try {
            a.getLeft().accept(this);
            a.getRight().accept(this);

            boolean left = (Boolean) operands.pop();
            boolean right = (Boolean) operands.pop();

            operands.push(left && right);
        } catch (Exception x) {
            throw new RuntimeException(x.getMessage());
        }
    }


    // Partem do rexp

    @Override
    public void visit(LessThan l){
        try {
            l.getLeft().accept(this);
            l.getRight().accept(this);

            Number left = (Number) operands.pop();
            Number right = (Number) operands.pop();

            if (left instanceof Float && right instanceof Float) {
                if (((Float) left) < ((Float) right)) {
                    operands.push(true);
                } else {
                    operands.push(false);
                }
            } else if (left instanceof Integer && right instanceof Integer) {
                if (((Integer) left) < ((Integer) right)) {
                    operands.push(true);
                } else {
                    operands.push(false);
                }
            }

        } catch (Exception x) {
            throw new RuntimeException(x.getMessage());
        }
    }

    @Override
    public void visit(Equality e){
        try {
            e.getLeft().accept(this);
            e.getRight().accept(this);
            Number left = (Number) operands.pop();
            Number right = (Number) operands.pop();
            if (left instanceof Float && right instanceof Float) {
                if (((Float) left) == ((Float) right)) {
                    operands.push(true);
                } else {
                    operands.push(false);
                }
            } else if (left instanceof Integer && right instanceof Integer) {
                if (((Integer) left) == ((Integer) right)) {
                    operands.push(true);
                } else {
                    operands.push(false);
                }
            }
        } catch (Exception x) {
            throw new RuntimeException(x.getMessage());
        }
    }

    @Override
    public void visit(Difference n){
        try {
            n.getLeft().accept(this);
            n.getRight().accept(this);
            Number left = (Number) operands.pop();
            Number right = (Number) operands.pop();
            if (left instanceof Float && right instanceof Float) {
                if (((Float) left) == ((Float) right)) {
                    operands.push(false);
                } else {
                    operands.push(true);
                }
            } else if (left instanceof Integer && right instanceof Integer) {
                if (((Integer) left) == ((Integer) right)) {
                    operands.push(false);
                } else {
                    operands.push(true);
                }
            }
        } catch (Exception x) {
            throw new RuntimeException(x.getMessage());
        }
    }

    // Partem do aexp

    @Override
    public void visit(Addition a){
        try {
            a.getLeft().accept(this);
            a.getRight().accept(this);
            Number left = (Number) operands.pop();
            Number right = (Number) operands.pop();
            if (left instanceof Float || right instanceof Float) {
                operands.push((Float) left + (Float) right);
            } else {
                operands.push((Integer) left + (Integer) right);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void visit(Subtraction s){
        try {
            s.getLeft().accept(this);
            s.getRight().accept(this);
            Number left = (Number) operands.pop();
            Number right = (Number) operands.pop();
            if (left instanceof Float || right instanceof Float) {
                operands.push((Float) left - (Float) right);
            } else {
                operands.push((Integer) left - (Integer) right);
            }
        } catch (Exception x) {
            throw new RuntimeException(x.getMessage());
        }
    }

    // Partem do mexp
    @Override
    public void visit(Multiplication m){
        try {
            m.getLeft().accept(this);
            m.getRight().accept(this);
            Number left = (Number) operands.pop();
            Number right = (Number) operands.pop();
            if (left instanceof Float || right instanceof Float) {
                operands.push((Float) left * (Float) right);
            } else {
                operands.push((Integer) left * (Integer) right);
            }
        } catch (Exception x) {
            throw new RuntimeException(x.getMessage());
        }
    }

    @Override
    public void visit(Division d){
        try {
            d.getLeft().accept(this);
            d.getRight().accept(this);
            Number left = (Number) operands.pop();
            Number right = (Number) operands.pop();
            if (left instanceof Float || right instanceof Float) {
                operands.push(((Float) left) / ((Float) right));
            } else {
                operands.push(((Integer) left) / ((Integer) right));
            }
        } catch (Exception x) {
            throw new RuntimeException(x.getMessage());
        }
    }

    @Override
    public void visit(Modular m){
        try {
            m.getLeft().accept(this);
            m.getRight().accept(this);
            Number left = (Number) operands.pop();
            Number right = (Number) operands.pop();
            if (left instanceof Float || right instanceof Float) {
                operands.push((Float) left % (Float) right);
            } else {
                operands.push((Integer) left % (Integer) right);
            }
        } catch (Exception x) {
            throw new RuntimeException(x.getMessage());
        }
    }

    // Partem do sexp

    @Override
    public void visit(Not n){
        try{
            n.getExpression().accept(this);
            operands.push(!(boolean) operands.pop());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void visit(Minus n){
        try {
            n.getExpression().accept(this);
            Number number = (Number) operands.pop();
            if (number instanceof Float) {
                operands.push((Float) number * -1);
            } else {
                operands.push((Integer) number * -1);
            }
        } catch (Exception x) {
            throw new RuntimeException(x.getMessage());
        }
    }

    @Override
    public void visit(BooleanValue b){ // True e False
        try{
            operands.push(b.getValue());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void visit(Null n){
        try{
            operands.push(null);
        } catch (Exception x) {
            throw new RuntimeException(x.getMessage());
        }
    }

    @Override
    public void visit(IntegerNumber i){
        try {
            operands.push(i.getValue());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void visit(FloatNumber p){
        try {
            operands.push(p.getValue());
        } catch (Exception x) {
            throw new RuntimeException(x.getMessage());
        }
    }

    @Override
    public void visit(CharLitteral c){
        try{
            operands.push(c.getValue());
        } catch (Exception x) {
            throw new RuntimeException(x.getMessage());
        }
    }

    // Partem do pexp

    @Override
    public void visit(PexpIdentifier i){

    }

    @Override
    public void visit(ExpParenthesis e){

    }
    
    @Override
    public void visit(TypeInstanciate t){
        try {
            t.getType().accept(this);
            if (t.getExpression() != null) {
                t.getExpression().accept(this);
                Integer i = (Integer) operands.pop();
                Object obj = operands.pop();
                List<Object> lista = new ArrayList<Object>(i);
                for (int k = 0; k < i; k++) {
                    lista.add(obj);
                }
                operands.push(lista);
            }
            if (t.getExpression() == null) {
                if (t.getType() instanceof TypeData) {
                    String data_id = ((TypeData) t.getType()).getId();
                    HashMap<String, Object> newVar = new HashMap<String, Object>();
                    for (Declaration d : datas.get(data_id).getDeclarations()) {
                        d.getType().accept(this);
                        newVar.put(d.getId(), operands.pop());
                    }
                    operands.push(newVar);
                }
            }
        } catch (Exception x) {
            throw new RuntimeException(x.getMessage());
        }
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
        try{
            Object r = env.peek().get(i.getId());
            if (r != null || (r == null && env.peek().containsKey(i.getId()))) {
                operands.push(r);
            } else {
                throw new RuntimeException("Erro!");
            }
        }catch(Exception x) {
            throw new RuntimeException(x.getMessage());
        }
    }

    @Override
    public void visit(DataAccess d){
        try {
            LValue lvalue = d.getLValue();
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            if (lvalue instanceof Identifier) {
                env.peek().put(((Identifier) lvalue).getId(), input);
            }
            else if (lvalue instanceof DataAccess) {
                Object obj = env.peek().get(((Identifier) ((DataAccess) lvalue).getLValue()).getId());
                ((HashMap<String, Object>) obj).put(((DataAccess) lvalue).getId(), input);
            }
        } catch (Exception x) {
            throw new RuntimeException(x.getMessage());
        }
    }

    @Override
    public void visit(ArrayAccess a){

    }

    // Partem do exps

    @Override
    public void visit(FCallParams f){
        try {
            for(Expression expression : f.getExps()){
                expression.accept(this);
            }
        } catch (Exception x) {
            throw new RuntimeException(x.getMessage());
        }
    }
}