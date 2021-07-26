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

    private Stack<HashMap<String, Object>> env; // Escopo de variaveis de objetos
    private HashMap<String, Function> funcs; // Funções da linguagem lang
    private HashMap<String, Data> datas; // Tipos de dados novos
    private Stack<Object> operands; // Operandos
    public HashMap<Integer, Object> parms; // Parametros de funções
    private boolean retMode, debug;

    public InterpretVisitor() {
        env = new Stack<HashMap<String, Object>>();
        env.push(new HashMap<String, Object>());
        funcs = new HashMap<String, Function>();
        parms = new HashMap<Integer, Object>();
        datas = new HashMap<String, Data>();
        operands = new Stack<Object>();
        retMode = false;
        debug = false; // Colocar false pra desligar o debug
    }

    public void debugMode() {
        System.out.println("\n----------------------------------\n");
        System.out.println("---- DADOS DO DEBUG MODE ----\n");
        System.out.println("---- DADOS DE env ----\n");
        for (int i = 0; i < env.size(); i++) {
            System.out.println(env.elementAt(i));
        }
        System.out.println("\n---- DADOS DE funcs ----\n");
        /*
         * Entry.getKey method returns the key and Entry.getValue returns the value of
         * the HashMap entry.
         */
        // Esta tendo erro se deixar só o to string
        for (HashMap.Entry<String, Function> entry : funcs.entrySet()) {
            // entry.getValue().toString() mostra a função completa
            System.out.println(entry.getKey() + " => " + entry.getValue().getId());     
        }
        System.out.println("\n---- DADOS DE parms ----\n");
        for (HashMap.Entry<Integer, Object> entry : parms.entrySet()) {
            System.out.println(entry.getKey() + " => " + entry.getValue().toString());
        }
        System.out.println("\n---- DADOS DE datas ----\n");
        for (HashMap.Entry<String, Data> entry : datas.entrySet()) {
            System.out.println(entry.getKey() + " => " + entry.getValue().toString());
        }
        System.out.println("\n---- DADOS DE Operands ----\n");
        for (int i = 0; i < operands.size(); i++) {
            System.out.println(operands.elementAt(i).toString());
        }
        System.out.println("\n----------------------------------\n");
    }

    // Partem do prog

    @Override
    public void visit(Program p) {
        Node main = null;

        if (p.getDatas() != null) {
            for (Data data : p.getDatas()) {
                datas.put(data.getId(), data);
            }
        }

        for (Function f : p.getFunctions()) {
            funcs.put(f.getId(), f);
            if (f.getId().equals("main")) {     // Verifica se tem a função main
                main = f;
            }
        }

        if (main == null) {
            throw new RuntimeException("Não há uma função chamada \'main\' ! abortando !");
        }

        main.accept(this);
    }

    // Partem do data

    @Override
    public void visit(Data d) {
        if(debug){
            // Imprime a função
            System.out.println("\n");
            System.out.println(d.toString());
            System.out.println("\n");
        }
    }

    // Partem do decl

    @Override
    public void visit(Declaration d) {

    }

    // Partem do func

    @Override
    public void visit(Function f) {
        if (debug) {
            // Imprime a função
            System.out.println("\n ---- Function -- " + f.getClass().getName());
            System.out.println(f.toString());
            System.out.println("\n");
        }

        // Cria um escopo local
        HashMap<String, Object> localEnv = new HashMap<String, Object>();
        if (f.getParameters() != null) {
            Parameters params = f.getParameters();
            params.accept(this);
            // Adiciona as variaveis do parametro no escopo local
            for (int i = f.getParameters().size() - 1; i >= 0; i--) {
                localEnv.put(params.getSingleId(i), operands.pop());
            }
        }
        // Adiciona o escopo da função no env
        env.push(localEnv);

        // Verifica os commandos que compoem o corpo da função
        for (Command command : f.getCommands()) {
            command.accept(this);
        }

        // Remove o escopo local criado pra função
        env.pop();
        retMode = false;
    }

    // Partem do params

    @Override
    public void visit(Parameters p) {
        try {
            if (debug) {
                // Imprime a string da classe
                System.out.println("\n --- Parameters --- " + p.getClass().getName());
                System.out.println(p.toString());
                System.out.println("\n");
            }

            // Verifica os tipos de cada parâmetro da função
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
            throw new RuntimeException(" (" + p.getLine() + ", " + p.getColumn() + ") " + x.getMessage());
        }
    }

    // Partem do Type

    @Override
    public void visit(TypeArray t) {

    }

    // Partem do btype

    @Override
    public void visit(TypeInt t) {
        try {
            boolean ehParametro = false;
            // Empilha os parametros de função
            for (Integer key : parms.keySet()) {
                // Adiciona na listagem de operandos
                operands.push(parms.get(key));
                ehParametro = true;
            }
            if(ehParametro == false){  // Não é função, é um instanciamento de tipo
                operands.push(t);           // Empilha o tipo no operands para que ele seja pego no TypeInstan
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + t.getLine() + ", " + t.getColumn() + ") " + x.getMessage());
        }
    }

    @Override
    public void visit(TypeChar t) {
        try {
            boolean ehParametro = false;
            // Empilha os parametros de função
            for (Integer key : parms.keySet()) {
                // Adiciona na listagem de operandos
                operands.push(parms.get(key));
                ehParametro = true;
            }
            if(ehParametro == false){  // Não é função, é um instanciamento de tipo
                operands.push(t);           // Empilha o tipo no operands para que ele seja pego no TypeInstan
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + t.getLine() + ", " + t.getColumn() + ") " + x.getMessage());
        }
    }

    @Override
    public void visit(TypeBool t) {
        try {
            boolean ehParametro = false;
            // Empilha os parametros de função
            for (Integer key : parms.keySet()) {
                // Adiciona na listagem de operandos
                operands.push(parms.get(key));
                ehParametro = true;
            }
            if(ehParametro == false){  // Não é função, é um instanciamento de tipo
                operands.push(t);           // Empilha o tipo no operands para que ele seja pego no TypeInstan
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + t.getLine() + ", " + t.getColumn() + ") " + x.getMessage());
        }
    }

    @Override
    public void visit(TypeFloat t) {
        try {
            boolean ehParametro = false;
            // Empilha os parametros de função
            for (Integer key : parms.keySet()) {
                // Adiciona na listagem de operandos
                operands.push(parms.get(key));
                ehParametro = true;
            }
            if(ehParametro == false){  // Não é função, é um instanciamento de tipo
                operands.push(t);           // Empilha o tipo no operands para que ele seja pego no TypeInstan
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + t.getLine() + ", " + t.getColumn() + ") " + x.getMessage());
        }
    }

    @Override
    public void visit(NameType n) {
        try {
            if (debug) {
                System.out.println("\n -- NameType -- " + n.getClass().getName());
                System.out.println(n.toString());
                System.out.println("\n");
            }
            Object r = env.peek().get(n.getID());
            if (r != null || (r == null && env.peek().containsKey(n.getID()))) {
                operands.push(r);
            } else {
                // throw new RuntimeException("Erro!");
                throw new RuntimeException(" (" + n.getLine() + ", " + n.getColumn() + ") " + " : Erro !!");
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + n.getLine() + ", " + n.getColumn() + ") " + x.getMessage());
        }
    }

    @Override
    public void visit(Type t) {

    }

    // Partem do cmd

    @Override
    public void visit(Command c) {
        try {
            c.accept(this);
        } catch (Exception x) {
            throw new RuntimeException(" (" + c.getLine() + ", " + c.getColumn() + ") " + x.getMessage());
        }
    }

    @Override
    public void visit(CommandsList c) {
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
            throw new RuntimeException(" (" + c.getLine() + ", " + c.getColumn() + ") " + x.getMessage());
        }
    }

    @Override
    public void visit(If i) {
        try {
            i.getExp().accept(this);

            // Desempilha os operandos com "parametro" do if
            if ((boolean) operands.pop()) {
                i.getCmd().accept(this); // Verifica se o corpo de comandos do if é aceito
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + i.getLine() + ", " + i.getColumn() + ") " + x.getMessage());
        }
    }

    @Override
    public void visit(IfElse i) {
        try {
            i.getExp().accept(this);

            // Desempilha os operandos com "parametro" do if
            if ((boolean) operands.pop()) {
                i.getCmd().accept(this); // Verifica se o corpo de comandos do if é aceito
            } else {
                i.getElseCmd().accept(this);
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + i.getLine() + ", " + i.getColumn() + ") " + x.getMessage());
        }
    }

    @Override
    public void visit(Iterate i) {
        try {
            if (debug) {
                System.out.println("\n");
                System.out.println(i.toString());
                System.out.println("\n");
            }

            i.getExp().accept(this);
            Object obj = operands.pop();
            if (obj instanceof Boolean) // Objeto do tipo booleano na lista de operandos
                while ((Boolean) obj) { // Repito enquanto esse objeto do parametro do iterate for falso
                    i.getExp().accept(this);
                    i.getCmd().accept(this);
                    obj = operands.pop();
                }
            else if (obj instanceof Integer) {
                // Iterate com um número limitado de vezes
                for (int j = 0; j < (Integer) obj; j++) {
                    i.getCmd().accept(this);
                }
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + i.getLine() + ", " + i.getColumn() + ") " + x.getMessage());
        }
    }

    @Override
    public void visit(Read r) {
        try {
            if (debug) {
                System.out.println("\n");
                System.out.println(r.toString());
                System.out.println("\n");
            }
            LValue lvalue = r.getLValue();
            Scanner sc = new Scanner(System.in); // Scanner para fazer a leitura de entrada pelo teclado
            String input = sc.nextLine();
            if (lvalue instanceof Identifier) {
                // Adiciona o valor digitado pelo usuário no escopo
                // (Nome da variavel, valor digitado)
                env.peek().put(((Identifier) lvalue).getId(), input);
            } else if (lvalue instanceof DataAccess) {
                if(((DataAccess)lvalue).getLValue() instanceof ArrayAccess){ // array de data
                    // Quando chega array do tipo pontos[0].x => lValue é um dataAccess
                    ArrayAccess arrayElement = ((ArrayAccess)((DataAccess)lvalue).getLValue());
                    arrayElement.getExp().accept(this); // Aceita e adiciona a posicao do array no operandos

                    String nomeAtributo = ((DataAccess)lvalue).getId();
                    Integer position = (Integer)operands.pop();

                    // o array é um list de elementos
                    String nomeArray = arrayElement.getId();

                    // Busca o array no env
                    List<Object> objetoArray = ((List<Object>) env.peek().get(nomeArray));
                    Integer tamanhoArray = ((List)objetoArray).size();
                    
                    if((position >= 0) && (position <= tamanhoArray - 1)){
                        Object elemento = objetoArray.get(position);    // pega o elemento na posicao
                        ((HashMap<String, Object>) elemento).put(nomeAtributo, input);
                    }
                    else{
                        throw new RuntimeException(" (" + r.getLine() + ", " + r.getColumn() + ") Erro: Acesso a uma posicao invalida no array \'"+nomeArray+"\'  !!!");
                    }
                }
                else{   // Tipo data normal
                    Object obj = env.peek().get(((Identifier) ((DataAccess) lvalue).getLValue()).getId());
                    ((HashMap<String, Object>) obj).put(((DataAccess) lvalue).getId(), input);

                }
            }
            sc.close();     // Fecha o scanner
        } catch (Exception x) {
            throw new RuntimeException(" (" + r.getLine() + ", " + r.getColumn() + ") " + x.getMessage());
        }
    }

    @Override
    public void visit(Print i) {
        try {
            if (debug) {
                System.out.println("\n");
                System.out.println(i.toString());
                System.out.println("\n");
            }
            i.getExpression().accept(this);     // Faz a expressão e coloca no operando

            // Tira o objeto da lista de operandos e imprime
            Object obj = operands.pop();
            System.out.print(obj);
        } catch (Exception e) {
            throw new RuntimeException(" (" + i.getLine() + ", " + i.getColumn() + ") " + e.getMessage());
        }
    }

    @Override
    public void visit(Return r) {
        for (Expression exp : r.getExps()) {
            exp.accept(this);
        }
        retMode = true;
    }

    @Override
    public void visit(Attribution a) {
        try {
            if (debug) {
                System.out.println("\n --- Attribution --- " + a.getClass().getName());
                System.out.println(a.toString());
                System.out.println("\n");
            }
            a.getExp().accept(this);

            // Variavel que vai ter os dados atribuidos nela
            LValue lvalue = a.getLValue();

            // Se a variavel estiver dentro de um data
            if (lvalue instanceof DataAccess) {
                
                // Se o lvalue do data for um arrayACCESS
                // Significa que é um ARRAY DE DATA
                if(((DataAccess)lvalue).getLValue() instanceof ArrayAccess){
                    ArrayAccess arrayElement = ((ArrayAccess)((DataAccess)lvalue).getLValue());
                    arrayElement.getExp().accept(this); // Aceita e adiciona a posicao do array no operandos

                    String nomeAtributo = ((DataAccess)lvalue).getId();
                    String nomeObjeto = ((DataAccess)lvalue).getDataId();
                    Integer position = (Integer)operands.pop();
                    a.getExp().accept(this);            // Passar o valor a ser atribuido pro operands
                    Integer valorAtribuicao = (Integer) operands.pop();

                    // o array é um list de elementos
                    String nomeArray = arrayElement.getId();

                    // Busca o array no env
                    List<Object> objetoArray = ((List<Object>) env.peek().get(nomeArray));
                    Integer tamanhoArray = ((List)objetoArray).size();

                    // System.out.println("461 --- " + objetoArray.toString() + " -- " + nomeAtributo +" -- "+ valorAtribuicao);
                    
                    if((position >= 0) && (position <= tamanhoArray - 1)){
                        Object elemento = objetoArray.get(position);    // pega o elemento na posicao
                        ((HashMap<String, Object>) elemento).put(nomeAtributo, valorAtribuicao);
                        //debugMode();
                    }
                    else{
                        throw new RuntimeException(" (" + a.getLine() + ", " + a.getColumn() + ") Erro: Acesso a uma posicao invalida no array \'"+nomeArray+"\'  !!!");
                    }
                }
                else{   // Somente o DATA 
                    // System.out.println("LINHA --- 443 --- " + a.getExp() + " --- " + lvalue.toString() + " --- " + arrayElement.toString());
                    String nomeAtributo = ((DataAccess)lvalue).getId();
                    String nomeObjeto = ((DataAccess)lvalue).getDataId();
                    // System.out.println("448 ---- " + nomeAtributo + " -- " + nomeObjeto);
    
                    // Atributo do lado do '=' 
                    Object atributo = operands.pop();
                    // Busca o objeto no env e adiciona o atributo na variavel dele
                    HashMap<String, Object> objetoDinamico = ((HashMap<String, Object>) env.peek().get(nomeObjeto));
                    
                    // Verifica se o atributo do objeto existe no hashmap
                    if(objetoDinamico.get(nomeAtributo) != null){
                        objetoDinamico.put(nomeAtributo, atributo);
                    }
                    else{
                        // Lança um erro se o atributo não tiver no objeto
                        throw new RuntimeException(" (" + a.getLine() + ", " + a.getColumn() + ") Erro: Atributo "+ "\'"+nomeAtributo+"\'"+" eh inexistente no objeto " + "\""+nomeObjeto+"\"");
                    }
                }
            } else if (lvalue instanceof Identifier) { 
                // Se é um Identificador literal, variavel ou resultados de funções
                // System.out.println("Linha 398 - ATTRIBUTION -- " + lvalue.getId() + " -- " + a.getExp());
                env.peek().put(((Identifier) lvalue).getId(), operands.pop());
            }
            else if(lvalue instanceof ArrayAccess){     // Atribuição a um array
                // o array é um list de elementos
                String nomeArray = ((ArrayAccess)a.getLValue()).getId();
                ((ArrayAccess)lvalue).getExp().accept(this);        // Aceita e adiciona a posicao no operandos
                Integer position = (Integer)operands.pop();         // Posicao do array

                // Busca o array no env
                List<Object> objetoArray = ((List<Object>) env.peek().get(nomeArray));
                Integer tamanhoArray = ((List)objetoArray).size();
                
                if((position >= 0) && (position <= tamanhoArray - 1)){
                    // Pega o elemento do topo de operands e adiciona na posição do vetor
                    ((List)objetoArray).set(position, (Integer)operands.pop());
                }
                else{
                    throw new RuntimeException(" (" + a.getLine() + ", " + a.getColumn() + ") Erro: Acesso a uma posicao invalida no array \'"+nomeArray+"\'  !!!");
                }
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + a.getLine() + ", " + a.getColumn() + ") " + x.getMessage());
        }
    }

    @Override
    public void visit(FunctionCall f) {
        try {
            // Trata chamadas de função do tipo: fat(10)<q>
            /**
             * ---- Regra cmd: ID OPEN_PARENT exps? CLOSE_PARENT (LESS_THAN lvalue (COMMA
             * lvalue)* GREATER_THAN)? SEMI # FunctionCall
             * 
             * Exemplo: divmod(5, 2)<q, r>; // Será retornada 2 valores e armazenados na
             * variavel q e r
             */
            if (debug) {
                System.out.println("\n --- FunctionCall  -- " + f.getClass().getName());
                System.out.println(f.toString());
                System.out.println("\n");
            }
                
            // Pega a função correspondente
            Function function = funcs.get(f.getId());

            // Garante a existencia da função
            if (f != null) {
                // Aceita cada expressão
                for (Expression exp : f.getExps()) {
                    exp.accept(this);
                }

                // Passa do operand para o params
                // monta o parametro da função
                if (f.getFCallParams() != null) {
                    int tempID = 0;
                    //System.out.println("Teste -- 455");
                    // Verifica os parametros da função
                    for (Expression exp : f.getFCallParams().getExps()) {
                        //System.out.println("Teste -- 458 -- " + exp.toString());
                        exp.accept(this);
                        Object obj = (Object) operands.pop();
                        //System.out.println("Teste -- 461 -- " + obj.toString());

                        // Adiciona o parametro no ambiente da função
                        parms.put(tempID, obj);
                        tempID++;
                    }
                }
                function.accept(this);  // Executa a função

                // Retorno da função para as duas variaveis determinadas
                if (f.getLValues() != null) {
                    List<LValue> ret = f.getLValues();
                    int it = ret.size() - 1;

                    // Inverte a ordem quando empilha os operadores, logo, deve ser
                    // Desempilhado do direita pra esquerda
                    for (LValue l : ret) {
                        env.peek().put(ret.get(it).getId(), operands.pop());
                        it--;
                    }
                }
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + f.getLine() + ", " + f.getColumn() + ") " + x.getMessage());
        }
    }

    // Partem do exp

    @Override
    public void visit(And a) {
        try {
            a.getLeft().accept(this);
            a.getRight().accept(this);
            boolean right = (Boolean) operands.pop();
            boolean left = (Boolean) operands.pop();
            operands.push(left && right);
        } catch (Exception x) {
            throw new RuntimeException(" (" + a.getLine() + ", " + a.getColumn() + ") " + x.getMessage());
        }
    }

    // Partem do rexp

    @Override
    public void visit(LessThan l) {
        try {
            l.getLeft().accept(this);
            l.getRight().accept(this);
            Number right = (Number) operands.pop();
            Number left = (Number) operands.pop();
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
            throw new RuntimeException(" (" + l.getLine() + ", " + l.getColumn() + ") " + x.getMessage());
        }
    }

    @Override
    public void visit(Equality e) {
        try {
            e.getLeft().accept(this);
            e.getRight().accept(this);
            Number right = (Number) operands.pop();
            Number left = (Number) operands.pop();
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
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    @Override
    public void visit(Difference n) {
        try {
            n.getLeft().accept(this);
            n.getRight().accept(this);
            Number right = (Number) operands.pop();
            Number left = (Number) operands.pop();
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
            throw new RuntimeException(" (" + n.getLine() + ", " + n.getColumn() + ") " + x.getMessage());
        }
    }

    // Partem do aexp

    @Override
    public void visit(Addition a) {
        try {
            a.getLeft().accept(this);
            a.getRight().accept(this);
            Number right = (Number) operands.pop();
            Number left = (Number) operands.pop();
            if (left instanceof Float || right instanceof Float) {
                operands.push((Float) left + (Float) right);
            } else {
                operands.push((Integer) left + (Integer) right);
            }
        } catch (Exception e) {
            throw new RuntimeException(" (" + a.getLine() + ", " + a.getColumn() + ") " + e.getMessage());
        }
    }

    @Override
    public void visit(Subtraction s) {
        try {
            s.getLeft().accept(this);
            s.getRight().accept(this);
            // Primeiro é empilhado da esquerda pra direita, logo, o topo da pilha
            // é o operando da direita
            Number right = (Number) operands.pop();
            Number left = (Number) operands.pop();
            if (left instanceof Float || right instanceof Float) {
                operands.push((Float) left - (Float) right);
            } else {
                operands.push((Integer) left - (Integer) right);
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + s.getLine() + ", " + s.getColumn() + ") " + x.getMessage());
        }
    }

    // Partem do mexp
    @Override
    public void visit(Multiplication m) {
        try {
            m.getLeft().accept(this);
            m.getRight().accept(this);
            // Primeiro é empilhado da esquerda pra direita, logo, o topo da pilha
            // é o operando da direita
            Number right = (Number) operands.pop();
            Number left = (Number) operands.pop();
            if (left instanceof Float || right instanceof Float) {
                operands.push((Float) left * (Float) right);
            } else {
                operands.push((Integer) left * (Integer) right);
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + m.getLine() + ", " + m.getColumn() + ") " + x.getMessage());
        }
    }

    @Override
    public void visit(Division d) {
        try {
            d.getLeft().accept(this);
            d.getRight().accept(this);
            // Primeiro é empilhado da esquerda pra direita, logo, o topo da pilha
            // é o operando da direita
            Number right = (Number) operands.pop();
            Number left = (Number) operands.pop();
            if (left instanceof Float || right instanceof Float) {
                operands.push(((Float) left) / ((Float) right));
            } else {
                operands.push(((Integer) left) / ((Integer) right));
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + d.getLine() + ", " + d.getColumn() + ") " + x.getMessage());
        }
    }

    @Override
    public void visit(Modular m) {
        try {
            m.getLeft().accept(this);
            m.getRight().accept(this);
            Number right = (Number) operands.pop();
            Number left = (Number) operands.pop();
            if (left instanceof Float || right instanceof Float) {
                operands.push((Float) left % (Float) right);
            } else {
                operands.push((Integer) left % (Integer) right);
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + m.getLine() + ", " + m.getColumn() + ") " + x.getMessage());
        }
    }

    // Partem do sexp

    @Override
    public void visit(Not n) {
        try {
            n.getExpression().accept(this);
            operands.push(!(boolean) operands.pop());
        } catch (Exception e) {
            throw new RuntimeException(" (" + n.getLine() + ", " + n.getColumn() + ") " + e.getMessage());
        }
    }

    @Override
    public void visit(Minus n) {
        try {
            n.getExpression().accept(this);
            Number number = (Number) operands.pop();
            if (number instanceof Float) {
                operands.push((Float) number * -1);
            } else {
                operands.push((Integer) number * -1);
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + n.getLine() + ", " + n.getColumn() + ") " + x.getMessage());
        }
    }

    @Override
    public void visit(BooleanValue b) { // True e False
        try {
            operands.push(b.getValue());
        } catch (Exception e) {
            throw new RuntimeException(" (" + b.getLine() + ", " + b.getColumn() + ") " + e.getMessage());
        }
    }

    @Override
    public void visit(Null n) {
        try {
            operands.push(n.getValue());
        } catch (Exception x) {
            throw new RuntimeException(" (" + n.getLine() + ", " + n.getColumn() + ") " + x.getMessage());
        }
    }

    @Override
    public void visit(IntegerNumber i) {
        try {
            operands.push(i.getValue());
        } catch (Exception e) {
            throw new RuntimeException(" (" + i.getLine() + ", " + i.getColumn() + ") " + e.getMessage());
        }
    }

    @Override
    public void visit(FloatNumber p) {
        try {
            operands.push(p.getValue());
        } catch (Exception x) {
            throw new RuntimeException(" (" + p.getLine() + ", " + p.getColumn() + ") " + x.getMessage());
        }
    }

    @Override
    public void visit(CharLitteral c) {
        try {
            operands.push(c.getValue());
        } catch (Exception x) {
            throw new RuntimeException(" (" + c.getLine() + ", " + c.getColumn() + ") " + x.getMessage());
        }
    }

    // Partem do pexp

    @Override
    public void visit(PexpIdentifier i) {

    }

    @Override
    public void visit(ExpParenthesis e) {

    }

    @Override
    public void visit(TypeInstanciate t) {
        try {
            if (debug) {
                // Imprime a função
                System.out.println("\n -- TypeInstanciate");
                System.out.println(t.toString());
                System.out.println("\n");
            }

            // Garante que não é um tipo Data
            if(t.getType() != null){
                t.getType().accept(this);           // Empilha o tipo no operands
                if (t.getExp() != null) {
                    t.getExp().accept(this);            // Executa exp passando o tamanho do vetor para operands
                    // System.out.println("LINHA 838 -- " + t.getType() + " --- " + t.getExp());
                    // Trata a condição de ser array do tipo DATA
                    if(t.getType() instanceof NameType){   

                        // Pega o tamanho do vetor na pilha de operandos
                        Integer i = (Integer) operands.pop();       // Tamanho do array já foi visto
                        //System.out.println(i);
                        Object obj = operands.pop();                // Tipo do array -> Int, Float, Char ....
                        //System.out.println(obj.toString());
                        List<Object> lista = new ArrayList<Object>(i); // Tipo array
                        for (int k = 0; k < i; k++) {
                            lista.add(obj);
                        }
                        operands.push(lista);
                    }
                    else{       // Array generico 
                        // Pega o tamanho do vetor na pilha de operandos
                        Integer i = (Integer) operands.pop();       // Tamanho do array já foi visto
                        //System.out.println(i);
                        Object obj = operands.pop();                // Tipo do array -> Int, Float, Char ....
                        //System.out.println(obj.toString());
                        List<Object> lista = new ArrayList<Object>(i); // Tipo array
                        for (int k = 0; k < i; k++) {
                            lista.add(obj);
                        }
                        operands.push(lista);
                    }
                }
                else{   // É um tipo de dado comum: Int, Float, Char
                    System.out.println("TTTTTTT");
                    t.getExpression().accept(this);                
                }
            }
            else{   // Tipo data que no qual o atributo Type é null
                if (t.getExp() == null) {                   // Tipo normal de data
                    // Pega o nome do objeto do Data
                    String dataID = t.getDataName();   
                    // Cria um Hashmap pra servir de alocação variaveis para o objeto
                    HashMap<String, Object> newVar = new HashMap<String, Object>();
                    
                    for (Declaration d : datas.get(dataID).getDeclarations()) {
                        // Verifica as declações das variaveis e tipos no data
                        d.getType().accept(this);

                        // Cria um objeto especial para destacar quais variaveis e seu tipo
                        // dentro do data
                        Object valorPadrao = new ObjectDefault(t.getLine(), t.getColumn(),
                        d.getId(), d.getType());

                        // Adiciona o objeto com as variaveis vazias
                        newVar.put(d.getId(), valorPadrao);    // Adiciona um objeto vazio
                    }
                    operands.push(newVar);
                }
                else{   // Trata a condição de ser array do tipo DATA
                    t.getExp().accept(this);            // Executa exp passando o tamanho do vetor para operands
                    // Pega o nome do objeto do Data
                    String dataID = t.getDataName();   

                    // Pega o tamanho do vetor na pilha de operandos
                    Integer i = (Integer) operands.pop();       // Tamanho do array já foi visto
                    System.out.println(i);

                    List<Object> lista = new ArrayList<Object>(i); // Tipo array

                    for (int k = 0; k < i; k++) {
                        // Cria um Hashmap pra servir de alocação variaveis para o objeto
                        HashMap<String, Object> newVar = new HashMap<String, Object>();
                        for (Declaration d : datas.get(dataID).getDeclarations()) {
                            // Cria um objeto especial para destacar quais variaveis e seu tipo
                            // dentro do data
                            Object valorPadrao = new ObjectDefault(t.getLine(), t.getColumn(),
                            d.getId(), d.getType());

                            // Adiciona o objeto com as variaveis vazias
                            newVar.put(d.getId(), valorPadrao);    // Adiciona um objeto vazio
                        }
                        lista.add(newVar);
                    }
                    operands.push(lista);
                }
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + t.getLine() + ", " + t.getColumn() + ") " + x.getMessage());
        }
    }

    @Override
    public void visit(FunctionReturn f) {
        /********************************************************************************
         * MESMO QUE TENHA SOMENTE 1 RETORNO, ELA DEVE SER CHAMADA ASSIM: fat(num−1)[0] *
         * AGORA SEM RETORNO PODE SER SÓ: fat(num−1)                                    *
         ********************************************************************************/
        // pexp: ID OPEN_PARENT exps? CLOSE_PARENT OPEN_BRACKET exp CLOSE_BRACKET #
        // 'FunctionReturn' // Como retorna 2 valores, logo precisa do
        // funcao(parametros)[indice] Exemplo: fat(num−1)[0]
        try {
            if (debug) {
                // Imprime a função
                System.out.println("\n -- Function Return");
                System.out.println(f.toString());
                System.out.println("\n");
            }
            // Pega a função correspondente
            Function function = funcs.get(f.getId());

            // Garante a existencia da função
            if (f != null) {
                if (f.getFCallParams() != null) {
                    int tempID = 0;

                    // Verifica os parametros da função
                    for (Expression exp : f.getFCallParams().getExps()) {
                        exp.accept(this);
                        Object obj = (Object) operands.pop();

                        // Adiciona o parametro no ambiente da função
                        parms.put(tempID, obj);
                        tempID++;
                    }
                }

                // Executa a função e coloca o retorno dos parametros em operands
                function.accept(this);

                // Pega o valor da posicão da que identifica qual variavel o
                // usuario quer que seja retornada
                IntegerNumber valueReturnedPos = (IntegerNumber) f.getExpIndex();

                // Desempilha e pega somente a posicao da variavel identificada pelo usuario
                // Verifica se a função tem dois retornos
                if(function.getReturnTypes().size() == 2){
                    if ((Integer) valueReturnedPos.getValue() == 0 ||
                        (Integer) valueReturnedPos.getValue() == 1
                    ) {
                        // Posicao 0, ou seja mais abaixo na pilha, logo, descarta o de cima
                        if ((Integer) valueReturnedPos.getValue() == 0) {
                            operands.pop();
                        }
                        // Tira da pilha, remove o resto e volta com ele pra pilha
                        Object topoPilha = operands.pop();
                        Integer qtdParametros = function.getParameters().getId().size();
                        // retira os valores que estão no operands que não são retorno da função
                        for(int i = 0; i < qtdParametros; i++){
                            operands.pop();
                        }
                        // Empilha de novo o topo
                        operands.push(topoPilha);
                        // Limpa dos parametros 
                        while(parms.size() > 0){ 
                            parms.values().clear(); 
                        }
                    } else {
                        throw new RuntimeException(" (" + f.getLine() + ", " + f.getColumn()
                                + ") Acesso a posicao invalida de elemento no retorno da funcao");
                    }
                }
                else if(function.getReturnTypes().size() == 1){ // Quando tiver somente 1 retorno
                    if ((Integer) valueReturnedPos.getValue() == 0) {

                        // Tira da pilha, remove o resto e volta com ele pra pilha
                        Object topoPilha = operands.pop();
                        Integer qtdParametros = function.getParameters().getId().size();
                        // retira os valores que estão no operands que não são retorno da função
                        for(int i = 0; i < qtdParametros; i++){
                            operands.pop();
                        }
                        // Empilha de novo o topo
                        operands.push(topoPilha);
                        // Limpa dos parametros 
                        while(parms.size() > 0){ 
                            parms.values().clear(); 
                        }
                    } else {
                        throw new RuntimeException(" (" + f.getLine() + ", " + f.getColumn()
                                + ") Acesso a posicao invalida de elemento no retorno da funcao");
                    }
                }
                else{
                    throw new RuntimeException(" (" + f.getLine() + ", " + f.getColumn()
                                + ") A funcao nao apresenta tipos de retorno");
                }   
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + f.getLine() + ", " + f.getColumn() + ") " + x.getMessage());
        }

    }

    // Partem do lvalue

    @Override
    public void visit(LValue l) {

    }

    @Override
    public void visit(ID i) {

    }

    @Override
    public void visit(Identifier i) {
        try {
            if (debug) {
                // Imprime a função
                System.out.println("\n -- Identifier");
                System.out.println(i.toString());
                System.out.println("\n");
            }
            Object r = env.peek().get(i.getId());
            if (r != null || (r == null && env.peek().containsKey(i.getId()))) {
                operands.push(r);
            } else {
                // throw new RuntimeException("Erro!");
                throw new RuntimeException(" (" + i.getLine() + ", " + i.getColumn() + ") " + ": Erro!!");
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + i.getLine() + ", " + i.getColumn() + ") " + x.getMessage());
        }
    }

    @Override
    public void visit(DataAccess d) {
        try {
            if (debug) {
                // Imprime a função
                System.out.println("\n -- DataAccess");
                System.out.println(d.toString());
                System.out.println("\n");
            }
            Object obj = env.peek().get(d.getLValue().getId());
            if(d.getLValue() instanceof ArrayAccess){ // array de data
                // Quando chega array do tipo pontos[0].x => d é um dataAccess
                if (obj != null) {
                    // obj chega como uma lista
                    ArrayAccess array = ((ArrayAccess)d.getLValue());
                    array.getExp().accept(this);    // Adiciona a posicao do vetor no operands
                    Integer position = (Integer)operands.pop();
                    String atributoDoObjeto = d.getId();
                    HashMap objeto = (HashMap)((List)obj).get(position);
                    if(objeto.containsKey(atributoDoObjeto)){   // Se o objeto tem o atributo
                        operands.push(objeto.get(atributoDoObjeto));
                    }
                    else{
                        // Atributo que não pertence ao objeto
                        throw new RuntimeException(" (" + d.getLine() + ", " + d.getColumn() + ") Erro: Atributo "+ "\'"+d.getId()+"\'"+" eh inexistente no objeto " + "\""+d.getLValue().getId()+"\" !!!");
                    }
                } else {
                    // Objeto não existe
                    throw new RuntimeException(" (" + d.getLine() + ", " + d.getColumn() + ") Erro: O Objeto "+ "\""+d.getLValue().getId()+"\" nao existe!!!");
                }
            }
            else{       // Data
                if (obj != null) {
                    if (((HashMap<String, Object>) obj).containsKey(d.getId())) {
                        operands.push(((HashMap<String, Object>) obj).get(d.getId()));
                    } else {
                        // Atributo que não pertence ao objeto
                        throw new RuntimeException(" (" + d.getLine() + ", " + d.getColumn() + ") Erro: Atributo "+ "\'"+d.getId()+"\'"+" eh inexistente no objeto " + "\""+d.getLValue().getId()+"\" !!!");
                    }
                } else {
                    // Objeto não existe
                    throw new RuntimeException(" (" + d.getLine() + ", " + d.getColumn() + ") Erro: O Objeto "+ "\""+d.getLValue().getId()+"\" nao existe!!!");
                }
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + d.getLine() + ", " + d.getColumn() + ") " + x.getMessage());
        }
    }

    @Override
    public void visit(ArrayAccess a) {
        try{
            if (debug) {
                // Imprime a função
                System.out.println("\n -- ArrayAccess --- " + a.getClass().getName());
                System.out.println(a.toString());
                System.out.println("\n");
            }
            // obj é uma lista => lista de elementos do array
            Object obj = env.peek().get(a.getLValue().getId());
            if (obj != null) {
                a.getExp().accept(this);        // Adiciona a posicao no vetor nos operands
                Integer position = (Integer) operands.pop();
                Integer tamanhoArray = ((List)obj).size();
                if((position >= 0) && (position <= tamanhoArray - 1)){
                    operands.push(((List)obj).get(position));
                }
                else{
                    throw new RuntimeException(" (" + a.getLine() + ", " + a.getColumn() + ") Erro: Acesso a uma posicao invalida no array \'"+a.getLValue().getId()+"\'  !!!");
                }
            } else {
                // Array não existe
                throw new RuntimeException(" (" + a.getLine() + ", " + a.getColumn() + ") Erro: O array "+ "\""+a.getLValue().getId()+"\" nao existe!!!");
            }
        }
        catch (Exception x) {
            throw new RuntimeException(" (" + a.getLine() + ", " + a.getColumn() + ") " + x.getMessage());
        }
    }

    // Partem do exps

    @Override
    public void visit(FCallParams f) {
        try {
            for (Expression expression : f.getExps()) {
                expression.accept(this);
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + f.getLine() + ", " + f.getColumn() + ") " + x.getMessage());
        }
    }
}