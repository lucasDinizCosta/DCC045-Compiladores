// Generated from Lang.g4 by ANTLR 4.9.2

/********************************************************
* Trabalho de Teoria dos Compiladores(DCC045)(2021/1)   *
*            Linguagem Lang                             *
* Nome: Lucas Diniz da Costa -- Matricula: 201465524C   *
*                                                       *
*********************************************************/

package lang.parser;    

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link LangParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface LangVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code Program}
	 * labeled alternative in {@link LangParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(LangParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DataDeclaration}
	 * labeled alternative in {@link LangParser#data}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDataDeclaration(LangParser.DataDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code VarDeclaration}
	 * labeled alternative in {@link LangParser#decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDeclaration(LangParser.VarDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Function}
	 * labeled alternative in {@link LangParser#func}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction(LangParser.FunctionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParametersFunction}
	 * labeled alternative in {@link LangParser#params}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParametersFunction(LangParser.ParametersFunctionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BTypeCall}
	 * labeled alternative in {@link LangParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBTypeCall(LangParser.BTypeCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TypeDeclaration}
	 * labeled alternative in {@link LangParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeDeclaration(LangParser.TypeDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BTypeInt}
	 * labeled alternative in {@link LangParser#btype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBTypeInt(LangParser.BTypeIntContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BTypeChar}
	 * labeled alternative in {@link LangParser#btype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBTypeChar(LangParser.BTypeCharContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BTypeBool}
	 * labeled alternative in {@link LangParser#btype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBTypeBool(LangParser.BTypeBoolContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BTypeFloat}
	 * labeled alternative in {@link LangParser#btype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBTypeFloat(LangParser.BTypeFloatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BTypeNameType}
	 * labeled alternative in {@link LangParser#btype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBTypeNameType(LangParser.BTypeNameTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CommandsList}
	 * labeled alternative in {@link LangParser#cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCommandsList(LangParser.CommandsListContext ctx);
	/**
	 * Visit a parse tree produced by the {@code If}
	 * labeled alternative in {@link LangParser#cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf(LangParser.IfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IfElse}
	 * labeled alternative in {@link LangParser#cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfElse(LangParser.IfElseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Iterate}
	 * labeled alternative in {@link LangParser#cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIterate(LangParser.IterateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Read}
	 * labeled alternative in {@link LangParser#cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRead(LangParser.ReadContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Print}
	 * labeled alternative in {@link LangParser#cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrint(LangParser.PrintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Return}
	 * labeled alternative in {@link LangParser#cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturn(LangParser.ReturnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Attribution}
	 * labeled alternative in {@link LangParser#cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttribution(LangParser.AttributionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FunctionCall}
	 * labeled alternative in {@link LangParser#cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCall(LangParser.FunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code RExpCall}
	 * labeled alternative in {@link LangParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRExpCall(LangParser.RExpCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AndOperation}
	 * labeled alternative in {@link LangParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndOperation(LangParser.AndOperationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AExpCall}
	 * labeled alternative in {@link LangParser#rexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAExpCall(LangParser.AExpCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LessThan}
	 * labeled alternative in {@link LangParser#rexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLessThan(LangParser.LessThanContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Equality}
	 * labeled alternative in {@link LangParser#rexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEquality(LangParser.EqualityContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Difference}
	 * labeled alternative in {@link LangParser#rexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDifference(LangParser.DifferenceContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AdditionOperation}
	 * labeled alternative in {@link LangParser#aexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditionOperation(LangParser.AdditionOperationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SubtractionOperation}
	 * labeled alternative in {@link LangParser#aexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubtractionOperation(LangParser.SubtractionOperationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MExpCall}
	 * labeled alternative in {@link LangParser#aexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMExpCall(LangParser.MExpCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DivisionOperation}
	 * labeled alternative in {@link LangParser#mexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDivisionOperation(LangParser.DivisionOperationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SExpCall}
	 * labeled alternative in {@link LangParser#mexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSExpCall(LangParser.SExpCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MultiplicationOperation}
	 * labeled alternative in {@link LangParser#mexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicationOperation(LangParser.MultiplicationOperationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ModularOperation}
	 * labeled alternative in {@link LangParser#mexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModularOperation(LangParser.ModularOperationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Not}
	 * labeled alternative in {@link LangParser#sexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNot(LangParser.NotContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Minus}
	 * labeled alternative in {@link LangParser#sexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMinus(LangParser.MinusContext ctx);
	/**
	 * Visit a parse tree produced by the {@code True}
	 * labeled alternative in {@link LangParser#sexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrue(LangParser.TrueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code False}
	 * labeled alternative in {@link LangParser#sexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFalse(LangParser.FalseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Null}
	 * labeled alternative in {@link LangParser#sexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNull(LangParser.NullContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IntegerNumber}
	 * labeled alternative in {@link LangParser#sexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegerNumber(LangParser.IntegerNumberContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FloatNumber}
	 * labeled alternative in {@link LangParser#sexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFloatNumber(LangParser.FloatNumberContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CharLitteral}
	 * labeled alternative in {@link LangParser#sexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharLitteral(LangParser.CharLitteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PExpCall}
	 * labeled alternative in {@link LangParser#sexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPExpCall(LangParser.PExpCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PexpIdentifier}
	 * labeled alternative in {@link LangParser#pexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPexpIdentifier(LangParser.PexpIdentifierContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpParenthesis}
	 * labeled alternative in {@link LangParser#pexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpParenthesis(LangParser.ExpParenthesisContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TypeInstanciate}
	 * labeled alternative in {@link LangParser#pexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeInstanciate(LangParser.TypeInstanciateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FunctionReturn}
	 * labeled alternative in {@link LangParser#pexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionReturn(LangParser.FunctionReturnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ArrayAccess}
	 * labeled alternative in {@link LangParser#lvalue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayAccess(LangParser.ArrayAccessContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Identifier}
	 * labeled alternative in {@link LangParser#lvalue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(LangParser.IdentifierContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DataAccess}
	 * labeled alternative in {@link LangParser#lvalue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDataAccess(LangParser.DataAccessContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FCallParams}
	 * labeled alternative in {@link LangParser#exps}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFCallParams(LangParser.FCallParamsContext ctx);
}