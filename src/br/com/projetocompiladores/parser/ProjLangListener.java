// Generated from ProjLang.g4 by ANTLR 4.9.2
package br.com.projetocompiladores.parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ProjLangParser}.
 */
public interface ProjLangListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ProjLangParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(ProjLangParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProjLangParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(ProjLangParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProjLangParser#bloco}.
	 * @param ctx the parse tree
	 */
	void enterBloco(ProjLangParser.BlocoContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProjLangParser#bloco}.
	 * @param ctx the parse tree
	 */
	void exitBloco(ProjLangParser.BlocoContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProjLangParser#cmd}.
	 * @param ctx the parse tree
	 */
	void enterCmd(ProjLangParser.CmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProjLangParser#cmd}.
	 * @param ctx the parse tree
	 */
	void exitCmd(ProjLangParser.CmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProjLangParser#cmdleitura}.
	 * @param ctx the parse tree
	 */
	void enterCmdleitura(ProjLangParser.CmdleituraContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProjLangParser#cmdleitura}.
	 * @param ctx the parse tree
	 */
	void exitCmdleitura(ProjLangParser.CmdleituraContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProjLangParser#cmdescrita}.
	 * @param ctx the parse tree
	 */
	void enterCmdescrita(ProjLangParser.CmdescritaContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProjLangParser#cmdescrita}.
	 * @param ctx the parse tree
	 */
	void exitCmdescrita(ProjLangParser.CmdescritaContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProjLangParser#cmdatrib}.
	 * @param ctx the parse tree
	 */
	void enterCmdatrib(ProjLangParser.CmdatribContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProjLangParser#cmdatrib}.
	 * @param ctx the parse tree
	 */
	void exitCmdatrib(ProjLangParser.CmdatribContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProjLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(ProjLangParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProjLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(ProjLangParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProjLangParser#termo}.
	 * @param ctx the parse tree
	 */
	void enterTermo(ProjLangParser.TermoContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProjLangParser#termo}.
	 * @param ctx the parse tree
	 */
	void exitTermo(ProjLangParser.TermoContext ctx);
}