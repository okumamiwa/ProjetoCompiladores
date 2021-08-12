// Generated from ProjLang.g4 by ANTLR 4.9.2
package br.com.projetocompiladores.parser;

	import br.com.projetocompiladores.datastructures.ProjSymbol;
	import br.com.projetocompiladores.datastructures.ProjVariable;
	import br.com.projetocompiladores.datastructures.ProjSymbolTable;
	import br.com.projetocompiladores.exceptions.ProjSemanticException;
	import br.com.projetocompiladores.ast.ProjProgram;
	import br.com.projetocompiladores.ast.AbstractCommand;
	import br.com.projetocompiladores.ast.CommandLeitura;
	import br.com.projetocompiladores.ast.CommandEscrita;
	import br.com.projetocompiladores.ast.CommandAtribuicao;
	import br.com.projetocompiladores.ast.CommandDecisao;
	import br.com.projetocompiladores.ast.CommandRepeticao;
	import br.com.projetocompiladores.ast.CommandFazerAte;
	import java.util.ArrayList;
	import java.util.Stack;

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
	 * Enter a parse tree produced by {@link ProjLangParser#decl}.
	 * @param ctx the parse tree
	 */
	void enterDecl(ProjLangParser.DeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProjLangParser#decl}.
	 * @param ctx the parse tree
	 */
	void exitDecl(ProjLangParser.DeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProjLangParser#declaravar}.
	 * @param ctx the parse tree
	 */
	void enterDeclaravar(ProjLangParser.DeclaravarContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProjLangParser#declaravar}.
	 * @param ctx the parse tree
	 */
	void exitDeclaravar(ProjLangParser.DeclaravarContext ctx);
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
	 * Enter a parse tree produced by {@link ProjLangParser#tipo}.
	 * @param ctx the parse tree
	 */
	void enterTipo(ProjLangParser.TipoContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProjLangParser#tipo}.
	 * @param ctx the parse tree
	 */
	void exitTipo(ProjLangParser.TipoContext ctx);
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
	 * Enter a parse tree produced by {@link ProjLangParser#cmdselecao}.
	 * @param ctx the parse tree
	 */
	void enterCmdselecao(ProjLangParser.CmdselecaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProjLangParser#cmdselecao}.
	 * @param ctx the parse tree
	 */
	void exitCmdselecao(ProjLangParser.CmdselecaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProjLangParser#cmdrepeticao}.
	 * @param ctx the parse tree
	 */
	void enterCmdrepeticao(ProjLangParser.CmdrepeticaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProjLangParser#cmdrepeticao}.
	 * @param ctx the parse tree
	 */
	void exitCmdrepeticao(ProjLangParser.CmdrepeticaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProjLangParser#cmdfazerate}.
	 * @param ctx the parse tree
	 */
	void enterCmdfazerate(ProjLangParser.CmdfazerateContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProjLangParser#cmdfazerate}.
	 * @param ctx the parse tree
	 */
	void exitCmdfazerate(ProjLangParser.CmdfazerateContext ctx);
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
	 * Enter a parse tree produced by {@link ProjLangParser#termcomp}.
	 * @param ctx the parse tree
	 */
	void enterTermcomp(ProjLangParser.TermcompContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProjLangParser#termcomp}.
	 * @param ctx the parse tree
	 */
	void exitTermcomp(ProjLangParser.TermcompContext ctx);
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