/********************************************************
* Trabalho de Teoria dos Compiladores(DCC045)(2021/1)   *
*                                                       *
* Projeto do Interpretador para a Linguagem Lang        *
* Nome: Lucas Diniz da Costa -- Matricula: 201465524C   *
*                                                       *
*********************************************************/
package lang.interpreter;

import java.util.ArrayList;
import java.util.List;

import lang.ast.*;
import lang.parser.LangBaseVisitor;
import lang.parser.LangParser.*;

import org.antlr.v4.runtime.tree.ParseTree;

/**
 * Classe auxiliar que pega a parseTree do ANTLR e implementa os métodos com
 * base nos nós criados da AST no padrão NODE
 * Classe auxiliar que adapta a parseTree criada pelo ANTLR para receber o Node da AST criado
 * e implementa os métodos do Visitor padrão do ANTLR
 */
public class VisitorAdapter extends LangBaseVisitor<Node> {
    // Os contextos são subclasses do LangParser da linguagem, gerado pelo ANTLR
    
    /**
     * Os contextos são filhos da classe "ParserRuleContext"
     * https://www.antlr.org/api/Java/org/antlr/v4/runtime/ParserRuleContext.html
     */

    @Override
    public Node visitProgram(ProgramContext ctx) {
        // ----- Regra
        // prog: data* func*
        // ctx.getStart() => Captura o token inicial do contexto

        Program program = new Program(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine()); // Linha e coluna

        for (int i = 0; i < (ctx.data().size()) && this.shouldVisitNextChild(ctx, this.defaultResult()); i++) {
            ParseTree childTree = ctx.data(i);
            program.addData((Data) this.aggregateResult(this.defaultResult(), childTree.accept(this)));
        }

        for (int i = 0; i < (ctx.func().size()) && this.shouldVisitNextChild(ctx, this.defaultResult()); i++) {
            ParseTree childTree = ctx.func(i);
            program.addFunction((Function) this.aggregateResult(this.defaultResult(), childTree.accept(this)));
        }

        return program;
    }

    @Override
    public Node visitDataDeclaration(DataDeclarationContext ctx) {
        // ----- Regra
        // data: DATA_TYPE NAME_TYPE OPEN_BRACES decl* CLOSE_BRACES # DataDeclaration
        /**
         * Modelo:
         * data Teste{
         * -- declarações
         *  2 :: Int;
         *  'a' :: Char;
         * }
         */

        String nametype = ctx.NAME_TYPE().getText(); // Captura o nome de tipo
        List<Declaration> decls = new ArrayList<Declaration>(); // Declarações

        // Percorre o número de declarações
        for (int i = 0; i < ctx.decl().size(); i++) {
            Declaration declarationAccept = (Declaration)ctx.decl().get(i).accept(this);
            decls.add(declarationAccept); // Listagem de declarações do data
        }

        return new Data(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), nametype, decls);
    }

    @Override
    public Node visitVarDeclaration(VarDeclarationContext ctx) {
        // ----- Regra
        // decl: ID DOUBLE_COLON type SEMI

        //Type tipo = ctx.type().accept(this);
        //ctx.type().

        return new Declaration(
            ctx.getStart().getLine(), 
            ctx.getStart().getCharPositionInLine(),
            ctx.getChild(0).getText(),      // Nome da variavel
            (Type) ctx.type().accept(this)  // Tipo da variavel: Int, Char, Bool,... OU NameType
        );
    }

    @Override
    public Node visitFunction(FunctionContext ctx) {
        // ----- Regra
        // func: ID OPEN_PARENT params? CLOSE_PARENT (COLON type (COMMA type)*)? 
        //    OPEN_BRACES cmd* CLOSE_BRACES    # Function

        // (COLON type (COMMA type)*)? -- Tipos de Retorno da função


        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();

        Function function = new Function(line, column, ctx.getChild(0).getText());  // Nome da função

        Parameters parameters;

        if (ctx.params() != null) { // Checa se há parametros na função
            parameters = (Parameters) ctx.params().accept(this);
            function.setParameters(parameters);
        }

        for (int i = 0; i < (ctx.type().size()) && this.shouldVisitNextChild(ctx, this.defaultResult()); i++) {
            ParseTree childTree = ctx.type(i);
            function.addReturnTypes((Type) this.aggregateResult(this.defaultResult(), childTree.accept(this)));
        }

        for (int i = 0; i < (ctx.cmd().size()) && this.shouldVisitNextChild(ctx, this.defaultResult()); i++) {
            ParseTree childTree = ctx.cmd(i);
            function.addCommand((Command) this.aggregateResult(this.defaultResult(), childTree.accept(this)));
        }

        return function;
    }

    @Override
    public Node visitParametersFunction(ParametersFunctionContext ctx) {
        // ----- Regra
        // params: ID DOUBLE_COLON type (COMMA ID DOUBLE_COLON type)*  # ParametersFunction
        
        List<String> ids = new ArrayList<>();
        List<Type> types = new ArrayList<>();

        for (int i = 0; i < ctx.type().size(); i++) {   // Encontra os ids e os tipos e armazena na classe Parameter
            ids.add(ctx.ID().get(i).getText());
            types.add((Type) ctx.type().get(i).accept(this));
        }

        Parameters parameters = new Parameters(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), ids, types);

        return parameters;
    }

    @Override
    public Node visitBTypeCall(BTypeCallContext ctx) {
        // ----- Regra
        // type: btype     # BTypeCall
        return super.visitBTypeCall(ctx);
    }

    @Override
    public Node visitTypeDeclaration(TypeDeclarationContext ctx) {
        // ----- Regra
        // type: type OPEN_BRACKET CLOSE_BRACKET   # TypeDeclaration
        Type type = (Type) ctx.type().accept(this);
        return new TypeArray(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), type);
    }

    @Override
    public Node visitBTypeInt(BTypeIntContext ctx) {
        // ----- Regra
        // btype: INT_TYPE     # BTypeInt
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        return new TypeInt(line, column);
    }

    @Override
    public Node visitBTypeChar(BTypeCharContext ctx) {
        // ----- Regra
        // btype: CHAR_TYPE     # BTypeChar
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        return new TypeChar(line, column);
    }

    @Override
    public Node visitBTypeBool(BTypeBoolContext ctx) {
        // ----- Regra
        // btype: BOOL_TYPE     # BTypeBool
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        return new TypeBool(line, column);
    }

    @Override
    public Node visitBTypeFloat(BTypeFloatContext ctx) {
        // ----- Regra
        // btype: FLOAT_TYPE    # BTypeFloat
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        return new TypeFloat(line, column);
    }

    @Override
    public Node visitBTypeNameType(BTypeNameTypeContext ctx) {
        // ----- Regra
        // btype: NAME_TYPE     # BTypeNameType
        String nameType = ctx.getChild(0).getText();    // Captura o nome do tipo
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        return new NameType(line, column, nameType);
    }

    // @Override
    // public Node visitBTypeID(BTypeIDContext ctx) {
    //     // ----- Regra
    //     // btype: ID     # BTypeID
    //     String id = ctx.getChild(0).getText();    // Captura o nome do id
    //     int line = ctx.getStart().getLine();
    //     int column = ctx.getStart().getCharPositionInLine();
    //     return new ID(line, column, id);
    // }

    @Override
    public Node visitCommandsList(CommandsListContext ctx) {
        // ----- Regra
        // cmd: OPEN_BRACES cmd* CLOSE_BRACES      # CommandsList
        List<Command> cmds = new ArrayList<>();

        for (int i = 0; i < ctx.cmd().size(); i++) {
            cmds.add((Command) ctx.cmd().get(i).accept(this));
        }

        return new CommandsList(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), cmds);
    }

    @Override
    public Node visitIf(IfContext ctx) {
        // ----- Regra
        // cmd: IF OPEN_PARENT exp CLOSE_PARENT cmd   # If
        Expression exp = (Expression) ctx.getChild(2).accept(this);
        Command cmd = (Command) ctx.getChild(4).accept(this);

        return new If(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), exp, cmd);
    }

    @Override
    public Node visitIfElse(IfElseContext ctx) {
        // ----- Regra
        // cmd: IF OPEN_PARENT exp CLOSE_PARENT cmd ELSE cmd  # IfElse
        Expression exp = (Expression) ctx.getChild(2).accept(this);
        Command cmd = (Command) ctx.getChild(4).accept(this);
        Command elseCmd = (Command) ctx.getChild(6).accept(this);

        return new IfElse(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), exp, cmd, elseCmd);
    }

    @Override
    public Node visitIterate(IterateContext ctx) {
        // ----- Regra
        // cmd: ITERATE OPEN_PARENT exp CLOSE_PARENT cmd  # Iterate
        Iterate it = (Iterate) ctx.getChild(0).accept(this);
        Expression exp = (Expression) ctx.getChild(2).accept(this);
        Command cmd = (Command) ctx.getChild(4).accept(this);

        return new Iterate(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), it, exp, cmd);
    }

    @Override
    public Node visitRead(ReadContext ctx) {
        // ----- Regra
        // cmd: READ lvalue SEMI  # Read
        LValue lValue = (LValue) ctx.getChild(1).accept(this);
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        return new Read(line, column, lValue);
    }

    @Override
    public Node visitPrint(PrintContext ctx) {
        // ----- Regra
        // cmd: PRINT exp SEMI    # Print
        Expression expression = (Expression) ctx.exp().accept(this);
        return new Print(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), expression);
    }

    @Override
    public Node visitReturn(ReturnContext ctx) {
        // ----- Regra
        // cmd: RETURN exp (COMMA exp)* SEMI  # Return
        List<Expression> exps = new ArrayList<Expression>();

        for (int i = 0; i < ctx.exp().size(); i++) {
            exps.add((Expression) ctx.exp().get(i).accept(this));
        }

        // System.out.println("LISTA DE EXPRESSION");
        // for(int i = 0; i < exps.size(); i++){
        //     System.out.println("\""+exps.get(i)+"\"");
        // }

        return new Return(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), exps);
    }

    @Override
    public Node visitAttribution(AttributionContext ctx) {
        // ----- Regra
        // cmd: lvalue EQUALS exp SEMI    # Attribution
        return new Attribution(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
                (LValue) ctx.lvalue().accept(this), (Expression) ctx.exp().accept(this));
    }

    @Override
    public Node visitFunctionCall(FunctionCallContext ctx) {
        // ----- Regra
        // cmd: ID OPEN_PARENT exps? CLOSE_PARENT (LESS_THAN lvalue (COMMA lvalue)* GREATER_THAN)? SEMI   # FunctionCall

        FunctionCall fcall = new FunctionCall(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), ctx.getChild(0).getText());

        // Verifica se há parametros na função
        if (ctx.exps() != null) {
            FCallParams exps = (FCallParams) ctx.exps().accept(this);

            fcall = new FunctionCall(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), ctx.getChild(0).getText(), exps);
        }

        for (int i = 0; i < ctx.lvalue().size() && this.shouldVisitNextChild(ctx, this.defaultResult()); i++) {
            ParseTree childTree = ctx.lvalue(i);
            fcall.addLValue((LValue) this.aggregateResult(this.defaultResult(), childTree.accept(this)));
        }

        return fcall;
    }

    @Override
    public Node visitRExpCall(RExpCallContext ctx) {
        // ----- Regra
        // exp: rexp      # RExpCall
        return super.visitRExpCall(ctx);
    }

    @Override
    public Node visitAndOperation(AndOperationContext ctx) {
        // ----- Regra
        // exp:<assoc=left> exp AND exp    # AndOperation
        Expression left = (Expression) ctx.getChild(0).accept(this);
        Expression right = (Expression) ctx.getChild(2).accept(this);

        return new And(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), left, right);
    }

    @Override
    public Node visitAExpCall(AExpCallContext ctx) {
        // ----- Regra
        // rexp: aexp      # AExpCall
        return super.visitAExpCall(ctx);
    }

    @Override
    public Node visitLessThan(LessThanContext ctx) {
        // ----- Regra
        // rexp: aexp LESS_THAN aexp   # LessThan
        Expression left = (Expression) ctx.getChild(0).accept(this);
        Expression right = (Expression) ctx.getChild(2).accept(this);

        return new LessThan(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), left, right);
    }

    @Override
    public Node visitEquality(EqualityContext ctx) {
        // ----- Regra
        // rexp: <assoc=left> rexp EQUALITY aexp    # Equality
        Expression left = (Expression) ctx.getChild(0).accept(this);
        Expression right = (Expression) ctx.getChild(2).accept(this);

        return new Equality(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), left, right);
    }

    @Override
    public Node visitDifference(DifferenceContext ctx) {
        // ----- Regra
        // rexp: <assoc=left> rexp DIFFERENCE aexp  # Difference
        Expression left = (Expression) ctx.getChild(0).accept(this);
        Expression right = (Expression) ctx.getChild(2).accept(this);

        return new Difference(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), left, right);
    }

    @Override
    public Node visitAdditionOperation(AdditionOperationContext ctx) {
        // ----- Regra
        // aexp: aexp PLUS mexp    # AdditionOperation
        Expression left = (Expression) ctx.getChild(0).accept(this);
        Expression right = (Expression) ctx.getChild(2).accept(this);

        return new Addition(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), left, right);
    }

    @Override
    public Node visitSubtractionOperation(SubtractionOperationContext ctx) {
        // ----- Regra
        // aexp: aexp MINUS mexp   # SubtractionOperation
        Expression left = (Expression) ctx.getChild(0).accept(this);
        Expression right = (Expression) ctx.getChild(2).accept(this);

        return new Subtraction(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), left, right);
    }

    @Override
    public Node visitMExpCall(MExpCallContext ctx) {
        // ----- Regra
        // aexp: mexp      # MExpCall
        return super.visitMExpCall(ctx);
    }

    @Override
    public Node visitDivisionOperation(DivisionOperationContext ctx) {
        // ----- Regra
        // mexp: <assoc=left> mexp SLASH sexp   # DivisionOperation
        Expression left = (Expression) ctx.getChild(0).accept(this);
        Expression right = (Expression) ctx.getChild(2).accept(this);

        return new Division(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), left, right);
    }

    @Override
    public Node visitSExpCall(SExpCallContext ctx) {
        // ----- Regra
        // mexp: sexp   # SExpCall
        return super.visitSExpCall(ctx);
    }

    @Override
    public Node visitMultiplicationOperation(MultiplicationOperationContext ctx) {
        // ----- Regra
        // mexp: <assoc=left> mexp TIMES sexp   # MultiplicationOperation
        Expression left = (Expression) ctx.getChild(0).accept(this);
        Expression right = (Expression) ctx.getChild(2).accept(this);

        return new Multiplication(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), left, right);
    }

    @Override
    public Node visitModularOperation(ModularOperationContext ctx) {
        // ----- Regra
        // mexp: <assoc=left> mexp PERCENT sexp # ModularOperation
        Expression left = (Expression) ctx.getChild(0).accept(this);
        Expression right = (Expression) ctx.getChild(2).accept(this);

        return new Modular(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), left, right);
    }

    @Override
    public Node visitNot(NotContext ctx) {
        // ----- Regra
        // sexp: <assoc=right> EXCLAMATION sexp # Not
        Expression exp = (Expression) ctx.getChild(1).accept(this);

        return new Not(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), exp);
    }

    @Override
    public Node visitMinus(MinusContext ctx) {
        // ----- Regra
        // sexp: <assoc=right> MINUS sexp   # Minus
        Expression exp = (Expression) ctx.getChild(1).accept(this);

        return new Minus(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), exp);
    }

    @Override
    public Node visitTrue(TrueContext ctx) {
        // ----- Regra
        // sexp: TRUE  # True
        return new BooleanValue(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
        Boolean.parseBoolean(ctx.getChild(0).getText()));
    }

    @Override
    public Node visitFalse(FalseContext ctx) {
        // ----- Regra
        // sexp: FALSE  # False
        return new BooleanValue(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
        Boolean.parseBoolean(ctx.getChild(0).getText()));
    }

    @Override
    public Node visitNull(NullContext ctx) {
        // ----- Regra
        // sexp: NULL  # Null
        return new Null(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());
    }

    @Override
    public Node visitIntegerNumber(IntegerNumberContext ctx) {
        // ----- Regra
        // sexp: INT   # IntegerNumber
        return new IntegerNumber(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), Integer.parseInt(ctx.getChild(0).getText()));
    }

    @Override
    public Node visitFloatNumber(FloatNumberContext ctx) {
        // ----- Regra
        // sexp: FLOAT   # FloatNumber
        return new FloatNumber(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), Float.parseFloat(ctx.FLOAT().getText()));
    }

    @Override
    public Node visitCharLitteral(CharLitteralContext ctx) {
        // ----- Regra
        // sexp: CHAR   # CharLitteral
        // Se atentar e lembrar que tem '\\n', '\\t'...
        // return new CharLitteral(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
                // ctx.CHAR().getText().charAt(1));
        return new CharLitteral(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
                ctx.CHAR().getText());
    }

    @Override
    public Node visitPExpCall(PExpCallContext ctx) {
        // ----- Regra
        // sexp: pexp   # PExpCall
        return super.visitPExpCall(ctx);
    }

    @Override
    public Node visitPexpIdentifier(PexpIdentifierContext ctx) {
        // ----- Regra
        // pexp: lvalue    # PexpIdentifier
        return super.visitPexpIdentifier(ctx);
    }

    @Override
    public Node visitExpParenthesis(ExpParenthesisContext ctx) {
        // ----- Regra
        // pexp: <assoc=left> OPEN_PARENT exp CLOSE_PARENT  # ExpParenthesis
        return (Expression) ctx.getChild(1).accept(this);
    }

    @Override
    public Node visitTypeInstanciate(TypeInstanciateContext ctx) {
        // ----- Regra
        // pexp: NEW type (OPEN_BRACKET exp CLOSE_BRACKET)?    # TypeInstanciate

        // se for um data, so coloca o nome dele
        if(ctx.type().accept(this) instanceof NameType){
            System.out.println(ctx.type().getText() + " --- 538");
            return new TypeInstanciate(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), ctx.type().getText());
        }
        // caso seja um new array, aceita a expressao   
        if (ctx.exp() != null){
            Expression exp = (Expression) ctx.exp().accept(this);
            Type type = (Type) ctx.type().accept(this);
            return new TypeInstanciate(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), exp, type);
        }
        else {  // caso nao seja um new array, só aceita o type mesmo
            Type type = (Type) ctx.type().accept(this);
            return new TypeInstanciate(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), type);
        }
    }

    @Override
    public Node visitFunctionReturn(FunctionReturnContext ctx) {
        // ----- Regra
        // pexp: ID OPEN_PARENT exps? CLOSE_PARENT OPEN_BRACKET exp CLOSE_BRACKET  # FunctionReturn // Como retorna 2 valores, logo precisa do funcao(parametros)[indice] Exemplo: fat(num−1)[0]
        String str = ctx.ID().getText();
        FCallParams fCallPar = (FCallParams) ctx.exps().accept(this);
        Expression exp = (Expression) ctx.exp().accept(this);
        return new FunctionReturn(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), str, fCallPar, exp);
    }

    @Override
    public Node visitArrayAccess(ArrayAccessContext ctx) {
        // ----- Regra
        // lvalue: <assoc=left> lvalue OPEN_BRACKET exp CLOSE_BRACKET # ArrayAccess
        LValue lVal = (LValue) ctx.getChild(0).accept(this);
        Expression exp = (Expression) ctx.getChild(2).accept(this);

        return new ArrayAccess(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), lVal, exp);
    }

    @Override
    public Node visitIdentifier(IdentifierContext ctx) {
        // ----- Regra
        // lvalue: ID      # Identifier
        return new Identifier(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
                ctx.getChild(0).getText());
    }

    @Override
    public Node visitNameType(NameTypeContext ctx) {
        // ----- Regra
        // lvalue: NAME_TYPE      # NameType
        return new Identifier(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
                ctx.getChild(0).getText());
    }

    @Override
    public Node visitDataAccess(DataAccessContext ctx) {
        // ----- Regra
        // lvalue: <assoc=left> lvalue DOT ID     # DataAccess
        
        LValue lVal = (LValue) ctx.lvalue().accept(this);
        String str = ctx.getChild(2).getText();
        String dataId = ctx.lvalue().getText();
        return new DataAccess(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), lVal, str, dataId);
    }

    @Override
    public Node visitFCallParams(FCallParamsContext ctx) {
        // ----- Regra
        // exps: exp (COMMA exp)*      # FCallParams
        FCallParams fcall = new FCallParams(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());
        List<Expression> exps = new ArrayList<>();
        // Captura as expressoes 'exp'
        for (int i = 0; i < ctx.exp().size(); i++) {
            exps.add((Expression) ctx.exp().get(i).accept(this));
        }
        fcall.setExps(exps);
        return fcall;
    }
}
