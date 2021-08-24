package lang.codeGenerator;

import lang.ast.*;
import lang.interpreter.*;
import lang.semantic.*;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class CPlusPlusVisitor extends Visitor {

    private STGroup groupTemplate;
    private ST type, stmt, expr;
    private List<ST> funcs, params, datas, declarations;
    private String fileName;

    // Indice que identifica qual o loopAtual, ou seja, se há vários iterate internos
    // A ideia é pra auxiliar no nome da variavel do for de repetição
    private int loopAtual = 0;

    TyEnv<LocalAmbiente<SType>> env;    // Ambiente do código

    // Atributos dos tipos data
    private HashMap<String, DataAttributes> datasAttrib;

    // Armzanena as Funcoes 
    private ArrayList<Function> functionsAST;

    public CPlusPlusVisitor(String fileName, TyEnv<LocalAmbiente<SType>> env, HashMap<String, DataAttributes> datasAttrib) {
        groupTemplate = new STGroupFile("./template/c++.stg");
        this.fileName = fileName;
        this.env = env;
        this.datasAttrib = datasAttrib;
        functionsAST = new ArrayList<Function>();
    }

    // Partem do prog
    @Override
    public void visit(Program p) {
        ST template = groupTemplate.getInstanceOf("program");
        template.add("name", fileName);
        datas = new ArrayList<ST>();
        // Aceita os tipos data para fazer a verificação de tipo
        for (Data d : p.getDatas()) {
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
        data.add("name", d.getNameType());  // o nome do tipo data
        DataAttributes dataElement = datasAttrib.get(d.getNameType());




        // LocalEnv<SType> local = env.get(f.getID());
        // Set<String> keys = local.getKeys();


        // f.getTipo().accept(this);
        // fun.add("type", type);

        params = new ArrayList<ST>();
        for (Param p : f.getParams()) {
            keys.remove(p.getID());
            p.accept(this);
        }
        fun.add("params", params);

        for (String key : keys) {
            SType t = local.get(key);
            if(!(t instanceof STyArr)){   // Se nao for array declara normalmente
                ST decl = groupTemplate.getInstanceOf("declaration");
                decl.add("name", key);
                processSType(t);       
                decl.add("type", type);
                fun.add("decl", decl);
            }
        }

        f.getBody().accept(this);
        fun.add("stmt", stmt);

        datas.add(fun);
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

        LocalAmbiente<SType> local = env.get(f.getId());
        Set<String> keys = local.getKeys();

        f.get().accept(this);
        fun.add("type", type);

        params = new ArrayList<ST>();
        for (Param p : f.getParams()) {
            keys.remove(p.getID());
            p.accept(this);
        }
        fun.add("params", params);

        for (String key : keys) {
            SType t = local.get(key);
            if(!(t instanceof STyArr)){   // Se nao for array declara normalmente
                ST decl = groupTemplate.getInstanceOf("param");
                decl.add("name", key);
                processSType(t);       
                decl.add("type", type);
                fun.add("decl", decl);
            }
        }

        f.getBody().accept(this);
        fun.add("stmt", stmt);

        funcs.add(fun);
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
        if (i.getElseCmd() != null) {
            i.getElseCmd().accept(this); // Executa a verificação de tipos nos comandos do else
            aux.add("else", stmt);
        }
        stmt = aux;
    }

    @Override
    public void visit(Iterate i) {
        ST aux = groupTemplate.getInstanceOf("iterate");
        i.getExp().accept(this);
        aux.add("expr", expr);
        loopAtual++;    // Incrementa o indice do loop atual
        aux.add("loopAtual", String.valueOf(loopAtual));
        i.getCmd().accept(this);
        aux.add("cmd", stmt);
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

        // stmt = groupTemplate.getInstanceOf("return");
        // r.getExpr().accept(this);
        // stmt.add("expr", expr);
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

    ////////////// Métodos ///////////
    private void processSType(SType t) {
        if (t instanceof STyInt)
            type = groupTemplate.getInstanceOf("int_type");
        else if (t instanceof STyBool)
            type = groupTemplate.getInstanceOf("boolean_type");
        else if (t instanceof STyFloat)
            type = groupTemplate.getInstanceOf("float_type");
        else if (t instanceof STyChar)
            type = groupTemplate.getInstanceOf("char_type");
    }

}
