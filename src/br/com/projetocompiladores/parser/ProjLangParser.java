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
	import br.com.projetocompiladores.ast.CommandRepeticaoFor;
	import br.com.projetocompiladores.ast.CommandFazerAte;
	import br.com.projetocompiladores.ast.CommandDecisaoTernario;
	import java.util.ArrayList;
	import java.util.Stack;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ProjLangParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, AP=14, FP=15, SC=16, OP=17, ATTR=18, 
		VIR=19, ACH=20, FCH=21, OPREL=22, ID=23, NUMBER=24, TEXT=25, WS=26, COMMENT=27, 
		LCOMMENT=28;
	public static final int
		RULE_prog = 0, RULE_decl = 1, RULE_declaravar = 2, RULE_bloco = 3, RULE_tipo = 4, 
		RULE_cmd = 5, RULE_cmdleitura = 6, RULE_cmdescrita = 7, RULE_cmdatrib = 8, 
		RULE_cmdselecao = 9, RULE_cmdselecaoter = 10, RULE_cmdrepeticao = 11, 
		RULE_cmdrepeticaoPor = 12, RULE_cmdfazerate = 13, RULE_expr = 14, RULE_termcomp = 15, 
		RULE_termo = 16;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "decl", "declaravar", "bloco", "tipo", "cmd", "cmdleitura", "cmdescrita", 
			"cmdatrib", "cmdselecao", "cmdselecaoter", "cmdrepeticao", "cmdrepeticaoPor", 
			"cmdfazerate", "expr", "termcomp", "termo"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'programa'", "'fimprog;'", "'numero'", "'texto'", "'leia'", "'escreva'", 
			"'se'", "'senao'", "'?'", "':'", "'enquanto'", "'repetir'", "'fazer'", 
			"'('", "')'", "';'", null, "'='", "','", "'{'", "'}'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, "AP", "FP", "SC", "OP", "ATTR", "VIR", "ACH", "FCH", "OPREL", 
			"ID", "NUMBER", "TEXT", "WS", "COMMENT", "LCOMMENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "ProjLang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


		private int _tipo;
		private String _varName;
		private String _varValue;
		private ProjSymbolTable symbolTable = new ProjSymbolTable();
		private ProjSymbol symbol;
		private ProjProgram program = new ProjProgram();
		private ArrayList<AbstractCommand> curThread;
		private Stack<ArrayList<AbstractCommand>> stack = new Stack<ArrayList<AbstractCommand>>();
		private String _readID;
		private String _writeID;
		private String _exprID;
		private String _exprContent;
		private String _exprDecision;
		private String _left;
		private String _right;
		private String _actionID;
		private String _declID;
		private ArrayList<AbstractCommand> listaTrue;
		private ArrayList<AbstractCommand> listaFalse;
		private ArrayList<AbstractCommand> listaCmd;
		private ArrayList<String> exprTypeList = new ArrayList<String>();
		
		public void verificaID(String id) {
			if (!symbolTable.exists(id)){
				throw new ProjSemanticException("Symbol " + id + " not declared");
			}
		}
		
		public void exibeComandos(){
			for (AbstractCommand c: program.getComandos()){
				System.out.println("-- " + c);
			}
		}
		
		public void generateCode(){
			program.generateTarget();
		}
		
		public String getTypeByID(String id) {
			return symbolTable.getTypeByID(id);
		}
		
		public void checkType(String left, String id, String expression){
			for(String t : exprTypeList)  {
				if(left != t) {
					throw new ProjSemanticException("Incompatible types " + left + " and " + t + " in " + id + " = " + expression);
				}
			}
		}
		
		public String verifyAndGetType( String expression) {
			String t = exprTypeList.get(0);
			for (String tipo: exprTypeList) {
				if (tipo != t) {
					throw new ProjSemanticException("Incompatible types in expression: " + expression);
				}
			}
			return t;
		}
		
		public ArrayList<String> warnings() {
			ArrayList<String> l = new ArrayList<String>();
			for(ProjSymbol s: symbolTable.getNonUsed()) {
				l.add("Variável <" + s.getName() + "> declarada, mas nao usada");
			}
			return l;
		}
		
		public void exibeWarnings(){
			ArrayList<String> warnings = warnings();
			if(warnings.size() > 0) {
				System.out.println("*".repeat(45) + " WARNINGS " + "*".repeat(45));
				for(String w : warnings) {
					System.out.println("** " + w);
				}
				System.out.println("*".repeat(100) + "\n");
			}
		}

	public ProjLangParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgContext extends ParserRuleContext {
		public DeclContext decl() {
			return getRuleContext(DeclContext.class,0);
		}
		public BlocoContext bloco() {
			return getRuleContext(BlocoContext.class,0);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProjLangListener ) ((ProjLangListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProjLangListener ) ((ProjLangListener)listener).exitProg(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(34);
			match(T__0);
			setState(35);
			decl();
			setState(36);
			bloco();
			setState(37);
			match(T__1);
			  	
					  		program.setVarTable(symbolTable);
			           	  	program.setComandos(stack.pop());
			        	
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclContext extends ParserRuleContext {
		public List<DeclaravarContext> declaravar() {
			return getRuleContexts(DeclaravarContext.class);
		}
		public DeclaravarContext declaravar(int i) {
			return getRuleContext(DeclaravarContext.class,i);
		}
		public DeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProjLangListener ) ((ProjLangListener)listener).enterDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProjLangListener ) ((ProjLangListener)listener).exitDecl(this);
		}
	}

	public final DeclContext decl() throws RecognitionException {
		DeclContext _localctx = new DeclContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_decl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(41); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(40);
				declaravar();
				}
				}
				setState(43); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__2 || _la==T__3 );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclaravarContext extends ParserRuleContext {
		public TipoContext tipo() {
			return getRuleContext(TipoContext.class,0);
		}
		public List<TerminalNode> ID() { return getTokens(ProjLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(ProjLangParser.ID, i);
		}
		public TerminalNode SC() { return getToken(ProjLangParser.SC, 0); }
		public List<TerminalNode> VIR() { return getTokens(ProjLangParser.VIR); }
		public TerminalNode VIR(int i) {
			return getToken(ProjLangParser.VIR, i);
		}
		public DeclaravarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaravar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProjLangListener ) ((ProjLangListener)listener).enterDeclaravar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProjLangListener ) ((ProjLangListener)listener).exitDeclaravar(this);
		}
	}

	public final DeclaravarContext declaravar() throws RecognitionException {
		DeclaravarContext _localctx = new DeclaravarContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_declaravar);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(45);
			tipo();
			setState(46);
			match(ID);

				                  _varName = _input.LT(-1).getText();
				                  _varValue = null;
				                  symbol = new ProjVariable(_varName, _tipo, _varValue);
				                  if (!symbolTable.exists(_varName)){
				                     symbolTable.add(symbol);	
				                  }
				                  else{
				                  	 throw new ProjSemanticException("Symbol " +_varName + " already declared");
				                  }
			                  	
			setState(53);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VIR) {
				{
				{
				setState(48);
				match(VIR);
				setState(49);
				match(ID);

					                  _varName = _input.LT(-1).getText();
					                  _varValue = null;
					                  symbol = new ProjVariable(_varName, _tipo, _varValue);
					                  if (!symbolTable.exists(_varName)){
					                     symbolTable.add(symbol);	
					                  }
					                  else{
					                  	 throw new ProjSemanticException("Symbol " +_varName + " already declared");
					                  }
				                    
				}
				}
				setState(55);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(56);
			match(SC);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlocoContext extends ParserRuleContext {
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public BlocoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bloco; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProjLangListener ) ((ProjLangListener)listener).enterBloco(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProjLangListener ) ((ProjLangListener)listener).exitBloco(this);
		}
	}

	public final BlocoContext bloco() throws RecognitionException {
		BlocoContext _localctx = new BlocoContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_bloco);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			 curThread = new ArrayList<AbstractCommand>(); 
				        stack.push(curThread);  
			          
			setState(60); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(59);
				cmd();
				}
				}
				setState(62); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << ID))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TipoContext extends ParserRuleContext {
		public TipoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tipo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProjLangListener ) ((ProjLangListener)listener).enterTipo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProjLangListener ) ((ProjLangListener)listener).exitTipo(this);
		}
	}

	public final TipoContext tipo() throws RecognitionException {
		TipoContext _localctx = new TipoContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_tipo);
		try {
			setState(68);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				enterOuterAlt(_localctx, 1);
				{
				setState(64);
				match(T__2);
				_tipo = ProjVariable.NUMBER;
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 2);
				{
				setState(66);
				match(T__3);
				_tipo = ProjVariable.TEXT;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdContext extends ParserRuleContext {
		public CmdleituraContext cmdleitura() {
			return getRuleContext(CmdleituraContext.class,0);
		}
		public CmdescritaContext cmdescrita() {
			return getRuleContext(CmdescritaContext.class,0);
		}
		public CmdatribContext cmdatrib() {
			return getRuleContext(CmdatribContext.class,0);
		}
		public CmdselecaoContext cmdselecao() {
			return getRuleContext(CmdselecaoContext.class,0);
		}
		public CmdrepeticaoContext cmdrepeticao() {
			return getRuleContext(CmdrepeticaoContext.class,0);
		}
		public CmdrepeticaoPorContext cmdrepeticaoPor() {
			return getRuleContext(CmdrepeticaoPorContext.class,0);
		}
		public CmdfazerateContext cmdfazerate() {
			return getRuleContext(CmdfazerateContext.class,0);
		}
		public CmdselecaoterContext cmdselecaoter() {
			return getRuleContext(CmdselecaoterContext.class,0);
		}
		public CmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProjLangListener ) ((ProjLangListener)listener).enterCmd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProjLangListener ) ((ProjLangListener)listener).exitCmd(this);
		}
	}

	public final CmdContext cmd() throws RecognitionException {
		CmdContext _localctx = new CmdContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_cmd);
		try {
			setState(78);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(70);
				cmdleitura();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(71);
				cmdescrita();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(72);
				cmdatrib();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(73);
				cmdselecao();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(74);
				cmdrepeticao();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(75);
				cmdrepeticaoPor();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(76);
				cmdfazerate();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(77);
				cmdselecaoter();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdleituraContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(ProjLangParser.AP, 0); }
		public TerminalNode ID() { return getToken(ProjLangParser.ID, 0); }
		public TerminalNode FP() { return getToken(ProjLangParser.FP, 0); }
		public TerminalNode SC() { return getToken(ProjLangParser.SC, 0); }
		public CmdleituraContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdleitura; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProjLangListener ) ((ProjLangListener)listener).enterCmdleitura(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProjLangListener ) ((ProjLangListener)listener).exitCmdleitura(this);
		}
	}

	public final CmdleituraContext cmdleitura() throws RecognitionException {
		CmdleituraContext _localctx = new CmdleituraContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_cmdleitura);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			match(T__4);
			setState(81);
			match(AP);
			setState(82);
			match(ID);
			 verificaID(_input.LT(-1).getText());
									     _readID = _input.LT(-1).getText();
			                 	  	
			setState(84);
			match(FP);
			setState(85);
			match(SC);

			              		ProjVariable var = (ProjVariable)symbolTable.get(_readID);
			              		CommandLeitura cmd = new CommandLeitura(_readID, var);
			              		stack.peek().add(cmd);
			              	
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdescritaContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(ProjLangParser.AP, 0); }
		public TerminalNode ID() { return getToken(ProjLangParser.ID, 0); }
		public TerminalNode FP() { return getToken(ProjLangParser.FP, 0); }
		public TerminalNode SC() { return getToken(ProjLangParser.SC, 0); }
		public CmdescritaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdescrita; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProjLangListener ) ((ProjLangListener)listener).enterCmdescrita(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProjLangListener ) ((ProjLangListener)listener).exitCmdescrita(this);
		}
	}

	public final CmdescritaContext cmdescrita() throws RecognitionException {
		CmdescritaContext _localctx = new CmdescritaContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_cmdescrita);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
			match(T__5);
			setState(89);
			match(AP);
			setState(90);
			match(ID);
			 verificaID(_input.LT(-1).getText());
										 _writeID = _input.LT(-1).getText();
			                 	  	
			setState(92);
			match(FP);
			setState(93);
			match(SC);

			               	  	CommandEscrita cmd = new CommandEscrita(_writeID);
			               	  	stack.peek().add(cmd);
			               	
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdatribContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(ProjLangParser.ID, 0); }
		public TerminalNode ATTR() { return getToken(ProjLangParser.ATTR, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode SC() { return getToken(ProjLangParser.SC, 0); }
		public CmdatribContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdatrib; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProjLangListener ) ((ProjLangListener)listener).enterCmdatrib(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProjLangListener ) ((ProjLangListener)listener).exitCmdatrib(this);
		}
	}

	public final CmdatribContext cmdatrib() throws RecognitionException {
		CmdatribContext _localctx = new CmdatribContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_cmdatrib);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			match(ID);
			 verificaID(_input.LT(-1).getText());
			                     _exprID = _input.LT(-1).getText();
			                     _left	 = getTypeByID(_exprID);
			                     exprTypeList = new ArrayList<String>();
			     	  		
			setState(98);
			match(ATTR);
			 _exprContent = ""; 
			setState(100);
			expr();
			setState(101);
			match(SC);

			               	 	CommandAtribuicao cmd = new CommandAtribuicao(_exprID, _exprContent);
			               	 	checkType(_left, _exprID, _exprContent);
			               	 	stack.peek().add(cmd);
			               	
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdselecaoContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(ProjLangParser.AP, 0); }
		public TerminalNode ID() { return getToken(ProjLangParser.ID, 0); }
		public TerminalNode OPREL() { return getToken(ProjLangParser.OPREL, 0); }
		public TermcompContext termcomp() {
			return getRuleContext(TermcompContext.class,0);
		}
		public TerminalNode FP() { return getToken(ProjLangParser.FP, 0); }
		public List<TerminalNode> ACH() { return getTokens(ProjLangParser.ACH); }
		public TerminalNode ACH(int i) {
			return getToken(ProjLangParser.ACH, i);
		}
		public List<TerminalNode> FCH() { return getTokens(ProjLangParser.FCH); }
		public TerminalNode FCH(int i) {
			return getToken(ProjLangParser.FCH, i);
		}
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public CmdselecaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdselecao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProjLangListener ) ((ProjLangListener)listener).enterCmdselecao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProjLangListener ) ((ProjLangListener)listener).exitCmdselecao(this);
		}
	}

	public final CmdselecaoContext cmdselecao() throws RecognitionException {
		CmdselecaoContext _localctx = new CmdselecaoContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_cmdselecao);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			match(T__6);
			setState(105);
			match(AP);
			 exprTypeList = new ArrayList<String>(); 
			setState(107);
			match(ID);
			 _exprDecision = _input.LT(-1).getText(); 
										verificaID(_exprDecision);
										_left	 = getTypeByID(_exprDecision);
								
			setState(109);
			match(OPREL);
			 _exprDecision += _input.LT(-1).getText(); 
			setState(111);
			termcomp();
			 	String id = _input.LT(-1).getText();
											_exprDecision += id;
											_right = verifyAndGetType(id);
								
			setState(113);
			match(FP);
			 	if(_left != _right) {
											throw new ProjSemanticException("Incompatible types " + _left + " and " + _right + " in " + _exprDecision);
										}
								
			setState(115);
			match(ACH);
			 	curThread = new ArrayList<AbstractCommand>(); 
			                      		stack.push(curThread);
			                    
			setState(118); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(117);
				cmd();
				}
				}
				setState(120); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << ID))) != 0) );
			setState(122);
			match(FCH);
			 listaTrue = stack.pop();	
			                    
			setState(135);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__7) {
				{
				setState(124);
				match(T__7);
				setState(125);
				match(ACH);

				                   	 	curThread = new ArrayList<AbstractCommand>();
				                   	 	stack.push(curThread);
				                   	
				{
				setState(128); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(127);
					cmd();
					}
					}
					setState(130); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << ID))) != 0) );
				}
				setState(132);
				match(FCH);

				                   		listaFalse = stack.pop();
				                   		CommandDecisao cmd = new CommandDecisao(_exprDecision, listaTrue, listaFalse);
				                   		stack.peek().add(cmd);
				                   	
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdselecaoterContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(ProjLangParser.ID, 0); }
		public TerminalNode OPREL() { return getToken(ProjLangParser.OPREL, 0); }
		public TermcompContext termcomp() {
			return getRuleContext(TermcompContext.class,0);
		}
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public CmdselecaoterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdselecaoter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProjLangListener ) ((ProjLangListener)listener).enterCmdselecaoter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProjLangListener ) ((ProjLangListener)listener).exitCmdselecaoter(this);
		}
	}

	public final CmdselecaoterContext cmdselecaoter() throws RecognitionException {
		CmdselecaoterContext _localctx = new CmdselecaoterContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_cmdselecaoter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(137);
			match(ID);
			 	_exprDecision = _input.LT(-1).getText(); 
										verificaID(_exprDecision);
										_left	 = getTypeByID(_exprDecision);
								
			setState(139);
			match(OPREL);
				 _exprDecision += _input.LT(-1).getText(); 
			setState(141);
			termcomp();
			 	String id = _input.LT(-1).getText();
											_exprDecision += id;
											_right = verifyAndGetType(id);
								
			setState(143);
			match(T__8);
				curThread = new ArrayList<AbstractCommand>(); 
			                      	stack.push(curThread);
			                    
			{
			setState(145);
			cmd();
			}
				listaTrue = stack.pop();	
			                    
			setState(147);
			match(T__9);
				curThread = new ArrayList<AbstractCommand>(); 
			                      	stack.push(curThread);
			                    
			{
			setState(149);
			cmd();
			}

			                    	listaFalse = stack.pop();
			                   		CommandDecisaoTernario cmd = new CommandDecisaoTernario(_exprDecision, listaTrue, listaFalse);
			                   		stack.peek().add(cmd);
			                    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdrepeticaoContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(ProjLangParser.AP, 0); }
		public TerminalNode ID() { return getToken(ProjLangParser.ID, 0); }
		public TerminalNode OPREL() { return getToken(ProjLangParser.OPREL, 0); }
		public TermcompContext termcomp() {
			return getRuleContext(TermcompContext.class,0);
		}
		public TerminalNode FP() { return getToken(ProjLangParser.FP, 0); }
		public TerminalNode ACH() { return getToken(ProjLangParser.ACH, 0); }
		public TerminalNode FCH() { return getToken(ProjLangParser.FCH, 0); }
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public CmdrepeticaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdrepeticao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProjLangListener ) ((ProjLangListener)listener).enterCmdrepeticao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProjLangListener ) ((ProjLangListener)listener).exitCmdrepeticao(this);
		}
	}

	public final CmdrepeticaoContext cmdrepeticao() throws RecognitionException {
		CmdrepeticaoContext _localctx = new CmdrepeticaoContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_cmdrepeticao);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(152);
			match(T__10);
			setState(153);
			match(AP);
			 exprTypeList = new ArrayList<String>(); 
			setState(155);
			match(ID);
			 _exprDecision = _input.LT(-1).getText(); 
												  verificaID(_exprDecision);
												  _left	 = getTypeByID(_exprDecision); 
										
			setState(157);
			match(OPREL);
			 _exprDecision += _input.LT(-1).getText(); 
			setState(159);
			termcomp();
			 	String id = _input.LT(-1).getText();
													_exprDecision += id;
													_right = verifyAndGetType(id);
										
			setState(161);
			match(FP);
			 	if(_left != _right) {
													throw new ProjSemanticException("Incompatible types " + _left + " and " + _right + " in " + _exprDecision);
												}
										
			setState(163);
			match(ACH);
				curThread = new ArrayList<AbstractCommand>();
												stack.push(curThread);
										
			setState(166); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(165);
				cmd();
				}
				}
				setState(168); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << ID))) != 0) );
			setState(170);
			match(FCH);
				listaCmd = stack.pop(); 
												CommandRepeticao cmd = new CommandRepeticao( _exprDecision, listaCmd);
												stack.peek().add(cmd);
										
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdrepeticaoPorContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(ProjLangParser.AP, 0); }
		public List<TerminalNode> ID() { return getTokens(ProjLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(ProjLangParser.ID, i);
		}
		public List<TerminalNode> ATTR() { return getTokens(ProjLangParser.ATTR); }
		public TerminalNode ATTR(int i) {
			return getToken(ProjLangParser.ATTR, i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> SC() { return getTokens(ProjLangParser.SC); }
		public TerminalNode SC(int i) {
			return getToken(ProjLangParser.SC, i);
		}
		public TerminalNode OPREL() { return getToken(ProjLangParser.OPREL, 0); }
		public TermcompContext termcomp() {
			return getRuleContext(TermcompContext.class,0);
		}
		public TerminalNode FP() { return getToken(ProjLangParser.FP, 0); }
		public TerminalNode ACH() { return getToken(ProjLangParser.ACH, 0); }
		public TerminalNode FCH() { return getToken(ProjLangParser.FCH, 0); }
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public CmdrepeticaoPorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdrepeticaoPor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProjLangListener ) ((ProjLangListener)listener).enterCmdrepeticaoPor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProjLangListener ) ((ProjLangListener)listener).exitCmdrepeticaoPor(this);
		}
	}

	public final CmdrepeticaoPorContext cmdrepeticaoPor() throws RecognitionException {
		CmdrepeticaoPorContext _localctx = new CmdrepeticaoPorContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_cmdrepeticaoPor);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(173);
			match(T__11);
			setState(174);
			match(AP);
			 	exprTypeList = new ArrayList<String>(); 
			setState(176);
			match(ID);
				 _exprID = _input.LT(-1).getText();
						                     	verificaID(_exprID);
						                     	_left	 = getTypeByID(_exprID);
						                     	exprTypeList = new ArrayList<String>();
						     	  		
			setState(178);
			match(ATTR);
			 _exprContent = "="; 
			setState(180);
			expr();
			setState(181);
			match(SC);

						               	 	checkType(_left, _exprID, _exprContent);
						               	 	_declID = _exprID + _exprContent;
						               	
			setState(183);
			match(ID);
			 	_exprDecision = _input.LT(-1).getText(); 
												  	verificaID(_exprDecision);
												  	_left	 = getTypeByID(_exprDecision); 
										
			setState(185);
			match(OPREL);
			 	_exprDecision += _input.LT(-1).getText(); 
			setState(187);
			termcomp();
			 	String id = _input.LT(-1).getText();
													_exprDecision += id;
													_right = verifyAndGetType(id);
										
			setState(189);
			match(SC);
			 	if(_left != _right) {
													throw new ProjSemanticException("Incompatible types " + _left + " and " + _right + " in " + _exprDecision);
												}
										
			setState(191);
			match(ID);
				_exprID = _input.LT(-1).getText();
						                     	verificaID(_exprID);
						                     	_left	 = getTypeByID(_exprID);
						                     	exprTypeList = new ArrayList<String>();
						     	  		
			setState(193);
			match(ATTR);
			 _exprContent = "="; 
			setState(195);
			expr();

						               	 	checkType(_left, _exprID, _exprContent);
						               	 	_actionID = _exprID + _exprContent;
						               	
			setState(197);
			match(FP);
			setState(198);
			match(ACH);
				curThread = new ArrayList<AbstractCommand>();
												stack.push(curThread);
										
			setState(201); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(200);
				cmd();
				}
				}
				setState(203); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << ID))) != 0) );
			setState(205);
			match(FCH);
				listaCmd = stack.pop(); 
												CommandRepeticaoFor cmd = new CommandRepeticaoFor( _declID, _exprDecision, _actionID, listaCmd);
												stack.peek().add(cmd);
										
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdfazerateContext extends ParserRuleContext {
		public TerminalNode ACH() { return getToken(ProjLangParser.ACH, 0); }
		public TerminalNode FCH() { return getToken(ProjLangParser.FCH, 0); }
		public TerminalNode AP() { return getToken(ProjLangParser.AP, 0); }
		public TerminalNode ID() { return getToken(ProjLangParser.ID, 0); }
		public TerminalNode OPREL() { return getToken(ProjLangParser.OPREL, 0); }
		public TermcompContext termcomp() {
			return getRuleContext(TermcompContext.class,0);
		}
		public TerminalNode FP() { return getToken(ProjLangParser.FP, 0); }
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public CmdfazerateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdfazerate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProjLangListener ) ((ProjLangListener)listener).enterCmdfazerate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProjLangListener ) ((ProjLangListener)listener).exitCmdfazerate(this);
		}
	}

	public final CmdfazerateContext cmdfazerate() throws RecognitionException {
		CmdfazerateContext _localctx = new CmdfazerateContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_cmdfazerate);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(208);
			match(T__12);
			setState(209);
			match(ACH);
				curThread = new ArrayList<AbstractCommand>();
											stack.push(curThread);
									
			setState(212); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(211);
				cmd();
				}
				}
				setState(214); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << ID))) != 0) );
			setState(216);
			match(FCH);
			setState(217);
			match(T__10);
			setState(218);
			match(AP);
			setState(219);
			match(ID);
				_exprDecision = _input.LT(-1).getText(); 
											verificaID(_exprDecision);
											_left	 = getTypeByID(_exprDecision); 
									
			setState(221);
			match(OPREL);
			 _exprDecision += _input.LT(-1).getText(); 
			setState(223);
			termcomp();
			 	String id = _input.LT(-1).getText();
												_exprDecision += id;
												_right = verifyAndGetType(id);
									
			setState(225);
			match(FP);

										if(_left != _right) {
											throw new ProjSemanticException("Incompatible types " + _left + " and " + _right + " in " + _exprDecision);
										}
										listaCmd = stack.pop();
										CommandFazerAte cmd = new CommandFazerAte(_exprDecision, listaCmd);
										stack.peek().add(cmd);		
									
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public List<TermoContext> termo() {
			return getRuleContexts(TermoContext.class);
		}
		public TermoContext termo(int i) {
			return getRuleContext(TermoContext.class,i);
		}
		public List<TerminalNode> OP() { return getTokens(ProjLangParser.OP); }
		public TerminalNode OP(int i) {
			return getToken(ProjLangParser.OP, i);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProjLangListener ) ((ProjLangListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProjLangListener ) ((ProjLangListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(228);
			termo();
			setState(234);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OP) {
				{
				{
				setState(229);
				match(OP);
				 _exprContent += _input.LT(-1).getText();
				setState(231);
				termo();
				}
				}
				setState(236);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermcompContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(ProjLangParser.ID, 0); }
		public TerminalNode NUMBER() { return getToken(ProjLangParser.NUMBER, 0); }
		public TermcompContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termcomp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProjLangListener ) ((ProjLangListener)listener).enterTermcomp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProjLangListener ) ((ProjLangListener)listener).exitTermcomp(this);
		}
	}

	public final TermcompContext termcomp() throws RecognitionException {
		TermcompContext _localctx = new TermcompContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_termcomp);
		try {
			setState(241);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(237);
				match(ID);
				 String id = _input.LT(-1).getText();
									  verificaID(id);
					               	  _exprContent += id;
					               	  exprTypeList.add(getTypeByID(id));
				              
				}
				break;
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(239);
				match(NUMBER);
				 _exprContent += _input.LT(-1).getText();
				              		  exprTypeList.add("NUMBER");
				              
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermoContext extends ParserRuleContext {
		public TermcompContext termcomp() {
			return getRuleContext(TermcompContext.class,0);
		}
		public TerminalNode TEXT() { return getToken(ProjLangParser.TEXT, 0); }
		public TermoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProjLangListener ) ((ProjLangListener)listener).enterTermo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProjLangListener ) ((ProjLangListener)listener).exitTermo(this);
		}
	}

	public final TermoContext termo() throws RecognitionException {
		TermoContext _localctx = new TermoContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_termo);
		try {
			setState(246);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
			case NUMBER:
				enterOuterAlt(_localctx, 1);
				{
				setState(243);
				termcomp();
				}
				break;
			case TEXT:
				enterOuterAlt(_localctx, 2);
				{
				setState(244);
				match(TEXT);
				 _exprContent += _input.LT(-1).getText();
				              		  exprTypeList.add("TEXT");
				              
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\36\u00fb\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\3\6\3,\n\3\r\3\16\3-\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\7\4\66\n\4\f\4\16\49\13\4\3\4\3\4\3\5\3\5\6\5?\n\5\r\5\16\5@\3\6\3"+
		"\6\3\6\3\6\5\6G\n\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7Q\n\7\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\6\13y\n\13\r\13\16\13z\3\13\3\13\3\13\3\13\3\13\3\13\6"+
		"\13\u0083\n\13\r\13\16\13\u0084\3\13\3\13\3\13\5\13\u008a\n\13\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\6\r\u00a9\n\r\r\r\16\r\u00aa\3"+
		"\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\6\16\u00cc\n\16\r\16\16\16\u00cd\3\16\3\16\3\16\3\17\3\17\3"+
		"\17\3\17\6\17\u00d7\n\17\r\17\16\17\u00d8\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\7\20\u00eb\n\20\f\20"+
		"\16\20\u00ee\13\20\3\21\3\21\3\21\3\21\5\21\u00f4\n\21\3\22\3\22\3\22"+
		"\5\22\u00f9\n\22\3\22\2\2\23\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \""+
		"\2\2\2\u00fd\2$\3\2\2\2\4+\3\2\2\2\6/\3\2\2\2\b<\3\2\2\2\nF\3\2\2\2\f"+
		"P\3\2\2\2\16R\3\2\2\2\20Z\3\2\2\2\22b\3\2\2\2\24j\3\2\2\2\26\u008b\3\2"+
		"\2\2\30\u009a\3\2\2\2\32\u00af\3\2\2\2\34\u00d2\3\2\2\2\36\u00e6\3\2\2"+
		"\2 \u00f3\3\2\2\2\"\u00f8\3\2\2\2$%\7\3\2\2%&\5\4\3\2&\'\5\b\5\2\'(\7"+
		"\4\2\2()\b\2\1\2)\3\3\2\2\2*,\5\6\4\2+*\3\2\2\2,-\3\2\2\2-+\3\2\2\2-."+
		"\3\2\2\2.\5\3\2\2\2/\60\5\n\6\2\60\61\7\31\2\2\61\67\b\4\1\2\62\63\7\25"+
		"\2\2\63\64\7\31\2\2\64\66\b\4\1\2\65\62\3\2\2\2\669\3\2\2\2\67\65\3\2"+
		"\2\2\678\3\2\2\28:\3\2\2\29\67\3\2\2\2:;\7\22\2\2;\7\3\2\2\2<>\b\5\1\2"+
		"=?\5\f\7\2>=\3\2\2\2?@\3\2\2\2@>\3\2\2\2@A\3\2\2\2A\t\3\2\2\2BC\7\5\2"+
		"\2CG\b\6\1\2DE\7\6\2\2EG\b\6\1\2FB\3\2\2\2FD\3\2\2\2G\13\3\2\2\2HQ\5\16"+
		"\b\2IQ\5\20\t\2JQ\5\22\n\2KQ\5\24\13\2LQ\5\30\r\2MQ\5\32\16\2NQ\5\34\17"+
		"\2OQ\5\26\f\2PH\3\2\2\2PI\3\2\2\2PJ\3\2\2\2PK\3\2\2\2PL\3\2\2\2PM\3\2"+
		"\2\2PN\3\2\2\2PO\3\2\2\2Q\r\3\2\2\2RS\7\7\2\2ST\7\20\2\2TU\7\31\2\2UV"+
		"\b\b\1\2VW\7\21\2\2WX\7\22\2\2XY\b\b\1\2Y\17\3\2\2\2Z[\7\b\2\2[\\\7\20"+
		"\2\2\\]\7\31\2\2]^\b\t\1\2^_\7\21\2\2_`\7\22\2\2`a\b\t\1\2a\21\3\2\2\2"+
		"bc\7\31\2\2cd\b\n\1\2de\7\24\2\2ef\b\n\1\2fg\5\36\20\2gh\7\22\2\2hi\b"+
		"\n\1\2i\23\3\2\2\2jk\7\t\2\2kl\7\20\2\2lm\b\13\1\2mn\7\31\2\2no\b\13\1"+
		"\2op\7\30\2\2pq\b\13\1\2qr\5 \21\2rs\b\13\1\2st\7\21\2\2tu\b\13\1\2uv"+
		"\7\26\2\2vx\b\13\1\2wy\5\f\7\2xw\3\2\2\2yz\3\2\2\2zx\3\2\2\2z{\3\2\2\2"+
		"{|\3\2\2\2|}\7\27\2\2}\u0089\b\13\1\2~\177\7\n\2\2\177\u0080\7\26\2\2"+
		"\u0080\u0082\b\13\1\2\u0081\u0083\5\f\7\2\u0082\u0081\3\2\2\2\u0083\u0084"+
		"\3\2\2\2\u0084\u0082\3\2\2\2\u0084\u0085\3\2\2\2\u0085\u0086\3\2\2\2\u0086"+
		"\u0087\7\27\2\2\u0087\u0088\b\13\1\2\u0088\u008a\3\2\2\2\u0089~\3\2\2"+
		"\2\u0089\u008a\3\2\2\2\u008a\25\3\2\2\2\u008b\u008c\7\31\2\2\u008c\u008d"+
		"\b\f\1\2\u008d\u008e\7\30\2\2\u008e\u008f\b\f\1\2\u008f\u0090\5 \21\2"+
		"\u0090\u0091\b\f\1\2\u0091\u0092\7\13\2\2\u0092\u0093\b\f\1\2\u0093\u0094"+
		"\5\f\7\2\u0094\u0095\b\f\1\2\u0095\u0096\7\f\2\2\u0096\u0097\b\f\1\2\u0097"+
		"\u0098\5\f\7\2\u0098\u0099\b\f\1\2\u0099\27\3\2\2\2\u009a\u009b\7\r\2"+
		"\2\u009b\u009c\7\20\2\2\u009c\u009d\b\r\1\2\u009d\u009e\7\31\2\2\u009e"+
		"\u009f\b\r\1\2\u009f\u00a0\7\30\2\2\u00a0\u00a1\b\r\1\2\u00a1\u00a2\5"+
		" \21\2\u00a2\u00a3\b\r\1\2\u00a3\u00a4\7\21\2\2\u00a4\u00a5\b\r\1\2\u00a5"+
		"\u00a6\7\26\2\2\u00a6\u00a8\b\r\1\2\u00a7\u00a9\5\f\7\2\u00a8\u00a7\3"+
		"\2\2\2\u00a9\u00aa\3\2\2\2\u00aa\u00a8\3\2\2\2\u00aa\u00ab\3\2\2\2\u00ab"+
		"\u00ac\3\2\2\2\u00ac\u00ad\7\27\2\2\u00ad\u00ae\b\r\1\2\u00ae\31\3\2\2"+
		"\2\u00af\u00b0\7\16\2\2\u00b0\u00b1\7\20\2\2\u00b1\u00b2\b\16\1\2\u00b2"+
		"\u00b3\7\31\2\2\u00b3\u00b4\b\16\1\2\u00b4\u00b5\7\24\2\2\u00b5\u00b6"+
		"\b\16\1\2\u00b6\u00b7\5\36\20\2\u00b7\u00b8\7\22\2\2\u00b8\u00b9\b\16"+
		"\1\2\u00b9\u00ba\7\31\2\2\u00ba\u00bb\b\16\1\2\u00bb\u00bc\7\30\2\2\u00bc"+
		"\u00bd\b\16\1\2\u00bd\u00be\5 \21\2\u00be\u00bf\b\16\1\2\u00bf\u00c0\7"+
		"\22\2\2\u00c0\u00c1\b\16\1\2\u00c1\u00c2\7\31\2\2\u00c2\u00c3\b\16\1\2"+
		"\u00c3\u00c4\7\24\2\2\u00c4\u00c5\b\16\1\2\u00c5\u00c6\5\36\20\2\u00c6"+
		"\u00c7\b\16\1\2\u00c7\u00c8\7\21\2\2\u00c8\u00c9\7\26\2\2\u00c9\u00cb"+
		"\b\16\1\2\u00ca\u00cc\5\f\7\2\u00cb\u00ca\3\2\2\2\u00cc\u00cd\3\2\2\2"+
		"\u00cd\u00cb\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce\u00cf\3\2\2\2\u00cf\u00d0"+
		"\7\27\2\2\u00d0\u00d1\b\16\1\2\u00d1\33\3\2\2\2\u00d2\u00d3\7\17\2\2\u00d3"+
		"\u00d4\7\26\2\2\u00d4\u00d6\b\17\1\2\u00d5\u00d7\5\f\7\2\u00d6\u00d5\3"+
		"\2\2\2\u00d7\u00d8\3\2\2\2\u00d8\u00d6\3\2\2\2\u00d8\u00d9\3\2\2\2\u00d9"+
		"\u00da\3\2\2\2\u00da\u00db\7\27\2\2\u00db\u00dc\7\r\2\2\u00dc\u00dd\7"+
		"\20\2\2\u00dd\u00de\7\31\2\2\u00de\u00df\b\17\1\2\u00df\u00e0\7\30\2\2"+
		"\u00e0\u00e1\b\17\1\2\u00e1\u00e2\5 \21\2\u00e2\u00e3\b\17\1\2\u00e3\u00e4"+
		"\7\21\2\2\u00e4\u00e5\b\17\1\2\u00e5\35\3\2\2\2\u00e6\u00ec\5\"\22\2\u00e7"+
		"\u00e8\7\23\2\2\u00e8\u00e9\b\20\1\2\u00e9\u00eb\5\"\22\2\u00ea\u00e7"+
		"\3\2\2\2\u00eb\u00ee\3\2\2\2\u00ec\u00ea\3\2\2\2\u00ec\u00ed\3\2\2\2\u00ed"+
		"\37\3\2\2\2\u00ee\u00ec\3\2\2\2\u00ef\u00f0\7\31\2\2\u00f0\u00f4\b\21"+
		"\1\2\u00f1\u00f2\7\32\2\2\u00f2\u00f4\b\21\1\2\u00f3\u00ef\3\2\2\2\u00f3"+
		"\u00f1\3\2\2\2\u00f4!\3\2\2\2\u00f5\u00f9\5 \21\2\u00f6\u00f7\7\33\2\2"+
		"\u00f7\u00f9\b\22\1\2\u00f8\u00f5\3\2\2\2\u00f8\u00f6\3\2\2\2\u00f9#\3"+
		"\2\2\2\20-\67@FPz\u0084\u0089\u00aa\u00cd\u00d8\u00ec\u00f3\u00f8";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}