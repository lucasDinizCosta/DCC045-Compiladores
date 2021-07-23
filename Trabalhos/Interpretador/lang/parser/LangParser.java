// Generated from Lang.g4 by ANTLR 4.9.2

/********************************************************
* Trabalho de Teoria dos Compiladores(DCC045)(2021/1)   *
*                                                       *
* Projeto do Analisador Sintático para a Linguagem Lang *
* Nome: Lucas Diniz da Costa -- Matricula: 201465524C   *
*                                                       *
*********************************************************/

package lang.parser;    

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class LangParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		EOL=1, WS=2, SINGLE_LINE_COMMENT=3, MULTI_LINE_COMMENT=4, INT_TYPE=5, 
		FLOAT_TYPE=6, CHAR_TYPE=7, BOOL_TYPE=8, DATA_TYPE=9, IF=10, ELSE=11, ITERATE=12, 
		READ=13, PRINT=14, RETURN=15, NEW=16, FALSE=17, TRUE=18, NULL=19, AND=20, 
		EQUALITY=21, DIFFERENCE=22, EXCLAMATION=23, COMMA=24, DOT=25, SEMI=26, 
		COLON=27, DOUBLE_COLON=28, LESS_THAN=29, GREATER_THAN=30, EQUALS=31, TIMES=32, 
		PLUS=33, MINUS=34, SLASH=35, PERCENT=36, OPEN_BRACKET=37, CLOSE_BRACKET=38, 
		OPEN_PARENT=39, CLOSE_PARENT=40, OPEN_BRACES=41, CLOSE_BRACES=42, ID=43, 
		NAME_TYPE=44, INT=45, FLOAT=46, CHAR=47;
	public static final int
		RULE_prog = 0, RULE_data = 1, RULE_decl = 2, RULE_func = 3, RULE_params = 4, 
		RULE_type = 5, RULE_btype = 6, RULE_cmd = 7, RULE_exp = 8, RULE_rexp = 9, 
		RULE_aexp = 10, RULE_mexp = 11, RULE_sexp = 12, RULE_pexp = 13, RULE_lvalue = 14, 
		RULE_exps = 15;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "data", "decl", "func", "params", "type", "btype", "cmd", "exp", 
			"rexp", "aexp", "mexp", "sexp", "pexp", "lvalue", "exps"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, "'Int'", "'Float'", "'Char'", "'Bool'", 
			"'data'", "'if'", "'else'", "'iterate'", "'read'", "'print'", "'return'", 
			"'new'", "'false'", "'true'", "'null'", "'&&'", "'=='", "'!='", "'!'", 
			"','", "'.'", "';'", "':'", "'::'", "'<'", "'>'", "'='", "'*'", "'+'", 
			"'-'", "'/'", "'%'", "'['", "']'", "'('", "')'", "'{'", "'}'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "EOL", "WS", "SINGLE_LINE_COMMENT", "MULTI_LINE_COMMENT", "INT_TYPE", 
			"FLOAT_TYPE", "CHAR_TYPE", "BOOL_TYPE", "DATA_TYPE", "IF", "ELSE", "ITERATE", 
			"READ", "PRINT", "RETURN", "NEW", "FALSE", "TRUE", "NULL", "AND", "EQUALITY", 
			"DIFFERENCE", "EXCLAMATION", "COMMA", "DOT", "SEMI", "COLON", "DOUBLE_COLON", 
			"LESS_THAN", "GREATER_THAN", "EQUALS", "TIMES", "PLUS", "MINUS", "SLASH", 
			"PERCENT", "OPEN_BRACKET", "CLOSE_BRACKET", "OPEN_PARENT", "CLOSE_PARENT", 
			"OPEN_BRACES", "CLOSE_BRACES", "ID", "NAME_TYPE", "INT", "FLOAT", "CHAR"
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
	public String getGrammarFileName() { return "Lang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public LangParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgContext extends ParserRuleContext {
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
	 
		public ProgContext() { }
		public void copyFrom(ProgContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ProgramContext extends ProgContext {
		public List<DataContext> data() {
			return getRuleContexts(DataContext.class);
		}
		public DataContext data(int i) {
			return getRuleContext(DataContext.class,i);
		}
		public List<FuncContext> func() {
			return getRuleContexts(FuncContext.class);
		}
		public FuncContext func(int i) {
			return getRuleContext(FuncContext.class,i);
		}
		public ProgramContext(ProgContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		int _la;
		try {
			_localctx = new ProgramContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(35);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DATA_TYPE) {
				{
				{
				setState(32);
				data();
				}
				}
				setState(37);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(41);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID) {
				{
				{
				setState(38);
				func();
				}
				}
				setState(43);
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

	public static class DataContext extends ParserRuleContext {
		public DataContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_data; }
	 
		public DataContext() { }
		public void copyFrom(DataContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class DataDeclarationContext extends DataContext {
		public TerminalNode DATA_TYPE() { return getToken(LangParser.DATA_TYPE, 0); }
		public TerminalNode NAME_TYPE() { return getToken(LangParser.NAME_TYPE, 0); }
		public TerminalNode OPEN_BRACES() { return getToken(LangParser.OPEN_BRACES, 0); }
		public TerminalNode CLOSE_BRACES() { return getToken(LangParser.CLOSE_BRACES, 0); }
		public List<DeclContext> decl() {
			return getRuleContexts(DeclContext.class);
		}
		public DeclContext decl(int i) {
			return getRuleContext(DeclContext.class,i);
		}
		public DataDeclarationContext(DataContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterDataDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitDataDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitDataDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DataContext data() throws RecognitionException {
		DataContext _localctx = new DataContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_data);
		int _la;
		try {
			_localctx = new DataDeclarationContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(44);
			match(DATA_TYPE);
			setState(45);
			match(NAME_TYPE);
			setState(46);
			match(OPEN_BRACES);
			setState(50);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID) {
				{
				{
				setState(47);
				decl();
				}
				}
				setState(52);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(53);
			match(CLOSE_BRACES);
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
		public DeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl; }
	 
		public DeclContext() { }
		public void copyFrom(DeclContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class VarDeclarationContext extends DeclContext {
		public TerminalNode ID() { return getToken(LangParser.ID, 0); }
		public TerminalNode DOUBLE_COLON() { return getToken(LangParser.DOUBLE_COLON, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(LangParser.SEMI, 0); }
		public VarDeclarationContext(DeclContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterVarDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitVarDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitVarDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclContext decl() throws RecognitionException {
		DeclContext _localctx = new DeclContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_decl);
		try {
			_localctx = new VarDeclarationContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(55);
			match(ID);
			setState(56);
			match(DOUBLE_COLON);
			setState(57);
			type(0);
			setState(58);
			match(SEMI);
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

	public static class FuncContext extends ParserRuleContext {
		public FuncContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func; }
	 
		public FuncContext() { }
		public void copyFrom(FuncContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class FunctionContext extends FuncContext {
		public TerminalNode ID() { return getToken(LangParser.ID, 0); }
		public TerminalNode OPEN_PARENT() { return getToken(LangParser.OPEN_PARENT, 0); }
		public TerminalNode CLOSE_PARENT() { return getToken(LangParser.CLOSE_PARENT, 0); }
		public TerminalNode OPEN_BRACES() { return getToken(LangParser.OPEN_BRACES, 0); }
		public TerminalNode CLOSE_BRACES() { return getToken(LangParser.CLOSE_BRACES, 0); }
		public ParamsContext params() {
			return getRuleContext(ParamsContext.class,0);
		}
		public TerminalNode COLON() { return getToken(LangParser.COLON, 0); }
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(LangParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(LangParser.COMMA, i);
		}
		public FunctionContext(FuncContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitFunction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitFunction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncContext func() throws RecognitionException {
		FuncContext _localctx = new FuncContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_func);
		int _la;
		try {
			_localctx = new FunctionContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(60);
			match(ID);
			setState(61);
			match(OPEN_PARENT);
			setState(63);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(62);
				params();
				}
			}

			setState(65);
			match(CLOSE_PARENT);
			setState(75);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(66);
				match(COLON);
				setState(67);
				type(0);
				setState(72);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(68);
					match(COMMA);
					setState(69);
					type(0);
					}
					}
					setState(74);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(77);
			match(OPEN_BRACES);
			setState(81);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << ITERATE) | (1L << READ) | (1L << PRINT) | (1L << RETURN) | (1L << OPEN_BRACES) | (1L << ID))) != 0)) {
				{
				{
				setState(78);
				cmd();
				}
				}
				setState(83);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(84);
			match(CLOSE_BRACES);
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

	public static class ParamsContext extends ParserRuleContext {
		public ParamsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_params; }
	 
		public ParamsContext() { }
		public void copyFrom(ParamsContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ParametersFunctionContext extends ParamsContext {
		public List<TerminalNode> ID() { return getTokens(LangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(LangParser.ID, i);
		}
		public List<TerminalNode> DOUBLE_COLON() { return getTokens(LangParser.DOUBLE_COLON); }
		public TerminalNode DOUBLE_COLON(int i) {
			return getToken(LangParser.DOUBLE_COLON, i);
		}
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(LangParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(LangParser.COMMA, i);
		}
		public ParametersFunctionContext(ParamsContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterParametersFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitParametersFunction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitParametersFunction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamsContext params() throws RecognitionException {
		ParamsContext _localctx = new ParamsContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_params);
		int _la;
		try {
			_localctx = new ParametersFunctionContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(86);
			match(ID);
			setState(87);
			match(DOUBLE_COLON);
			setState(88);
			type(0);
			setState(95);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(89);
				match(COMMA);
				setState(90);
				match(ID);
				setState(91);
				match(DOUBLE_COLON);
				setState(92);
				type(0);
				}
				}
				setState(97);
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

	public static class TypeContext extends ParserRuleContext {
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
	 
		public TypeContext() { }
		public void copyFrom(TypeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class BTypeCallContext extends TypeContext {
		public BtypeContext btype() {
			return getRuleContext(BtypeContext.class,0);
		}
		public BTypeCallContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterBTypeCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitBTypeCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitBTypeCall(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TypeDeclarationContext extends TypeContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode OPEN_BRACKET() { return getToken(LangParser.OPEN_BRACKET, 0); }
		public TerminalNode CLOSE_BRACKET() { return getToken(LangParser.CLOSE_BRACKET, 0); }
		public TypeDeclarationContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterTypeDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitTypeDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitTypeDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		return type(0);
	}

	private TypeContext type(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		TypeContext _localctx = new TypeContext(_ctx, _parentState);
		TypeContext _prevctx = _localctx;
		int _startState = 10;
		enterRecursionRule(_localctx, 10, RULE_type, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new BTypeCallContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(99);
			btype();
			}
			_ctx.stop = _input.LT(-1);
			setState(106);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new TypeDeclarationContext(new TypeContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_type);
					setState(101);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(102);
					match(OPEN_BRACKET);
					setState(103);
					match(CLOSE_BRACKET);
					}
					} 
				}
				setState(108);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class BtypeContext extends ParserRuleContext {
		public BtypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_btype; }
	 
		public BtypeContext() { }
		public void copyFrom(BtypeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class BTypeBoolContext extends BtypeContext {
		public TerminalNode BOOL_TYPE() { return getToken(LangParser.BOOL_TYPE, 0); }
		public BTypeBoolContext(BtypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterBTypeBool(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitBTypeBool(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitBTypeBool(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BTypeFloatContext extends BtypeContext {
		public TerminalNode FLOAT_TYPE() { return getToken(LangParser.FLOAT_TYPE, 0); }
		public BTypeFloatContext(BtypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterBTypeFloat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitBTypeFloat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitBTypeFloat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BTypeCharContext extends BtypeContext {
		public TerminalNode CHAR_TYPE() { return getToken(LangParser.CHAR_TYPE, 0); }
		public BTypeCharContext(BtypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterBTypeChar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitBTypeChar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitBTypeChar(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BTypeIDContext extends BtypeContext {
		public TerminalNode ID() { return getToken(LangParser.ID, 0); }
		public BTypeIDContext(BtypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterBTypeID(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitBTypeID(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitBTypeID(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BTypeNameTypeContext extends BtypeContext {
		public TerminalNode NAME_TYPE() { return getToken(LangParser.NAME_TYPE, 0); }
		public BTypeNameTypeContext(BtypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterBTypeNameType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitBTypeNameType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitBTypeNameType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BTypeIntContext extends BtypeContext {
		public TerminalNode INT_TYPE() { return getToken(LangParser.INT_TYPE, 0); }
		public BTypeIntContext(BtypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterBTypeInt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitBTypeInt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitBTypeInt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BtypeContext btype() throws RecognitionException {
		BtypeContext _localctx = new BtypeContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_btype);
		try {
			setState(115);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT_TYPE:
				_localctx = new BTypeIntContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(109);
				match(INT_TYPE);
				}
				break;
			case CHAR_TYPE:
				_localctx = new BTypeCharContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(110);
				match(CHAR_TYPE);
				}
				break;
			case BOOL_TYPE:
				_localctx = new BTypeBoolContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(111);
				match(BOOL_TYPE);
				}
				break;
			case FLOAT_TYPE:
				_localctx = new BTypeFloatContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(112);
				match(FLOAT_TYPE);
				}
				break;
			case NAME_TYPE:
				_localctx = new BTypeNameTypeContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(113);
				match(NAME_TYPE);
				}
				break;
			case ID:
				_localctx = new BTypeIDContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(114);
				match(ID);
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
		public CmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd; }
	 
		public CmdContext() { }
		public void copyFrom(CmdContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ReadContext extends CmdContext {
		public TerminalNode READ() { return getToken(LangParser.READ, 0); }
		public LvalueContext lvalue() {
			return getRuleContext(LvalueContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(LangParser.SEMI, 0); }
		public ReadContext(CmdContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterRead(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitRead(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitRead(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PrintContext extends CmdContext {
		public TerminalNode PRINT() { return getToken(LangParser.PRINT, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(LangParser.SEMI, 0); }
		public PrintContext(CmdContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterPrint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitPrint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitPrint(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ReturnContext extends CmdContext {
		public TerminalNode RETURN() { return getToken(LangParser.RETURN, 0); }
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public TerminalNode SEMI() { return getToken(LangParser.SEMI, 0); }
		public List<TerminalNode> COMMA() { return getTokens(LangParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(LangParser.COMMA, i);
		}
		public ReturnContext(CmdContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterReturn(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitReturn(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitReturn(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IfElseContext extends CmdContext {
		public TerminalNode IF() { return getToken(LangParser.IF, 0); }
		public TerminalNode OPEN_PARENT() { return getToken(LangParser.OPEN_PARENT, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode CLOSE_PARENT() { return getToken(LangParser.CLOSE_PARENT, 0); }
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(LangParser.ELSE, 0); }
		public IfElseContext(CmdContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterIfElse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitIfElse(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitIfElse(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IterateContext extends CmdContext {
		public TerminalNode ITERATE() { return getToken(LangParser.ITERATE, 0); }
		public TerminalNode OPEN_PARENT() { return getToken(LangParser.OPEN_PARENT, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode CLOSE_PARENT() { return getToken(LangParser.CLOSE_PARENT, 0); }
		public CmdContext cmd() {
			return getRuleContext(CmdContext.class,0);
		}
		public IterateContext(CmdContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterIterate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitIterate(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitIterate(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CommandsListContext extends CmdContext {
		public TerminalNode OPEN_BRACES() { return getToken(LangParser.OPEN_BRACES, 0); }
		public TerminalNode CLOSE_BRACES() { return getToken(LangParser.CLOSE_BRACES, 0); }
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public CommandsListContext(CmdContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterCommandsList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitCommandsList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitCommandsList(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FunctionCallContext extends CmdContext {
		public TerminalNode ID() { return getToken(LangParser.ID, 0); }
		public TerminalNode OPEN_PARENT() { return getToken(LangParser.OPEN_PARENT, 0); }
		public TerminalNode CLOSE_PARENT() { return getToken(LangParser.CLOSE_PARENT, 0); }
		public TerminalNode SEMI() { return getToken(LangParser.SEMI, 0); }
		public ExpsContext exps() {
			return getRuleContext(ExpsContext.class,0);
		}
		public TerminalNode LESS_THAN() { return getToken(LangParser.LESS_THAN, 0); }
		public List<LvalueContext> lvalue() {
			return getRuleContexts(LvalueContext.class);
		}
		public LvalueContext lvalue(int i) {
			return getRuleContext(LvalueContext.class,i);
		}
		public TerminalNode GREATER_THAN() { return getToken(LangParser.GREATER_THAN, 0); }
		public List<TerminalNode> COMMA() { return getTokens(LangParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(LangParser.COMMA, i);
		}
		public FunctionCallContext(CmdContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterFunctionCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitFunctionCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitFunctionCall(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IfContext extends CmdContext {
		public TerminalNode IF() { return getToken(LangParser.IF, 0); }
		public TerminalNode OPEN_PARENT() { return getToken(LangParser.OPEN_PARENT, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode CLOSE_PARENT() { return getToken(LangParser.CLOSE_PARENT, 0); }
		public CmdContext cmd() {
			return getRuleContext(CmdContext.class,0);
		}
		public IfContext(CmdContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterIf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitIf(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitIf(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AttributionContext extends CmdContext {
		public LvalueContext lvalue() {
			return getRuleContext(LvalueContext.class,0);
		}
		public TerminalNode EQUALS() { return getToken(LangParser.EQUALS, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(LangParser.SEMI, 0); }
		public AttributionContext(CmdContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterAttribution(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitAttribution(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitAttribution(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CmdContext cmd() throws RecognitionException {
		CmdContext _localctx = new CmdContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_cmd);
		int _la;
		try {
			setState(189);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				_localctx = new CommandsListContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(117);
				match(OPEN_BRACES);
				setState(121);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << ITERATE) | (1L << READ) | (1L << PRINT) | (1L << RETURN) | (1L << OPEN_BRACES) | (1L << ID))) != 0)) {
					{
					{
					setState(118);
					cmd();
					}
					}
					setState(123);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(124);
				match(CLOSE_BRACES);
				}
				break;
			case 2:
				_localctx = new IfContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(125);
				match(IF);
				setState(126);
				match(OPEN_PARENT);
				setState(127);
				exp(0);
				setState(128);
				match(CLOSE_PARENT);
				setState(129);
				cmd();
				}
				break;
			case 3:
				_localctx = new IfElseContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(131);
				match(IF);
				setState(132);
				match(OPEN_PARENT);
				setState(133);
				exp(0);
				setState(134);
				match(CLOSE_PARENT);
				setState(135);
				cmd();
				setState(136);
				match(ELSE);
				setState(137);
				cmd();
				}
				break;
			case 4:
				_localctx = new IterateContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(139);
				match(ITERATE);
				setState(140);
				match(OPEN_PARENT);
				setState(141);
				exp(0);
				setState(142);
				match(CLOSE_PARENT);
				setState(143);
				cmd();
				}
				break;
			case 5:
				_localctx = new ReadContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(145);
				match(READ);
				setState(146);
				lvalue(0);
				setState(147);
				match(SEMI);
				}
				break;
			case 6:
				_localctx = new PrintContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(149);
				match(PRINT);
				setState(150);
				exp(0);
				setState(151);
				match(SEMI);
				}
				break;
			case 7:
				_localctx = new ReturnContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(153);
				match(RETURN);
				setState(154);
				exp(0);
				setState(159);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(155);
					match(COMMA);
					setState(156);
					exp(0);
					}
					}
					setState(161);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(162);
				match(SEMI);
				}
				break;
			case 8:
				_localctx = new AttributionContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(164);
				lvalue(0);
				setState(165);
				match(EQUALS);
				setState(166);
				exp(0);
				setState(167);
				match(SEMI);
				}
				break;
			case 9:
				_localctx = new FunctionCallContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(169);
				match(ID);
				setState(170);
				match(OPEN_PARENT);
				setState(172);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NEW) | (1L << FALSE) | (1L << TRUE) | (1L << NULL) | (1L << EXCLAMATION) | (1L << MINUS) | (1L << OPEN_PARENT) | (1L << ID) | (1L << INT) | (1L << FLOAT) | (1L << CHAR))) != 0)) {
					{
					setState(171);
					exps();
					}
				}

				setState(174);
				match(CLOSE_PARENT);
				setState(186);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LESS_THAN) {
					{
					setState(175);
					match(LESS_THAN);
					setState(176);
					lvalue(0);
					setState(181);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(177);
						match(COMMA);
						setState(178);
						lvalue(0);
						}
						}
						setState(183);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(184);
					match(GREATER_THAN);
					}
				}

				setState(188);
				match(SEMI);
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

	public static class ExpContext extends ParserRuleContext {
		public ExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exp; }
	 
		public ExpContext() { }
		public void copyFrom(ExpContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class RExpCallContext extends ExpContext {
		public RexpContext rexp() {
			return getRuleContext(RexpContext.class,0);
		}
		public RExpCallContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterRExpCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitRExpCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitRExpCall(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AndOperationContext extends ExpContext {
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public TerminalNode AND() { return getToken(LangParser.AND, 0); }
		public AndOperationContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterAndOperation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitAndOperation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitAndOperation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpContext exp() throws RecognitionException {
		return exp(0);
	}

	private ExpContext exp(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpContext _localctx = new ExpContext(_ctx, _parentState);
		ExpContext _prevctx = _localctx;
		int _startState = 16;
		enterRecursionRule(_localctx, 16, RULE_exp, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new RExpCallContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(192);
			rexp(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(199);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new AndOperationContext(new ExpContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_exp);
					setState(194);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(195);
					match(AND);
					setState(196);
					exp(3);
					}
					} 
				}
				setState(201);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class RexpContext extends ParserRuleContext {
		public RexpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rexp; }
	 
		public RexpContext() { }
		public void copyFrom(RexpContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class AExpCallContext extends RexpContext {
		public AexpContext aexp() {
			return getRuleContext(AexpContext.class,0);
		}
		public AExpCallContext(RexpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterAExpCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitAExpCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitAExpCall(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LessThanContext extends RexpContext {
		public List<AexpContext> aexp() {
			return getRuleContexts(AexpContext.class);
		}
		public AexpContext aexp(int i) {
			return getRuleContext(AexpContext.class,i);
		}
		public TerminalNode LESS_THAN() { return getToken(LangParser.LESS_THAN, 0); }
		public LessThanContext(RexpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterLessThan(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitLessThan(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitLessThan(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EqualityContext extends RexpContext {
		public RexpContext rexp() {
			return getRuleContext(RexpContext.class,0);
		}
		public TerminalNode EQUALITY() { return getToken(LangParser.EQUALITY, 0); }
		public AexpContext aexp() {
			return getRuleContext(AexpContext.class,0);
		}
		public EqualityContext(RexpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterEquality(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitEquality(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitEquality(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DifferenceContext extends RexpContext {
		public RexpContext rexp() {
			return getRuleContext(RexpContext.class,0);
		}
		public TerminalNode DIFFERENCE() { return getToken(LangParser.DIFFERENCE, 0); }
		public AexpContext aexp() {
			return getRuleContext(AexpContext.class,0);
		}
		public DifferenceContext(RexpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterDifference(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitDifference(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitDifference(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RexpContext rexp() throws RecognitionException {
		return rexp(0);
	}

	private RexpContext rexp(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		RexpContext _localctx = new RexpContext(_ctx, _parentState);
		RexpContext _prevctx = _localctx;
		int _startState = 18;
		enterRecursionRule(_localctx, 18, RULE_rexp, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(208);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				{
				_localctx = new LessThanContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(203);
				aexp(0);
				setState(204);
				match(LESS_THAN);
				setState(205);
				aexp(0);
				}
				break;
			case 2:
				{
				_localctx = new AExpCallContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(207);
				aexp(0);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(218);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(216);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
					case 1:
						{
						_localctx = new EqualityContext(new RexpContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_rexp);
						setState(210);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(211);
						match(EQUALITY);
						setState(212);
						aexp(0);
						}
						break;
					case 2:
						{
						_localctx = new DifferenceContext(new RexpContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_rexp);
						setState(213);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(214);
						match(DIFFERENCE);
						setState(215);
						aexp(0);
						}
						break;
					}
					} 
				}
				setState(220);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class AexpContext extends ParserRuleContext {
		public AexpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aexp; }
	 
		public AexpContext() { }
		public void copyFrom(AexpContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class AdditionOperationContext extends AexpContext {
		public AexpContext aexp() {
			return getRuleContext(AexpContext.class,0);
		}
		public TerminalNode PLUS() { return getToken(LangParser.PLUS, 0); }
		public MexpContext mexp() {
			return getRuleContext(MexpContext.class,0);
		}
		public AdditionOperationContext(AexpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterAdditionOperation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitAdditionOperation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitAdditionOperation(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SubtractionOperationContext extends AexpContext {
		public AexpContext aexp() {
			return getRuleContext(AexpContext.class,0);
		}
		public TerminalNode MINUS() { return getToken(LangParser.MINUS, 0); }
		public MexpContext mexp() {
			return getRuleContext(MexpContext.class,0);
		}
		public SubtractionOperationContext(AexpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterSubtractionOperation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitSubtractionOperation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitSubtractionOperation(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MExpCallContext extends AexpContext {
		public MexpContext mexp() {
			return getRuleContext(MexpContext.class,0);
		}
		public MExpCallContext(AexpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterMExpCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitMExpCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitMExpCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AexpContext aexp() throws RecognitionException {
		return aexp(0);
	}

	private AexpContext aexp(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		AexpContext _localctx = new AexpContext(_ctx, _parentState);
		AexpContext _prevctx = _localctx;
		int _startState = 20;
		enterRecursionRule(_localctx, 20, RULE_aexp, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new MExpCallContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(222);
			mexp(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(232);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(230);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
					case 1:
						{
						_localctx = new AdditionOperationContext(new AexpContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_aexp);
						setState(224);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(225);
						match(PLUS);
						setState(226);
						mexp(0);
						}
						break;
					case 2:
						{
						_localctx = new SubtractionOperationContext(new AexpContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_aexp);
						setState(227);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(228);
						match(MINUS);
						setState(229);
						mexp(0);
						}
						break;
					}
					} 
				}
				setState(234);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class MexpContext extends ParserRuleContext {
		public MexpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mexp; }
	 
		public MexpContext() { }
		public void copyFrom(MexpContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class DivisionOperationContext extends MexpContext {
		public MexpContext mexp() {
			return getRuleContext(MexpContext.class,0);
		}
		public TerminalNode SLASH() { return getToken(LangParser.SLASH, 0); }
		public SexpContext sexp() {
			return getRuleContext(SexpContext.class,0);
		}
		public DivisionOperationContext(MexpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterDivisionOperation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitDivisionOperation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitDivisionOperation(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SExpCallContext extends MexpContext {
		public SexpContext sexp() {
			return getRuleContext(SexpContext.class,0);
		}
		public SExpCallContext(MexpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterSExpCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitSExpCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitSExpCall(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MultiplicationOperationContext extends MexpContext {
		public MexpContext mexp() {
			return getRuleContext(MexpContext.class,0);
		}
		public TerminalNode TIMES() { return getToken(LangParser.TIMES, 0); }
		public SexpContext sexp() {
			return getRuleContext(SexpContext.class,0);
		}
		public MultiplicationOperationContext(MexpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterMultiplicationOperation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitMultiplicationOperation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitMultiplicationOperation(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ModularOperationContext extends MexpContext {
		public MexpContext mexp() {
			return getRuleContext(MexpContext.class,0);
		}
		public TerminalNode PERCENT() { return getToken(LangParser.PERCENT, 0); }
		public SexpContext sexp() {
			return getRuleContext(SexpContext.class,0);
		}
		public ModularOperationContext(MexpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterModularOperation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitModularOperation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitModularOperation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MexpContext mexp() throws RecognitionException {
		return mexp(0);
	}

	private MexpContext mexp(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		MexpContext _localctx = new MexpContext(_ctx, _parentState);
		MexpContext _prevctx = _localctx;
		int _startState = 22;
		enterRecursionRule(_localctx, 22, RULE_mexp, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new SExpCallContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(236);
			sexp();
			}
			_ctx.stop = _input.LT(-1);
			setState(249);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(247);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
					case 1:
						{
						_localctx = new MultiplicationOperationContext(new MexpContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_mexp);
						setState(238);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(239);
						match(TIMES);
						setState(240);
						sexp();
						}
						break;
					case 2:
						{
						_localctx = new DivisionOperationContext(new MexpContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_mexp);
						setState(241);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(242);
						match(SLASH);
						setState(243);
						sexp();
						}
						break;
					case 3:
						{
						_localctx = new ModularOperationContext(new MexpContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_mexp);
						setState(244);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(245);
						match(PERCENT);
						setState(246);
						sexp();
						}
						break;
					}
					} 
				}
				setState(251);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class SexpContext extends ParserRuleContext {
		public SexpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sexp; }
	 
		public SexpContext() { }
		public void copyFrom(SexpContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class PExpCallContext extends SexpContext {
		public PexpContext pexp() {
			return getRuleContext(PexpContext.class,0);
		}
		public PExpCallContext(SexpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterPExpCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitPExpCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitPExpCall(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NotContext extends SexpContext {
		public TerminalNode EXCLAMATION() { return getToken(LangParser.EXCLAMATION, 0); }
		public SexpContext sexp() {
			return getRuleContext(SexpContext.class,0);
		}
		public NotContext(SexpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterNot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitNot(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitNot(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NullContext extends SexpContext {
		public TerminalNode NULL() { return getToken(LangParser.NULL, 0); }
		public NullContext(SexpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterNull(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitNull(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitNull(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FloatNumberContext extends SexpContext {
		public TerminalNode FLOAT() { return getToken(LangParser.FLOAT, 0); }
		public FloatNumberContext(SexpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterFloatNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitFloatNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitFloatNumber(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IntegerNumberContext extends SexpContext {
		public TerminalNode INT() { return getToken(LangParser.INT, 0); }
		public IntegerNumberContext(SexpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterIntegerNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitIntegerNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitIntegerNumber(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TrueContext extends SexpContext {
		public TerminalNode TRUE() { return getToken(LangParser.TRUE, 0); }
		public TrueContext(SexpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterTrue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitTrue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitTrue(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FalseContext extends SexpContext {
		public TerminalNode FALSE() { return getToken(LangParser.FALSE, 0); }
		public FalseContext(SexpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterFalse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitFalse(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitFalse(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CharLitteralContext extends SexpContext {
		public TerminalNode CHAR() { return getToken(LangParser.CHAR, 0); }
		public CharLitteralContext(SexpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterCharLitteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitCharLitteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitCharLitteral(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MinusContext extends SexpContext {
		public TerminalNode MINUS() { return getToken(LangParser.MINUS, 0); }
		public SexpContext sexp() {
			return getRuleContext(SexpContext.class,0);
		}
		public MinusContext(SexpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterMinus(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitMinus(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitMinus(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SexpContext sexp() throws RecognitionException {
		SexpContext _localctx = new SexpContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_sexp);
		try {
			setState(263);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case EXCLAMATION:
				_localctx = new NotContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(252);
				match(EXCLAMATION);
				setState(253);
				sexp();
				}
				break;
			case MINUS:
				_localctx = new MinusContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(254);
				match(MINUS);
				setState(255);
				sexp();
				}
				break;
			case TRUE:
				_localctx = new TrueContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(256);
				match(TRUE);
				}
				break;
			case FALSE:
				_localctx = new FalseContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(257);
				match(FALSE);
				}
				break;
			case NULL:
				_localctx = new NullContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(258);
				match(NULL);
				}
				break;
			case INT:
				_localctx = new IntegerNumberContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(259);
				match(INT);
				}
				break;
			case FLOAT:
				_localctx = new FloatNumberContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(260);
				match(FLOAT);
				}
				break;
			case CHAR:
				_localctx = new CharLitteralContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(261);
				match(CHAR);
				}
				break;
			case NEW:
			case OPEN_PARENT:
			case ID:
				_localctx = new PExpCallContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(262);
				pexp();
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

	public static class PexpContext extends ParserRuleContext {
		public PexpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pexp; }
	 
		public PexpContext() { }
		public void copyFrom(PexpContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class FunctionReturnContext extends PexpContext {
		public TerminalNode ID() { return getToken(LangParser.ID, 0); }
		public TerminalNode OPEN_PARENT() { return getToken(LangParser.OPEN_PARENT, 0); }
		public TerminalNode CLOSE_PARENT() { return getToken(LangParser.CLOSE_PARENT, 0); }
		public TerminalNode OPEN_BRACKET() { return getToken(LangParser.OPEN_BRACKET, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode CLOSE_BRACKET() { return getToken(LangParser.CLOSE_BRACKET, 0); }
		public ExpsContext exps() {
			return getRuleContext(ExpsContext.class,0);
		}
		public FunctionReturnContext(PexpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterFunctionReturn(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitFunctionReturn(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitFunctionReturn(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TypeInstanciateContext extends PexpContext {
		public TerminalNode NEW() { return getToken(LangParser.NEW, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode OPEN_BRACKET() { return getToken(LangParser.OPEN_BRACKET, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode CLOSE_BRACKET() { return getToken(LangParser.CLOSE_BRACKET, 0); }
		public TypeInstanciateContext(PexpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterTypeInstanciate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitTypeInstanciate(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitTypeInstanciate(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpParenthesisContext extends PexpContext {
		public TerminalNode OPEN_PARENT() { return getToken(LangParser.OPEN_PARENT, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode CLOSE_PARENT() { return getToken(LangParser.CLOSE_PARENT, 0); }
		public ExpParenthesisContext(PexpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterExpParenthesis(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitExpParenthesis(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitExpParenthesis(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PexpIdentifierContext extends PexpContext {
		public LvalueContext lvalue() {
			return getRuleContext(LvalueContext.class,0);
		}
		public PexpIdentifierContext(PexpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterPexpIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitPexpIdentifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitPexpIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PexpContext pexp() throws RecognitionException {
		PexpContext _localctx = new PexpContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_pexp);
		int _la;
		try {
			setState(288);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
			case 1:
				_localctx = new PexpIdentifierContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(265);
				lvalue(0);
				}
				break;
			case 2:
				_localctx = new ExpParenthesisContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(266);
				match(OPEN_PARENT);
				setState(267);
				exp(0);
				setState(268);
				match(CLOSE_PARENT);
				}
				break;
			case 3:
				_localctx = new TypeInstanciateContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(270);
				match(NEW);
				setState(271);
				type(0);
				setState(276);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
				case 1:
					{
					setState(272);
					match(OPEN_BRACKET);
					setState(273);
					exp(0);
					setState(274);
					match(CLOSE_BRACKET);
					}
					break;
				}
				}
				break;
			case 4:
				_localctx = new FunctionReturnContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(278);
				match(ID);
				setState(279);
				match(OPEN_PARENT);
				setState(281);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NEW) | (1L << FALSE) | (1L << TRUE) | (1L << NULL) | (1L << EXCLAMATION) | (1L << MINUS) | (1L << OPEN_PARENT) | (1L << ID) | (1L << INT) | (1L << FLOAT) | (1L << CHAR))) != 0)) {
					{
					setState(280);
					exps();
					}
				}

				setState(283);
				match(CLOSE_PARENT);
				setState(284);
				match(OPEN_BRACKET);
				setState(285);
				exp(0);
				setState(286);
				match(CLOSE_BRACKET);
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

	public static class LvalueContext extends ParserRuleContext {
		public LvalueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lvalue; }
	 
		public LvalueContext() { }
		public void copyFrom(LvalueContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ArrayAccessContext extends LvalueContext {
		public LvalueContext lvalue() {
			return getRuleContext(LvalueContext.class,0);
		}
		public TerminalNode OPEN_BRACKET() { return getToken(LangParser.OPEN_BRACKET, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode CLOSE_BRACKET() { return getToken(LangParser.CLOSE_BRACKET, 0); }
		public ArrayAccessContext(LvalueContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterArrayAccess(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitArrayAccess(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitArrayAccess(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IdentifierContext extends LvalueContext {
		public TerminalNode ID() { return getToken(LangParser.ID, 0); }
		public IdentifierContext(LvalueContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitIdentifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DataAccessContext extends LvalueContext {
		public LvalueContext lvalue() {
			return getRuleContext(LvalueContext.class,0);
		}
		public TerminalNode DOT() { return getToken(LangParser.DOT, 0); }
		public TerminalNode ID() { return getToken(LangParser.ID, 0); }
		public DataAccessContext(LvalueContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterDataAccess(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitDataAccess(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitDataAccess(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LvalueContext lvalue() throws RecognitionException {
		return lvalue(0);
	}

	private LvalueContext lvalue(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		LvalueContext _localctx = new LvalueContext(_ctx, _parentState);
		LvalueContext _prevctx = _localctx;
		int _startState = 28;
		enterRecursionRule(_localctx, 28, RULE_lvalue, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new IdentifierContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(291);
			match(ID);
			}
			_ctx.stop = _input.LT(-1);
			setState(303);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(301);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
					case 1:
						{
						_localctx = new ArrayAccessContext(new LvalueContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_lvalue);
						setState(293);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(294);
						match(OPEN_BRACKET);
						setState(295);
						exp(0);
						setState(296);
						match(CLOSE_BRACKET);
						}
						break;
					case 2:
						{
						_localctx = new DataAccessContext(new LvalueContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_lvalue);
						setState(298);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(299);
						match(DOT);
						setState(300);
						match(ID);
						}
						break;
					}
					} 
				}
				setState(305);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ExpsContext extends ParserRuleContext {
		public ExpsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exps; }
	 
		public ExpsContext() { }
		public void copyFrom(ExpsContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class FCallParamsContext extends ExpsContext {
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(LangParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(LangParser.COMMA, i);
		}
		public FCallParamsContext(ExpsContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterFCallParams(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitFCallParams(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitFCallParams(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpsContext exps() throws RecognitionException {
		ExpsContext _localctx = new ExpsContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_exps);
		int _la;
		try {
			_localctx = new FCallParamsContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(306);
			exp(0);
			setState(311);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(307);
				match(COMMA);
				setState(308);
				exp(0);
				}
				}
				setState(313);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 5:
			return type_sempred((TypeContext)_localctx, predIndex);
		case 8:
			return exp_sempred((ExpContext)_localctx, predIndex);
		case 9:
			return rexp_sempred((RexpContext)_localctx, predIndex);
		case 10:
			return aexp_sempred((AexpContext)_localctx, predIndex);
		case 11:
			return mexp_sempred((MexpContext)_localctx, predIndex);
		case 14:
			return lvalue_sempred((LvalueContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean type_sempred(TypeContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean exp_sempred(ExpContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean rexp_sempred(RexpContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 3);
		case 3:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean aexp_sempred(AexpContext _localctx, int predIndex) {
		switch (predIndex) {
		case 4:
			return precpred(_ctx, 3);
		case 5:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean mexp_sempred(MexpContext _localctx, int predIndex) {
		switch (predIndex) {
		case 6:
			return precpred(_ctx, 4);
		case 7:
			return precpred(_ctx, 3);
		case 8:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean lvalue_sempred(LvalueContext _localctx, int predIndex) {
		switch (predIndex) {
		case 9:
			return precpred(_ctx, 2);
		case 10:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\61\u013d\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\7\2"+
		"$\n\2\f\2\16\2\'\13\2\3\2\7\2*\n\2\f\2\16\2-\13\2\3\3\3\3\3\3\3\3\7\3"+
		"\63\n\3\f\3\16\3\66\13\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\5\5B"+
		"\n\5\3\5\3\5\3\5\3\5\3\5\7\5I\n\5\f\5\16\5L\13\5\5\5N\n\5\3\5\3\5\7\5"+
		"R\n\5\f\5\16\5U\13\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\7\6`\n\6\f\6"+
		"\16\6c\13\6\3\7\3\7\3\7\3\7\3\7\3\7\7\7k\n\7\f\7\16\7n\13\7\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\5\bv\n\b\3\t\3\t\7\tz\n\t\f\t\16\t}\13\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\7\t\u00a0\n\t\f\t\16"+
		"\t\u00a3\13\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u00af\n\t\3"+
		"\t\3\t\3\t\3\t\3\t\7\t\u00b6\n\t\f\t\16\t\u00b9\13\t\3\t\3\t\5\t\u00bd"+
		"\n\t\3\t\5\t\u00c0\n\t\3\n\3\n\3\n\3\n\3\n\3\n\7\n\u00c8\n\n\f\n\16\n"+
		"\u00cb\13\n\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u00d3\n\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\7\13\u00db\n\13\f\13\16\13\u00de\13\13\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\7\f\u00e9\n\f\f\f\16\f\u00ec\13\f\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\7\r\u00fa\n\r\f\r\16\r\u00fd\13\r"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u010a\n\16"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u0117\n\17"+
		"\3\17\3\17\3\17\5\17\u011c\n\17\3\17\3\17\3\17\3\17\3\17\5\17\u0123\n"+
		"\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\7\20\u0130"+
		"\n\20\f\20\16\20\u0133\13\20\3\21\3\21\3\21\7\21\u0138\n\21\f\21\16\21"+
		"\u013b\13\21\3\21\2\b\f\22\24\26\30\36\22\2\4\6\b\n\f\16\20\22\24\26\30"+
		"\32\34\36 \2\2\2\u0160\2%\3\2\2\2\4.\3\2\2\2\69\3\2\2\2\b>\3\2\2\2\nX"+
		"\3\2\2\2\fd\3\2\2\2\16u\3\2\2\2\20\u00bf\3\2\2\2\22\u00c1\3\2\2\2\24\u00d2"+
		"\3\2\2\2\26\u00df\3\2\2\2\30\u00ed\3\2\2\2\32\u0109\3\2\2\2\34\u0122\3"+
		"\2\2\2\36\u0124\3\2\2\2 \u0134\3\2\2\2\"$\5\4\3\2#\"\3\2\2\2$\'\3\2\2"+
		"\2%#\3\2\2\2%&\3\2\2\2&+\3\2\2\2\'%\3\2\2\2(*\5\b\5\2)(\3\2\2\2*-\3\2"+
		"\2\2+)\3\2\2\2+,\3\2\2\2,\3\3\2\2\2-+\3\2\2\2./\7\13\2\2/\60\7.\2\2\60"+
		"\64\7+\2\2\61\63\5\6\4\2\62\61\3\2\2\2\63\66\3\2\2\2\64\62\3\2\2\2\64"+
		"\65\3\2\2\2\65\67\3\2\2\2\66\64\3\2\2\2\678\7,\2\28\5\3\2\2\29:\7-\2\2"+
		":;\7\36\2\2;<\5\f\7\2<=\7\34\2\2=\7\3\2\2\2>?\7-\2\2?A\7)\2\2@B\5\n\6"+
		"\2A@\3\2\2\2AB\3\2\2\2BC\3\2\2\2CM\7*\2\2DE\7\35\2\2EJ\5\f\7\2FG\7\32"+
		"\2\2GI\5\f\7\2HF\3\2\2\2IL\3\2\2\2JH\3\2\2\2JK\3\2\2\2KN\3\2\2\2LJ\3\2"+
		"\2\2MD\3\2\2\2MN\3\2\2\2NO\3\2\2\2OS\7+\2\2PR\5\20\t\2QP\3\2\2\2RU\3\2"+
		"\2\2SQ\3\2\2\2ST\3\2\2\2TV\3\2\2\2US\3\2\2\2VW\7,\2\2W\t\3\2\2\2XY\7-"+
		"\2\2YZ\7\36\2\2Za\5\f\7\2[\\\7\32\2\2\\]\7-\2\2]^\7\36\2\2^`\5\f\7\2_"+
		"[\3\2\2\2`c\3\2\2\2a_\3\2\2\2ab\3\2\2\2b\13\3\2\2\2ca\3\2\2\2de\b\7\1"+
		"\2ef\5\16\b\2fl\3\2\2\2gh\f\4\2\2hi\7\'\2\2ik\7(\2\2jg\3\2\2\2kn\3\2\2"+
		"\2lj\3\2\2\2lm\3\2\2\2m\r\3\2\2\2nl\3\2\2\2ov\7\7\2\2pv\7\t\2\2qv\7\n"+
		"\2\2rv\7\b\2\2sv\7.\2\2tv\7-\2\2uo\3\2\2\2up\3\2\2\2uq\3\2\2\2ur\3\2\2"+
		"\2us\3\2\2\2ut\3\2\2\2v\17\3\2\2\2w{\7+\2\2xz\5\20\t\2yx\3\2\2\2z}\3\2"+
		"\2\2{y\3\2\2\2{|\3\2\2\2|~\3\2\2\2}{\3\2\2\2~\u00c0\7,\2\2\177\u0080\7"+
		"\f\2\2\u0080\u0081\7)\2\2\u0081\u0082\5\22\n\2\u0082\u0083\7*\2\2\u0083"+
		"\u0084\5\20\t\2\u0084\u00c0\3\2\2\2\u0085\u0086\7\f\2\2\u0086\u0087\7"+
		")\2\2\u0087\u0088\5\22\n\2\u0088\u0089\7*\2\2\u0089\u008a\5\20\t\2\u008a"+
		"\u008b\7\r\2\2\u008b\u008c\5\20\t\2\u008c\u00c0\3\2\2\2\u008d\u008e\7"+
		"\16\2\2\u008e\u008f\7)\2\2\u008f\u0090\5\22\n\2\u0090\u0091\7*\2\2\u0091"+
		"\u0092\5\20\t\2\u0092\u00c0\3\2\2\2\u0093\u0094\7\17\2\2\u0094\u0095\5"+
		"\36\20\2\u0095\u0096\7\34\2\2\u0096\u00c0\3\2\2\2\u0097\u0098\7\20\2\2"+
		"\u0098\u0099\5\22\n\2\u0099\u009a\7\34\2\2\u009a\u00c0\3\2\2\2\u009b\u009c"+
		"\7\21\2\2\u009c\u00a1\5\22\n\2\u009d\u009e\7\32\2\2\u009e\u00a0\5\22\n"+
		"\2\u009f\u009d\3\2\2\2\u00a0\u00a3\3\2\2\2\u00a1\u009f\3\2\2\2\u00a1\u00a2"+
		"\3\2\2\2\u00a2\u00a4\3\2\2\2\u00a3\u00a1\3\2\2\2\u00a4\u00a5\7\34\2\2"+
		"\u00a5\u00c0\3\2\2\2\u00a6\u00a7\5\36\20\2\u00a7\u00a8\7!\2\2\u00a8\u00a9"+
		"\5\22\n\2\u00a9\u00aa\7\34\2\2\u00aa\u00c0\3\2\2\2\u00ab\u00ac\7-\2\2"+
		"\u00ac\u00ae\7)\2\2\u00ad\u00af\5 \21\2\u00ae\u00ad\3\2\2\2\u00ae\u00af"+
		"\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0\u00bc\7*\2\2\u00b1\u00b2\7\37\2\2\u00b2"+
		"\u00b7\5\36\20\2\u00b3\u00b4\7\32\2\2\u00b4\u00b6\5\36\20\2\u00b5\u00b3"+
		"\3\2\2\2\u00b6\u00b9\3\2\2\2\u00b7\u00b5\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8"+
		"\u00ba\3\2\2\2\u00b9\u00b7\3\2\2\2\u00ba\u00bb\7 \2\2\u00bb\u00bd\3\2"+
		"\2\2\u00bc\u00b1\3\2\2\2\u00bc\u00bd\3\2\2\2\u00bd\u00be\3\2\2\2\u00be"+
		"\u00c0\7\34\2\2\u00bfw\3\2\2\2\u00bf\177\3\2\2\2\u00bf\u0085\3\2\2\2\u00bf"+
		"\u008d\3\2\2\2\u00bf\u0093\3\2\2\2\u00bf\u0097\3\2\2\2\u00bf\u009b\3\2"+
		"\2\2\u00bf\u00a6\3\2\2\2\u00bf\u00ab\3\2\2\2\u00c0\21\3\2\2\2\u00c1\u00c2"+
		"\b\n\1\2\u00c2\u00c3\5\24\13\2\u00c3\u00c9\3\2\2\2\u00c4\u00c5\f\4\2\2"+
		"\u00c5\u00c6\7\26\2\2\u00c6\u00c8\5\22\n\5\u00c7\u00c4\3\2\2\2\u00c8\u00cb"+
		"\3\2\2\2\u00c9\u00c7\3\2\2\2\u00c9\u00ca\3\2\2\2\u00ca\23\3\2\2\2\u00cb"+
		"\u00c9\3\2\2\2\u00cc\u00cd\b\13\1\2\u00cd\u00ce\5\26\f\2\u00ce\u00cf\7"+
		"\37\2\2\u00cf\u00d0\5\26\f\2\u00d0\u00d3\3\2\2\2\u00d1\u00d3\5\26\f\2"+
		"\u00d2\u00cc\3\2\2\2\u00d2\u00d1\3\2\2\2\u00d3\u00dc\3\2\2\2\u00d4\u00d5"+
		"\f\5\2\2\u00d5\u00d6\7\27\2\2\u00d6\u00db\5\26\f\2\u00d7\u00d8\f\4\2\2"+
		"\u00d8\u00d9\7\30\2\2\u00d9\u00db\5\26\f\2\u00da\u00d4\3\2\2\2\u00da\u00d7"+
		"\3\2\2\2\u00db\u00de\3\2\2\2\u00dc\u00da\3\2\2\2\u00dc\u00dd\3\2\2\2\u00dd"+
		"\25\3\2\2\2\u00de\u00dc\3\2\2\2\u00df\u00e0\b\f\1\2\u00e0\u00e1\5\30\r"+
		"\2\u00e1\u00ea\3\2\2\2\u00e2\u00e3\f\5\2\2\u00e3\u00e4\7#\2\2\u00e4\u00e9"+
		"\5\30\r\2\u00e5\u00e6\f\4\2\2\u00e6\u00e7\7$\2\2\u00e7\u00e9\5\30\r\2"+
		"\u00e8\u00e2\3\2\2\2\u00e8\u00e5\3\2\2\2\u00e9\u00ec\3\2\2\2\u00ea\u00e8"+
		"\3\2\2\2\u00ea\u00eb\3\2\2\2\u00eb\27\3\2\2\2\u00ec\u00ea\3\2\2\2\u00ed"+
		"\u00ee\b\r\1\2\u00ee\u00ef\5\32\16\2\u00ef\u00fb\3\2\2\2\u00f0\u00f1\f"+
		"\6\2\2\u00f1\u00f2\7\"\2\2\u00f2\u00fa\5\32\16\2\u00f3\u00f4\f\5\2\2\u00f4"+
		"\u00f5\7%\2\2\u00f5\u00fa\5\32\16\2\u00f6\u00f7\f\4\2\2\u00f7\u00f8\7"+
		"&\2\2\u00f8\u00fa\5\32\16\2\u00f9\u00f0\3\2\2\2\u00f9\u00f3\3\2\2\2\u00f9"+
		"\u00f6\3\2\2\2\u00fa\u00fd\3\2\2\2\u00fb\u00f9\3\2\2\2\u00fb\u00fc\3\2"+
		"\2\2\u00fc\31\3\2\2\2\u00fd\u00fb\3\2\2\2\u00fe\u00ff\7\31\2\2\u00ff\u010a"+
		"\5\32\16\2\u0100\u0101\7$\2\2\u0101\u010a\5\32\16\2\u0102\u010a\7\24\2"+
		"\2\u0103\u010a\7\23\2\2\u0104\u010a\7\25\2\2\u0105\u010a\7/\2\2\u0106"+
		"\u010a\7\60\2\2\u0107\u010a\7\61\2\2\u0108\u010a\5\34\17\2\u0109\u00fe"+
		"\3\2\2\2\u0109\u0100\3\2\2\2\u0109\u0102\3\2\2\2\u0109\u0103\3\2\2\2\u0109"+
		"\u0104\3\2\2\2\u0109\u0105\3\2\2\2\u0109\u0106\3\2\2\2\u0109\u0107\3\2"+
		"\2\2\u0109\u0108\3\2\2\2\u010a\33\3\2\2\2\u010b\u0123\5\36\20\2\u010c"+
		"\u010d\7)\2\2\u010d\u010e\5\22\n\2\u010e\u010f\7*\2\2\u010f\u0123\3\2"+
		"\2\2\u0110\u0111\7\22\2\2\u0111\u0116\5\f\7\2\u0112\u0113\7\'\2\2\u0113"+
		"\u0114\5\22\n\2\u0114\u0115\7(\2\2\u0115\u0117\3\2\2\2\u0116\u0112\3\2"+
		"\2\2\u0116\u0117\3\2\2\2\u0117\u0123\3\2\2\2\u0118\u0119\7-\2\2\u0119"+
		"\u011b\7)\2\2\u011a\u011c\5 \21\2\u011b\u011a\3\2\2\2\u011b\u011c\3\2"+
		"\2\2\u011c\u011d\3\2\2\2\u011d\u011e\7*\2\2\u011e\u011f\7\'\2\2\u011f"+
		"\u0120\5\22\n\2\u0120\u0121\7(\2\2\u0121\u0123\3\2\2\2\u0122\u010b\3\2"+
		"\2\2\u0122\u010c\3\2\2\2\u0122\u0110\3\2\2\2\u0122\u0118\3\2\2\2\u0123"+
		"\35\3\2\2\2\u0124\u0125\b\20\1\2\u0125\u0126\7-\2\2\u0126\u0131\3\2\2"+
		"\2\u0127\u0128\f\4\2\2\u0128\u0129\7\'\2\2\u0129\u012a\5\22\n\2\u012a"+
		"\u012b\7(\2\2\u012b\u0130\3\2\2\2\u012c\u012d\f\3\2\2\u012d\u012e\7\33"+
		"\2\2\u012e\u0130\7-\2\2\u012f\u0127\3\2\2\2\u012f\u012c\3\2\2\2\u0130"+
		"\u0133\3\2\2\2\u0131\u012f\3\2\2\2\u0131\u0132\3\2\2\2\u0132\37\3\2\2"+
		"\2\u0133\u0131\3\2\2\2\u0134\u0139\5\22\n\2\u0135\u0136\7\32\2\2\u0136"+
		"\u0138\5\22\n\2\u0137\u0135\3\2\2\2\u0138\u013b\3\2\2\2\u0139\u0137\3"+
		"\2\2\2\u0139\u013a\3\2\2\2\u013a!\3\2\2\2\u013b\u0139\3\2\2\2!%+\64AJ"+
		"MSalu{\u00a1\u00ae\u00b7\u00bc\u00bf\u00c9\u00d2\u00da\u00dc\u00e8\u00ea"+
		"\u00f9\u00fb\u0109\u0116\u011b\u0122\u012f\u0131\u0139";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}