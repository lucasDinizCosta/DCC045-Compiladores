package lang.interpreter;

import lang.ast.*;
import lang.parser.LangParser.*;

/**
 * Classe auxiliar que pega o visitor base do ANTLR e implementa os métodos com
 * base nos nós criados da AST
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

        // FAZER
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

        String nametype = ctx.getChild(1).getText(); // Captura o nome de tipo
        List<Declaration> decls = new ArrayList<Declaration>(); // Declarações

        // Percorre o número de declarações
        for (int i = 0; i < ctx.decl().size(); i++) {
            Declaration declarationAccept = ctx.decl().get(i).accept(this);
            decls.add(declarationAccept); // Listagem de declarações do data
        }

        return new Data(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), nametype, decls);
    }

    @Override
    public Node visitVarDeclaration(VarDeclarationContext ctx) {
        // ----- Regra
        // decl: ID DOUBLE_COLON type SEMI

        return new Declaration(
            ctx.getStart().getLine(), 
            ctx.getStart().getCharPositionInLine(),
            ctx.getChild(0).getText(),      // Nome da variavel
            (Type) ctx.type().accept(this)  // Tipo da variavel: Int, Char, Bool,...
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
        
        return super.visitBTypeCall(ctx);
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
        return visitChildren(ctx);
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

    @Override
    public Node visitBTypeID(BTypeIDContext ctx) {
        // ----- Regra
        // btype: ID     # BTypeID
        String id = ctx.getChild(0).getText();    // Captura o nome do tipo
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        return new ID(line, column, id);
    }

    @Override
    public Node visitCommandsList(CommandsListContext ctx) {
        // ----- Regra
        // cmd: OPEN_BRACES cmd* CLOSE_BRACES      # CommandsList
        return visitChildren(ctx);
    }

    @Override
    public Node visitIf(IfContext ctx) {
        // ----- Regra
        // cmd: IF OPEN_PARENT exp CLOSE_PARENT cmd   # If
        return visitChildren(ctx);
    }

    @Override
    public Node visitIfElse(IfElseContext ctx) {
        // ----- Regra
        // cmd: IF OPEN_PARENT exp CLOSE_PARENT cmd ELSE cmd  # IfElse
        return visitChildren(ctx);
    }

    @Override
    public Node visitIterate(IterateContext ctx) {
        // ----- Regra
        // cmd: ITERATE OPEN_PARENT exp CLOSE_PARENT cmd  # Iterate
        return visitChildren(ctx);
    }

    @Override
    public Node visitRead(ReadContext ctx) {
        // ----- Regra
        // cmd: READ lvalue SEMI  # Read
        
        String readName = ctx.getChild(0).getText();    // Captura o nome do tipo
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        return new Read(line, column, readName);
    }

    @Override
    public Node visitPrint(PrintContext ctx) {
        // ----- Regra
        // cmd: PRINT exp SEMI    # Print
        return visitChildren(ctx);
    }

    @Override
    public Node visitReturn(ReturnContext ctx) {
        // ----- Regra
        // cmd: RETURN exp (COMMA exp)* SEMI  # Return
        return visitChildren(ctx);
    }

    @Override
    public Node visitAttribution(AttributionContext ctx) {
        // ----- Regra
        // cmd: lvalue EQUALS exp SEMI    # Attribution
        return visitChildren(ctx);
    }

    @Override
    public Node visitFunctionCall(FunctionCallContext ctx) {
        // ----- Regra
        // cmd: ID OPEN_PARENT exps? CLOSE_PARENT (LESS_THAN lvalue (COMMA lvalue)* GREATER_THAN)? SEMI   # FunctionCall
        return visitChildren(ctx);
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
        return visitChildren(ctx);
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
        return visitChildren(ctx);
    }

    @Override
    public Node visitEquality(EqualityContext ctx) {
        // ----- Regra
        // rexp: <assoc=left> rexp EQUALITY aexp    # Equality
        return visitChildren(ctx);
    }

    @Override
    public Node visitDifference(DifferenceContext ctx) {
        // ----- Regra
        // rexp: <assoc=left> rexp DIFFERENCE aexp  # Difference
        return visitChildren(ctx);
    }

    @Override
    public Node visitAdditionOperation(AdditionOperationContext ctx) {
        // ----- Regra
        // aexp: aexp PLUS mexp    # AdditionOperation
        return visitChildren(ctx);
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
        return visitChildren(ctx);
    }

    @Override
    public Node visitModularOperation(ModularOperationContext ctx) {
        // ----- Regra
        // mexp: <assoc=left> mexp PERCENT sexp # ModularOperation
        return visitChildren(ctx);
    }

    @Override
    public Node visitNot(NotContext ctx) {
        // ----- Regra
        // sexp: <assoc=right> EXCLAMATION sexp # Not
        return visitChildren(ctx);
    }

    @Override
    public Node visitMinus(MinusContext ctx) {
        // ----- Regra
        // sexp: <assoc=right> MINUS sexp   # Minus
        return visitChildren(ctx);
    }

    @Override
    public Node visitTrue(TrueContext ctx) {
        // ----- Regra
        // sexp: TRUE  # True
        return new BooleanValue(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
        BooleanValue.parseBoolean(ctx.getChild(0).getText()));
    }

    @Override
    public Node visitFalse(FalseContext ctx) {
        // ----- Regra
        // sexp: FALSE  # False
        return new BooleanValue(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
        BooleanValue.parseBoolean(ctx.getChild(0).getText()));
    }

    @Override
    public Node visitNull(NullContext ctx) {
        // ----- Regra
        // sexp: NULL  # Null
        return visitChildren(ctx);
    }

    @Override
    public Node visitIntegerNumber(IntegerContext ctx) {
        // ----- Regra
        // sexp: INT   # IntegerNumber
        return new IntegerNumber(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), Integer.parseInt(ctx.getChild(0).getText()));
    }

    @Override
    public Node visitFloatNumber(FloatContext ctx) {
        // ----- Regra
        // sexp: FLOAT   # FloatNumber
        return new FloatNumber(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), Float.parseFloat(ctx.getChild(0).getText()));
    }

    @Override
    public Node visitCharLitteral(CharacterContext ctx) {
        // ----- Regra
        // sexp: CHAR   # CharLitteral
        // Se atentar e lembrar que tem '\\n', '\\t'...
        return new CharLitteral(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
                ctx.CHAR().getText().charAt(1));
    }

    @Override
    public Node visitPExpCall(PExpCallContext ctx) {
        // ----- Regra
        // sexp: pexp   # PExpCall
        return super.visitPExpCall(ctx);
    }

    @Override
    public Node visitLitteralValueCall(LitteralValueCallContext ctx) {
        // ----- Regra
        // pexp: lvalue    # LitteralValueCall
        return super.visitLitteralValueCall(ctx);
    }

    @Override
    public Node visitExpParenthesis(ExpParenthesisContext ctx) {
        // ----- Regra
        // pexp: <assoc=left> OPEN_PARENT exp CLOSE_PARENT  # ExpParenthesis
        
        return visitChildren(ctx);
    }

    @Override
    public Node visitTypeInstanciate(TypeInstanciateContext ctx) {
        // ----- Regra
        // pexp: NEW type (OPEN_BRACKET exp CLOSE_BRACKET)?    # TypeInstanciate
        return visitChildren(ctx);
    }

    @Override
    public Node visitFunctionReturn(FunctionReturnContext ctx) {
        // ----- Regra
        // pexp: ID OPEN_PARENT exps? CLOSE_PARENT OPEN_BRACKET exp CLOSE_BRACKET  # FunctionReturn // Como retorna 2 valores, logo precisa do funcao(parametros)[indice] Exemplo: fat(num−1)[0]
        return visitChildren(ctx);
    }

    @Override
    public Node visitArrayAccess(ArrayAccessContext ctx) {
        // ----- Regra
        // lvalue: <assoc=left> lvalue OPEN_BRACKET exp CLOSE_BRACKET # ArrayAccess
        return visitChildren(ctx);
    }

    @Override
    public Node visitIdentifier(IdentifierContext ctx) {
        // ----- Regra
        // lvalue: ID      # Identifier
        return visitChildren(ctx);
    }

    @Override
    public Node visitDataAccess(DataAccessContext ctx) {
        // ----- Regra
        // lvalue: <assoc=left> lvalue DOT ID     # DataAccess
        return visitChildren(ctx);
    }

    @Override
    public Node visitFCallParams(FCallParamsContext ctx) {
        // ----- Regra
        // exps: exp (COMMA exp)*      # FCallParams
        return visitChildren(ctx);
    }
}
