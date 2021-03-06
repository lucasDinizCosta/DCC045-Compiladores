/********************************************************
* Trabalho de Teoria dos Compiladores(DCC045)(2021/1)   *
*            Linguagem Lang                             *
* Nome: Lucas Diniz da Costa -- Matricula: 201465524C   *
*                                                       *
*********************************************************/
package lang.semantic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import lang.interpreter.Visitor;
import lang.ast.*;
import lang.semantic.*;

// Define o visitor que fará a analise semantica 
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

    // Objeto que pega valor numerico, usado pra saber qual retorno de função para checar os tipos
    // tipo func(1, 2)[1];  // Qual será o tipo do indice 1
    private Object positionReturnFunction;

    // Armzanena as Funcoes para poder compara os tipos na sobrecarga
    private ArrayList<Function> funcs;


    public TypeCheckVisitor() {
        stk = new Stack<SType>();
        env = new TyEnv<LocalAmbiente<SType>>();
        logError = new ArrayList<String>();
        datas = new HashMap<String, DataAttributes>();
        funcs = new ArrayList<Function>();
    }

    // Retorna a quantidade de erros
    public int getNumErrors() {
        return logError.size();
    }

    public void printErrors() {
        System.out.println("-------------- ERROS DE TIPO -----------\n");
        int indice = 1;
        for (String s : logError) {
            System.out.println("=> " + (indice < 10 ? "0" + indice : indice) + ") - " + s);
            indice++;
        }
        System.out.println("\n--------------------------------------");
    }

    // https://www.techiedelight.com/get-current-line-number-java/
    // Retorna a linha do código fonte ao passar pela instrução
    public int getLineNumber(){
        // return new Throwable().getStackTrace()[0].getLineNumber();
        return Thread.currentThread().getStackTrace()[2].getLineNumber();
    }

    // Retorna a lista de funções da arvore AST com base no nome
    // Para comparar a sobrecarga no FunctionReturn e FunctionCall
    public ArrayList<Function> getFunctionAST(String nome){
        ArrayList<Function> funcoesAST = new ArrayList<Function>();
        for(Function f : funcs){
            if(f.getId().equals(nome)){
                funcoesAST.add(f);
            }
        }
        return funcoesAST;
    }

    public Boolean functionIsValid(Integer linha, Integer coluna, LocalAmbiente<SType> funcaoNova){
        // Verificaremos os parametros, os tipos dos parametros, os tipos dos retornos
        boolean isValid = true;
        ArrayList<LocalAmbiente<SType>> funcoes = null;
        funcoes = env.getFuncoes(funcaoNova.getFuncID());// Retorna a lista de funções
        if(funcoes == null){ // Logo nao existe a funcao na base ainda e portanto ela é valida
            return true;
        }
        else{   
        // Existe na base funcoes com o mesmo nome agora teremos que conferir 
        // os dados da funcao para ver se uma funcao igual existe

            for(int i = 0; i < funcoes.size(); i++){
                LocalAmbiente<SType> funcaoBase = funcoes.get(i);
                STyFun funcaoBaseTipo = (STyFun)funcaoBase.getFuncType();
                STyFun funcaoNovaTipo = (STyFun)funcaoNova.getFuncType();
                if(funcaoBaseTipo.getTypes().length == funcaoNovaTipo.getTypes().length){
                    boolean isDifferentType = false;
                    // testa o casamento de todos os tipos
                    for(int j = 0; j < funcaoBaseTipo.getTypes().length; j++){
                        // se for diferente
                        if(!(funcaoBaseTipo.getTypes()[j].match(funcaoNovaTipo.getTypes()[j]))){
                            isDifferentType = true;
                            break;
                        }
                    }
                    if(!isDifferentType){
                        logError.add("(" + getLineNumber()+ ") Erro em (linha: " + linha + ", coluna: " + coluna + "): Ambiguidade na funcao \'" + funcaoBase.getFuncID() + "\' pois a nova funcao tem a mesma quantidade de parametros e tipos iguais e o mesmo nome de uma funcao que ja existe !!!");
                        stk.push(tyErr);
                        isValid = false;
                    }
                }
            }
            return isValid;
        }
    }

    // Partem do prog
    @Override
    public void visit(Program p) {
        Node main = null;

        // Aceita os tipos data para fazer a verificação de tipo
        for (Data d : p.getDatas()) {
            d.accept(this);
        }

        boolean checaMain = false;

        // Cria as funções no env
        for(Function f : p.getFunctions()){
            if(f.getId().equals("main")){   // Checa se a funcao main já existe
                if(env.getFuncoes(f.getId()).size() > 0){ // Tenta sobrecarregar a funcao main
                    logError.add("(" + getLineNumber()+ ") Erro em (linha: " + f.getLine() + ", coluna: " + f.getColumn() + "): A funcao \'" + main + "\' deve ser UNICA com esse nome e nao deve ter sobrecarga de funcoes com seu nome !");
                    stk.push(tyErr);
                    checaMain = true;
                }   
            }

            if(checaMain){  // Se tentou sobrecarregar a funcao main ==> Continua e ignora a funcao de sobrecarga
                checaMain = false;
                continue;
            }
            
            
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
                    // Compara o nome das variaveis para verificar se tem nomes iguais
                    for(int j = 0; j <= i; j++){
                        // Compara todos e garante que nao compara o parametro com ele mesmo
                        if(namesParameter[j].equals(f.getParameters().getSingleId(i)) & i != j){
                            logError.add("(" + getLineNumber()+ ") Erro em (linha: " + f.getLine() + ", coluna: " + f.getColumn() + "): Os parametros \'" + f.getParameters().getSingleParameterToString(j) + "\' e \'"+f.getParameters().getSingleParameterToString(i) +"\' tem nomes iguais para as variaveis!");
                            stk.push(tyErr);
                            break;
                        }
                    }
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

            LocalAmbiente<SType> novaFuncao = new LocalAmbiente<SType>(f.getId(), new STyFun(parameterType, returnType, namesParameter, f.getId()));

            // Passa o ambiente da funcao para checar sobrecarga e verificar se existe funcoes iguais
            boolean funcaoValida = functionIsValid(f.getLine(), f.getColumn(), novaFuncao);

            if(funcaoValida){
                // adiciona no ambiente
                env.add(novaFuncao);

                // Adiciona na lista de funções
                funcs.add(f);
            }

        }

        // Checa as funções
        for (Function f : p.getFunctions()) {
            f.accept(this);
            if (f.getId().equals("main")) {     // Verifica se tem a função main
                
                // Certifica que a funcao main nao deve ter parametros
                if(((Parameters)f.getParameters()).getType().size() != 0){
                    logError.add("(" + getLineNumber()+ ") Erro em (linha: " + f.getLine() + ", coluna: " + f.getColumn() + "): A funcao \'main\' nao pode ter parametros na sua declaracao !");
                    stk.push(tyErr);
                }
                main = f;
            }
        }

        if (main == null) {
            logError.add("(" + getLineNumber()+ ") Erro em (linha: " + p.getLine() + ", coluna: " + p.getColumn() + "): Nao ha a funcao chamada \'main\' !");
            stk.push(tyErr);
        }
    }

    // Partem do data
    @Override
    public void visit(Data d) {
        if (datas.get(d.getId()) != null) { // Tentativa de adicionar dois datas com mesmo nome
            logError.add("(" + getLineNumber()+ ") Erro em (linha: " + d.getLine() + ", coluna: " + d.getColumn() + "): O tipo data \'" + d.getId() + "\' ja existe !");
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
        // Pega o ambiente da função
        ArrayList<LocalAmbiente> funcFinded = (ArrayList)env.getFuncoes(f.getId());
        temp = (LocalAmbiente<SType>)funcFinded.get(0);   // Só uma funcao
        if(funcFinded.size() > 1){  // Tem sobrecarga 
            for(int i = 0; i < funcFinded.size(); i++){
                LocalAmbiente<SType> funcaoBase = funcFinded.get(i);
                STyFun funcaoBaseTipo = (STyFun)funcaoBase.getFuncType();

                // Se a funcao tem o mesmo numero de parametros entao pode ser a correta
                if(funcaoBaseTipo.getTypes().length == f.getParameters().getType().size()){
                    int counterTypes = 0;   // Conta os tipos iguais para achar a funcao certa
                    for(int j = 0; j < funcaoBaseTipo.getTypes().length; j++){

                        // Compara pelo nome dos tipos pq se for tentar usar o 'equals' entre os tipos
                        // Da problema pois sao regioes diferentes de memoria
                        if(funcaoBaseTipo.getTypes()[j].toString().equals(
                            ((Type)f.getParameters().getSingleType(j)).toString()   // Compara com o tipo da funcao
                        )){
                            counterTypes++;
                        }
                    }
                    if(counterTypes == funcaoBaseTipo.getTypes().length ){
                        temp = (LocalAmbiente<SType>)funcFinded.get(i);
                        break;
                    }
                }
            }
        }

        if (f.getParameters() != null) {
            Parameters params = f.getParameters();

            // Adiciona as variaveis do parametro no escopo local
            for (int i = 0; i < params.size(); i++) {
                params.getSingleType(i).accept(this);
                temp.set(params.getSingleId(i), stk.pop());
            }
        }

        boolean verificacaoIf = false;
        for(int i = 0; i < f.getCommands().size(); i++){
            Command command = f.getCommands().get(i);
            command.accept(this);
            if(command instanceof If && i == f.getCommands().size() - 1){   // Ultimo comando é um if
                verificacaoIf = true;
                retChk = false;
            }
        }

        SType[] tiposRetornoPadrao = new SType[0];
        if (temp.getFuncType() instanceof STyFun) {
            // Padrao da documentação da função
            tiposRetornoPadrao = ((STyFun) temp.getFuncType()).getReturnTypes();
        }

        if (!retChk && tiposRetornoPadrao.length > 0) {
            if(verificacaoIf){
                logError.add("(" + getLineNumber()+ ") Erro em (linha: " + f.getLine() + ", coluna: " + f.getColumn() + "): Na funcao \'" + f.getId() + "\' falta um estado de retorno depois do ultimo comando \'if\' !");
                stk.push(tyErr);
            }
            else{
                logError.add("(" + getLineNumber()+ ") Erro em (linha: " + f.getLine() + ", coluna: " + f.getColumn() + "): Funcao " + f.getId() + " deve retornar algum valor.");
                stk.push(tyErr);
            }   
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
        if(t.getType() instanceof NameType && datas.get(((NameType)t.getType()).getID()) == null){ // Tipo data nao existe
            logError.add("(" + getLineNumber()+ ") Erro em (linha: " + t.getLine() + ", coluna: " 
            + t.getColumn() + "): O tipo data \'" + t.getType() 
            + "\' nao existe para poder ser criado um array.");
            stk.push(tyErr);
        }
        else{
            t.getType().accept(this); // Empilha o tipo do array
            SType tipo = stk.pop();
            if(tipo instanceof STyData){    // Array de data ==> Testar se o data existe
                if(datas.get(((STyData)tipo).getName()) != null){   // Verifica se o tipo Data existe
                    STyArr array = new STyArr(tipo);
                    stk.push(array);
                }
                else{
                    logError.add("(" + getLineNumber()+ ") Erro em (linha: " + t.getLine() + ", coluna: " 
                    + t.getColumn() + "): O tipo data \'" + ((STyData)tipo).getName() 
                    + "\' nao existe para poder se criar um array.");
                    stk.push(tyErr);
                }
            }
            else if(tipo instanceof STyErr){
                logError.add("(" + getLineNumber()+ ") Erro em (linha: " + t.getLine() + ", coluna: " 
                + t.getColumn() + "): O tipo data \'" + t.getType() 
                + "\' nao existe para poder se criar um array.");
                stk.push(tyErr);
            }
            else{
                STyArr array = new STyArr(tipo);
                stk.push(array);
            }
        }
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
        if(datas.get(i.getID()) != null){   // Se o tipo data existe
            STyData tipoData = new STyData(i.getID());
            stk.push(tipoData);
        }
        else{
            logError.add("(" + getLineNumber()+ ") Erro em (linha: " + i.getLine() + ", coluna: " + i.getColumn()
                + "): Tipo data passado como parametro na funcao nao existe =>\'" + i.getID() + "\' ");
            stk.push(tyErr);
        }
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
                + "): Expressao de teste do IF deve ser tipo Bool e nao \'" + expressao.toString() + "\' !");
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
                + "): Expressao de teste do IF deve ser tipo Bool e nao \'" + expressao.toString() + "\' !");
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
                + "\' !");
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
        int qtdExpRetorno = 0;
        for (Expression exp : r.getExps()) {    // Processa a expressões de retorno da função
            exp.accept(this);   // Aceita a expressão e empilha no stk
            qtdExpRetorno++;
        }
        if (temp.getFuncType() instanceof STyFun) {
            // Padrao da documentação da função
            SType[] tiposRetornoPadrao = ((STyFun) temp.getFuncType()).getReturnTypes();
            SType[] tiposRetornados = new SType[qtdExpRetorno];

            // Desempilha os tipos retornados
            for(int i = 0; i < qtdExpRetorno; i++){
                tiposRetornados[i] = stk.pop();
            }
            
            // Quantidades de retorno para diferente em relação a quantidade descrita na função
            if(qtdExpRetorno != tiposRetornoPadrao.length){
                logError.add("(" + getLineNumber()+ ") Erro em (linha: " + r.getLine() + ", coluna: " 
                + r.getColumn() + "): Foram especificados " + tiposRetornoPadrao.length 
                + " tipos de retorno na documentacao da funcao mas no comando \'return\' apresenta " 
                + qtdExpRetorno + " retorno(s) !!");
                stk.push(tyErr);
            }

            int contRetornos = 0;

            // O retorno deve ser desempilhado na ordem contraria
            for(int i = tiposRetornoPadrao.length - 1; i >= 0; i--){  // Verificação de tipo nos retornos

                if(contRetornos > qtdExpRetorno){   // Sai da função se a quantidade for diferente
                    break;
                }

                if(!tiposRetornados[contRetornos].match(tiposRetornoPadrao[i])){
                    logError.add("(" + getLineNumber()+ ") Erro em (linha: " + r.getLine() + ", coluna: " 
                    + r.getColumn() + "): O tipo retornado na funcao \'" + tiposRetornados[contRetornos] + "\'" + " eh diferente de \'"
                    + tiposRetornoPadrao[i] +"\' !!!");
                    stk.push(tyErr);
                }
                
                contRetornos++;
            }
        } else {
            stk.pop().match(temp.getFuncType());
        }
        retChk = true;
    }

    @Override
    public void visit(Attribution a) {
        // a = 2 + b + ponto.x + array[1];

        // Variavel que vai ter os dados atribuidos nela
        LValue lvalue = a.getLValue();

        if (lvalue instanceof Identifier) {
            // Empilha o tipo da expressao que sera atribuida
            a.getExp().accept(this);

            SType tipoExpressao = stk.pop();

            if (tipoExpressao instanceof STyData) {
                if ((temp.get(lvalue.getId()) == null)) { // Variavel de data nao existe
                    String name = ((STyData)tipoExpressao).getName();
                    STyData newData = new STyData(name);

                    if (datas.get(name) == null) {
                        logError.add("(" + getLineNumber()+ ") Erro em (linha: " + a.getLine() + ", coluna: " + a.getColumn() + "): O tipo de Data " + name + " ainda nao foi declarado.");
                    }
                    else {
                        temp.set(lvalue.getId(), newData); // empilha a nova variavel de data
                    }
                } else {
                    SType tipoVariavel = temp.get(lvalue.getId());

                    if (!tipoExpressao.match(tipoVariavel)) {
                        logError.add("(" + getLineNumber()+ ") Erro em (linha: " + a.getLine() + ", coluna: " + a.getColumn()
                        + "): Reatribuicao de variavel => Problema na atribuicao de variável. Os tipos nao casam: " + tipoExpressao + " <-> " + "Data");
                        stk.push(tyErr);
                    }
                }
            } else { // Nao é tipo data

                // ver a parte de indices - por enquanto so ve se n foi declarada ainda
                // se a var n foi declarada, atribui o novo tipo pra ela
                if ((temp.get(lvalue.getId()) == null)) {
                    temp.set(lvalue.getId(), tipoExpressao);
                } else { // se ja foi declarada, verifica se o tipo casa com o tipo dela
                    SType tipoVariavel = temp.get(lvalue.getId());

                    if (!tipoExpressao.match(tipoVariavel)) {
                        logError.add("(" + getLineNumber()+ ") Erro em (linha: " + a.getLine() + ", coluna: " + a.getColumn()
                        + "): Reatribuicao de variavel => Problema na atribuicao de variavel. Os tipos nao casam: " + tipoExpressao + " <-> "
                        + tipoVariavel);
                        stk.push(tyErr);
                    }

                }
            }

        } else if (lvalue instanceof ArrayAccess) {
            if(((ArrayAccess)lvalue).getLValue() != null && ((ArrayAccess)lvalue).getLValue() instanceof ArrayAccess){   // Trata o caso de matriz
                ArrayAccess matriz = (ArrayAccess)((ArrayAccess)lvalue).getLValue();
                
                lvalue.accept(this);    // Empilha o tipo da matriz e verifica os indices

                // ver a parte de indices - por enquanto so ve se n foi declarada ainda
                // se a var n foi declarada, atribui o novo tipo, equivalente à expressao
                if ((temp.get(matriz.getId()) == null)) {

                    a.getExp().accept(this);       

                    SType st = stk.pop();
                    STyArr arr = new STyArr(st);

                    // adiciona o array no contexto, com o tipo dado pela expressão
                    temp.set(matriz.getId(), arr);
                }
                // caso ja exista o array, verifica se o tipo casa com o esperado da atribuiçao
                else {
                    a.getExp().accept(this);    // Empilha o objeto da expressao => new int, new Ponto ou somente uma variavel

                    // se nao for variavel, confere o valor
                    if (!(a.getExp() instanceof Identifier)) {
                        SType tipoExpAtribuicao = stk.pop();
                        SType tipoMatriz = stk.pop();

                        // Compara o tipo o objeto a ser adiciona com o tipo do array
                        if (!tipoMatriz.match(tipoExpAtribuicao)) {   
                            logError.add("(" + getLineNumber()+ ") Erro em (linha: " + a.getLine() + ", coluna: " + a.getColumn()
                                + "): Problema na atribuicao de variavel. Os tipos nao casam: " + tipoExpAtribuicao + " <-> "
                                + tipoMatriz);
                            stk.push(tyErr);
                        }
                    }

                }
            }
            else{   // Array 
                // aceita a expressao e joga pro topo da pilha. vai verificar posteriormente
                // dentro do ArrayAccess se casa

                lvalue.accept(this);        // Empilha o tipo do array

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
                    a.getExp().accept(this);        // Empilha o tipo da expressao que será atribuida

                    SType tipoExpAtribuicao = stk.pop();
                    SType tipoArray = stk.pop();

                    if (!tipoArray.match(tipoExpAtribuicao)) {   
                        logError.add("(" + getLineNumber()+ ") Erro em (linha: " + a.getLine() + ", coluna: " + a.getColumn()
                            + "): Problema na atribuicao de variavel. Os tipos nao casam: " + tipoExpAtribuicao + " <-> "
                            + tipoArray);
                        stk.push(tyErr);
                    }
                }
            }
        } else if (lvalue instanceof DataAccess) {
            // aceita a expresso e joga pro topo da pilha. vai verificar posteriormente
            // dentro do dataAccess se casa

            if(((DataAccess)lvalue).getLValue() != null && ((DataAccess)lvalue).getLValue() instanceof ArrayAccess){    // Matriz de data

                a.getExp().accept(this);    // Empilha o tipo da expressao que será atribuida

                lvalue.accept(this);    // Empilha o tipo da matriz;
            }
            else{
                a.getExp().accept(this);    // Empilha o tipo da expressao que será atribuida

                lvalue.accept(this);    // Empilha o Tipo do atributo do data ou o tipo data mesmo
            }
            
            SType tipoVariavel = stk.pop();
            SType tipoExpressao = stk.pop();
            DataAccess d = (DataAccess)lvalue;
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
         * pode ser tbm
         * divmod(5,2); SEM RETORNO
         */
        // Informacoes da funcao que sera retomada no functionReturn
        Integer qtdParamPassados = 0;           // A funcao nao foi passado parametros
        if(f.getFCallParams() != null){
            qtdParamPassados = f.getFCallParams().getExps().size(); // A funcao foi passada parametros
        }
        String nomeFuncao = f.getId();
        

        // Pega o ambiente da função
        ArrayList<LocalAmbiente> funcFinded = (ArrayList)env.getFuncoes(nomeFuncao);

        // Pega a função correspondente
        LocalAmbiente<SType> function = (LocalAmbiente<SType>)funcFinded.get(0);   // Só uma funcao
        if(funcFinded.size() > 1){  // Tem sobrecarga 
            ArrayList<Function> funcoesAST = getFunctionAST(nomeFuncao);
            for(int i = 0; i < funcFinded.size(); i++){
                LocalAmbiente<SType> funcaoBase = funcFinded.get(i);

                STyFun funcaoBaseTipo = (STyFun)funcaoBase.getFuncType();

                // Se a funcao tem o mesmo numero de parametros
                if(funcaoBaseTipo.getTypes().length == qtdParamPassados){
                    int contTiposIguais = 0;
                    int indiceExp = 0;
                    // Empilha os tipos das expressões passadas como parametro na chamada da FunctionReturn
                    // Compara com os tipos da função Find e verifica se coincide
                    for (Expression exp : f.getFCallParams().getExps()) {
                        // Empilha a expressao do parametro
                        exp.accept(this);
                        SType tipoParametro = funcaoBaseTipo.getTypes()[indiceExp];    // Tipo do parametro do campo da função
                        SType parametroPassado = stk.pop(); // parametro passado na chamada da funcao
                        // Compara pelo nome pois se comparar só com o equals, são regioes de 
                        // memoria diferente, então nao funciona
                        if(tipoParametro.toString().equals(parametroPassado.toString())){
                            contTiposIguais++;
                        }
                        else{
                            break;
                        }
                        indiceExp++;
                    }
                    if(contTiposIguais == funcaoBaseTipo.getTypes().length){
                        function = (LocalAmbiente<SType>)funcFinded.get(i);
                        break;
                    }
                }

                // Nao compara retornos pois na descricao do trabalho foi solicitado se atentar 
                // Na sobrecarga apenas de parametros

                // Quantidade de retornos
                /*if(funcaoDeclaracao.getReturnTypes().size() == funcaoBaseTipo.getReturnTypes().length){
                    boolean verificaRetDif = false;
                    for(int j = 0; j < funcaoDeclaracao.getReturnTypes().size(); j++){
                        String tipo = funcaoDeclaracao.getReturnTypes().get(j).toString();
                        // Compara os nomes de tipos, se for diferente nao eh a funcao
                        if(!(tipo.equals(funcaoBaseTipo.getReturnTypes()[j].toString()))){
                            verificaRetDif = true;
                        }
                    }
                    if(verificaRetDif){
                        continue;
                    }

                    
                }*/
            }
        }

        // Garante a existencia da função
        if (function != null) {

            // Passa do operand para o params
            // monta o parametro da função
            if (f.getFCallParams() != null) {

                STyFun tipoFuncao = (STyFun) function.getFuncType();

                int indiceParamPassado = 0;

                // Verifica os tipos dos parametros passado
                for (Expression exp : f.getFCallParams().getExps()) {
                    // Empilha a expressao do parametro
                    exp.accept(this);
                    // Verifica se o tamanho dos parametros é o mesmo informado pelo usuario
                    if(indiceParamPassado < tipoFuncao.getTypes().length){ 
                        SType tipoParametro = tipoFuncao.getTypes()[indiceParamPassado];    // Tipo do parametro do campo da função
                        SType parametroPassado = stk.pop(); // parametro passado na chamada da funcao

                        // Se o parametro passado não casar com o da função, emite um erro
                        if (!tipoParametro.match(parametroPassado)) {
                            logError.add("(" + getLineNumber()+ ") Erro em (linha: " + f.getLine() + ", coluna: " + f.getColumn()
                                + "): Argumentos com tipos incompativeis com o da funcao \'" + f.getId()+"\' => " + parametroPassado + " diferente de " + tipoParametro);
                            stk.push(tyErr);
                        } 
                        
                    }
                    indiceParamPassado++;
                }
                Integer qtdParametrosInformados = ((List)f.getFCallParams().getExps()).size();
                if( qtdParametrosInformados > tipoFuncao.getTypes().length || qtdParametrosInformados < tipoFuncao.getTypes().length){
                    logError.add("(" + getLineNumber()+ ") Erro em (linha: " + f.getLine() + ", coluna: " + f.getColumn()
                    + "): Na chamada da funcao \'" + f.getId()+"\' foram passados " + qtdParametrosInformados + " parametros enquanto que a funcao deveria receber " + tipoFuncao.getTypes().length + " parametro !");
                    stk.push(tyErr);
                }
            }

            // Retorno da função para as duas variaveis determinadas
            if (f.getLValues() != null) {
                // Garante que a função tem retorno e seja a mesma quantidade solicitada pelo usuario
                if(((STyFun)function.getFuncType()).getReturnTypes() != null &&
                f.getLValues().size() == ((STyFun)function.getFuncType()).getReturnTypes().length){
                    List<LValue> ret = f.getLValues();
                    int it = ret.size() - 1;

                    // Inverte a ordem quando empilha os operadores, logo, deve ser
                    // Desempilhado do direita pra esquerda
                    for (LValue l : ret) {
                        if(temp.get(ret.get(it).getId()) != null){  // Variavel existe, entao tem um tipo
                            if(temp.get(ret.get(it).getId()).match(((STyFun)function.getFuncType()).getReturnTypes()[it])){     // Verifica se o tipo bate com o retorno da função
                                temp.set(ret.get(it).getId(), ((STyFun)function.getFuncType()).getReturnTypes()[it]);
                            }
                            else{
                                logError.add("(" + getLineNumber()+ ") Erro em (linha: " + f.getLine() + ", coluna: " + f.getColumn() + "): A variavel \'" + ret.get(it).getId() + "\' ja existe e seu tipo eh \'" + temp.get(ret.get(it).getId()) + "\' e portanto, nao pode receber o valor do tipo \'" + ((STyFun)function.getFuncType()).getReturnTypes()[it] + "\' retornado pela funcao \'" + f.getId() + "\'");
                                stk.push(tyErr);
                            }
                        }
                        else{
                            temp.set(ret.get(it).getId(), ((STyFun)function.getFuncType()).getReturnTypes()[it]);
                        }
                        it--;
                    }

                    }
                else{   // A função não tem retorno ou é menor que o numero de retornos solicitados pelo usuario
                    logError.add("(" + getLineNumber()+ ") Erro em (linha: " + f.getLine() + ", coluna: " + f.getColumn()
                    + "): Na chamada da funcao foram solicitados \'" + f.getLValues().size()+"\' retorno(s) mas a funcao original apresenta " + ((STyFun)function.getFuncType()).getReturnTypes().length + " retorno(s) !");
                    stk.push(tyErr);
                }
            }
        }
    }

    private void typeArithmeticBinOp(Node n, String opName) {
        SType tyr = stk.pop();
        SType tyl = stk.pop();
        if ((tyr.match(tyInt))) {
            if (tyl.match(tyInt)){//|| tyl.match(tyFloat)) {
                stk.push(tyl);
            } else {
                logError.add("(" + getLineNumber()+ ") Erro em (linha: " + n.getLine() + ", coluna: " + n.getColumn() + "): Operador \'" + opName + "\' nao se aplica aos tipos "
                    + tyl.toString() + " e " + tyr.toString());
                stk.push(tyErr);
            }

        } else if (tyr.match(tyFloat)) {
            if (tyl.match(tyFloat)) {
                stk.push(tyr); 
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
        positionReturnFunction = i;
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
                if(datas.get(t.getDataName()) != null){ // Tipo data existe
                    STyData tyData = new STyData(t.getDataName());
                    // Empilha o tipo da variavel e no attribution certifica se é valido
                    stk.add(tyData);
                }
                else{
                    logError.add("(" + getLineNumber()+ ") Erro em (linha: " + t.getLine() + ", coluna: " + t.getColumn()
                        + "): o tipo data \'" + t.getDataName() + "\' nao existe !");
                    stk.push(tyErr);
                }
            } else { // Array de data
                // Empilha o tamanho do array
                t.getExp().accept(this);

                SType tamanhoArray = stk.pop();
                if (!tamanhoArray.match(tyInt)) { // Verifica tamanho int para o array
                    logError.add("(" + getLineNumber()+ ") Erro em (linha: " + t.getLine() + ", coluna: " + t.getColumn()
                        + "): o tamanho de um array so pode ser atribuido com o tipo int e nao \'" + tamanhoArray + "\' .");
                    stk.push(tyErr);
                }

                if(datas.get(t.getDataName()) != null){ // Tipo data existe
                    STyData tyData = new STyData(t.getDataName());

                    // Cria o tipo de array com referencia ao tipo primitivo informado
                    STyArr array = new STyArr(tyData);
                    stk.add(array);
                }
                else{
                    logError.add("(" + getLineNumber()+ ") Erro em (linha: " + t.getLine() + ", coluna: " + t.getColumn()
                        + "): o tipo data \'" + t.getDataName() + "\' nao existe !");
                    stk.push(tyErr);
                }
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
        // TEM RETORNO A FUNCAO ===> Obrigatorio

        // Informacoes da funcao que sera retomada no functionReturn
        Integer qtdParamPassados = 0;           // A funcao nao foi passado parametros
        if(f.getFCallParams() != null){
            qtdParamPassados = f.getFCallParams().getExps().size(); // A funcao foi passada parametros
        }
        String nomeFuncao = f.getId();
        

        // Pega o ambiente da função
        ArrayList<LocalAmbiente> funcFinded = (ArrayList)env.getFuncoes(nomeFuncao);

        // Pega a função correspondente
        LocalAmbiente<SType> function = (LocalAmbiente<SType>)funcFinded.get(0);   // Só uma funcao
        if(funcFinded.size() > 1){  // Tem sobrecarga 
            ArrayList<Function> funcoesAST = getFunctionAST(nomeFuncao);
            for(int i = 0; i < funcFinded.size(); i++){
                LocalAmbiente<SType> funcaoBase = funcFinded.get(i);

                STyFun funcaoBaseTipo = (STyFun)funcaoBase.getFuncType();

                // Se a funcao tem o mesmo numero de parametros
                if(funcaoBaseTipo.getTypes().length == qtdParamPassados){
                    int contTiposIguais = 0;
                    int indiceExp = 0;
                    // Empilha os tipos das expressões passadas como parametro na chamada da FunctionReturn
                    // Compara com os tipos da função Find e verifica se coincide
                    for (Expression exp : f.getFCallParams().getExps()) {
                        // Empilha a expressao do parametro
                        exp.accept(this);
                        SType tipoParametro = funcaoBaseTipo.getTypes()[indiceExp];    // Tipo do parametro do campo da função
                        SType parametroPassado = stk.pop(); // parametro passado na chamada da funcao
                        // Compara pelo nome pois se comparar só com o equals, são regioes de 
                        // memoria diferente, então nao funciona
                        if(tipoParametro.toString().equals(parametroPassado.toString())){
                            contTiposIguais++;
                        }
                        else{
                            break;
                        }
                        indiceExp++;
                    }
                    if(contTiposIguais == funcaoBaseTipo.getTypes().length){
                        function = (LocalAmbiente<SType>)funcFinded.get(i);
                        break;
                    }
                }

                // Nao compara retornos pois na descricao do trabalho foi solicitado se atentar 
                // Na sobrecarga apenas de parametros

                // Quantidade de retornos
                /*if(funcaoDeclaracao.getReturnTypes().size() == funcaoBaseTipo.getReturnTypes().length){
                    boolean verificaRetDif = false;
                    for(int j = 0; j < funcaoDeclaracao.getReturnTypes().size(); j++){
                        String tipo = funcaoDeclaracao.getReturnTypes().get(j).toString();
                        // Compara os nomes de tipos, se for diferente nao eh a funcao
                        if(!(tipo.equals(funcaoBaseTipo.getReturnTypes()[j].toString()))){
                            verificaRetDif = true;
                        }
                    }
                    if(verificaRetDif){
                        continue;
                    }

                    
                }*/
            }
        }

        // Garante a existencia da função
        if (function != null) {

            if (f.getFCallParams() != null) {

                STyFun tipoFuncao = (STyFun) function.getFuncType();

                int tempID = 0;

                // Verifica se a quantidade de parametros informados é o mesmo da função
                if(f.getFCallParams().getExps().size() == tipoFuncao.getTypes().length){
                    // Verifica os tipos dos parametros passado
                    for (Expression exp : f.getFCallParams().getExps()) {
                        // Empilha o tipo da expressao passada como parametro na chamada da funcao FunctionReturn
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
                    // // Empilha o ultimo tipo da função
                    // stk.push(tipoFuncao.getTypes()[tipoFuncao.getTypes().length - 1]);
                }
                else{
                    if(f.getFCallParams().getExps().size() > tipoFuncao.getTypes().length){
                        logError.add("(" + getLineNumber()+ ") Erro em (linha: " + f.getLine() + ", coluna: " + f.getColumn()
                        + "): Foi passado mais argumentos que a quantidade de parametros que a funcao \'" + f.getId()+"\'"
                        + " apresenta, sendo: " + f.getFCallParams().getExps().size() + " argumento(s) no parametro na chamada da funcao e " + tipoFuncao.getTypes().length + " argumento(s) no parametro na declaracao da funcao  !!!");
                        stk.push(tyErr);
                    }
                    else{
                        logError.add("(" + getLineNumber()+ ") Erro em (linha: " + f.getLine() + ", coluna: " + f.getColumn()
                        + "): Foi passado menos argumentos que a quantidade de parametros que a funcao \'" + f.getId()+"\'"
                        + " apresenta, sendo: " + f.getFCallParams().getExps().size() + " argumento(s) no parametro na chamada da funcao e " + tipoFuncao.getTypes().length + " argumento(s) no parametro na declaracao da funcao  !!!");
                        stk.push(tyErr);
                    }
                }
            }
            else{
                STyFun tipoFuncao = (STyFun) function.getFuncType();

                if(tipoFuncao.getTypes().length > 0){   // Tem parametros na declaracao da funcao mas nao tem na chamada dela
                    logError.add("(" + getLineNumber()+ ") Erro em (linha: " + f.getLine() + ", coluna: " + f.getColumn() + 
                        "): Foi passado 0 argumentos como parametro na chamada da funcao \'" + f.getId()+"\'"
                        + " sendo que na declaracao da funcao ela precisa de " + tipoFuncao.getTypes().length + " parametro(s) !!"
                    );
                    stk.push(tyErr);
                }
            }
        }

        // verifica se o valor passado de posicao do array é inteiro
        f.getExpIndex().accept(this);

        SType tipoPosicaoRetorno = stk.pop();
        if (!tipoPosicaoRetorno.match(tyInt)) {
            logError.add("(" + getLineNumber()+ ") Erro em (linha: " + f.getLine() + ", coluna:" + f.getColumn()
                + "): O retorno da funcao so pode ser acessado com uma posicao em valor Int e nao \'" 
                + tipoPosicaoRetorno + "\' !");
            stk.push(tyErr);
        }

        // Certifica que o valor da posicao de retorno existe e é um inteiro
        // Se uma variavel for passada como posicao, simplesmente essa checagem de retorno não é feita
        if(!(f.getExpIndex() instanceof Identifier)){   // Se nao for variavel
            if(positionReturnFunction != null && positionReturnFunction instanceof IntegerNumber){
                STyFun tipoFuncao = (STyFun) function.getFuncType();
                IntegerNumber posicao = (IntegerNumber)positionReturnFunction;
                stk.push(tipoFuncao.getReturnTypes()[posicao.getValue()]);
            }
        }
        else{   // Empilha os dois retornos ====> Problema pode ser corrigido no interpretador
            // Como nao temos acesso ao valor numerico e olhamos só o tipo
            // Ficaria complicado afirmar se o retorno está com o tipo certo caso fosse uma variavel
            // Sendo que empilhamos somente o tipo e não o valor inteiro
            STyFun tipoFuncao = (STyFun) function.getFuncType();
            IntegerNumber posicao = (IntegerNumber)positionReturnFunction;
            for(int i = 0; i < tipoFuncao.getReturnTypes().length; i++){
                stk.push(tipoFuncao.getReturnTypes()[i]);
            }
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

        // Certifica a existencia do tipo data passado no escopo atual
        if (temp.get(d.getDataId()) instanceof STyData) {

            STyData dataType = (STyData) temp.get(d.getDataId());

            if (datas.get(dataType.getName()) == null) {
                logError.add("(" + getLineNumber()+ ") Erro em (linha: "+d.getLine() + ", coluna: " + d.getColumn() 
                + "): Acesso a um tipo de data inexistente: " + dataType.getName());
                stk.push(tyErr);
            }
            else{
                DataAttributes data = datas.get(dataType.getName());
                boolean atribEncontrado = false;
                for(int i = 0; i < data.getVariaveis().size(); i++){
                    if(data.getVariaveis().get(i).equals(d.getId())){
                        stk.push(data.getTipos().get(i));
                        atribEncontrado = true;
                        break;
                    }
                }
                if(!atribEncontrado){   // Atributo nao encontrado
                    logError.add("(" + getLineNumber()+ ") Erro em (linha: " + d.getLine() + ", coluna: " + d.getColumn() + "): Atributo \'" + d.getId() + "\' eh inexistente em \'"+ dataType.getName()+"\' !!");
                    stk.push(tyErr);
                }
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

            // Empilha o tipo do array/matriz
            d.getLValue().accept(this);

            STyData dataType = (STyData) stk.pop();
            // STyArr arrayType = (STyArr) temp.get(d.getLValue().getId());
            // STyData dataType = (STyData) arrayType.getArg();

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
                    stk.push(dataTypes.get(i)); // Empilha o tipo do atributo
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
            logError.add("(" + getLineNumber()+ ") Erro em (linha: " + d.getLine() + ", coluna: " + d.getColumn() + "): A variavel \'" + d.getDataId() + "\' nao existe e portanto nao pode ter atributos !!!");
            stk.push(tyErr);
        }
    }

    @Override
    public void visit(ArrayAccess a) {
        if(a.getLValue() instanceof Identifier){    // Já for a variavel entao é um array
            if(temp.get(a.getLValue().getId()) != null){
                SType tipoAux = temp.get(a.getLValue().getId());
                if(tipoAux instanceof STyArr){
                    SType argumento = ((STyArr)tipoAux).getArg();
                    stk.push(argumento);           // Empilha o tipo do array
                }
            }
            else{
                logError.add("(" + getLineNumber()+ ") Erro em (linha: " + a.getLine() + ", coluna: " + a.getColumn() + "): a variavel  \'" + a.getLValue().getId() + "\' nao existe !!");
                stk.push(tyErr);
            }
        }
        else if(a.getLValue() instanceof ArrayAccess){   // é matriz
            if(temp.get(a.getLValue().getId()) != null){
                SType tipoAux = temp.get(a.getLValue().getId());
                if(tipoAux instanceof STyArr){
                    SType argumento = ((STyArr)tipoAux).getArg();
                    if(argumento instanceof STyArr ){
                        SType tipoMatriz = ((STyArr)argumento).getArg();
                        if(tipoMatriz instanceof STyArr){
                            logError.add("(" + getLineNumber()+ ") Erro em (linha: " + a.getLine() + ", coluna: " + a.getColumn() + "): Nao eh possivel uma matriz no formato "+ tipoAux + "!!");
                            stk.push(tyErr);
                        }
                        else{
                            // Verifica o indice da linha da matriz
                            ((ArrayAccess)a.getLValue()).getExp().accept(this);
                            SType tipoLinha = stk.pop();
                            if (!tipoLinha.match(tyInt)) { // Verifica se o tipo da posicao da linha na matriz é um valor inteiro
                                logError.add("(" + getLineNumber()+ ") Erro em (linha: " + a.getLine() + ", coluna: " + a.getColumn() + "): Arrays so podem ter sua posicao acessada se o indice for um numero inteiro e nao \'" + tipoLinha + "\' !!");
                                stk.push(tyErr);
                            }

                            stk.push(tipoMatriz);       // Empilha o tipo da matriz
                        }
                    }
                    else{
                        stk.push(argumento);           // Empilha o tipo do array
                    }
                }
            }
            else{
                logError.add("(" + getLineNumber()+ ") Erro em (linha: " + a.getLine() + ", coluna: " + a.getColumn() + "): a variavel  \'" + a.getLValue().getId() + "\' nao existe !!");
                stk.push(tyErr);
            }
        }
            
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
            exp.accept(this);
        }
    }
}
