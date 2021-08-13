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
		VIR=19, ACH=20, FCH=21, OPREL=22, ID=23, NUMBER=24, TEXT=25, WS=26;
	public static final int
		RULE_prog = 0, RULE_decl = 1, RULE_declaravar = 2, RULE_bloco = 3, RULE_tipo = 4, 
		RULE_cmd = 5, RULE_cmdleitura = 6, RULE_cmdescrita = 7, RULE_cmdatrib = 8, 
		RULE_cmdselecao = 9, RULE_cmdselecaoter = 10, RULE_cmdrepeticao = 11, 
		RULE_cmdfazerate = 12, RULE_expr = 13, RULE_termcomp = 14, RULE_termo = 15;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "decl", "declaravar", "bloco", "tipo", "cmd", "cmdleitura", "cmdescrita", 
			"cmdatrib", "cmdselecao", "cmdselecaoter", "cmdrepeticao", "cmdfazerate", 
			"expr", "termcomp", "termo"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'programa'", "'fimprog;'", "'numero'", "'texto'", "'leia'", "'escreva'", 
			"'se'", "'senao'", "'?'", "':'", "'enquanto'", "'fazer'", "'ate'", "'('", 
			"')'", "';'", null, "'='", "','", "'{'", "'}'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, "AP", "FP", "SC", "OP", "ATTR", "VIR", "ACH", "FCH", "OPREL", 
			"ID", "NUMBER", "TEXT", "WS"
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
			setState(32);
			match(T__0);
			setState(33);
			decl();
			setState(34);
			bloco();
			setState(35);
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
			setState(39); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(38);
				declaravar();
				}
				}
				setState(41); 
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
			setState(43);
			tipo();
			setState(44);
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
			                  	
			setState(51);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VIR) {
				{
				{
				setState(46);
				match(VIR);
				setState(47);
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
				setState(53);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(54);
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
			          
			setState(58); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(57);
				cmd();
				}
				}
				setState(60); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__10) | (1L << T__11) | (1L << ID))) != 0) );
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
			setState(66);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				enterOuterAlt(_localctx, 1);
				{
				setState(62);
				match(T__2);
				_tipo = ProjVariable.NUMBER;
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 2);
				{
				setState(64);
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
			setState(75);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(68);
				cmdleitura();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(69);
				cmdescrita();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(70);
				cmdatrib();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(71);
				cmdselecao();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(72);
				cmdrepeticao();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(73);
				cmdfazerate();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(74);
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
			setState(77);
			match(T__4);
			setState(78);
			match(AP);
			setState(79);
			match(ID);
			 verificaID(_input.LT(-1).getText());
									     _readID = _input.LT(-1).getText();
			                 	  	
			setState(81);
			match(FP);
			setState(82);
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
			setState(85);
			match(T__5);
			setState(86);
			match(AP);
			setState(87);
			match(ID);
			 verificaID(_input.LT(-1).getText());
										 _writeID = _input.LT(-1).getText();
			                 	  	
			setState(89);
			match(FP);
			setState(90);
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
			setState(93);
			match(ID);
			 verificaID(_input.LT(-1).getText());
			                     _exprID = _input.LT(-1).getText();
			                     _left	 = getTypeByID(_exprID);
			                     exprTypeList = new ArrayList<String>();
			     	  		
			setState(95);
			match(ATTR);
			 _exprContent = ""; 
			setState(97);
			expr();
			setState(98);
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
			setState(101);
			match(T__6);
			setState(102);
			match(AP);
			 exprTypeList = new ArrayList<String>(); 
			setState(104);
			match(ID);
			 _exprDecision = _input.LT(-1).getText(); 
										verificaID(_exprDecision);
										_left	 = getTypeByID(_exprDecision);
								
			setState(106);
			match(OPREL);
			 _exprDecision += _input.LT(-1).getText(); 
			setState(108);
			termcomp();
			 	String id = _input.LT(-1).getText();
											_exprDecision += id;
											_right = verifyAndGetType(id);
								
			setState(110);
			match(FP);
			 	if(_left != _right) {
											throw new ProjSemanticException("Incompatible types " + _left + " and " + _right + " in " + _exprDecision);
										}
								
			setState(112);
			match(ACH);
			 	curThread = new ArrayList<AbstractCommand>(); 
			                      		stack.push(curThread);
			                    
			setState(115); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(114);
				cmd();
				}
				}
				setState(117); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__10) | (1L << T__11) | (1L << ID))) != 0) );
			setState(119);
			match(FCH);
			 listaTrue = stack.pop();	
			                    
			setState(132);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__7) {
				{
				setState(121);
				match(T__7);
				setState(122);
				match(ACH);

				                   	 	curThread = new ArrayList<AbstractCommand>();
				                   	 	stack.push(curThread);
				                   	
				{
				setState(125); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(124);
					cmd();
					}
					}
					setState(127); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__10) | (1L << T__11) | (1L << ID))) != 0) );
				}
				setState(129);
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
			match(T__8);
				curThread = new ArrayList<AbstractCommand>(); 
			                      	stack.push(curThread);
			                    
			{
			setState(142);
			cmd();
			}
				listaTrue = stack.pop();	
			                    
			setState(144);
			match(T__9);
				curThread = new ArrayList<AbstractCommand>(); 
			                      	stack.push(curThread);
			                    
			{
			setState(146);
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
			setState(149);
			match(T__10);
			setState(150);
			match(AP);
			 exprTypeList = new ArrayList<String>(); 
			setState(152);
			match(ID);
			 _exprDecision = _input.LT(-1).getText(); 
												  verificaID(_exprDecision);
												  _left	 = getTypeByID(_exprDecision); 
										
			setState(154);
			match(OPREL);
			 _exprDecision += _input.LT(-1).getText(); 
			setState(156);
			termcomp();
			 	String id = _input.LT(-1).getText();
													_exprDecision += id;
													_right = verifyAndGetType(id);
										
			setState(158);
			match(FP);
			 	if(_left != _right) {
													throw new ProjSemanticException("Incompatible types " + _left + " and " + _right + " in " + _exprDecision);
												}
										
			setState(160);
			match(ACH);
				curThread = new ArrayList<AbstractCommand>();
												stack.push(curThread);
										
			setState(163); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(162);
				cmd();
				}
				}
				setState(165); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__10) | (1L << T__11) | (1L << ID))) != 0) );
			setState(167);
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
		enterRule(_localctx, 24, RULE_cmdfazerate);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(170);
			match(T__11);
			setState(171);
			match(ACH);
				curThread = new ArrayList<AbstractCommand>();
											stack.push(curThread);
									
			setState(174); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(173);
				cmd();
				}
				}
				setState(176); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__10) | (1L << T__11) | (1L << ID))) != 0) );
			setState(178);
			match(FCH);
			setState(179);
			match(T__12);
			setState(180);
			match(AP);
			setState(181);
			match(ID);
				_exprDecision = _input.LT(-1).getText(); 
											verificaID(_exprDecision);
											_left	 = getTypeByID(_exprDecision); 
									
			setState(183);
			match(OPREL);
			 _exprDecision += _input.LT(-1).getText(); 
			setState(185);
			termcomp();
			 	String id = _input.LT(-1).getText();
												_exprDecision += id;
												_right = verifyAndGetType(id);
									
			setState(187);
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
		enterRule(_localctx, 26, RULE_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(190);
			termo();
			setState(196);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OP) {
				{
				{
				setState(191);
				match(OP);
				 _exprContent += _input.LT(-1).getText();
				setState(193);
				termo();
				}
				}
				setState(198);
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
		enterRule(_localctx, 28, RULE_termcomp);
		try {
			setState(203);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(199);
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
				setState(201);
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
		enterRule(_localctx, 30, RULE_termo);
		try {
			setState(208);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
			case NUMBER:
				enterOuterAlt(_localctx, 1);
				{
				setState(205);
				termcomp();
				}
				break;
			case TEXT:
				enterOuterAlt(_localctx, 2);
				{
				setState(206);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\34\u00d5\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\3\6\3*\n\3\r\3\16\3+\3\4\3\4\3\4\3\4\3\4\3\4\7\4\64"+
		"\n\4\f\4\16\4\67\13\4\3\4\3\4\3\5\3\5\6\5=\n\5\r\5\16\5>\3\6\3\6\3\6\3"+
		"\6\5\6E\n\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7N\n\7\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\6\13v\n\13\r\13\16\13w\3\13\3\13\3\13\3\13\3\13\3\13\6\13\u0080"+
		"\n\13\r\13\16\13\u0081\3\13\3\13\3\13\5\13\u0087\n\13\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\6\r\u00a6\n\r\r\r\16\r\u00a7\3\r\3\r\3"+
		"\r\3\16\3\16\3\16\3\16\6\16\u00b1\n\16\r\16\16\16\u00b2\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\7\17"+
		"\u00c5\n\17\f\17\16\17\u00c8\13\17\3\20\3\20\3\20\3\20\5\20\u00ce\n\20"+
		"\3\21\3\21\3\21\5\21\u00d3\n\21\3\21\2\2\22\2\4\6\b\n\f\16\20\22\24\26"+
		"\30\32\34\36 \2\2\2\u00d6\2\"\3\2\2\2\4)\3\2\2\2\6-\3\2\2\2\b:\3\2\2\2"+
		"\nD\3\2\2\2\fM\3\2\2\2\16O\3\2\2\2\20W\3\2\2\2\22_\3\2\2\2\24g\3\2\2\2"+
		"\26\u0088\3\2\2\2\30\u0097\3\2\2\2\32\u00ac\3\2\2\2\34\u00c0\3\2\2\2\36"+
		"\u00cd\3\2\2\2 \u00d2\3\2\2\2\"#\7\3\2\2#$\5\4\3\2$%\5\b\5\2%&\7\4\2\2"+
		"&\'\b\2\1\2\'\3\3\2\2\2(*\5\6\4\2)(\3\2\2\2*+\3\2\2\2+)\3\2\2\2+,\3\2"+
		"\2\2,\5\3\2\2\2-.\5\n\6\2./\7\31\2\2/\65\b\4\1\2\60\61\7\25\2\2\61\62"+
		"\7\31\2\2\62\64\b\4\1\2\63\60\3\2\2\2\64\67\3\2\2\2\65\63\3\2\2\2\65\66"+
		"\3\2\2\2\668\3\2\2\2\67\65\3\2\2\289\7\22\2\29\7\3\2\2\2:<\b\5\1\2;=\5"+
		"\f\7\2<;\3\2\2\2=>\3\2\2\2><\3\2\2\2>?\3\2\2\2?\t\3\2\2\2@A\7\5\2\2AE"+
		"\b\6\1\2BC\7\6\2\2CE\b\6\1\2D@\3\2\2\2DB\3\2\2\2E\13\3\2\2\2FN\5\16\b"+
		"\2GN\5\20\t\2HN\5\22\n\2IN\5\24\13\2JN\5\30\r\2KN\5\32\16\2LN\5\26\f\2"+
		"MF\3\2\2\2MG\3\2\2\2MH\3\2\2\2MI\3\2\2\2MJ\3\2\2\2MK\3\2\2\2ML\3\2\2\2"+
		"N\r\3\2\2\2OP\7\7\2\2PQ\7\20\2\2QR\7\31\2\2RS\b\b\1\2ST\7\21\2\2TU\7\22"+
		"\2\2UV\b\b\1\2V\17\3\2\2\2WX\7\b\2\2XY\7\20\2\2YZ\7\31\2\2Z[\b\t\1\2["+
		"\\\7\21\2\2\\]\7\22\2\2]^\b\t\1\2^\21\3\2\2\2_`\7\31\2\2`a\b\n\1\2ab\7"+
		"\24\2\2bc\b\n\1\2cd\5\34\17\2de\7\22\2\2ef\b\n\1\2f\23\3\2\2\2gh\7\t\2"+
		"\2hi\7\20\2\2ij\b\13\1\2jk\7\31\2\2kl\b\13\1\2lm\7\30\2\2mn\b\13\1\2n"+
		"o\5\36\20\2op\b\13\1\2pq\7\21\2\2qr\b\13\1\2rs\7\26\2\2su\b\13\1\2tv\5"+
		"\f\7\2ut\3\2\2\2vw\3\2\2\2wu\3\2\2\2wx\3\2\2\2xy\3\2\2\2yz\7\27\2\2z\u0086"+
		"\b\13\1\2{|\7\n\2\2|}\7\26\2\2}\177\b\13\1\2~\u0080\5\f\7\2\177~\3\2\2"+
		"\2\u0080\u0081\3\2\2\2\u0081\177\3\2\2\2\u0081\u0082\3\2\2\2\u0082\u0083"+
		"\3\2\2\2\u0083\u0084\7\27\2\2\u0084\u0085\b\13\1\2\u0085\u0087\3\2\2\2"+
		"\u0086{\3\2\2\2\u0086\u0087\3\2\2\2\u0087\25\3\2\2\2\u0088\u0089\7\31"+
		"\2\2\u0089\u008a\b\f\1\2\u008a\u008b\7\30\2\2\u008b\u008c\b\f\1\2\u008c"+
		"\u008d\5\36\20\2\u008d\u008e\b\f\1\2\u008e\u008f\7\13\2\2\u008f\u0090"+
		"\b\f\1\2\u0090\u0091\5\f\7\2\u0091\u0092\b\f\1\2\u0092\u0093\7\f\2\2\u0093"+
		"\u0094\b\f\1\2\u0094\u0095\5\f\7\2\u0095\u0096\b\f\1\2\u0096\27\3\2\2"+
		"\2\u0097\u0098\7\r\2\2\u0098\u0099\7\20\2\2\u0099\u009a\b\r\1\2\u009a"+
		"\u009b\7\31\2\2\u009b\u009c\b\r\1\2\u009c\u009d\7\30\2\2\u009d\u009e\b"+
		"\r\1\2\u009e\u009f\5\36\20\2\u009f\u00a0\b\r\1\2\u00a0\u00a1\7\21\2\2"+
		"\u00a1\u00a2\b\r\1\2\u00a2\u00a3\7\26\2\2\u00a3\u00a5\b\r\1\2\u00a4\u00a6"+
		"\5\f\7\2\u00a5\u00a4\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00a5\3\2\2\2\u00a7"+
		"\u00a8\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9\u00aa\7\27\2\2\u00aa\u00ab\b"+
		"\r\1\2\u00ab\31\3\2\2\2\u00ac\u00ad\7\16\2\2\u00ad\u00ae\7\26\2\2\u00ae"+
		"\u00b0\b\16\1\2\u00af\u00b1\5\f\7\2\u00b0\u00af\3\2\2\2\u00b1\u00b2\3"+
		"\2\2\2\u00b2\u00b0\3\2\2\2\u00b2\u00b3\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4"+
		"\u00b5\7\27\2\2\u00b5\u00b6\7\17\2\2\u00b6\u00b7\7\20\2\2\u00b7\u00b8"+
		"\7\31\2\2\u00b8\u00b9\b\16\1\2\u00b9\u00ba\7\30\2\2\u00ba\u00bb\b\16\1"+
		"\2\u00bb\u00bc\5\36\20\2\u00bc\u00bd\b\16\1\2\u00bd\u00be\7\21\2\2\u00be"+
		"\u00bf\b\16\1\2\u00bf\33\3\2\2\2\u00c0\u00c6\5 \21\2\u00c1\u00c2\7\23"+
		"\2\2\u00c2\u00c3\b\17\1\2\u00c3\u00c5\5 \21\2\u00c4\u00c1\3\2\2\2\u00c5"+
		"\u00c8\3\2\2\2\u00c6\u00c4\3\2\2\2\u00c6\u00c7\3\2\2\2\u00c7\35\3\2\2"+
		"\2\u00c8\u00c6\3\2\2\2\u00c9\u00ca\7\31\2\2\u00ca\u00ce\b\20\1\2\u00cb"+
		"\u00cc\7\32\2\2\u00cc\u00ce\b\20\1\2\u00cd\u00c9\3\2\2\2\u00cd\u00cb\3"+
		"\2\2\2\u00ce\37\3\2\2\2\u00cf\u00d3\5\36\20\2\u00d0\u00d1\7\33\2\2\u00d1"+
		"\u00d3\b\21\1\2\u00d2\u00cf\3\2\2\2\u00d2\u00d0\3\2\2\2\u00d3!\3\2\2\2"+
		"\17+\65>DMw\u0081\u0086\u00a7\u00b2\u00c6\u00cd\u00d2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}