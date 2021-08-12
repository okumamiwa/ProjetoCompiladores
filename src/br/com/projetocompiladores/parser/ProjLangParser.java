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
		AP=10, FP=11, SC=12, OP=13, ATTR=14, VIR=15, ACH=16, FCH=17, OPREL=18, 
		ID=19, NUMBER=20, TEXT=21, WS=22;
	public static final int
		RULE_prog = 0, RULE_decl = 1, RULE_declaravar = 2, RULE_bloco = 3, RULE_tipo = 4, 
		RULE_cmd = 5, RULE_cmdleitura = 6, RULE_cmdescrita = 7, RULE_cmdatrib = 8, 
		RULE_cmdselecao = 9, RULE_cmdrepeticao = 10, RULE_expr = 11, RULE_termcomp = 12, 
		RULE_termo = 13;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "decl", "declaravar", "bloco", "tipo", "cmd", "cmdleitura", "cmdescrita", 
			"cmdatrib", "cmdselecao", "cmdrepeticao", "expr", "termcomp", "termo"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'programa'", "'fimprog;'", "'numero'", "'texto'", "'leia'", "'escreva'", 
			"'se'", "'senao'", "'enquanto'", "'('", "')'", "';'", null, "'='", "','", 
			"'{'", "'}'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, "AP", "FP", 
			"SC", "OP", "ATTR", "VIR", "ACH", "FCH", "OPREL", "ID", "NUMBER", "TEXT", 
			"WS"
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
				System.out.println(c);
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
			setState(28);
			match(T__0);
			setState(29);
			decl();
			setState(30);
			bloco();
			setState(31);
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
			setState(35); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(34);
				declaravar();
				}
				}
				setState(37); 
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
			setState(39);
			tipo();
			setState(40);
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
			                  	
			setState(47);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VIR) {
				{
				{
				setState(42);
				match(VIR);
				setState(43);
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
				setState(49);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(50);
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
			          
			setState(54); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(53);
				cmd();
				}
				}
				setState(56); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__8) | (1L << ID))) != 0) );
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
			setState(62);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				enterOuterAlt(_localctx, 1);
				{
				setState(58);
				match(T__2);
				_tipo = ProjVariable.NUMBER;
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 2);
				{
				setState(60);
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
			setState(69);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
				enterOuterAlt(_localctx, 1);
				{
				setState(64);
				cmdleitura();
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 2);
				{
				setState(65);
				cmdescrita();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(66);
				cmdatrib();
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 4);
				{
				setState(67);
				cmdselecao();
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 5);
				{
				setState(68);
				cmdrepeticao();
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
			setState(71);
			match(T__4);
			setState(72);
			match(AP);
			setState(73);
			match(ID);
			 verificaID(_input.LT(-1).getText());
									     _readID = _input.LT(-1).getText();
			                 	  	
			setState(75);
			match(FP);
			setState(76);
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
			setState(79);
			match(T__5);
			setState(80);
			match(AP);
			setState(81);
			match(ID);
			 verificaID(_input.LT(-1).getText());
										 _writeID = _input.LT(-1).getText();
			                 	  	
			setState(83);
			match(FP);
			setState(84);
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
			setState(87);
			match(ID);
			 verificaID(_input.LT(-1).getText());
			                     _exprID = _input.LT(-1).getText();
			                     _left	 = getTypeByID(_exprID);
			                     exprTypeList = new ArrayList<String>();
			     	  		
			setState(89);
			match(ATTR);
			 _exprContent = ""; 
			setState(91);
			expr();
			setState(92);
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
			setState(95);
			match(T__6);
			setState(96);
			match(AP);
			 exprTypeList = new ArrayList<String>(); 
			setState(98);
			match(ID);
			 _exprDecision = _input.LT(-1).getText(); 
										_left	 = getTypeByID(_exprDecision);
								
			setState(100);
			match(OPREL);
			 _exprDecision += _input.LT(-1).getText(); 
			setState(102);
			termcomp();
			 	String id = _input.LT(-1).getText();
											_exprDecision += id;
											_right = verifyAndGetType(id);
								
			setState(104);
			match(FP);
			 	if(_left != _right) {
											throw new ProjSemanticException("Incompatible types " + _left + " and " + _right + " in " + _exprDecision);
										}
								
			setState(106);
			match(ACH);
			 	curThread = new ArrayList<AbstractCommand>(); 
			                      		stack.push(curThread);
			                    
			setState(109); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(108);
				cmd();
				}
				}
				setState(111); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__8) | (1L << ID))) != 0) );
			setState(113);
			match(FCH);
			 listaTrue = stack.pop();	
			                    
			setState(126);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__7) {
				{
				setState(115);
				match(T__7);
				setState(116);
				match(ACH);

				                   	 	curThread = new ArrayList<AbstractCommand>();
				                   	 	stack.push(curThread);
				                   	
				{
				setState(119); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(118);
					cmd();
					}
					}
					setState(121); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__8) | (1L << ID))) != 0) );
				}
				setState(123);
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
		enterRule(_localctx, 20, RULE_cmdrepeticao);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(128);
			match(T__8);
			setState(129);
			match(AP);
			 exprTypeList = new ArrayList<String>(); 
			setState(131);
			match(ID);
			 _exprDecision = _input.LT(-1).getText(); 
												  _left	 = getTypeByID(_exprDecision); 
										
			setState(133);
			match(OPREL);
			 _exprDecision += _input.LT(-1).getText(); 
			setState(135);
			termcomp();
			 	String id = _input.LT(-1).getText();
													_exprDecision += id;
													_right = verifyAndGetType(id);
										
			setState(137);
			match(FP);
			 	if(_left != _right) {
													throw new ProjSemanticException("Incompatible types " + _left + " and " + _right + " in " + _exprDecision);
												}
										
			setState(139);
			match(ACH);
				curThread = new ArrayList<AbstractCommand>();
												stack.push(curThread);
										
			setState(142); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(141);
				cmd();
				}
				}
				setState(144); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__8) | (1L << ID))) != 0) );
			setState(146);
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
		enterRule(_localctx, 22, RULE_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(149);
			termo();
			setState(155);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OP) {
				{
				{
				setState(150);
				match(OP);
				 _exprContent += _input.LT(-1).getText();
				setState(152);
				termo();
				}
				}
				setState(157);
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
		enterRule(_localctx, 24, RULE_termcomp);
		try {
			setState(162);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(158);
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
				setState(160);
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
		enterRule(_localctx, 26, RULE_termo);
		try {
			setState(167);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
			case NUMBER:
				enterOuterAlt(_localctx, 1);
				{
				setState(164);
				termcomp();
				}
				break;
			case TEXT:
				enterOuterAlt(_localctx, 2);
				{
				setState(165);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\30\u00ac\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\3\2\3\2\3\2\3\2\3\3"+
		"\6\3&\n\3\r\3\16\3\'\3\4\3\4\3\4\3\4\3\4\3\4\7\4\60\n\4\f\4\16\4\63\13"+
		"\4\3\4\3\4\3\5\3\5\6\59\n\5\r\5\16\5:\3\6\3\6\3\6\3\6\5\6A\n\6\3\7\3\7"+
		"\3\7\3\7\3\7\5\7H\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\6\13p\n\13\r\13\16\13"+
		"q\3\13\3\13\3\13\3\13\3\13\3\13\6\13z\n\13\r\13\16\13{\3\13\3\13\3\13"+
		"\5\13\u0081\n\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\6\f\u0091\n\f\r\f\16\f\u0092\3\f\3\f\3\f\3\r\3\r\3\r\3\r\7\r\u009c"+
		"\n\r\f\r\16\r\u009f\13\r\3\16\3\16\3\16\3\16\5\16\u00a5\n\16\3\17\3\17"+
		"\3\17\5\17\u00aa\n\17\3\17\2\2\20\2\4\6\b\n\f\16\20\22\24\26\30\32\34"+
		"\2\2\2\u00ac\2\36\3\2\2\2\4%\3\2\2\2\6)\3\2\2\2\b\66\3\2\2\2\n@\3\2\2"+
		"\2\fG\3\2\2\2\16I\3\2\2\2\20Q\3\2\2\2\22Y\3\2\2\2\24a\3\2\2\2\26\u0082"+
		"\3\2\2\2\30\u0097\3\2\2\2\32\u00a4\3\2\2\2\34\u00a9\3\2\2\2\36\37\7\3"+
		"\2\2\37 \5\4\3\2 !\5\b\5\2!\"\7\4\2\2\"#\b\2\1\2#\3\3\2\2\2$&\5\6\4\2"+
		"%$\3\2\2\2&\'\3\2\2\2\'%\3\2\2\2\'(\3\2\2\2(\5\3\2\2\2)*\5\n\6\2*+\7\25"+
		"\2\2+\61\b\4\1\2,-\7\21\2\2-.\7\25\2\2.\60\b\4\1\2/,\3\2\2\2\60\63\3\2"+
		"\2\2\61/\3\2\2\2\61\62\3\2\2\2\62\64\3\2\2\2\63\61\3\2\2\2\64\65\7\16"+
		"\2\2\65\7\3\2\2\2\668\b\5\1\2\679\5\f\7\28\67\3\2\2\29:\3\2\2\2:8\3\2"+
		"\2\2:;\3\2\2\2;\t\3\2\2\2<=\7\5\2\2=A\b\6\1\2>?\7\6\2\2?A\b\6\1\2@<\3"+
		"\2\2\2@>\3\2\2\2A\13\3\2\2\2BH\5\16\b\2CH\5\20\t\2DH\5\22\n\2EH\5\24\13"+
		"\2FH\5\26\f\2GB\3\2\2\2GC\3\2\2\2GD\3\2\2\2GE\3\2\2\2GF\3\2\2\2H\r\3\2"+
		"\2\2IJ\7\7\2\2JK\7\f\2\2KL\7\25\2\2LM\b\b\1\2MN\7\r\2\2NO\7\16\2\2OP\b"+
		"\b\1\2P\17\3\2\2\2QR\7\b\2\2RS\7\f\2\2ST\7\25\2\2TU\b\t\1\2UV\7\r\2\2"+
		"VW\7\16\2\2WX\b\t\1\2X\21\3\2\2\2YZ\7\25\2\2Z[\b\n\1\2[\\\7\20\2\2\\]"+
		"\b\n\1\2]^\5\30\r\2^_\7\16\2\2_`\b\n\1\2`\23\3\2\2\2ab\7\t\2\2bc\7\f\2"+
		"\2cd\b\13\1\2de\7\25\2\2ef\b\13\1\2fg\7\24\2\2gh\b\13\1\2hi\5\32\16\2"+
		"ij\b\13\1\2jk\7\r\2\2kl\b\13\1\2lm\7\22\2\2mo\b\13\1\2np\5\f\7\2on\3\2"+
		"\2\2pq\3\2\2\2qo\3\2\2\2qr\3\2\2\2rs\3\2\2\2st\7\23\2\2t\u0080\b\13\1"+
		"\2uv\7\n\2\2vw\7\22\2\2wy\b\13\1\2xz\5\f\7\2yx\3\2\2\2z{\3\2\2\2{y\3\2"+
		"\2\2{|\3\2\2\2|}\3\2\2\2}~\7\23\2\2~\177\b\13\1\2\177\u0081\3\2\2\2\u0080"+
		"u\3\2\2\2\u0080\u0081\3\2\2\2\u0081\25\3\2\2\2\u0082\u0083\7\13\2\2\u0083"+
		"\u0084\7\f\2\2\u0084\u0085\b\f\1\2\u0085\u0086\7\25\2\2\u0086\u0087\b"+
		"\f\1\2\u0087\u0088\7\24\2\2\u0088\u0089\b\f\1\2\u0089\u008a\5\32\16\2"+
		"\u008a\u008b\b\f\1\2\u008b\u008c\7\r\2\2\u008c\u008d\b\f\1\2\u008d\u008e"+
		"\7\22\2\2\u008e\u0090\b\f\1\2\u008f\u0091\5\f\7\2\u0090\u008f\3\2\2\2"+
		"\u0091\u0092\3\2\2\2\u0092\u0090\3\2\2\2\u0092\u0093\3\2\2\2\u0093\u0094"+
		"\3\2\2\2\u0094\u0095\7\23\2\2\u0095\u0096\b\f\1\2\u0096\27\3\2\2\2\u0097"+
		"\u009d\5\34\17\2\u0098\u0099\7\17\2\2\u0099\u009a\b\r\1\2\u009a\u009c"+
		"\5\34\17\2\u009b\u0098\3\2\2\2\u009c\u009f\3\2\2\2\u009d\u009b\3\2\2\2"+
		"\u009d\u009e\3\2\2\2\u009e\31\3\2\2\2\u009f\u009d\3\2\2\2\u00a0\u00a1"+
		"\7\25\2\2\u00a1\u00a5\b\16\1\2\u00a2\u00a3\7\26\2\2\u00a3\u00a5\b\16\1"+
		"\2\u00a4\u00a0\3\2\2\2\u00a4\u00a2\3\2\2\2\u00a5\33\3\2\2\2\u00a6\u00aa"+
		"\5\32\16\2\u00a7\u00a8\7\27\2\2\u00a8\u00aa\b\17\1\2\u00a9\u00a6\3\2\2"+
		"\2\u00a9\u00a7\3\2\2\2\u00aa\35\3\2\2\2\16\'\61:@Gq{\u0080\u0092\u009d"+
		"\u00a4\u00a9";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}