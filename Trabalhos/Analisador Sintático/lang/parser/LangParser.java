// Generated from Lang.g4 by ANTLR 4.9.2
 
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
		EOL=1, WS=2, LINE_COMMENT=3, COMMENT=4, NUMBER_INTEGER=5, NUMBER_FLOAT=6, 
		LETTER=7, DIGIT=8, LOWERCASE=9, UPPERCASE=10, CHARACTER_LITTERAL=11, ID=12, 
		NAME_TYPE=13, INT=14, FLOAT=15, CHAR=16, BOOL=17, DATA=18, IF=19, ELSE=20, 
		ITERATE=21, READ=22, PRINT=23, RETURN=24, NEW=25, FALSE=26, TRUE=27, NULL=28, 
		AND=29, EQUALITY=30, DIFFERENCE=31, EXCLAMATION=32, COMMA=33, DOT=34, 
		SEMI=35, COLON=36, DOUBLE_COLON=37, LESS_THAN=38, GREATER_THAN=39, EQUALS=40, 
		TIMES=41, PLUS=42, MINUS=43, SLASH=44, BACK_SLASH=45, PERCENT=46, AMPERSAND=47, 
		SINGLE_QUOTES=48, OPEN_BRACKET=49, CLOSE_BRACKET=50, OPEN_PARENT=51, CLOSE_PARENT=52, 
		OPEN_BRACES=53, CLOSE_BRACES=54;
	public static final int
		RULE_prog = 0, RULE_data = 1, RULE_decl = 2, RULE_func = 3, RULE_params = 4, 
		RULE_type = 5, RULE_btype = 6, RULE_cmd = 7, RULE_exp = 8, RULE_rexp = 9, 
		RULE_aexp = 10, RULE_mexp = 11, RULE_sexp = 12, RULE_pexp = 13, RULE_one_value = 14, 
		RULE_exps = 15;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "data", "decl", "func", "params", "type", "btype", "cmd", "exp", 
			"rexp", "aexp", "mexp", "sexp", "pexp", "one_value", "exps"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, "'Int'", "'Float'", "'Char'", "'Bool'", "'data'", "'if'", 
			"'else'", "'iterate'", "'read'", "'print'", "'return'", "'new'", "'false'", 
			"'true'", "'null'", "'&&'", "'=='", "'!='", "'!'", "','", "'.'", "';'", 
			"':'", "'::'", "'<'", "'>'", "'='", "'*'", "'+'", "'-'", "'/'", "'\\'", 
			"'%'", "'&'", "'''", "'['", "']'", "'('", "')'", "'{'", "'}'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "EOL", "WS", "LINE_COMMENT", "COMMENT", "NUMBER_INTEGER", "NUMBER_FLOAT", 
			"LETTER", "DIGIT", "LOWERCASE", "UPPERCASE", "CHARACTER_LITTERAL", "ID", 
			"NAME_TYPE", "INT", "FLOAT", "CHAR", "BOOL", "DATA", "IF", "ELSE", "ITERATE", 
			"READ", "PRINT", "RETURN", "NEW", "FALSE", "TRUE", "NULL", "AND", "EQUALITY", 
			"DIFFERENCE", "EXCLAMATION", "COMMA", "DOT", "SEMI", "COLON", "DOUBLE_COLON", 
			"LESS_THAN", "GREATER_THAN", "EQUALS", "TIMES", "PLUS", "MINUS", "SLASH", 
			"BACK_SLASH", "PERCENT", "AMPERSAND", "SINGLE_QUOTES", "OPEN_BRACKET", 
			"CLOSE_BRACKET", "OPEN_PARENT", "CLOSE_PARENT", "OPEN_BRACES", "CLOSE_BRACES"
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
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitProg(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(35);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DATA) {
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
		public TerminalNode DATA() { return getToken(LangParser.DATA, 0); }
		public TerminalNode NAME_TYPE() { return getToken(LangParser.NAME_TYPE, 0); }
		public TerminalNode OPEN_BRACES() { return getToken(LangParser.OPEN_BRACES, 0); }
		public TerminalNode CLOSE_BRACES() { return getToken(LangParser.CLOSE_BRACES, 0); }
		public List<DeclContext> decl() {
			return getRuleContexts(DeclContext.class);
		}
		public DeclContext decl(int i) {
			return getRuleContext(DeclContext.class,i);
		}
		public DataContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_data; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterData(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitData(this);
		}
	}

	public final DataContext data() throws RecognitionException {
		DataContext _localctx = new DataContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_data);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(44);
			match(DATA);
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
		public TerminalNode ID() { return getToken(LangParser.ID, 0); }
		public TerminalNode DOUBLE_COLON() { return getToken(LangParser.DOUBLE_COLON, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(LangParser.SEMI, 0); }
		public DeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitDecl(this);
		}
	}

	public final DeclContext decl() throws RecognitionException {
		DeclContext _localctx = new DeclContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_decl);
		try {
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
		public FuncContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterFunc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitFunc(this);
		}
	}

	public final FuncContext func() throws RecognitionException {
		FuncContext _localctx = new FuncContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_func);
		int _la;
		try {
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
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ID) | (1L << IF) | (1L << ITERATE) | (1L << READ) | (1L << PRINT) | (1L << RETURN) | (1L << OPEN_BRACES))) != 0)) {
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
		public List<TerminalNode> ID() { return getTokens(LangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(LangParser.ID, i);
		}
		public List<TerminalNode> DOUBLE_COLON() { return getTokens(LangParser.DOUBLE_COLON); }
		public TerminalNode DOUBLE_COLON(int i) {
			return getToken(LangParser.DOUBLE_COLON, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(LangParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(LangParser.COMMA, i);
		}
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public ParamsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_params; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterParams(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitParams(this);
		}
	}

	public final ParamsContext params() throws RecognitionException {
		ParamsContext _localctx = new ParamsContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_params);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(86);
			match(ID);
			setState(87);
			match(DOUBLE_COLON);
			setState(94);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(88);
				match(COMMA);
				setState(89);
				match(ID);
				setState(90);
				match(DOUBLE_COLON);
				setState(91);
				type(0);
				}
				}
				setState(96);
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
		public BtypeContext btype() {
			return getRuleContext(BtypeContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode OPEN_BRACKET() { return getToken(LangParser.OPEN_BRACKET, 0); }
		public TerminalNode CLOSE_BRACKET() { return getToken(LangParser.CLOSE_BRACKET, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitType(this);
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
			setState(98);
			btype();
			}
			_ctx.stop = _input.LT(-1);
			setState(105);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new TypeContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_type);
					setState(100);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(101);
					match(OPEN_BRACKET);
					setState(102);
					match(CLOSE_BRACKET);
					}
					} 
				}
				setState(107);
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
		public TerminalNode INT() { return getToken(LangParser.INT, 0); }
		public TerminalNode CHAR() { return getToken(LangParser.CHAR, 0); }
		public TerminalNode BOOL() { return getToken(LangParser.BOOL, 0); }
		public TerminalNode FLOAT() { return getToken(LangParser.FLOAT, 0); }
		public TerminalNode NAME_TYPE() { return getToken(LangParser.NAME_TYPE, 0); }
		public TerminalNode ID() { return getToken(LangParser.ID, 0); }
		public BtypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_btype; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterBtype(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitBtype(this);
		}
	}

	public final BtypeContext btype() throws RecognitionException {
		BtypeContext _localctx = new BtypeContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_btype);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ID) | (1L << NAME_TYPE) | (1L << INT) | (1L << FLOAT) | (1L << CHAR) | (1L << BOOL))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class CmdContext extends ParserRuleContext {
		public TerminalNode OPEN_BRACES() { return getToken(LangParser.OPEN_BRACES, 0); }
		public TerminalNode CLOSE_BRACES() { return getToken(LangParser.CLOSE_BRACES, 0); }
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public TerminalNode IF() { return getToken(LangParser.IF, 0); }
		public TerminalNode OPEN_PARENT() { return getToken(LangParser.OPEN_PARENT, 0); }
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public TerminalNode CLOSE_PARENT() { return getToken(LangParser.CLOSE_PARENT, 0); }
		public TerminalNode ELSE() { return getToken(LangParser.ELSE, 0); }
		public TerminalNode ITERATE() { return getToken(LangParser.ITERATE, 0); }
		public TerminalNode READ() { return getToken(LangParser.READ, 0); }
		public List<One_valueContext> one_value() {
			return getRuleContexts(One_valueContext.class);
		}
		public One_valueContext one_value(int i) {
			return getRuleContext(One_valueContext.class,i);
		}
		public TerminalNode SEMI() { return getToken(LangParser.SEMI, 0); }
		public TerminalNode PRINT() { return getToken(LangParser.PRINT, 0); }
		public TerminalNode RETURN() { return getToken(LangParser.RETURN, 0); }
		public List<TerminalNode> COMMA() { return getTokens(LangParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(LangParser.COMMA, i);
		}
		public TerminalNode EQUALS() { return getToken(LangParser.EQUALS, 0); }
		public TerminalNode ID() { return getToken(LangParser.ID, 0); }
		public ExpsContext exps() {
			return getRuleContext(ExpsContext.class,0);
		}
		public TerminalNode LESS_THAN() { return getToken(LangParser.LESS_THAN, 0); }
		public TerminalNode GREATER_THAN() { return getToken(LangParser.GREATER_THAN, 0); }
		public CmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterCmd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitCmd(this);
		}
	}

	public final CmdContext cmd() throws RecognitionException {
		CmdContext _localctx = new CmdContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_cmd);
		int _la;
		try {
			setState(182);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(110);
				match(OPEN_BRACES);
				setState(114);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ID) | (1L << IF) | (1L << ITERATE) | (1L << READ) | (1L << PRINT) | (1L << RETURN) | (1L << OPEN_BRACES))) != 0)) {
					{
					{
					setState(111);
					cmd();
					}
					}
					setState(116);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(117);
				match(CLOSE_BRACES);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(118);
				match(IF);
				setState(119);
				match(OPEN_PARENT);
				setState(120);
				exp(0);
				setState(121);
				match(CLOSE_PARENT);
				setState(122);
				cmd();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(124);
				match(IF);
				setState(125);
				match(OPEN_PARENT);
				setState(126);
				exp(0);
				setState(127);
				match(CLOSE_PARENT);
				setState(128);
				cmd();
				setState(129);
				match(ELSE);
				setState(130);
				cmd();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(132);
				match(ITERATE);
				setState(133);
				match(OPEN_PARENT);
				setState(134);
				exp(0);
				setState(135);
				match(CLOSE_PARENT);
				setState(136);
				cmd();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(138);
				match(READ);
				setState(139);
				one_value(0);
				setState(140);
				match(SEMI);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(142);
				match(PRINT);
				setState(143);
				exp(0);
				setState(144);
				match(SEMI);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(146);
				match(RETURN);
				setState(147);
				exp(0);
				setState(152);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(148);
					match(COMMA);
					setState(149);
					exp(0);
					}
					}
					setState(154);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(155);
				match(SEMI);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(157);
				one_value(0);
				setState(158);
				match(EQUALS);
				setState(159);
				exp(0);
				setState(160);
				match(SEMI);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(162);
				match(ID);
				setState(163);
				match(OPEN_PARENT);
				setState(165);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NUMBER_INTEGER) | (1L << NUMBER_FLOAT) | (1L << CHARACTER_LITTERAL) | (1L << ID) | (1L << NEW) | (1L << FALSE) | (1L << TRUE) | (1L << NULL) | (1L << EXCLAMATION) | (1L << MINUS) | (1L << OPEN_PARENT))) != 0)) {
					{
					setState(164);
					exps();
					}
				}

				setState(167);
				match(CLOSE_PARENT);
				setState(179);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LESS_THAN) {
					{
					setState(168);
					match(LESS_THAN);
					setState(169);
					one_value(0);
					setState(174);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(170);
						match(COMMA);
						setState(171);
						one_value(0);
						}
						}
						setState(176);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(177);
					match(GREATER_THAN);
					}
				}

				setState(181);
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
		public RexpContext rexp() {
			return getRuleContext(RexpContext.class,0);
		}
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public TerminalNode AND() { return getToken(LangParser.AND, 0); }
		public ExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitExp(this);
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
			setState(185);
			rexp(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(192);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ExpContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_exp);
					setState(187);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(188);
					match(AND);
					setState(189);
					exp(3);
					}
					} 
				}
				setState(194);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
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
		public List<AexpContext> aexp() {
			return getRuleContexts(AexpContext.class);
		}
		public AexpContext aexp(int i) {
			return getRuleContext(AexpContext.class,i);
		}
		public TerminalNode LESS_THAN() { return getToken(LangParser.LESS_THAN, 0); }
		public RexpContext rexp() {
			return getRuleContext(RexpContext.class,0);
		}
		public TerminalNode EQUALITY() { return getToken(LangParser.EQUALITY, 0); }
		public TerminalNode DIFFERENCE() { return getToken(LangParser.DIFFERENCE, 0); }
		public RexpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rexp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterRexp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitRexp(this);
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
			setState(201);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				{
				setState(196);
				aexp(0);
				setState(197);
				match(LESS_THAN);
				setState(198);
				aexp(0);
				}
				break;
			case 2:
				{
				setState(200);
				aexp(0);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(211);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(209);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
					case 1:
						{
						_localctx = new RexpContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_rexp);
						setState(203);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(204);
						match(EQUALITY);
						setState(205);
						aexp(0);
						}
						break;
					case 2:
						{
						_localctx = new RexpContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_rexp);
						setState(206);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(207);
						match(DIFFERENCE);
						setState(208);
						aexp(0);
						}
						break;
					}
					} 
				}
				setState(213);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
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
		public MexpContext mexp() {
			return getRuleContext(MexpContext.class,0);
		}
		public AexpContext aexp() {
			return getRuleContext(AexpContext.class,0);
		}
		public TerminalNode PLUS() { return getToken(LangParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(LangParser.MINUS, 0); }
		public AexpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aexp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterAexp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitAexp(this);
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
			setState(215);
			mexp(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(225);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(223);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
					case 1:
						{
						_localctx = new AexpContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_aexp);
						setState(217);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(218);
						match(PLUS);
						setState(219);
						mexp(0);
						}
						break;
					case 2:
						{
						_localctx = new AexpContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_aexp);
						setState(220);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(221);
						match(MINUS);
						setState(222);
						mexp(0);
						}
						break;
					}
					} 
				}
				setState(227);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
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
		public SexpContext sexp() {
			return getRuleContext(SexpContext.class,0);
		}
		public MexpContext mexp() {
			return getRuleContext(MexpContext.class,0);
		}
		public TerminalNode TIMES() { return getToken(LangParser.TIMES, 0); }
		public TerminalNode SLASH() { return getToken(LangParser.SLASH, 0); }
		public TerminalNode PERCENT() { return getToken(LangParser.PERCENT, 0); }
		public MexpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mexp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterMexp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitMexp(this);
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
			setState(229);
			sexp();
			}
			_ctx.stop = _input.LT(-1);
			setState(242);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(240);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
					case 1:
						{
						_localctx = new MexpContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_mexp);
						setState(231);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(232);
						match(TIMES);
						setState(233);
						sexp();
						}
						break;
					case 2:
						{
						_localctx = new MexpContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_mexp);
						setState(234);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(235);
						match(SLASH);
						setState(236);
						sexp();
						}
						break;
					case 3:
						{
						_localctx = new MexpContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_mexp);
						setState(237);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(238);
						match(PERCENT);
						setState(239);
						sexp();
						}
						break;
					}
					} 
				}
				setState(244);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
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
		public TerminalNode EXCLAMATION() { return getToken(LangParser.EXCLAMATION, 0); }
		public SexpContext sexp() {
			return getRuleContext(SexpContext.class,0);
		}
		public TerminalNode MINUS() { return getToken(LangParser.MINUS, 0); }
		public TerminalNode TRUE() { return getToken(LangParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(LangParser.FALSE, 0); }
		public TerminalNode NULL() { return getToken(LangParser.NULL, 0); }
		public TerminalNode NUMBER_INTEGER() { return getToken(LangParser.NUMBER_INTEGER, 0); }
		public TerminalNode NUMBER_FLOAT() { return getToken(LangParser.NUMBER_FLOAT, 0); }
		public TerminalNode CHARACTER_LITTERAL() { return getToken(LangParser.CHARACTER_LITTERAL, 0); }
		public PexpContext pexp() {
			return getRuleContext(PexpContext.class,0);
		}
		public SexpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sexp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterSexp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitSexp(this);
		}
	}

	public final SexpContext sexp() throws RecognitionException {
		SexpContext _localctx = new SexpContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_sexp);
		try {
			setState(256);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case EXCLAMATION:
				enterOuterAlt(_localctx, 1);
				{
				setState(245);
				match(EXCLAMATION);
				setState(246);
				sexp();
				}
				break;
			case MINUS:
				enterOuterAlt(_localctx, 2);
				{
				setState(247);
				match(MINUS);
				setState(248);
				sexp();
				}
				break;
			case TRUE:
				enterOuterAlt(_localctx, 3);
				{
				setState(249);
				match(TRUE);
				}
				break;
			case FALSE:
				enterOuterAlt(_localctx, 4);
				{
				setState(250);
				match(FALSE);
				}
				break;
			case NULL:
				enterOuterAlt(_localctx, 5);
				{
				setState(251);
				match(NULL);
				}
				break;
			case NUMBER_INTEGER:
				enterOuterAlt(_localctx, 6);
				{
				setState(252);
				match(NUMBER_INTEGER);
				}
				break;
			case NUMBER_FLOAT:
				enterOuterAlt(_localctx, 7);
				{
				setState(253);
				match(NUMBER_FLOAT);
				}
				break;
			case CHARACTER_LITTERAL:
				enterOuterAlt(_localctx, 8);
				{
				setState(254);
				match(CHARACTER_LITTERAL);
				}
				break;
			case ID:
			case NEW:
			case OPEN_PARENT:
				enterOuterAlt(_localctx, 9);
				{
				setState(255);
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
		public One_valueContext one_value() {
			return getRuleContext(One_valueContext.class,0);
		}
		public TerminalNode OPEN_PARENT() { return getToken(LangParser.OPEN_PARENT, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode CLOSE_PARENT() { return getToken(LangParser.CLOSE_PARENT, 0); }
		public TerminalNode NEW() { return getToken(LangParser.NEW, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode OPEN_BRACKET() { return getToken(LangParser.OPEN_BRACKET, 0); }
		public TerminalNode CLOSE_BRACKET() { return getToken(LangParser.CLOSE_BRACKET, 0); }
		public TerminalNode ID() { return getToken(LangParser.ID, 0); }
		public ExpsContext exps() {
			return getRuleContext(ExpsContext.class,0);
		}
		public PexpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pexp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterPexp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitPexp(this);
		}
	}

	public final PexpContext pexp() throws RecognitionException {
		PexpContext _localctx = new PexpContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_pexp);
		int _la;
		try {
			setState(281);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(258);
				one_value(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(259);
				match(OPEN_PARENT);
				setState(260);
				exp(0);
				setState(261);
				match(CLOSE_PARENT);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(263);
				match(NEW);
				setState(264);
				type(0);
				setState(269);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
				case 1:
					{
					setState(265);
					match(OPEN_BRACKET);
					setState(266);
					exp(0);
					setState(267);
					match(CLOSE_BRACKET);
					}
					break;
				}
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(271);
				match(ID);
				setState(272);
				match(OPEN_PARENT);
				setState(274);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NUMBER_INTEGER) | (1L << NUMBER_FLOAT) | (1L << CHARACTER_LITTERAL) | (1L << ID) | (1L << NEW) | (1L << FALSE) | (1L << TRUE) | (1L << NULL) | (1L << EXCLAMATION) | (1L << MINUS) | (1L << OPEN_PARENT))) != 0)) {
					{
					setState(273);
					exps();
					}
				}

				setState(276);
				match(CLOSE_PARENT);
				setState(277);
				match(OPEN_BRACKET);
				setState(278);
				exp(0);
				setState(279);
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

	public static class One_valueContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(LangParser.ID, 0); }
		public One_valueContext one_value() {
			return getRuleContext(One_valueContext.class,0);
		}
		public TerminalNode OPEN_BRACKET() { return getToken(LangParser.OPEN_BRACKET, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode CLOSE_BRACKET() { return getToken(LangParser.CLOSE_BRACKET, 0); }
		public TerminalNode DOT() { return getToken(LangParser.DOT, 0); }
		public One_valueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_one_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterOne_value(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitOne_value(this);
		}
	}

	public final One_valueContext one_value() throws RecognitionException {
		return one_value(0);
	}

	private One_valueContext one_value(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		One_valueContext _localctx = new One_valueContext(_ctx, _parentState);
		One_valueContext _prevctx = _localctx;
		int _startState = 28;
		enterRecursionRule(_localctx, 28, RULE_one_value, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(284);
			match(ID);
			}
			_ctx.stop = _input.LT(-1);
			setState(296);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(294);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
					case 1:
						{
						_localctx = new One_valueContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_one_value);
						setState(286);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(287);
						match(OPEN_BRACKET);
						setState(288);
						exp(0);
						setState(289);
						match(CLOSE_BRACKET);
						}
						break;
					case 2:
						{
						_localctx = new One_valueContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_one_value);
						setState(291);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(292);
						match(DOT);
						setState(293);
						match(ID);
						}
						break;
					}
					} 
				}
				setState(298);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
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
		public ExpsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exps; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterExps(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitExps(this);
		}
	}

	public final ExpsContext exps() throws RecognitionException {
		ExpsContext _localctx = new ExpsContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_exps);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(299);
			exp(0);
			setState(304);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(300);
				match(COMMA);
				setState(301);
				exp(0);
				}
				}
				setState(306);
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
			return one_value_sempred((One_valueContext)_localctx, predIndex);
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
	private boolean one_value_sempred(One_valueContext _localctx, int predIndex) {
		switch (predIndex) {
		case 9:
			return precpred(_ctx, 2);
		case 10:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\38\u0136\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\7\2$\n"+
		"\2\f\2\16\2\'\13\2\3\2\7\2*\n\2\f\2\16\2-\13\2\3\3\3\3\3\3\3\3\7\3\63"+
		"\n\3\f\3\16\3\66\13\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\5\5B\n\5"+
		"\3\5\3\5\3\5\3\5\3\5\7\5I\n\5\f\5\16\5L\13\5\5\5N\n\5\3\5\3\5\7\5R\n\5"+
		"\f\5\16\5U\13\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\7\6_\n\6\f\6\16\6b\13"+
		"\6\3\7\3\7\3\7\3\7\3\7\3\7\7\7j\n\7\f\7\16\7m\13\7\3\b\3\b\3\t\3\t\7\t"+
		"s\n\t\f\t\16\tv\13\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\7\t\u0099\n\t\f\t\16\t\u009c\13\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\5\t\u00a8\n\t\3\t\3\t\3\t\3\t\3\t\7\t\u00af\n\t\f\t\16"+
		"\t\u00b2\13\t\3\t\3\t\5\t\u00b6\n\t\3\t\5\t\u00b9\n\t\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\7\n\u00c1\n\n\f\n\16\n\u00c4\13\n\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\5\13\u00cc\n\13\3\13\3\13\3\13\3\13\3\13\3\13\7\13\u00d4\n\13\f\13"+
		"\16\13\u00d7\13\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\7\f\u00e2\n\f\f"+
		"\f\16\f\u00e5\13\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\7\r"+
		"\u00f3\n\r\f\r\16\r\u00f6\13\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\5\16\u0103\n\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\5\17\u0110\n\17\3\17\3\17\3\17\5\17\u0115\n\17\3\17\3"+
		"\17\3\17\3\17\3\17\5\17\u011c\n\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\7\20\u0129\n\20\f\20\16\20\u012c\13\20\3\21\3\21"+
		"\3\21\7\21\u0131\n\21\f\21\16\21\u0134\13\21\3\21\2\b\f\22\24\26\30\36"+
		"\22\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \2\3\3\2\16\23\2\u0154\2%\3"+
		"\2\2\2\4.\3\2\2\2\69\3\2\2\2\b>\3\2\2\2\nX\3\2\2\2\fc\3\2\2\2\16n\3\2"+
		"\2\2\20\u00b8\3\2\2\2\22\u00ba\3\2\2\2\24\u00cb\3\2\2\2\26\u00d8\3\2\2"+
		"\2\30\u00e6\3\2\2\2\32\u0102\3\2\2\2\34\u011b\3\2\2\2\36\u011d\3\2\2\2"+
		" \u012d\3\2\2\2\"$\5\4\3\2#\"\3\2\2\2$\'\3\2\2\2%#\3\2\2\2%&\3\2\2\2&"+
		"+\3\2\2\2\'%\3\2\2\2(*\5\b\5\2)(\3\2\2\2*-\3\2\2\2+)\3\2\2\2+,\3\2\2\2"+
		",\3\3\2\2\2-+\3\2\2\2./\7\24\2\2/\60\7\17\2\2\60\64\7\67\2\2\61\63\5\6"+
		"\4\2\62\61\3\2\2\2\63\66\3\2\2\2\64\62\3\2\2\2\64\65\3\2\2\2\65\67\3\2"+
		"\2\2\66\64\3\2\2\2\678\78\2\28\5\3\2\2\29:\7\16\2\2:;\7\'\2\2;<\5\f\7"+
		"\2<=\7%\2\2=\7\3\2\2\2>?\7\16\2\2?A\7\65\2\2@B\5\n\6\2A@\3\2\2\2AB\3\2"+
		"\2\2BC\3\2\2\2CM\7\66\2\2DE\7&\2\2EJ\5\f\7\2FG\7#\2\2GI\5\f\7\2HF\3\2"+
		"\2\2IL\3\2\2\2JH\3\2\2\2JK\3\2\2\2KN\3\2\2\2LJ\3\2\2\2MD\3\2\2\2MN\3\2"+
		"\2\2NO\3\2\2\2OS\7\67\2\2PR\5\20\t\2QP\3\2\2\2RU\3\2\2\2SQ\3\2\2\2ST\3"+
		"\2\2\2TV\3\2\2\2US\3\2\2\2VW\78\2\2W\t\3\2\2\2XY\7\16\2\2Y`\7\'\2\2Z["+
		"\7#\2\2[\\\7\16\2\2\\]\7\'\2\2]_\5\f\7\2^Z\3\2\2\2_b\3\2\2\2`^\3\2\2\2"+
		"`a\3\2\2\2a\13\3\2\2\2b`\3\2\2\2cd\b\7\1\2de\5\16\b\2ek\3\2\2\2fg\f\4"+
		"\2\2gh\7\63\2\2hj\7\64\2\2if\3\2\2\2jm\3\2\2\2ki\3\2\2\2kl\3\2\2\2l\r"+
		"\3\2\2\2mk\3\2\2\2no\t\2\2\2o\17\3\2\2\2pt\7\67\2\2qs\5\20\t\2rq\3\2\2"+
		"\2sv\3\2\2\2tr\3\2\2\2tu\3\2\2\2uw\3\2\2\2vt\3\2\2\2w\u00b9\78\2\2xy\7"+
		"\25\2\2yz\7\65\2\2z{\5\22\n\2{|\7\66\2\2|}\5\20\t\2}\u00b9\3\2\2\2~\177"+
		"\7\25\2\2\177\u0080\7\65\2\2\u0080\u0081\5\22\n\2\u0081\u0082\7\66\2\2"+
		"\u0082\u0083\5\20\t\2\u0083\u0084\7\26\2\2\u0084\u0085\5\20\t\2\u0085"+
		"\u00b9\3\2\2\2\u0086\u0087\7\27\2\2\u0087\u0088\7\65\2\2\u0088\u0089\5"+
		"\22\n\2\u0089\u008a\7\66\2\2\u008a\u008b\5\20\t\2\u008b\u00b9\3\2\2\2"+
		"\u008c\u008d\7\30\2\2\u008d\u008e\5\36\20\2\u008e\u008f\7%\2\2\u008f\u00b9"+
		"\3\2\2\2\u0090\u0091\7\31\2\2\u0091\u0092\5\22\n\2\u0092\u0093\7%\2\2"+
		"\u0093\u00b9\3\2\2\2\u0094\u0095\7\32\2\2\u0095\u009a\5\22\n\2\u0096\u0097"+
		"\7#\2\2\u0097\u0099\5\22\n\2\u0098\u0096\3\2\2\2\u0099\u009c\3\2\2\2\u009a"+
		"\u0098\3\2\2\2\u009a\u009b\3\2\2\2\u009b\u009d\3\2\2\2\u009c\u009a\3\2"+
		"\2\2\u009d\u009e\7%\2\2\u009e\u00b9\3\2\2\2\u009f\u00a0\5\36\20\2\u00a0"+
		"\u00a1\7*\2\2\u00a1\u00a2\5\22\n\2\u00a2\u00a3\7%\2\2\u00a3\u00b9\3\2"+
		"\2\2\u00a4\u00a5\7\16\2\2\u00a5\u00a7\7\65\2\2\u00a6\u00a8\5 \21\2\u00a7"+
		"\u00a6\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9\u00b5\7\66"+
		"\2\2\u00aa\u00ab\7(\2\2\u00ab\u00b0\5\36\20\2\u00ac\u00ad\7#\2\2\u00ad"+
		"\u00af\5\36\20\2\u00ae\u00ac\3\2\2\2\u00af\u00b2\3\2\2\2\u00b0\u00ae\3"+
		"\2\2\2\u00b0\u00b1\3\2\2\2\u00b1\u00b3\3\2\2\2\u00b2\u00b0\3\2\2\2\u00b3"+
		"\u00b4\7)\2\2\u00b4\u00b6\3\2\2\2\u00b5\u00aa\3\2\2\2\u00b5\u00b6\3\2"+
		"\2\2\u00b6\u00b7\3\2\2\2\u00b7\u00b9\7%\2\2\u00b8p\3\2\2\2\u00b8x\3\2"+
		"\2\2\u00b8~\3\2\2\2\u00b8\u0086\3\2\2\2\u00b8\u008c\3\2\2\2\u00b8\u0090"+
		"\3\2\2\2\u00b8\u0094\3\2\2\2\u00b8\u009f\3\2\2\2\u00b8\u00a4\3\2\2\2\u00b9"+
		"\21\3\2\2\2\u00ba\u00bb\b\n\1\2\u00bb\u00bc\5\24\13\2\u00bc\u00c2\3\2"+
		"\2\2\u00bd\u00be\f\4\2\2\u00be\u00bf\7\37\2\2\u00bf\u00c1\5\22\n\5\u00c0"+
		"\u00bd\3\2\2\2\u00c1\u00c4\3\2\2\2\u00c2\u00c0\3\2\2\2\u00c2\u00c3\3\2"+
		"\2\2\u00c3\23\3\2\2\2\u00c4\u00c2\3\2\2\2\u00c5\u00c6\b\13\1\2\u00c6\u00c7"+
		"\5\26\f\2\u00c7\u00c8\7(\2\2\u00c8\u00c9\5\26\f\2\u00c9\u00cc\3\2\2\2"+
		"\u00ca\u00cc\5\26\f\2\u00cb\u00c5\3\2\2\2\u00cb\u00ca\3\2\2\2\u00cc\u00d5"+
		"\3\2\2\2\u00cd\u00ce\f\5\2\2\u00ce\u00cf\7 \2\2\u00cf\u00d4\5\26\f\2\u00d0"+
		"\u00d1\f\4\2\2\u00d1\u00d2\7!\2\2\u00d2\u00d4\5\26\f\2\u00d3\u00cd\3\2"+
		"\2\2\u00d3\u00d0\3\2\2\2\u00d4\u00d7\3\2\2\2\u00d5\u00d3\3\2\2\2\u00d5"+
		"\u00d6\3\2\2\2\u00d6\25\3\2\2\2\u00d7\u00d5\3\2\2\2\u00d8\u00d9\b\f\1"+
		"\2\u00d9\u00da\5\30\r\2\u00da\u00e3\3\2\2\2\u00db\u00dc\f\5\2\2\u00dc"+
		"\u00dd\7,\2\2\u00dd\u00e2\5\30\r\2\u00de\u00df\f\4\2\2\u00df\u00e0\7-"+
		"\2\2\u00e0\u00e2\5\30\r\2\u00e1\u00db\3\2\2\2\u00e1\u00de\3\2\2\2\u00e2"+
		"\u00e5\3\2\2\2\u00e3\u00e1\3\2\2\2\u00e3\u00e4\3\2\2\2\u00e4\27\3\2\2"+
		"\2\u00e5\u00e3\3\2\2\2\u00e6\u00e7\b\r\1\2\u00e7\u00e8\5\32\16\2\u00e8"+
		"\u00f4\3\2\2\2\u00e9\u00ea\f\6\2\2\u00ea\u00eb\7+\2\2\u00eb\u00f3\5\32"+
		"\16\2\u00ec\u00ed\f\5\2\2\u00ed\u00ee\7.\2\2\u00ee\u00f3\5\32\16\2\u00ef"+
		"\u00f0\f\4\2\2\u00f0\u00f1\7\60\2\2\u00f1\u00f3\5\32\16\2\u00f2\u00e9"+
		"\3\2\2\2\u00f2\u00ec\3\2\2\2\u00f2\u00ef\3\2\2\2\u00f3\u00f6\3\2\2\2\u00f4"+
		"\u00f2\3\2\2\2\u00f4\u00f5\3\2\2\2\u00f5\31\3\2\2\2\u00f6\u00f4\3\2\2"+
		"\2\u00f7\u00f8\7\"\2\2\u00f8\u0103\5\32\16\2\u00f9\u00fa\7-\2\2\u00fa"+
		"\u0103\5\32\16\2\u00fb\u0103\7\35\2\2\u00fc\u0103\7\34\2\2\u00fd\u0103"+
		"\7\36\2\2\u00fe\u0103\7\7\2\2\u00ff\u0103\7\b\2\2\u0100\u0103\7\r\2\2"+
		"\u0101\u0103\5\34\17\2\u0102\u00f7\3\2\2\2\u0102\u00f9\3\2\2\2\u0102\u00fb"+
		"\3\2\2\2\u0102\u00fc\3\2\2\2\u0102\u00fd\3\2\2\2\u0102\u00fe\3\2\2\2\u0102"+
		"\u00ff\3\2\2\2\u0102\u0100\3\2\2\2\u0102\u0101\3\2\2\2\u0103\33\3\2\2"+
		"\2\u0104\u011c\5\36\20\2\u0105\u0106\7\65\2\2\u0106\u0107\5\22\n\2\u0107"+
		"\u0108\7\66\2\2\u0108\u011c\3\2\2\2\u0109\u010a\7\33\2\2\u010a\u010f\5"+
		"\f\7\2\u010b\u010c\7\63\2\2\u010c\u010d\5\22\n\2\u010d\u010e\7\64\2\2"+
		"\u010e\u0110\3\2\2\2\u010f\u010b\3\2\2\2\u010f\u0110\3\2\2\2\u0110\u011c"+
		"\3\2\2\2\u0111\u0112\7\16\2\2\u0112\u0114\7\65\2\2\u0113\u0115\5 \21\2"+
		"\u0114\u0113\3\2\2\2\u0114\u0115\3\2\2\2\u0115\u0116\3\2\2\2\u0116\u0117"+
		"\7\66\2\2\u0117\u0118\7\63\2\2\u0118\u0119\5\22\n\2\u0119\u011a\7\64\2"+
		"\2\u011a\u011c\3\2\2\2\u011b\u0104\3\2\2\2\u011b\u0105\3\2\2\2\u011b\u0109"+
		"\3\2\2\2\u011b\u0111\3\2\2\2\u011c\35\3\2\2\2\u011d\u011e\b\20\1\2\u011e"+
		"\u011f\7\16\2\2\u011f\u012a\3\2\2\2\u0120\u0121\f\4\2\2\u0121\u0122\7"+
		"\63\2\2\u0122\u0123\5\22\n\2\u0123\u0124\7\64\2\2\u0124\u0129\3\2\2\2"+
		"\u0125\u0126\f\3\2\2\u0126\u0127\7$\2\2\u0127\u0129\7\16\2\2\u0128\u0120"+
		"\3\2\2\2\u0128\u0125\3\2\2\2\u0129\u012c\3\2\2\2\u012a\u0128\3\2\2\2\u012a"+
		"\u012b\3\2\2\2\u012b\37\3\2\2\2\u012c\u012a\3\2\2\2\u012d\u0132\5\22\n"+
		"\2\u012e\u012f\7#\2\2\u012f\u0131\5\22\n\2\u0130\u012e\3\2\2\2\u0131\u0134"+
		"\3\2\2\2\u0132\u0130\3\2\2\2\u0132\u0133\3\2\2\2\u0133!\3\2\2\2\u0134"+
		"\u0132\3\2\2\2 %+\64AJMS`kt\u009a\u00a7\u00b0\u00b5\u00b8\u00c2\u00cb"+
		"\u00d3\u00d5\u00e1\u00e3\u00f2\u00f4\u0102\u010f\u0114\u011b\u0128\u012a"+
		"\u0132";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}