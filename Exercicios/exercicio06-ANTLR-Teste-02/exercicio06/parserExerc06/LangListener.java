// Generated from Lang.g4 by ANTLR 4.9.2

package parserExerc06;

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
	 * Enter a parse tree produced by {@link LangParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterStmt(LangParser.StmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitStmt(LangParser.StmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(LangParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(LangParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#factor}.
	 * @param ctx the parse tree
	 */
	void enterFactor(LangParser.FactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#factor}.
	 * @param ctx the parse tree
	 */
	void exitFactor(LangParser.FactorContext ctx);
}