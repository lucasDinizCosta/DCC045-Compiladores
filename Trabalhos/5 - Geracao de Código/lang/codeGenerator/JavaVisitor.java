package lang.codeGenerator;

import lang.ast.*;
import lang.interpreter.*;
import lang.semantic.*;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;

public class JavaVisitor extends Visitor {

    private STGroup groupTemplate;
    private ST type, stmt, expr, variavel;
    private List<ST> funcs, params, datas, declarations;
    private String fileName;

    // Indice que identifica qual o loopAtual, ou seja, se há vários iterate internos
    // A ideia é pra auxiliar no nome da variavel do for de repetição
    private int loopAtual = 0;

    TyEnv<LocalAmbiente<SType>> env;    // Ambiente do código

    // Atributos dos tipos data ==> Com o tipo semantico
    private HashMap<String, DataAttributes> datasAttrib;

    // Armazena as Funcoes 
    private ArrayList<Function> functionsAST;

    // Tipos de dados novos
    private HashMap<String, Data> datasAST;    
    
    // idRetorno é o indice de qual elemento será retornado quando a função apresenta 2 retornos
    // para ser tratado no comando return
    private int idRetorno = 0;

    public JavaVisitor(String fileName, TyEnv<LocalAmbiente<SType>> env, HashMap<String, DataAttributes> datasAttrib) {
        groupTemplate = new STGroupFile("./lang/codeGenerator/templates/java.stg");
        this.fileName = fileName;
        this.env = env;
        this.datasAttrib = datasAttrib;
        functionsAST = new ArrayList<Function>();
        datasAST = new HashMap<String, Data>();
    }

    // Partem do prog
    @Override
    public void visit(Program p) {
        ST template = groupTemplate.getInstanceOf("program");
        template.add("name", fileName);
        datas = new ArrayList<ST>();
        // Aceita os tipos data para fazer a verificação de tipo
        for (Data d : p.getDatas()) {
            datasAST.put(d.getId(), d);
            d.accept(this);
        }
        template.add("datas", datas);

        funcs = new ArrayList<ST>();
        // Checa as funções
        for (Function f : p.getFunctions()) {
            functionsAST.add(f);
            f.accept(this);
        }
        template.add("funcs", funcs);

        System.out.println(template.render()); // Imprime na tela o código em alto nivel gerado
    }

    // Retorna o ambiente de geração de código
    public TyEnv<LocalAmbiente<SType>> getEnv() {return env;}

    // https://www.techiedelight.com/get-current-line-number-java/
    // Retorna a linha do código fonte ao passar pela instrução
    public int getLineNumber(){
        // return new Throwable().getStackTrace()[0].getLineNumber();
        return Thread.currentThread().getStackTrace()[2].getLineNumber();
    }

    // Partem do data
    @Override
    public void visit(Data d) {
        ST data = groupTemplate.getInstanceOf("data");
        data.add("name", d.getId());  // o nome do tipo data
        declarations = new ArrayList<ST>();
        DataAttributes dataElement = datasAttrib.get(d.getId());
        List<Declaration> listaDeclaracoes = d.getDeclarations();
        int indiceTipo = 0;
        declarations.clear();
        for(Declaration declaration : listaDeclaracoes){
            ST decl = groupTemplate.getInstanceOf("declaration");
            SType tipo = dataElement.getTipos().get(indiceTipo);
            decl.add("name", declaration.getId());
            if(tipo instanceof STyArr){
                adjustSTyArr((STyArr)tipo);
            }
            else{
                processSType(tipo);       
            }
            decl.add("type", type);
            declarations.add(decl);
            indiceTipo++;
        }
        data.add("decl", declarations);
        datas.add(data);
    }

    // Partem do decl
    @Override
    public void visit(Declaration d) {
        // Nao faz nada, é tratado em outra funcao
    }

    // Partem do func
    @Override
    public void visit(Function f) {
        ST fun = groupTemplate.getInstanceOf("func");
        fun.add("name", f.getId());
        // Pega todas as funções que tem o mesmo nome
        ArrayList<LocalAmbiente> funcFinded = (ArrayList)env.getFuncoes(f.getId());
         // Só uma funcao
        LocalAmbiente<SType> local = (LocalAmbiente<SType>)funcFinded.get(0);  
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
                        local = (LocalAmbiente<SType>)funcFinded.get(i);
                        break;
                    }
                }
            }
        }

        if(f.getReturnTypes().size() < 2){
            if(f.getReturnTypes().size() == 0){ // void => 0 retornos
                if(f.getId().equals("main")){   // Função 'main' pra C++ tem retornar valor inteiro
                    fun.add("type", "int");
                }
                else{
                    fun.add("type", "void");
                }
            }
            else if(f.getReturnTypes().size() == 1){    // 1 retorno somente
                // A função mesmo com somente 1 retorno terá seu nome alterado
                fun = groupTemplate.getInstanceOf("func");
                String nomeFuncao = f.getId() + "_retorno_00";
                fun.add("name", nomeFuncao);
                f.getReturnTypes().get(0).accept(this); // Empilha o único tipo de retorno que será o tipo da função
                fun.add("type", type);
            }

            // Declaração das variaveis que são usadas no corpo da função
            Set<String> keys = local.getKeys();

            // Instancia a lista que vai armazenar os comandos da função
            params = new ArrayList<ST>();

            if (f.getParameters() != null) {
                Parameters paramsList = f.getParameters();

                // Adiciona as variaveis do parametro no escopo local
                for (int i = 0; i < paramsList.size(); i++) {
                    SType t = ((STyFun) local.getFuncType()).getTypes()[i]; // Pega o tipo do parametro
                    ST p = groupTemplate.getInstanceOf("param");
                    String nomeParametro = paramsList.getSingleId(i);
                    p.add("name", nomeParametro);
                    if(t instanceof STyArr){
                        adjustSTyArr((STyArr)t);
                    }
                    else{
                        processSType(t);       
                    }
                    p.add("type", type);
                    params.add(p);

                    // Remove o parametro da função da lista de variaveis do corpo da função
                    keys.remove(nomeParametro);
                }
            }
            fun.add("params", params);

            // Instancia as variaveis antes de usar nas operações presente no corpo da função
            for (String key : keys) {
                SType t = local.get(key);
                // if(!(t instanceof STyArr)){   // Se nao for array declara normalmente
                ST decl = groupTemplate.getInstanceOf("param");
                decl.add("name", key);
                if(t instanceof STyArr){
                    adjustSTyArr((STyArr)t);
                }
                else{
                    processSType(t);       
                }       
                decl.add("type", type);
                fun.add("decl", decl);
                // }
            }

            for(int i = 0; i < f.getCommands().size(); i++){
                Command command = f.getCommands().get(i);
                command.accept(this);
                fun.add("stmt", stmt);
            }
            

            // Adiciona o 'return 0;' na função main do código em C++
            if(f.getId().equals("main")){
                stmt = groupTemplate.getInstanceOf("return");
                stmt.add("expr", 0 );  // 'return 0;'
                fun.add("stmt", stmt);
            }

            funcs.add(fun);
        }
        else{   // 2 retornos
            /**
             * Vai adicionar duas funções da seguinte forma:
             * Exemplo: 
             *  -- Em lang ==> tipo float e int
             *  soma(n :: int, n1 :: int): float, int{
             *  -- Em C++
             *  float soma_retorno_01(int n, int n1) ||| int soma_retorno_02(int n, int n1)
             */
            idRetorno = 0;
            // Para cada tipo de retorno, será criada uma função diferente
            for(int j = 0; j < f.getReturnTypes().size(); j++){
                fun = groupTemplate.getInstanceOf("func");
                String nomeFuncao = f.getId() + "_retorno_0" + idRetorno;
                fun.add("name", nomeFuncao);

                // Empilha o tipo de retorno da função
                f.getReturnTypes().get(idRetorno).accept(this); 
                fun.add("type", type);

                // Declaração das variaveis que são usadas no corpo da função
                Set<String> keys = local.getKeys();

                // Instancia a lista que vai armazenar os comandos da função
                params = new ArrayList<ST>();

                if (f.getParameters() != null) {
                    Parameters paramsList = f.getParameters();

                    // Adiciona as variaveis do parametro no escopo local
                    for (int i = 0; i < paramsList.size(); i++) {
                        SType t = ((STyFun) local.getFuncType()).getTypes()[i]; // Pega o tipo do parametro
                        ST p = groupTemplate.getInstanceOf("param");
                        String nomeParametro = paramsList.getSingleId(i);
                        p.add("name", nomeParametro);
                        if(t instanceof STyArr){
                            adjustSTyArr((STyArr)t);
                        }
                        else{
                            processSType(t);       
                        }
                        p.add("type", type);
                        params.add(p);

                        // Remove o parametro da função da lista de variaveis do corpo da função
                        keys.remove(nomeParametro);
                    }
                }
                fun.add("params", params);

                // Instancia as variaveis antes de usar nas operações presente no corpo da função
                for (String key : keys) {
                    SType t = local.get(key);
                    // if(!(t instanceof STyArr)){   // Se nao for array declara normalmente
                    ST decl = groupTemplate.getInstanceOf("param");
                    decl.add("name", key);
                    if(t instanceof STyArr){
                        adjustSTyArr((STyArr)t);
                    }
                    else{
                        processSType(t);       
                    }       
                    decl.add("type", type);
                    fun.add("decl", decl);
                    // }
                }

                for(int i = 0; i < f.getCommands().size(); i++){
                    Command command = f.getCommands().get(i);
                    command.accept(this);
                    fun.add("stmt", stmt);
                }

                funcs.add(fun);
                idRetorno++;
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
        ST aux = groupTemplate.getInstanceOf("array_type");
        t.getType().accept(this);
        aux.add("type", type);
        type = aux;
    }

    // Partem do btype
    @Override
    public void visit(TypeInt t) {
        type = groupTemplate.getInstanceOf("int_type");
    }

    @Override
    public void visit(TypeChar t) {
        type = groupTemplate.getInstanceOf("char_type");
    }

    @Override
    public void visit(TypeBool t) {
        type = groupTemplate.getInstanceOf("bool_type");
    }

    @Override
    public void visit(TypeFloat t) {
        type = groupTemplate.getInstanceOf("float_type");
    }

    @Override
    public void visit(Type t) {
        // Nao faz nada pois já foi tratado em outra funcao
    }

    @Override
    public void visit(NameType i) { // TypeData
        // Nao faz nada pois já foi tratado em outra funcao
        type = groupTemplate.getInstanceOf("data_type");
        type.add("data", i.getID());
        /*if(datas.get(i.getID()) != null){   // Se o tipo data existe
            STyData tipoData = new STyData(i.getID());
            stk.push(tipoData);
        }
        else{
            logError.add("(" + getLineNumber()+ ") Erro em (linha: " + i.getLine() + ", coluna: " + i.getColumn()
                + "): Tipo data passado como parametro na funcao nao existe =>\'" + i.getID() + "\' ");
            stk.push(tyErr);
        }*/
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
        ST aux = groupTemplate.getInstanceOf("if");
        i.getExp().accept(this); // Empilha a expressao de verificacao do If
        aux.add("expr", expr);
        i.getCmd().accept(this);
        aux.add("cmd", stmt);
        stmt = aux;
    }

    @Override
    public void visit(IfElse i) {
        ST aux = groupTemplate.getInstanceOf("if_else");
        i.getExp().accept(this);// Empilha a expressao de verificacao do If e Else
        aux.add("expr", expr);
        i.getCmd().accept(this);
        aux.add("cmd", stmt);
        i.getElseCmd().accept(this); // Executa a verificação de tipos nos comandos do else
        aux.add("els", stmt);
        stmt = aux;
    }

    @Override
    public void visit(Iterate i) {
        ST aux = groupTemplate.getInstanceOf("iterate");
        i.getExp().accept(this);
        aux.add("expr", expr);
        loopAtual++;    // Incrementa o indice do loop atual
        aux.add("loopAtual", String.valueOf(loopAtual));
        if(i.getCmd() instanceof CommandsList){ // Se for uma lista de comandos no corpo do iterate
            CommandsList cmdList = (CommandsList)i.getCmd();
            for(int j = 0; j < cmdList.getCommands().size(); j++){
                cmdList.getCommands().get(j).accept(this);
                aux.add("cmd", stmt);
            }
        }
        else{
            i.getCmd().accept(this);
            aux.add("cmd", stmt);
        }
        System.out.println(getLineNumber() + " -- " + i.getCmd() + " ---- " + i.getCmd().getClass().getSimpleName());
        // aux.add("cmd", stmt);
        loopAtual--;    // Decrementa o indice do loop atual
        stmt = aux;
    }

    @Override
    public void visit(Read r) {
        stmt = groupTemplate.getInstanceOf("read");
        r.getLValue().accept(this);
        stmt.add("expr", expr);
    }

    @Override
    public void visit(Print i) {
        stmt = groupTemplate.getInstanceOf("print");
        i.getExpression().accept(this);
        stmt.add("expr", expr);
    }

    @Override
    public void visit(Return r) {
        if(r.getExps().size() == 1){
            stmt = groupTemplate.getInstanceOf("return");
            // Processa a expressões de retorno da função
            r.getExps().get(0).accept(this);
            stmt.add("expr", expr);
        }
        else{   // Quando a função tem 2 retornos
            stmt = groupTemplate.getInstanceOf("return");
            // Processa a expressões de retorno da função
            r.getExps().get(idRetorno).accept(this);
            stmt.add("expr", expr);
        }
    }

    @Override
    public void visit(Attribution a) {
        // a = 2 + b + ponto.x + array[1];

        stmt = groupTemplate.getInstanceOf("attribution");

        // Variavel que vai ter os dados atribuidos nela
        LValue lvalue = a.getLValue();

        System.out.println(getLineNumber() + " --- " + lvalue + " --- " + a.getExp());

        lvalue.accept(this);

        if (lvalue instanceof Identifier) {
            // System.out.println(getLineNumber() + " --- " + variavel.render());
            // lvalue.accept(this);

            stmt.add("var", expr);  //lvalue
            // Empilha o tipo da expressao que sera atribuida
            a.getExp().accept(this);
            stmt.add("expr", expr);

        } else if (lvalue instanceof ArrayAccess) {
            if(((ArrayAccess)lvalue).getLValue() != null && ((ArrayAccess)lvalue).getLValue() instanceof ArrayAccess){   // Trata o caso de matriz
                stmt.add("var", expr);  //lvalue
                // Empilha o tipo da expressao que sera atribuida
                a.getExp().accept(this);
                stmt.add("expr", expr);
                /*ArrayAccess matriz = (ArrayAccess)((ArrayAccess)lvalue).getLValue();
                
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

                }*/
            }
            else{   // Array 
                // aceita a expressao e joga pro topo da pilha. vai verificar posteriormente
                // dentro do ArrayAccess se casa
                System.out.println(getLineNumber() + " --- " + expr.render());
                stmt.add("var", expr);  //lvalue
                // Empilha o tipo da expressao que sera atribuida
                a.getExp().accept(this);
                stmt.add("expr", expr);

                /*lvalue.accept(this);        // Empilha o tipo do array

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
                }*/
            }
        } else if (lvalue instanceof DataAccess) {
            // aceita a expresso e joga pro topo da pilha. vai verificar posteriormente
            // dentro do dataAccess se casa

            stmt.add("var", expr);  //lvalue
            // Empilha o tipo da expressao que sera atribuida
            a.getExp().accept(this);
            stmt.add("expr", expr);

            /*if(((DataAccess)lvalue).getLValue() != null && ((DataAccess)lvalue).getLValue() instanceof ArrayAccess){    // Matriz de data

                a.getExp().accept(this);    // Empilha o tipo da expressao que será atribuida

                lvalue.accept(this);    // Empilha o tipo da matriz;
            }
            else{
                a.getExp().accept(this);    // Empilha o tipo da expressao que será atribuida

                lvalue.accept(this);    // Empilha o Tipo do atributo do data ou o tipo data mesmo
            }
            
            SType tipoVariavel = stk.pop();
            SType tipoExpressao = stk.pop();
            DataAccess d = (DataAccess)lvalue;*/

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
        /*Integer qtdParamPassados = 0;           // A funcao nao foi passado parametros
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
        }*/
    }

    // Partem do exp
    @Override
    public void visit(And a) {
        ST aux = groupTemplate.getInstanceOf("and_expr");
        a.getLeft().accept(this);
        aux.add("left_expr", expr);
        a.getRight().accept(this);
        aux.add("right_expr", expr);
        expr = aux;
    }

    // Partem do rexp
    @Override
    public void visit(LessThan l) {
        ST aux = groupTemplate.getInstanceOf("lessThan_expr");
        l.getLeft().accept(this);
        aux.add("left_expr", expr);
        l.getRight().accept(this);
        aux.add("right_expr", expr);
        expr = aux;
    }

    @Override
    public void visit(Equality e) {
        ST aux = groupTemplate.getInstanceOf("equallity_expr");
        e.getLeft().accept(this);
        aux.add("left_expr", expr);
        e.getRight().accept(this);
        aux.add("right_expr", expr);
        expr = aux;
    }

    @Override
    public void visit(Difference n) {
        ST aux = groupTemplate.getInstanceOf("diff_expr");
        n.getLeft().accept(this);
        aux.add("left_expr", expr);
        n.getRight().accept(this);
        aux.add("right_expr", expr);
        expr = aux;
    }

    // Partem do aexp
    @Override
    public void visit(Addition a) {
        System.out.println(getLineNumber() + " --- " + a + " --- " + a.getLeft());
        ST aux = groupTemplate.getInstanceOf("add_expr");
        a.getLeft().accept(this);
        aux.add("left_expr", expr);
        a.getRight().accept(this);
        aux.add("right_expr", expr);
        expr = aux;
    }

    @Override
    public void visit(Subtraction s) {
        ST aux = groupTemplate.getInstanceOf("sub_expr");
        s.getLeft().accept(this);
        aux.add("left_expr", expr);
        s.getRight().accept(this);
        aux.add("right_expr", expr);
        expr = aux;
    }

    // Partem do mexp
    @Override
    public void visit(Multiplication m) {
        ST aux = groupTemplate.getInstanceOf("mult_expr");
        m.getLeft().accept(this);
        aux.add("left_expr", expr);
        m.getRight().accept(this);
        aux.add("right_expr", expr);
        expr = aux;
    }

    @Override
    public void visit(Division d) {
        ST aux = groupTemplate.getInstanceOf("div_expr");
        d.getLeft().accept(this);
        aux.add("left_expr", expr);
        d.getRight().accept(this);
        aux.add("right_expr", expr);
        expr = aux;
    }

    @Override
    public void visit(Modular m) {
        ST aux = groupTemplate.getInstanceOf("mod_expr");
        m.getLeft().accept(this);
        aux.add("left_expr", expr);
        m.getRight().accept(this);
        aux.add("right_expr", expr);
        expr = aux;
    }

    // Partem do sexp
    @Override
    public void visit(Not n) {
        ST aux = groupTemplate.getInstanceOf("not_expr");
        n.getExpression().accept(this);
        aux.add("expr", expr);
        expr = aux;
    }

    @Override
    public void visit(Minus n) {
        ST aux = groupTemplate.getInstanceOf("minus_expr");
        n.getExpression().accept(this);
        aux.add("expr", expr);
        expr = aux;
    }

    // True e False
    @Override
    public void visit(BooleanValue b) {
        expr = groupTemplate.getInstanceOf("boolean_expr");
        expr.add("value", b.getValue());
    }

    @Override
    public void visit(Null n) {
        expr = groupTemplate.getInstanceOf("null_type");
        expr.add("value", n.getValue());
    }

    @Override
    public void visit(IntegerNumber i) {
        expr = groupTemplate.getInstanceOf("int_expr");
        expr.add("value", i.getValue());
    }

    @Override
    public void visit(FloatNumber p) {
        expr = groupTemplate.getInstanceOf("float_expr");
        expr.add("value", p.getValue() + "f");
    }

    @Override
    public void visit(CharLitteral c) {
        expr = groupTemplate.getInstanceOf("char_expr");
        expr.add("value", c.getValue());
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
        ST aux = groupTemplate.getInstanceOf("typeInstanciate");

        if (t.getType() != null) {
            if (t.getExp() != null) { // Array comum e Array de data
                if(t.getType() instanceof TypeArray){   // Matriz
                    // Troca o modo de criação da matriz
                    // Na Lang: ... new Char[][5]
                    // Outras linguagens: ... new Char[5][]

                    // System.out.println(getLineNumber() + " --- " + t + " -- " 
                    // + t.getType() + " --- " + t.getExp());
                    TypeArray tArray = (TypeArray)t.getType();
                    ST lvalue = groupTemplate.getInstanceOf("lvalue");
                    tArray.getType().accept(this);  // Converte o tipo do array pro padrao java: Ex: Char -> char
                    lvalue.add("name", type);
                    ST arrayAccess = groupTemplate.getInstanceOf("array_access");
                    // Empilha o numero de linhas da matriz
                    t.getExp().accept(this);
                    arrayAccess.add("expr", expr);
                    lvalue.add("array", arrayAccess);  
                    aux.add("type", lvalue);    // Adiciona o numero de linhas na frente da declaração

                    // Adiciona uma expressão vazia somente para ter os colchetes das colunas na matriz
                    aux.add("expr", "");
                    
                    

                    // Empilha o tipo do array
                    // t.getType().accept(this);
                    // aux.add("type", type);
                }
                else{       // Array
                    // System.out.println(getLineNumber() + " --- " + t + " -- " 
                    // + t.getType() + " --- " + t.getExp());

                    // Empilha o tipo do array
                    t.getType().accept(this);
                    aux.add("type", type);
                    
                    // Empilha o tamanho do array
                    t.getExp().accept(this);
                    aux.add("expr", expr);
                }  
            } else { // new Int; -- new Float; -- new Char; -- new data;
                t.getType().accept(this);
                aux.add("type", type);
            }
        }

        expr = aux;
    }

    @Override
    public void visit(FunctionReturn f) {
        /************************************************************************************************
         * MESMO QUE TENHA SOMENTE 1 RETORNO, ELA DEVE SER CHAMADA ASSIM: fat(num−1)[0]                 *
         * Regra                                                                                        *
         * pexp: ID OPEN_PARENT exps? CLOSE_PARENT OPEN_BRACKET exp CLOSE_BRACKET  # FunctionReturn     *
         * // Como retorna 2 valores, logo precisa do funcao(parametros)[indice] Exemplo: fat(num−1)[0] *
         ***********************************************************************************************/
        ST aux = groupTemplate.getInstanceOf("functionReturn");
		aux.add("name", f.getId());
        f.getExpIndex().accept(this);   // Coloca o indice do retorno na expressao
        aux.add("expr", expr);

        String nomeFuncao = f.getId();
        Integer qtdParamPassados = 0;           // A funcao nao foi passado parametros
        if(f.getFCallParams() != null){
            qtdParamPassados = f.getFCallParams().getExps().size(); // A funcao foi passada parametros
        }

        // Adiciona os parametros passados na chamada da função
		for (Expression exp : f.getFCallParams().getExps()) {
			exp.accept(this);
			aux.add("args", expr);
		}
		expr = aux;

        // pexp: ID OPEN_PARENT exps? CLOSE_PARENT OPEN_BRACKET exp CLOSE_BRACKET #
        // 'FunctionReturn' // Como retorna 2 valores, logo precisa do
        // funcao(parametros)[indice] Exemplo: fat(num−1)[0]
        // TEM RETORNO A FUNCAO ===> Obrigatorio

        // Informacoes da funcao que sera retomada no functionReturn
        /*Integer qtdParamPassados = 0;           // A funcao nao foi passado parametros
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
        }*/
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
        expr = groupTemplate.getInstanceOf("lvalue");
        expr.add("name", i.getId());
    }

    @Override
    public void visit(DataAccess d) {
        expr = groupTemplate.getInstanceOf("lvalue");
        expr.add("name", d.toString());
    }

    @Override
    public void visit(ArrayAccess a) {
        expr = groupTemplate.getInstanceOf("lvalue");
        expr.add("name", a.toString()); 
    }

    // Partem do exps
    @Override
    public void visit(FCallParams f) {
        for (Expression exp : f.getExps()) {
            exp.accept(this);
        }
    }

    ////////////// Métodos ///////////
    private void processSType(SType t) {
        if (t instanceof STyInt)
            type = groupTemplate.getInstanceOf("int_type");
        else if (t instanceof STyBool)
            type = groupTemplate.getInstanceOf("boolean_type");
        else if (t instanceof STyFloat)
            type = groupTemplate.getInstanceOf("float_type");
        else if (t instanceof STyCharacter)
            type = groupTemplate.getInstanceOf("char_type");
        else if (t instanceof STyData){
            type = groupTemplate.getInstanceOf("data_type");
            type.add("data", ((STyData)t).getName());
        }
    }

    private void adjustSTyArr(STyArr t){
        List<ST> array = new ArrayList<ST>();   // Lista dos arrays
        SType tipoArray = t;    // Utiliza uma cópia de t, pois será atualizado
        // Adiciona os tipos array em uma lista
        // Exemplo: mat[][] => adiciona mat[]'[]' => depois mat'[]'[]
        while(tipoArray instanceof STyArr){     
            type = groupTemplate.getInstanceOf("array_type");
            array.add(type);
            tipoArray = ((STyArr)tipoArray).getArg();
        }
        // Pega do elemento mais externo para o mais interno que será o tipo do array
        for(int i = 1; i < array.size(); i++){  // Ajusta o tipo caso tenha array de array
            ST aux = array.get(i);
            aux.add("type", array.get(i - 1));
        }
        processSType(tipoArray);                // Passa o tipo do array ou matriz || Exemplo: Ponto, Char, Int, Float
        array.get(0).add("type", type);         // Adiciona o tipo do array no elemento mais interno
        type = array.get(array.size() - 1);     // O tipo completo será o da ultima posição da lista
    }

}
