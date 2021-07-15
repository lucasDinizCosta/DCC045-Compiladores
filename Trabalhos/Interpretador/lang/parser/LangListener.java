// Generated from Lang.g4 by ANTLR 4.9.2

/********************************************************
* Trabalho de Teoria dos Compiladores(DCC045)(2021/1)   *
*                                                       *
* Projeto do Analisador Sintático para a Linguagem Lang *
* Nome: Lucas Diniz da Costa -- Matricula: 201465524C   *
*                                                       *
*********************************************************/

package lang.parser;    

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link LangParser}.
 */
public interface LangListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link LangParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(LangParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(LangParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#data}.
	 * @param ctx the parse tree
	 */
	void enterData(LangParser.DataContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#data}.
	 * @param ctx the parse tree
	 */
	void exitData(LangParser.DataContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#decl}.
	 * @param ctx the parse tree
	 */
	void enterDecl(LangParser.DeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#decl}.
	 * @param ctx the parse tree
	 */
	void exitDecl(LangParser.DeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#func}.
	 * @param ctx the parse tree
	 */
	void enterFunc(LangParser.FuncContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#func}.
	 * @param ctx the parse tree
	 */
	void exitFunc(LangParser.FuncContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#params}.
	 * @param ctx the parse tree
	 */
	void enterParams(LangParser.ParamsContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#params}.
	 * @param ctx the parse tree
	 */
	void exitParams(LangParser.ParamsContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(LangParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(LangParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#btype}.
	 * @param ctx the parse tree
	 */
	void enterBtype(LangParser.BtypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#btype}.
	 * @param ctx the parse tree
	 */
	void exitBtype(LangParser.BtypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#cmd}.
	 * @param ctx the parse tree
	 */
	void enterCmd(LangParser.CmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#cmd}.
	 * @param ctx the parse tree
	 */
	void exitCmd(LangParser.CmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExp(LangParser.ExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExp(LangParser.ExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#rexp}.
	 * @param ctx the parse tree
	 */
	void enterRexp(LangParser.RexpContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#rexp}.
	 * @param ctx the parse tree
	 */
	void exitRexp(LangParser.RexpContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#aexp}.
	 * @param ctx the parse tree
	 */
	void enterAexp(LangParser.AexpContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#aexp}.
	 * @param ctx the parse tree
	 */
	void exitAexp(LangParser.AexpContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#mexp}.
	 * @param ctx the parse tree
	 */
	void enterMexp(LangParser.MexpContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#mexp}.
	 * @param ctx the parse tree
	 */
	void exitMexp(LangParser.MexpContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#sexp}.
	 * @param ctx the parse tree
	 */
	void enterSexp(LangParser.SexpContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#sexp}.
	 * @param ctx the parse tree
	 */
	void exitSexp(LangParser.SexpContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#pexp}.
	 * @param ctx the parse tree
	 */
	void enterPexp(LangParser.PexpContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#pexp}.
	 * @param ctx the parse tree
	 */
	void exitPexp(LangParser.PexpContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#lvalue}.
	 * @param ctx the parse tree
	 */
	void enterLvalue(LangParser.LvalueContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#lvalue}.
	 * @param ctx the parse tree
	 */
	void exitLvalue(LangParser.LvalueContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#exps}.
	 * @param ctx the parse tree
	 */
	void enterExps(LangParser.ExpsContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#exps}.
	 * @param ctx the parse tree
	 */
	void exitExps(LangParser.ExpsContext ctx);
}