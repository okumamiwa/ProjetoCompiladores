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
		T__9=10, T__10=11, AP=12, FP=13, SC=14, OP=15, ATTR=16, VIR=17, ACH=18, 
		FCH=19, OPREL=20, ID=21, NUMBER=22, TEXT=23, WS=24;
	public static final int
		RULE_prog = 0, RULE_decl = 1, RULE_declaravar = 2, RULE_bloco = 3, RULE_tipo = 4, 
		RULE_cmd = 5, RULE_cmdleitura = 6, RULE_cmdescrita = 7, RULE_cmdatrib = 8, 
		RULE_cmdselecao = 9, RULE_cmdrepeticao = 10, RULE_cmdfazerate = 11, RULE_expr = 12, 
		RULE_termcomp = 13, RULE_termo = 14;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "decl", "declaravar", "bloco", "tipo", "cmd", "cmdleitura", "cmdescrita", 
			"cmdatrib", "cmdselecao", "cmdrepeticao", "cmdfazerate", "expr", "termcomp", 
			"termo"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'programa'", "'fimprog;'", "'numero'", "'texto'", "'leia'", "'escreva'", 
			"'se'", "'senao'", "'enquanto'", "'fazer'", "'ate'", "'('", "')'", "';'", 
			null, "'='", "','", "'{'", "'}'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			"AP", "FP", "SC", "OP", "ATTR", "VIR", "ACH", "FCH", "OPREL", "ID", "NUMBER", 
			"TEXT", "WS"
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
			setState(30);
			match(T__0);
			setState(31);
			decl();
			setState(32);
			bloco();
			setState(33);
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
			setState(37); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(36);
				declaravar();
				}
				}
				setState(39); 
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
			setState(41);
			tipo();
			setState(42);
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
			                  	
			setState(49);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VIR) {
				{
				{
				setState(44);
				match(VIR);
				setState(45);
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
				setState(51);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(52);
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
			          
			setState(56); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(55);
				cmd();
				}
				}
				setState(58); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__8) | (1L << T__9) | (1L << ID))) != 0) );
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
			setState(64);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				enterOuterAlt(_localctx, 1);
				{
				setState(60);
				match(T__2);
				_tipo = ProjVariable.NUMBER;
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 2);
				{
				setState(62);
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
		public CmdfazerateContext cmdfazerate() {
			return getRuleContext(CmdfazerateContext.class,0);
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
			setState(72);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
				enterOuterAlt(_localctx, 1);
				{
				setState(66);
				cmdleitura();
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 2);
				{
				setState(67);
				cmdescrita();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(68);
				cmdatrib();
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 4);
				{
				setState(69);
				cmdselecao();
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 5);
				{
				setState(70);
				cmdrepeticao();
				}
				break;
			case T__9:
				enterOuterAlt(_localctx, 6);
				{
				setState(71);
				cmdfazerate();
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
			setState(74);
			match(T__4);
			setState(75);
			match(AP);
			setState(76);
			match(ID);
			 verificaID(_input.LT(-1).getText());
									     _readID = _input.LT(-1).getText();
			                 	  	
			setState(78);
			match(FP);
			setState(79);
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
			setState(82);
			match(T__5);
			setState(83);
			match(AP);
			setState(84);
			match(ID);
			 verificaID(_input.LT(-1).getText());
										 _writeID = _input.LT(-1).getText();
			                 	  	
			setState(86);
			match(FP);
			setState(87);
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
			setState(90);
			match(ID);
			 verificaID(_input.LT(-1).getText());
			                     _exprID = _input.LT(-1).getText();
			                     _left	 = getTypeByID(_exprID);
			                     exprTypeList = new ArrayList<String>();
			     	  		
			setState(92);
			match(ATTR);
			 _exprContent = ""; 
			setState(94);
			expr();
			setState(95);
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
			setState(98);
			match(T__6);
			setState(99);
			match(AP);
			 exprTypeList = new ArrayList<String>(); 
			setState(101);
			match(ID);
			 _exprDecision = _input.LT(-1).getText(); 
										verificaID(_exprDecision);
										_left	 = getTypeByID(_exprDecision);
								
			setState(103);
			match(OPREL);
			 _exprDecision += _input.LT(-1).getText(); 
			setState(105);
			termcomp();
			 	String id = _input.LT(-1).getText();
											_exprDecision += id;
											_right = verifyAndGetType(id);
								
			setState(107);
			match(FP);
			 	if(_left != _right) {
											throw new ProjSemanticException("Incompatible types " + _left + " and " + _right + " in " + _exprDecision);
										}
								
			setState(109);
			match(ACH);
			 	curThread = new ArrayList<AbstractCommand>(); 
			                      		stack.push(curThread);
			                    
			setState(112); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(111);
				cmd();
				}
				}
				setState(114); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__8) | (1L << T__9) | (1L << ID))) != 0) );
			setState(116);
			match(FCH);
			 listaTrue = stack.pop();	
			                    
			setState(129);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__7) {
				{
				setState(118);
				match(T__7);
				setState(119);
				match(ACH);

				                   	 	curThread = new ArrayList<AbstractCommand>();
				                   	 	stack.push(curThread);
				                   	
				{
				setState(122); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(121);
					cmd();
					}
					}
					setState(124); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__8) | (1L << T__9) | (1L << ID))) != 0) );
				}
				setState(126);
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
			setState(131);
			match(T__8);
			setState(132);
			match(AP);
			 exprTypeList = new ArrayList<String>(); 
			setState(134);
			match(ID);
			 _exprDecision = _input.LT(-1).getText(); 
												  verificaID(_exprDecision);
												  _left	 = getTypeByID(_exprDecision); 
										
			setState(136);
			match(OPREL);
			 _exprDecision += _input.LT(-1).getText(); 
			setState(138);
			termcomp();
			 	String id = _input.LT(-1).getText();
													_exprDecision += id;
													_right = verifyAndGetType(id);
										
			setState(140);
			match(FP);
			 	if(_left != _right) {
													throw new ProjSemanticException("Incompatible types " + _left + " and " + _right + " in " + _exprDecision);
												}
										
			setState(142);
			match(ACH);
				curThread = new ArrayList<AbstractCommand>();
												stack.push(curThread);
										
			setState(145); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(144);
				cmd();
				}
				}
				setState(147); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__8) | (1L << T__9) | (1L << ID))) != 0) );
			setState(149);
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
		enterRule(_localctx, 22, RULE_cmdfazerate);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(152);
			match(T__9);
			setState(153);
			match(ACH);
				curThread = new ArrayList<AbstractCommand>();
											stack.push(curThread);
									
			setState(156); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(155);
				cmd();
				}
				}
				setState(158); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__8) | (1L << T__9) | (1L << ID))) != 0) );
			setState(160);
			match(FCH);
			setState(161);
			match(T__10);
			setState(162);
			match(AP);
			setState(163);
			match(ID);
				_exprDecision = _input.LT(-1).getText(); 
											verificaID(_exprDecision);
											_left	 = getTypeByID(_exprDecision); 
									
			setState(165);
			match(OPREL);
			 _exprDecision += _input.LT(-1).getText(); 
			setState(167);
			termcomp();
			 	String id = _input.LT(-1).getText();
												_exprDecision += id;
												_right = verifyAndGetType(id);
									
			setState(169);
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
		enterRule(_localctx, 24, RULE_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(172);
			termo();
			setState(178);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OP) {
				{
				{
				setState(173);
				match(OP);
				 _exprContent += _input.LT(-1).getText();
				setState(175);
				termo();
				}
				}
				setState(180);
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
		enterRule(_localctx, 26, RULE_termcomp);
		try {
			setState(185);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(181);
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
				setState(183);
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
		enterRule(_localctx, 28, RULE_termo);
		try {
			setState(190);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
			case NUMBER:
				enterOuterAlt(_localctx, 1);
				{
				setState(187);
				termcomp();
				}
				break;
			case TEXT:
				enterOuterAlt(_localctx, 2);
				{
				setState(188);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\32\u00c3\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\3\6\3(\n\3\r\3\16\3)\3\4\3\4\3\4\3\4\3\4\3\4\7\4\62\n\4\f\4\16"+
		"\4\65\13\4\3\4\3\4\3\5\3\5\6\5;\n\5\r\5\16\5<\3\6\3\6\3\6\3\6\5\6C\n\6"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\5\7K\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\6\13s\n\13"+
		"\r\13\16\13t\3\13\3\13\3\13\3\13\3\13\3\13\6\13}\n\13\r\13\16\13~\3\13"+
		"\3\13\3\13\5\13\u0084\n\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\6\f\u0094\n\f\r\f\16\f\u0095\3\f\3\f\3\f\3\r\3\r\3\r\3\r"+
		"\6\r\u009f\n\r\r\r\16\r\u00a0\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\16\3\16\3\16\3\16\7\16\u00b3\n\16\f\16\16\16\u00b6\13\16\3"+
		"\17\3\17\3\17\3\17\5\17\u00bc\n\17\3\20\3\20\3\20\5\20\u00c1\n\20\3\20"+
		"\2\2\21\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36\2\2\2\u00c4\2 \3\2\2\2"+
		"\4\'\3\2\2\2\6+\3\2\2\2\b8\3\2\2\2\nB\3\2\2\2\fJ\3\2\2\2\16L\3\2\2\2\20"+
		"T\3\2\2\2\22\\\3\2\2\2\24d\3\2\2\2\26\u0085\3\2\2\2\30\u009a\3\2\2\2\32"+
		"\u00ae\3\2\2\2\34\u00bb\3\2\2\2\36\u00c0\3\2\2\2 !\7\3\2\2!\"\5\4\3\2"+
		"\"#\5\b\5\2#$\7\4\2\2$%\b\2\1\2%\3\3\2\2\2&(\5\6\4\2\'&\3\2\2\2()\3\2"+
		"\2\2)\'\3\2\2\2)*\3\2\2\2*\5\3\2\2\2+,\5\n\6\2,-\7\27\2\2-\63\b\4\1\2"+
		"./\7\23\2\2/\60\7\27\2\2\60\62\b\4\1\2\61.\3\2\2\2\62\65\3\2\2\2\63\61"+
		"\3\2\2\2\63\64\3\2\2\2\64\66\3\2\2\2\65\63\3\2\2\2\66\67\7\20\2\2\67\7"+
		"\3\2\2\28:\b\5\1\29;\5\f\7\2:9\3\2\2\2;<\3\2\2\2<:\3\2\2\2<=\3\2\2\2="+
		"\t\3\2\2\2>?\7\5\2\2?C\b\6\1\2@A\7\6\2\2AC\b\6\1\2B>\3\2\2\2B@\3\2\2\2"+
		"C\13\3\2\2\2DK\5\16\b\2EK\5\20\t\2FK\5\22\n\2GK\5\24\13\2HK\5\26\f\2I"+
		"K\5\30\r\2JD\3\2\2\2JE\3\2\2\2JF\3\2\2\2JG\3\2\2\2JH\3\2\2\2JI\3\2\2\2"+
		"K\r\3\2\2\2LM\7\7\2\2MN\7\16\2\2NO\7\27\2\2OP\b\b\1\2PQ\7\17\2\2QR\7\20"+
		"\2\2RS\b\b\1\2S\17\3\2\2\2TU\7\b\2\2UV\7\16\2\2VW\7\27\2\2WX\b\t\1\2X"+
		"Y\7\17\2\2YZ\7\20\2\2Z[\b\t\1\2[\21\3\2\2\2\\]\7\27\2\2]^\b\n\1\2^_\7"+
		"\22\2\2_`\b\n\1\2`a\5\32\16\2ab\7\20\2\2bc\b\n\1\2c\23\3\2\2\2de\7\t\2"+
		"\2ef\7\16\2\2fg\b\13\1\2gh\7\27\2\2hi\b\13\1\2ij\7\26\2\2jk\b\13\1\2k"+
		"l\5\34\17\2lm\b\13\1\2mn\7\17\2\2no\b\13\1\2op\7\24\2\2pr\b\13\1\2qs\5"+
		"\f\7\2rq\3\2\2\2st\3\2\2\2tr\3\2\2\2tu\3\2\2\2uv\3\2\2\2vw\7\25\2\2w\u0083"+
		"\b\13\1\2xy\7\n\2\2yz\7\24\2\2z|\b\13\1\2{}\5\f\7\2|{\3\2\2\2}~\3\2\2"+
		"\2~|\3\2\2\2~\177\3\2\2\2\177\u0080\3\2\2\2\u0080\u0081\7\25\2\2\u0081"+
		"\u0082\b\13\1\2\u0082\u0084\3\2\2\2\u0083x\3\2\2\2\u0083\u0084\3\2\2\2"+
		"\u0084\25\3\2\2\2\u0085\u0086\7\13\2\2\u0086\u0087\7\16\2\2\u0087\u0088"+
		"\b\f\1\2\u0088\u0089\7\27\2\2\u0089\u008a\b\f\1\2\u008a\u008b\7\26\2\2"+
		"\u008b\u008c\b\f\1\2\u008c\u008d\5\34\17\2\u008d\u008e\b\f\1\2\u008e\u008f"+
		"\7\17\2\2\u008f\u0090\b\f\1\2\u0090\u0091\7\24\2\2\u0091\u0093\b\f\1\2"+
		"\u0092\u0094\5\f\7\2\u0093\u0092\3\2\2\2\u0094\u0095\3\2\2\2\u0095\u0093"+
		"\3\2\2\2\u0095\u0096\3\2\2\2\u0096\u0097\3\2\2\2\u0097\u0098\7\25\2\2"+
		"\u0098\u0099\b\f\1\2\u0099\27\3\2\2\2\u009a\u009b\7\f\2\2\u009b\u009c"+
		"\7\24\2\2\u009c\u009e\b\r\1\2\u009d\u009f\5\f\7\2\u009e\u009d\3\2\2\2"+
		"\u009f\u00a0\3\2\2\2\u00a0\u009e\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\u00a2"+
		"\3\2\2\2\u00a2\u00a3\7\25\2\2\u00a3\u00a4\7\r\2\2\u00a4\u00a5\7\16\2\2"+
		"\u00a5\u00a6\7\27\2\2\u00a6\u00a7\b\r\1\2\u00a7\u00a8\7\26\2\2\u00a8\u00a9"+
		"\b\r\1\2\u00a9\u00aa\5\34\17\2\u00aa\u00ab\b\r\1\2\u00ab\u00ac\7\17\2"+
		"\2\u00ac\u00ad\b\r\1\2\u00ad\31\3\2\2\2\u00ae\u00b4\5\36\20\2\u00af\u00b0"+
		"\7\21\2\2\u00b0\u00b1\b\16\1\2\u00b1\u00b3\5\36\20\2\u00b2\u00af\3\2\2"+
		"\2\u00b3\u00b6\3\2\2\2\u00b4\u00b2\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b5\33"+
		"\3\2\2\2\u00b6\u00b4\3\2\2\2\u00b7\u00b8\7\27\2\2\u00b8\u00bc\b\17\1\2"+
		"\u00b9\u00ba\7\30\2\2\u00ba\u00bc\b\17\1\2\u00bb\u00b7\3\2\2\2\u00bb\u00b9"+
		"\3\2\2\2\u00bc\35\3\2\2\2\u00bd\u00c1\5\34\17\2\u00be\u00bf\7\31\2\2\u00bf"+
		"\u00c1\b\20\1\2\u00c0\u00bd\3\2\2\2\u00c0\u00be\3\2\2\2\u00c1\37\3\2\2"+
		"\2\17)\63<BJt~\u0083\u0095\u00a0\u00b4\u00bb\u00c0";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}