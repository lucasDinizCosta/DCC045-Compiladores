package lang.semantic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    private STyErr tyErr = STyErr.newSTyErr();

    // Armazena as mensagens de erro
    private ArrayList<String> logError;

    // Armazena o ambiente das funções
    private TyEnv<LocalAmbiente<SType>> env;

    // Ambiente temporario da função para executar os comandos
    private LocalAmbiente<SType> temp;

    // Pilha de tipos da linguagem lang
    private Stack<SType> stk;

    // Retorno de função => Se o retorno for acionado não deve ser feito os outros
    // comandos da função
    private boolean retChk;

    // armazena os dados do tipo data
    private HashMap<String, DataAttributes> datas; // (Nome do tipo, Atributos e seus tipos)

    public TypeCheckVisitor() {
        stk = new Stack<SType>();
        env = new TyEnv<LocalAmbiente<SType>>();
        logError = new ArrayList<String>();
        datas = new HashMap<String, DataAttributes>();
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

    // https://www.techiedelight.com/get-current-line-number-java/
    // Retorna a linha do código fonte ao passar pela instrução
    public int getLineNumber(){
        // return new Throwable().getStackTrace()[0].getLineNumber();
        return Thread.currentThread().getStackTrace()[2].getLineNumber();
    }

    // Partem do prog
    @Override
    public void visit(Program p) {

        // Aceita os tipos data para fazer a verificação de tipo
        for (Data d : p.getDatas()) {
            d.accept(this);
        }

        // passa para funcoes

        /*boolean shouldAddFunc = true;

        for (Function f : p.getFunctions()) {

            // valida as funcoes que deveriam ser aceitas
            shouldAddFunc = true;

            // garantindo sobrecarga - verificando pelo num de parametros
            for (int i = 0; i < sobrecarga.size(); i++) {

                // se encontrar uma funcao com mesmo nome na lista de sobrecarga, vai verficiar
                // os params
                if (f.getId().equals(sobrecarga.get(i).getId())) {

                    // se esta funcao com mesmo nome for main, ja retorna o erro
                    if (f.getId().equals("main")) {
                        shouldAddFunc = false;
                        logError.add("Erro em (" + f.getLine() + ", " + f.getColumn()
                                + "). Só pode haver uma função denominada \"main\".");
                    }

                    // caso contrario, verifica a possibilidade de sobrecarga
                    else {
                        // se ja tiver sido declarada com mesmas mesmo num de param, retorna erro
                        if (f.getParameters() != null
                                && f.getParameters().size() == sobrecarga.get(i).getTypes().length) {
                            shouldAddFunc = false;
                            logError.add("Erro em (" + f.getLine() + ", " + f.getColumn()
                                    + "). Sobrecarga inválida para função " + f.getId()
                                    + "; já declarada uma função de mesmo ID e mesmo número de parâmetros.");
                        }
                    }

                }
            }

            // se for func valida, continua normal, para incrementar o env e sobrecarga
            if (shouldAddFunc) {
                SType[] xs = new SType[0];
                SType[] xt = new SType[0];

                // instancia o vetor com tamanho do num de params, se nao for sem params
                if (f.getParameters() != null) {
                    xs = new SType[f.getParameters().getType().size()];
                }

                // instancia o vetor com tamanho do num de retornos, se nao for sem retornos
                if (f.getReturnTypes() != null) {
                    xt = new SType[f.getReturnTypes().size()];
                }

                // aceita os tipos de param e incrementa no xs pra ser salvo dps no env e
                // sobrecarga. Aproveita pra conferir se os parametros tem id diferente
                ArrayList<String> idPar = new ArrayList<String>();
                for (int i = 0; i < f.getParameters().size(); i++) {

                    // procura se o id do parametro ja existe
                    for (int j = 0; j < idPar.size(); j++) {
                        if (f.getParameters().getSingleId(i).equals(idPar.get(j))) {
                            logError.add("Erro em (" + f.getLine() + ", " + f.getColumn() + "). Função " + f.getId()
                                    + " possui parâmetros com mesmo id.");
                        }
                    }

                    idPar.add(f.getParameters().getSingleId(i));

                    f.getParameters().getSingleType(i).accept(this);
                    xs[i] = stk.pop();
                }

                // aceita os tipos de retorno e incrementa no xs pra ser salvo dps no env e
                // sobrecarga
                for (int i = 0; i < f.getReturnTypes().size(); i++) {
                    f.getReturnTypes().get(i).accept(this);
                    xt[i] = stk.pop();
                }

                // adiciona no ambiente
                env.set(f.getId(), new LocalEnv<SType>(f.getId(), new STyFun(xs, xt)));

                // adiciona no arraylist de tdas as funcoes, para verificar sobrecarga
                // posteriormente numa chamada de funçao
                sobrecarga.add(new STyFun(f.getId(), xs, xt));
            }

        }*/
        // Cria as funções no env
        for(Function f : p.getFunctions()){
            SType[] parameterType = new SType[0];
            SType[] returnType = new SType[0];
            String[] namesParameter = new String[0];

            // instancia o vetor com tamanho do num de params, se nao for sem params
            if (f.getParameters() != null) {
                parameterType = new SType[f.getParameters().getType().size()];
                namesParameter = new String[f.getParameters().getType().size()];
                for (int i = 0; i < f.getParameters().size(); i++) {
                    f.getParameters().getSingleType(i).accept(this);
                    parameterType[i] = stk.pop();
                    namesParameter[i] = f.getParameters().getSingleId(i);
                }
            }

            // instancia o vetor com tamanho do num de retornos, se nao for sem retornos
            if (f.getReturnTypes() != null) {
                returnType = new SType[f.getReturnTypes().size()];
                for (int i = 0; i < f.getReturnTypes().size(); i++) {
                    f.getReturnTypes().get(i).accept(this);
                    returnType[i] = stk.pop();
                }
            }
            // adiciona no ambiente
            env.set(f.getId(), new LocalAmbiente<SType>(f.getId(), new STyFun(parameterType, returnType, namesParameter)));
        }

        // Checa as funções
        for (Function f : p.getFunctions()) {
            // adiciona no ambiente
            f.accept(this);
        }
    }

    // Partem do data
    @Override
    public void visit(Data d) {
        if (datas.get(d.getId()) != null) { // Tentativa de adicionar dois datas com mesmo nome
            logError.add("(" + getLineNumber()+ ") Erro em (linha: " + d.getLine() + ", coluna: " + d.getColumn() + "): O tipo data \'" + d.getId() + "\' ja existe.");
            stk.push(tyErr);
        } else {
            ArrayList<String> variaveis = new ArrayList<String>();
            ArrayList<SType> tipos = new ArrayList<SType>();
            for (Declaration declaration : d.getDeclarations()) {

                // verificando campos com mesmo nome
                for (int i = 0; i < variaveis.size(); i++) {
                    if (variaveis.get(i).equals(declaration.getId())) {
                        logError.add("(" + getLineNumber()+ ") Erro em (linha: " + d.getLine() + ", coluna: " + d.getColumn() + "): O campo \'" + declaration.getId()
                            + "\' do tipo de data \'" + d.getId() + "\' ja foi definido.");
                        stk.push(tyErr);
                    }
                }
                variaveis.add(declaration.getId());
                declaration.getType().accept(this); // Coloca o tipo na pilha
                SType tipoVariavel = stk.pop();
                tipos.add(tipoVariavel);

            }

            // Adiciona o tipo data no hashmap
            datas.put((String) d.getId(), new DataAttributes(d.getId(), variaveis, tipos));
        }
    }

    // Partem do decl
    @Override
    public void visit(Declaration d) {
        // Nao faz nada, é tratado em outra funcao
    }

    // Partem do func
    @Override
    public void visit(Function f) {
        retChk = false;
        temp = env.get(f.getId()); // Pega o ambiente da função
        if (f.getParameters() != null) {
            Parameters params = f.getParameters();

            // Adiciona as variaveis do parametro no escopo local
            for (int i = 0; i < params.size(); i++) {
                params.getSingleType(i).accept(this);
                temp.set(params.getSingleId(i), stk.pop());
            }
        }

        for (Command command : f.getCommands()) {
            command.accept(this); // Executa os comandos que compoem o corpo da função
            if (retChk) // Se encontrou um return, para de expandir outros comandos
                break;
        }

        if (!retChk && f.getReturnTypes().size() > 0) {
            logError.add("(" + getLineNumber()+ ") Erro em (linha: " + f.getLine() + ", coluna: " + f.getColumn() + "): Função " + f.getId() + " deve retornar algum valor.");
        }
    }

    // Partem do params
    @Override
    public void visit(Parameters p) {
        // Nao faz nada pois já foi tratado em outra funcao
    }

    // Partem do Type
    @Override
    public void visit(TypeArray t) {
        t.getType().accept(this); // Empilha o tipo do array
        SType tipo = stk.pop();
        STyArr array = new STyArr(tipo);
        stk.push(array);
    }

    // Partem do btype
    @Override
    public void visit(TypeInt t) {
        stk.push(tyInt);
    }

    @Override
    public void visit(TypeChar t) {
        stk.push(tyChar);
    }

    @Override
    public void visit(TypeBool t) {
        stk.push(tyBool);
    }

    @Override
    public void visit(TypeFloat t) {
        stk.push(tyFloat);
    }

    @Override
    public void visit(Type t) {
        // Nao faz nada pois já foi tratado em outra funcao
    }

    @Override
    public void visit(NameType i) { // TypeData
        // Nao faz nada pois já foi tratado em outra funcao
    }

    // Partem do cmd
    @Override
    public void visit(Command c) {
        c.accept(this); // Executa o comando
    }

    @Override
    public void visit(CommandsList c) {
        for (Command command : c.getCommands()) { // Executa os comandos
            command.accept(this);
        }
    }

    @Override
    public void visit(If i) {
        i.getExp().accept(this); // Empilha a expressao de verificacao do If
        SType expressao = stk.pop();
        if (expressao.match(tyBool)) {
            retChk = false; // a variavel de retorno de função é falsa até encontrar um commando return
            i.getCmd().accept(this); // Verifica se o corpo de comandos do if é aceito
        } else {
            logError.add("(" + getLineNumber()+ ") Erro em (linha: " + i.getLine() + ", coluna: " + i.getColumn()
                + "): Expressão de teste do IF deve ser tipo Bool ou Int e nao \'" + expressao.toString() + "\' ");
            stk.push(tyErr);
        }
    }

    @Override
    public void visit(IfElse i) {
        boolean begin;
        boolean end = true;// end = true;

        i.getExp().accept(this); // Empilha a expressao de verificacao do If e Else
        SType expressao = stk.pop();
        if (expressao.match(tyBool)) {
            retChk = false; // a variavel de retorno de função é falsa até encontrar um commando return
            i.getCmd().accept(this); // Verifica o corpo do If
            begin = retChk; // Pega o resultado do corpo do if em relação a comandos de return

            if (i.getElseCmd() != null) {
                retChk = false;
                i.getElseCmd().accept(this); // Executa a verificação de tipos nos comandos do else
                end = retChk; // Pega o resultado do corpo do else em relação a comandos de return
            }
            // Garante que os dois tenham retorno
            retChk = begin && end;
        } else {
            logError.add("(" + getLineNumber()+ ") Erro em (linha: " + i.getLine() + ", coluna: " + i.getColumn()
                + "): Expressão de teste do IF deve ser tipo Bool ou Int e nao \'" + expressao.toString() + "\' ");
            stk.push(tyErr);
        }
    }

    @Override
    public void visit(Iterate i) {
        i.getExp().accept(this); // Empilha o valor lógico da expressão
        SType expressao = stk.pop();
        if (expressao.match(tyBool)) {
            i.getCmd().accept(this);
        } else if (expressao.match(tyInt)) {
            i.getCmd().accept(this);
        } else {
            logError.add("(" + getLineNumber()+ ") Erro em (linha: " + i.getLine() + ", coluna: " + i.getColumn()
                + "): Expressão de teste do Iterate so aceita tipo Bool ou Int e nao \'" + expressao.toString()
                + "\' ");
            stk.push(tyErr);
        }

    }

    @Override
    public void visit(Read r) {
        r.getLValue().accept(this);
        /*
         * if(){
         * 
         * } else{ logError.add(r.getLine() + ", " + r.getColumn() +
         * ": O comando read so grava informacoes em variaveis do tipo Int, Float ou Char"
         * ); stk.push(tyErr); }
         */

    }

    @Override
    public void visit(Print i) {
        i.getExpression().accept(this);
        if (stk.size() != 0) { // Verifica se a pilha está vazia
            stk.pop();
        }
    }

    @Override
    public void visit(Return r) {
        for (Expression exp : r.getExps()) {    // Processa a expressões de retorno da função
            exp.accept(this);   // Aceita a expressão e empilha no stk
        }
        if (temp.getFuncType() instanceof STyFun) {
            SType[] tipos = ((STyFun) temp.getFuncType()).getTypes();
            tipos[tipos.length - 1].match(stk.pop());
        } else {
            stk.pop().match(temp.getFuncType());
        }
        retChk = true;
    }

    @Override
    public void visit(Attribution a) {
        // a = 2 + b + ponto.x + array[1];
        System.out.println(getLineNumber() + " ---- " + a + " ---- " + a.getLValue() + " ---- " + a.getLValue().getClass());

        // Variavel que vai ter os dados atribuidos nela
        LValue lvalue = a.getLValue();

        if (lvalue instanceof Identifier) {
            // Empilha o tipo da expressao que sera atribuida
            a.getExp().accept(this);
            SType tipoExpressao = stk.pop();

            if (tipoExpressao instanceof STyData) {
                if ((temp.get(lvalue.getId()) == null)) { // Variavel de data nao existe
                    String name = ((TypeInstanciate) a.getExp()).getDataName();
                    STyData newData = new STyData(name);

                    if (datas.get(name) == null) {
                        logError.add("(" + getLineNumber()+ ") Erro em (linha: " + a.getLine() + ", coluna: " + a.getColumn() + "): O tipo de Data "
                            + name + " ainda nao foi declarado.");
                    }

                    else {
                        temp.set(lvalue.getId(), newData); // empilha a nova variavel de data
                    }
                } else {
                    SType current = temp.get(lvalue.getId());
                    // a.getExp().accept(this);

                    SType tpilha = stk.pop();

                    if (!tpilha.match(current)) {
                        logError.add("(" + getLineNumber()+ ") Erro em (linha: " + a.getLine() + ", coluna: " + a.getColumn()
                            + "): Problema na atribuicao de variável. Os tipos nao casam: " + tpilha + " <-> "
                            + "Data");
                        stk.push(tyErr);
                    }
                }
            } else { // Nao é tipo data

                // ver a parte de indices - por enquanto so ve se n foi declarada ainda
                // se a var n foi declarada, atribui o novo tipo pra ela
                if ((temp.get(lvalue.getId()) == null)) {
                    //a.getExp().accept(this);
                    // SType st = stk.pop();
                    temp.set(lvalue.getId(), tipoExpressao);
                } else { // se ja foi declarada, verifica se o tipo casa com o tipo dela
                    SType current = temp.get(lvalue.getId());
                    SType tpilha = tipoExpressao;

                    if (!tpilha.match(current)) {
                        logError.add("(" + getLineNumber()+ ") Erro em (linha: " + a.getLine() + ", coluna: " + a.getColumn()
                            + "): Problema na atribuicao de variavel. Os tipos nao casam: " + tpilha + " <-> "
                            + current);
                        stk.push(tyErr);
                    }

                }
            }

        } else if (lvalue instanceof ArrayAccess) {
            // aceita a expresso e joga pro topo da pilha. vai verificar posteriormente
            // dentro do ArrayAccess se casa

            lvalue.accept(this);

            // ver a parte de indices - por enquanto so ve se n foi declarada ainda
            // se a var n foi declarada, atribui o novo tipo, equivalente à expressao
            if ((temp.get(lvalue.getId()) == null)) {

                a.getExp().accept(this);       

                SType st = stk.pop();
                STyArr arr = new STyArr(st);

                // adiciona o array no contexto, com o tipo dado pela expressão
                temp.set(lvalue.getId(), arr);
            }
            // caso ja exista o array, verifica se o tipo casa com o esperado da atribuiçao
            else {

                a.getExp().accept(this);

                // se nao for variavel, confere o valor
                if (!(a.getExp() instanceof Identifier)) {
                    STyArr arr = (STyArr) temp.get(lvalue.getId());
                    SType pilha = stk.pop();
                    if(arr.getArg() instanceof STyFloat && pilha instanceof STyInt){
                        // Array de float aceita o tipo inteiro mas desde que seja convertido
                            
                    }
                    else{
                        if (!pilha.match(arr.getArg())) {   
                            logError.add("(" + getLineNumber()+ ") Erro em (linha: " + a.getLine() + ", coluna: " + a.getColumn()
                                + "): Problema na atribuicao de variavel. Os tipos nao casam: " + pilha + " <-> "
                                + arr.getArg());
                            stk.push(tyErr);
                        }
                    }
                }

            }

        } else if (lvalue instanceof DataAccess) {
            // aceita a expresso e joga pro topo da pilha. vai verificar posteriormente
            // dentro do dataAccess se casa
            System.out.println(getLineNumber() + " ---- " + stk);
            a.getExp().accept(this);    // Empilha o tipo da expressao que será atribuida

            lvalue.accept(this);    // Empilha o Tipo do atributo do data ou o tipo data mesmo

            SType tipoVariavel = stk.pop();
            SType tipoExpressao = stk.pop();
            DataAccess d = (DataAccess)lvalue;
            System.out.println(getLineNumber() + " ---- " + stk);
            if(!tipoExpressao.match(tipoVariavel)){  // Compara o tipo da expressao com o do atributo
                logError.add("(" + getLineNumber()+ ") Erro em (linha: " + d.getLine() + ", coluna: " + d.getColumn()
                    + "): Tipos incompativeis. O tipo do atributo \'" + d.getId()
                    + "\' do array de data \'" + d.getDataId() + "\' eh \'"+ tipoVariavel+ "\' e nao \'"
                    + tipoExpressao +"\' !!!"
                );
                stk.push(tyErr);
            }


        }
    }

    @Override
    public void visit(FunctionCall f) {
        // Trata chamadas de função do tipo: fat(10)<q>
        /**
         * ---- Regra cmd: ID OPEN_PARENT exps? CLOSE_PARENT (LESS_THAN lvalue (COMMA
         * lvalue)* GREATER_THAN)? SEMI # FunctionCall
         * 
         * Exemplo: divmod(5, 2)<q, r>; // Será retornada 2 valores e armazenados na
         * variavel q e r
         */

        // Pega a função correspondente
        LocalAmbiente<SType> function = env.get(f.getId());

        // Garante a existencia da função
        if (function != null) {

            // Passa do operand para o params
            // monta o parametro da função
            if (f.getFCallParams() != null) {

                STyFun tipoFuncao = (STyFun) function.getFuncType();

                int tempID = 0;

                // Verifica os tipos dos parametros passado
                for (Expression exp : f.getFCallParams().getExps()) {
                    // Empilha a expressao do parametro
                    exp.accept(this);
                    SType tipoParametro = tipoFuncao.getTypes()[tempID];    // Tipo do parametro do campo da função
                    SType parametroPassado = stk.pop(); // parametro passado na chamada da funcao

                    // Se o parametro passado não casar com o da função, emite um erro
                    if (!tipoParametro.match(parametroPassado)) {
                        logError.add("(" + getLineNumber()+ ") Erro em (linha: " + f.getLine() + ", coluna: " + f.getColumn()
                            + "): Argumentos com tipos incompatíveis com o da função \'" + f.getId()+"\'");
                        stk.push(tyErr);
                    }

                    tempID++;
                }
                // Empilha o ultimo tipo da função
                stk.push(tipoFuncao.getTypes()[tipoFuncao.getTypes().length - 1]);
            }

            // Retorno da função para as duas variaveis determinadas
            /*if (f.getLValues() != null) {
                List<LValue> ret = f.getLValues();
                int it = ret.size() - 1;

                // Inverte a ordem quando empilha os operadores, logo, deve ser
                // Desempilhado do direita pra esquerda
                for (LValue l : ret) {
                    env.peek().put(ret.get(it).getId(), operands.pop());
                    it--;
                }
            }*/
        }
    }

    private void typeArithmeticBinOp(Node n, String opName) {
        SType tyr = stk.pop();
        SType tyl = stk.pop();
        if ((tyr.match(tyInt))) {
            if (tyl.match(tyInt) || tyl.match(tyFloat)) {
                stk.push(tyl);
            } else {
                logError.add("(" + getLineNumber()+ ") Erro em (linha: " + n.getLine() + ", coluna: " + n.getColumn() + "): Operador \'" + opName + "\' nao se aplica aos tipos "
                    + tyl.toString() + " e " + tyr.toString());
                stk.push(tyErr);
            }

        } else if (tyr.match(tyFloat)) {
            if (tyl.match(tyInt) || tyl.match(tyFloat)) {
                stk.push(tyr); // Independente do tipo tyl, empilho Float
            } else {
                logError.add("(" + getLineNumber()+ ") Erro em (linha: " + n.getLine() + ", coluna: " + n.getColumn() + "): Operador \'" + opName + "\' nao se aplica aos tipos "
                    + tyl.toString() + " e " + tyr.toString());
                stk.push(tyErr);
            }
        } else {
            logError.add("(" + getLineNumber()+ ") Erro em (linha: " + n.getLine() + ", coluna: " + n.getColumn() + "): Operador \'" + opName + "\' nao se aplica aos tipos "
                + tyl.toString() + " e " + tyr.toString());
            stk.push(tyErr);
        }
    }

    // Partem do exp
    @Override
    public void visit(And a) {
        a.getLeft().accept(this);
        a.getRight().accept(this);
        SType tyr = stk.pop();
        SType tyl = stk.pop();
        if (tyr.match(tyBool) && tyl.match(tyBool)) {
            stk.push(tyBool);
        } else {
            logError.add("(" + getLineNumber()+ ") Erro em (linha: " + a.getLine() + ", coluna: " + a.getColumn() + "): Operador \'&&\' nao se aplica aos tipos " + tyl.toString()
                + " e " + tyr.toString());
            stk.push(tyErr);
        }
    }

    // Partem do rexp
    @Override
    public void visit(LessThan l) {
        l.getLeft().accept(this);
        l.getRight().accept(this);
        SType tyr = stk.pop();
        SType tyl = stk.pop();
        if ((tyr.match(tyInt) || tyr.match(tyFloat)) && (tyl.match(tyInt) || tyr.match(tyFloat))) {
            stk.push(tyBool);
        } else {
            logError.add("(" + getLineNumber()+ ") Erro em (linha: " + l.getLine() + ", coluna: " + l.getColumn() + "): Operador \'<\' nao se aplica aos tipos " + tyl.toString()
                + " e " + tyr.toString());
            stk.push(tyErr);
        }
    }

    @Override
    public void visit(Equality e) {
        e.getLeft().accept(this);
        e.getRight().accept(this);
        SType tyr = stk.pop();
        SType tyl = stk.pop();
        if ((tyr.match(tyInt) || tyr.match(tyFloat)) && (tyl.match(tyInt) || tyr.match(tyFloat))) {
            stk.push(tyBool);
        } else if (tyl.match(tyChar) && tyr.match(tyChar)) {
            stk.push(tyBool);
        } else {
            logError.add("(" + getLineNumber()+ ") Erro em (linha: " + e.getLine() + ", coluna: " + e.getColumn() + "): Operador \'==\' nao se aplica aos tipos " + tyl.toString()
                + " e " + tyr.toString());
            stk.push(tyErr);
        }
    }

    @Override
    public void visit(Difference n) {
        n.getLeft().accept(this);
        n.getRight().accept(this);
        SType tyr = stk.pop();
        SType tyl = stk.pop();
        if ((tyr.match(tyInt) || tyr.match(tyFloat)) && (tyl.match(tyInt) || tyr.match(tyFloat))) {
            stk.push(tyBool);
        } else if (tyl.match(tyChar) && tyr.match(tyChar)) {
            stk.push(tyBool);
        } else {
            logError.add("(" + getLineNumber()+ ") Erro em (linha: " + n.getLine() + ", coluna: " + n.getColumn() + "): Operador \'!=\' nao se aplica aos tipos " + tyl.toString()
                + " e " + tyr.toString());
            stk.push(tyErr);
        }
    }

    // Partem do aexp
    @Override
    public void visit(Addition a) {
        a.getLeft().accept(this);
        a.getRight().accept(this);
        typeArithmeticBinOp(a, "+");
    }

    @Override
    public void visit(Subtraction s) {
        s.getLeft().accept(this);
        s.getRight().accept(this);
        typeArithmeticBinOp(s, "-");
    }

    // Partem do mexp
    @Override
    public void visit(Multiplication m) {
        m.getLeft().accept(this);
        m.getRight().accept(this);
        typeArithmeticBinOp(m, "*");
    }

    @Override
    public void visit(Division d) {
        d.getLeft().accept(this);
        d.getRight().accept(this);
        typeArithmeticBinOp(d, "/");
    }

    @Override
    public void visit(Modular m) {
        m.getLeft().accept(this);
        m.getRight().accept(this);
        SType tyr = stk.pop();
        SType tyl = stk.pop();
        if (tyr.match(tyInt) && tyl.match(tyInt)) {
            stk.push(tyInt);
        } else {
            logError.add("(" + getLineNumber()+ ") Erro em (linha: " + m.getLine() + ", coluna: " + m.getColumn() + "): Operador \'%\' nao se aplica aos tipos " + tyl.toString()
                + " e " + tyr.toString());
            stk.push(tyErr);
        }
    }

    // Partem do sexp
    @Override
    public void visit(Not n) {
        n.getExpression().accept(this);
        SType tyr = stk.pop();
        if (tyr.match(tyBool)) {
            stk.push(tyBool);
        } else {
            logError.add("(" + getLineNumber()+ ") Erro em (linha: " + n.getLine() + ", coluna: " + n.getColumn() + "): Operador \'!\' nao se aplica ao tipo " + tyr.toString());
            stk.push(tyErr);
        }

    }

    @Override
    public void visit(Minus n) {
        n.getExpression().accept(this);
        SType tyr = stk.pop();
        if (tyr.match(tyInt)) {
            stk.push(tyInt);
        } else if (tyr.match(tyFloat)) {
            stk.push(tyFloat);
        } else {
            logError.add("(" + getLineNumber()+ ") Erro em (linha: " + n.getLine() + ", coluna: " + n.getColumn() + "): Operador \'-\' nao se aplica ao tipo " + tyr.toString());
            stk.push(tyErr);
        }
    }

    // True e False
    @Override
    public void visit(BooleanValue b) {
        stk.push(tyBool);
    }

    @Override
    public void visit(Null n) {
        stk.push(tyNull);
    }

    @Override
    public void visit(IntegerNumber i) {
        stk.push(tyInt);
    }

    @Override
    public void visit(FloatNumber p) {
        stk.push(tyFloat);
    }

    @Override
    public void visit(CharLitteral c) {
        stk.push(tyChar);
    }

    // Partem do pexp
    @Override
    public void visit(PexpIdentifier i) {
        // Nao faz nada
    }

    @Override
    public void visit(ExpParenthesis e) {
        // Nao faz nada
    }

    @Override
    public void visit(TypeInstanciate t) {
        // a = new Int, a = new Ponto, a = new Ponto[8];

        // Garante que não é um tipo Data
        if (t.getType() != null) {
            if (t.getExp() != null) { // Array comum
                // Empilha o tipo do array
                t.getType().accept(this);

                // Empilha o tamanho do array
                t.getExp().accept(this);
                SType tamanhoArray = stk.pop();
                if (!tamanhoArray.match(tyInt)) { // Verifica tamanho int para o array
                    logError.add("(" + getLineNumber()+ ") Erro em (linha: " + t.getLine() + ", coluna: " + t.getColumn()
                        + "): o tamanho de um array so pode ser atribuido com o tipo int e nao \'" + tamanhoArray
                        + "\' .");
                    stk.push(tyErr);
                }
                SType tipoArray = stk.pop();
                // Cria o tipo de array com referencia ao tipo primitivo informado
                STyArr array = new STyArr(tipoArray);
                stk.add(array);
            } else { // new Int;
                // Empilha o tipo da variavel e no attribution certifica se é valido
                t.getType().accept(this);
            }
        } else {
            if (t.getExp() == null) { // Tipo normal de data
                STyData tyData = new STyData(t.getDataName());
                // Empilha o tipo da variavel e no attribution certifica se é valido
                stk.add(tyData);
            } else { // Array de data
                // Empilha o tamanho do array
                t.getExp().accept(this);

                SType tamanhoArray = stk.pop();
                if (!tamanhoArray.match(tyInt)) { // Verifica tamanho int para o array
                    logError.add("(" + getLineNumber()+ ") Erro em (linha: " + t.getLine() + ", coluna: " + t.getColumn()
                        + "): o tamanho de um array so pode ser atribuido com o tipo int e nao \'" + tamanhoArray + "\' .");
                    stk.push(tyErr);
                }
                // SType tipoArray = stk.pop();
                STyData tyData = new STyData(t.getDataName());
                // Cria o tipo de array com referencia ao tipo primitivo informado
                STyArr array = new STyArr(tyData);
                stk.add(array);
            }
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

        System.out.println(getLineNumber() + " ---- " + f + " --- " + stk);

        // Pega a função correspondente
        LocalAmbiente<SType> function = env.get(f.getId());

        // Garante a existencia da função
        if (function != null) {

            // Passa do operand para o params
            // monta o parametro da função
            if (f.getFCallParams() != null) {

                STyFun tipoFuncao = (STyFun) function.getFuncType();

                int tempID = 0;

                System.out.println(getLineNumber() + " --- " + f.getFCallParams().getExps());

                // Verifica se a quantidade de parametros informados é o mesmo da função
                if(f.getFCallParams().getExps().size() == tipoFuncao.getTypes().length){
                    // Verifica os tipos dos parametros passado
                    for (Expression exp : f.getFCallParams().getExps()) {
                        // Empilha a expressao do parametro
                        exp.accept(this);
                        SType tipoParametro = tipoFuncao.getTypes()[tempID];    // Tipo do parametro do campo da função
                        SType parametroPassado = stk.pop(); // parametro passado na chamada da funcao

                        // Se o parametro passado não casar com o da função, emite um erro
                        if (!tipoParametro.match(parametroPassado)) {
                            logError.add("(" + getLineNumber()+ ") Erro em (linha: " + f.getLine() + ", coluna: " + f.getColumn()
                                + "): Argumentos com tipos incompativeis com os parametros da funcao \'" + f.getId()+"\'"
                                + " => O parametro \'" + tipoFuncao.getTypesName()[tempID] + "\' deve apresentar o tipo \'"
                                + tipoParametro + "\' e nao \'" + parametroPassado + "\' !!!"
                            );
                            stk.push(tyErr);
                        }

                        tempID++;
                    }
                    // Empilha o ultimo tipo da função
                    stk.push(tipoFuncao.getTypes()[tipoFuncao.getTypes().length - 1]);
                }
                else{
                    if(f.getFCallParams().getExps().size() > tipoFuncao.getTypes().length){
                        logError.add("(" + getLineNumber()+ ") Erro em (linha: " + f.getLine() + ", coluna: " + f.getColumn()
                        + "): Foi passado mais argumentos que a quantidade de parametros que a funcao \'" + f.getId()+"\'"
                        + " apresenta !!!");
                        stk.push(tyErr);
                    }
                    else{
                        logError.add("(" + getLineNumber()+ ") Erro em (linha: " + f.getLine() + ", coluna: " + f.getColumn()
                        + "): Foi passado menos argumentos que a quantidade de parametros que a funcao \'" + f.getId()+"\'"
                        + " apresenta !!!");
                        stk.push(tyErr);
                    }
                }
            }
        }

        // aceita a lista de expressoes, para futura verificação de tipos
        //f.getFCallParams().accept(this);    // Empilha os tipos dos parametros

        // verifica se o valor passado de posicao do array é inteiro
        f.getExpIndex().accept(this);

        System.out.println(getLineNumber() + " ---- " + f + " --- " + stk);
        SType tipoPosicaoRetorno = stk.pop();
        if (!tipoPosicaoRetorno.match(tyInt)) {
            logError.add("(" + getLineNumber()+ ") Erro em (linha: " + f.getLine() + ", coluna:" + f.getColumn()
                + "): O retorno da funcao so pode ser acessado com uma posicao em valor Int e nao \'" 
                + tipoPosicaoRetorno + "\' !");
            stk.push(tyErr);
        }

    }

    // Partem do lvalue
    @Override
    public void visit(LValue l) {
        /* Nao faz nada */
    }

    @Override
    public void visit(ID i) {
        /* Nao faz nada */
    }

    @Override
    public void visit(Identifier i) {
        if (temp.get(i.getId()) == null) {
            logError.add("(" + getLineNumber()+ ") Erro em (linha: "+i.getLine() + ", coluna: " + i.getColumn() + "): A variavel \'" + i.getId() + "\' nao existe!!!");
            stk.push(tyErr);
        } else {
            stk.push(temp.get(i.getId()));
        }
    }

    @Override
    public void visit(DataAccess d) {
        System.out.println(getLineNumber() + " ---- " + d + " ---- " + d.getDataId() + " ---- " 
        + d.getLValue() + " ---- " + d.getLValue().getId() + " ----- " + d.getId());

        // Certifica a existencia do tipo data passado no escopo atual
        if (temp.get(d.getDataId()) instanceof STyData) {

            STyData dataType = (STyData) temp.get(d.getDataId());

            if (datas.get(dataType.getName()) == null) {
                logError.add("(" + getLineNumber()+ ") Erro em (linha: "+d.getLine() + ", coluna: " + d.getColumn() 
                + "): Acesso à um tipo de data inexistente: " + dataType.getName());
            }

        } // verificando existencia do Data na base de dados
        else if (datas.get(d.getDataId()) != null) {    // Tipo data normal

            // verificando se o campo acessado existe
            ArrayList<String> variaveis = datas.get(d.getDataId()).getVariaveis();
            ArrayList<SType> dataTypes = datas.get(d.getDataId()).getTipos();

            boolean varEncontrada = false; // marca se encontrou

            // Verifica se a variavel empilha está presente no Data e ainda verifica o tipo
            // dela
            for (int i = 0; i < variaveis.size(); i++) {

                // Compara o nome do atributo com as variaveis(atributo) que estão dentro do
                // data
                if (variaveis.get(i).equals(d.getId())) {

                    // O tipo da expressao está no topo da pilha
                    // Agora compara-se o tipo do atributo(variavel)
                    if (!stk.pop().match(dataTypes.get(i))) {
                        logError.add("(" + getLineNumber()+ ") Erro em (linha: " + d.getLine() + ", coluna: " + d.getColumn()
                            + "): Erro de tipo no acesso a um data. Verifique o tipo do atributo \'" + d.getId()
                            + "\' do data \'" + d.getDataId() + "\' !!");
                        stk.push(tyErr);
                    }
                    varEncontrada = true;
                    break;
                }
            }

            // Variavel inexistente no objeto, logo é um erro
            if (!varEncontrada) {
                logError.add("(" + getLineNumber()+ ") Erro em (linha: " + d.getLine() + ", coluna: " + d.getColumn() + "): \'" + d.getId()
                    + "\' => Atributo inexistente no objeto \'" + d.getDataId() + "\'");
                stk.push(tyErr);
            }

        }
        else if (temp.get(d.getLValue().getId()) instanceof STyArr) {    // Verifica se é array de data

            System.out.println(getLineNumber() + " ---  "+ stk);
            // Se chegou no dataAccess significa que a expressao de atribuição já está na pilha
            // SType tipoExpressao = stk.pop();
            
            // STyData dataType = (STyData) temp.get(d.getDataId());
            STyArr arrayType = (STyArr) temp.get(d.getLValue().getId());
            STyData dataType = (STyData) arrayType.getArg();

            System.out.println(getLineNumber()+ " --- " + arrayType + " --- " + arrayType.getArg());
            

            // verificando se o campo acessado existe
            ArrayList<String> variaveis = datas.get(dataType.getName()).getVariaveis();
            ArrayList<SType> dataTypes = datas.get(dataType.getName()).getTipos();

            boolean varEncontrada = false; // marca se encontrou

            // Verifica se a variavel empilha está presente no Data e ainda verifica o tipo
            // dela
            for (int i = 0; i < variaveis.size(); i++) {

                // Compara o nome do atributo com as variaveis(atributo) que estão dentro do
                // data
                if (variaveis.get(i).equals(d.getId())) {
                    // SType tipoAtributo = stk.pop();

                    System.out.println(getLineNumber() + " --- " + dataTypes.get(i));
                    stk.push(dataTypes.get(i)); // Empilha o tipo do atributo
                    /*if(!dataTypes.get(i).match(tipoExpressao)){  // Compara o tipo da expressao com o do atributo
                        logError.add("(" + getLineNumber()+ ") Erro em (linha: " + d.getLine() + ", coluna: " + d.getColumn()
                            + "): Tipos incompativeis. O tipo do atributo \'" + d.getId()
                            + "\' do array de data \'" + d.getDataId() + "\' eh \'"+ dataTypes.get(i)+ "\' e nao \'"
                            + tipoExpressao +"\' !!!"
                        );
                        stk.push(tyErr);
                    }*/

                    // O tipo da expressao está no topo da pilha
                    // Agora compara-se o tipo do atributo(variavel)
                    /*if (!tipoAtributo.match(dataTypes.get(i))) {
                        logError.add("(" + getLineNumber()+ ") Erro em (linha: " + d.getLine() + ", coluna: " + d.getColumn()
                            + "): Erro de tipo no acesso a um data. Verifique o tipo do atributo \'" + d.getId()
                            + "\' do data \'" + d.getDataId() + "\' !!");
                        stk.push(tyErr);
                    }*/
                    varEncontrada = true;
                    break;
                }
            }

            // Variavel inexistente no objeto, logo é um erro
            if (!varEncontrada) {
                logError.add("(" + getLineNumber() + ") Erro em (linha: " + d.getLine() + ", coluna: " + d.getColumn() + "): \'" + d.getId()
                    + "\' => Atributo inexistente no objeto \'" + d.getDataId() + "\'");
                stk.push(tyErr);
            }

        }
        else {
            logError.add("(" + getLineNumber()+ ") Erro em (linha: " + d.getLine() + ", coluna: " + d.getColumn() + "): o tipo data \'" + d.getDataId() + "\' nao existe !!!");
            stk.push(tyErr);
        }
    }

    @Override
    public void visit(ArrayAccess a) {
        a.getExp().accept(this); // Verifica se a posicao foi passada
        SType tipo = stk.pop();
        if (!tipo.match(tyInt)) { // Verifica se o tipo da posicao do array é um valor inteiro
            logError.add("(" + getLineNumber()+ ") Erro em (linha: " + a.getLine() + ", coluna: " + a.getColumn() + "): Arrays so podem ter sua posicao acessada se o indice for um numero inteiro e nao \'" + tipo + "\' !!");
            stk.push(tyErr);
        }
    }

    // Partem do exps
    @Override
    public void visit(FCallParams f) {
        for (Expression exp : f.getExps()) {
            System.out.println(getLineNumber() + " --- " + exp + " --- " + exp.getClass().getSimpleName());
            exp.accept(this);
        }
    }
}
