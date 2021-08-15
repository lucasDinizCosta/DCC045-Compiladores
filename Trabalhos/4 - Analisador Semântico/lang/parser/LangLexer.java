// Generated from Lang.g4 by ANTLR 4.9.2

/********************************************************
* Trabalho de Teoria dos Compiladores(DCC045)(2021/1)   *
*            Linguagem Lang                             *
* Nome: Lucas Diniz da Costa -- Matricula: 201465524C   *
*                                                       *
*********************************************************/

package lang.parser;    

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class LangLexer extends Lexer {
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
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"EOL", "WS", "SINGLE_LINE_COMMENT", "MULTI_LINE_COMMENT", "INT_TYPE", 
			"FLOAT_TYPE", "CHAR_TYPE", "BOOL_TYPE", "DATA_TYPE", "IF", "ELSE", "ITERATE", 
			"READ", "PRINT", "RETURN", "NEW", "FALSE", "TRUE", "NULL", "AND", "EQUALITY", 
			"DIFFERENCE", "EXCLAMATION", "COMMA", "DOT", "SEMI", "COLON", "DOUBLE_COLON", 
			"LESS_THAN", "GREATER_THAN", "EQUALS", "TIMES", "PLUS", "MINUS", "SLASH", 
			"PERCENT", "OPEN_BRACKET", "CLOSE_BRACKET", "OPEN_PARENT", "CLOSE_PARENT", 
			"OPEN_BRACES", "CLOSE_BRACES", "ID", "NAME_TYPE", "INT", "FLOAT", "CHAR"
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


	public LangLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Lang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\61\u0150\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\3\2\5\2c\n\2\3\2\3\2\3\2\3\2\3\3\6"+
		"\3j\n\3\r\3\16\3k\3\3\3\3\3\4\3\4\3\4\3\4\7\4t\n\4\f\4\16\4w\13\4\3\4"+
		"\3\4\3\4\3\4\3\5\3\5\3\5\3\5\7\5\u0081\n\5\f\5\16\5\u0084\13\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3"+
		"\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f"+
		"\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21"+
		"\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\24"+
		"\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\26\3\26\3\26\3\27\3\27\3\27\3\30"+
		"\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\35\3\36\3\36"+
		"\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\3&\3\'\3\'\3(\3(\3"+
		")\3)\3*\3*\3+\3+\3,\3,\7,\u010e\n,\f,\16,\u0111\13,\3-\3-\7-\u0115\n-"+
		"\f-\16-\u0118\13-\3.\6.\u011b\n.\r.\16.\u011c\3/\7/\u0120\n/\f/\16/\u0123"+
		"\13/\3/\3/\3/\7/\u0128\n/\f/\16/\u012b\13/\3\60\3\60\5\60\u012f\n\60\3"+
		"\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3"+
		"\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3"+
		"\60\3\60\5\60\u014f\n\60\4u\u0082\2\61\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21"+
		"\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30"+
		"/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.["+
		"/]\60_\61\3\2\b\4\2\13\13\"\"\3\2c|\6\2\62;C\\aac|\3\2C\\\3\2\62;\5\2"+
		"\2(*]_\u0081\2\u015e\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2"+
		"\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3"+
		"\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2"+
		"\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2"+
		"\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2"+
		"\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2"+
		"\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q"+
		"\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2"+
		"\2\2\2_\3\2\2\2\3b\3\2\2\2\5i\3\2\2\2\7o\3\2\2\2\t|\3\2\2\2\13\u008a\3"+
		"\2\2\2\r\u008e\3\2\2\2\17\u0094\3\2\2\2\21\u0099\3\2\2\2\23\u009e\3\2"+
		"\2\2\25\u00a3\3\2\2\2\27\u00a6\3\2\2\2\31\u00ab\3\2\2\2\33\u00b3\3\2\2"+
		"\2\35\u00b8\3\2\2\2\37\u00be\3\2\2\2!\u00c5\3\2\2\2#\u00c9\3\2\2\2%\u00cf"+
		"\3\2\2\2\'\u00d4\3\2\2\2)\u00d9\3\2\2\2+\u00dc\3\2\2\2-\u00df\3\2\2\2"+
		"/\u00e2\3\2\2\2\61\u00e4\3\2\2\2\63\u00e6\3\2\2\2\65\u00e8\3\2\2\2\67"+
		"\u00ea\3\2\2\29\u00ec\3\2\2\2;\u00ef\3\2\2\2=\u00f1\3\2\2\2?\u00f3\3\2"+
		"\2\2A\u00f5\3\2\2\2C\u00f7\3\2\2\2E\u00f9\3\2\2\2G\u00fb\3\2\2\2I\u00fd"+
		"\3\2\2\2K\u00ff\3\2\2\2M\u0101\3\2\2\2O\u0103\3\2\2\2Q\u0105\3\2\2\2S"+
		"\u0107\3\2\2\2U\u0109\3\2\2\2W\u010b\3\2\2\2Y\u0112\3\2\2\2[\u011a\3\2"+
		"\2\2]\u0121\3\2\2\2_\u014e\3\2\2\2ac\7\17\2\2ba\3\2\2\2bc\3\2\2\2cd\3"+
		"\2\2\2de\7\f\2\2ef\3\2\2\2fg\b\2\2\2g\4\3\2\2\2hj\t\2\2\2ih\3\2\2\2jk"+
		"\3\2\2\2ki\3\2\2\2kl\3\2\2\2lm\3\2\2\2mn\b\3\2\2n\6\3\2\2\2op\7/\2\2p"+
		"q\7/\2\2qu\3\2\2\2rt\13\2\2\2sr\3\2\2\2tw\3\2\2\2uv\3\2\2\2us\3\2\2\2"+
		"vx\3\2\2\2wu\3\2\2\2xy\5\3\2\2yz\3\2\2\2z{\b\4\2\2{\b\3\2\2\2|}\7}\2\2"+
		"}~\7/\2\2~\u0082\3\2\2\2\177\u0081\13\2\2\2\u0080\177\3\2\2\2\u0081\u0084"+
		"\3\2\2\2\u0082\u0083\3\2\2\2\u0082\u0080\3\2\2\2\u0083\u0085\3\2\2\2\u0084"+
		"\u0082\3\2\2\2\u0085\u0086\7/\2\2\u0086\u0087\7\177\2\2\u0087\u0088\3"+
		"\2\2\2\u0088\u0089\b\5\2\2\u0089\n\3\2\2\2\u008a\u008b\7K\2\2\u008b\u008c"+
		"\7p\2\2\u008c\u008d\7v\2\2\u008d\f\3\2\2\2\u008e\u008f\7H\2\2\u008f\u0090"+
		"\7n\2\2\u0090\u0091\7q\2\2\u0091\u0092\7c\2\2\u0092\u0093\7v\2\2\u0093"+
		"\16\3\2\2\2\u0094\u0095\7E\2\2\u0095\u0096\7j\2\2\u0096\u0097\7c\2\2\u0097"+
		"\u0098\7t\2\2\u0098\20\3\2\2\2\u0099\u009a\7D\2\2\u009a\u009b\7q\2\2\u009b"+
		"\u009c\7q\2\2\u009c\u009d\7n\2\2\u009d\22\3\2\2\2\u009e\u009f\7f\2\2\u009f"+
		"\u00a0\7c\2\2\u00a0\u00a1\7v\2\2\u00a1\u00a2\7c\2\2\u00a2\24\3\2\2\2\u00a3"+
		"\u00a4\7k\2\2\u00a4\u00a5\7h\2\2\u00a5\26\3\2\2\2\u00a6\u00a7\7g\2\2\u00a7"+
		"\u00a8\7n\2\2\u00a8\u00a9\7u\2\2\u00a9\u00aa\7g\2\2\u00aa\30\3\2\2\2\u00ab"+
		"\u00ac\7k\2\2\u00ac\u00ad\7v\2\2\u00ad\u00ae\7g\2\2\u00ae\u00af\7t\2\2"+
		"\u00af\u00b0\7c\2\2\u00b0\u00b1\7v\2\2\u00b1\u00b2\7g\2\2\u00b2\32\3\2"+
		"\2\2\u00b3\u00b4\7t\2\2\u00b4\u00b5\7g\2\2\u00b5\u00b6\7c\2\2\u00b6\u00b7"+
		"\7f\2\2\u00b7\34\3\2\2\2\u00b8\u00b9\7r\2\2\u00b9\u00ba\7t\2\2\u00ba\u00bb"+
		"\7k\2\2\u00bb\u00bc\7p\2\2\u00bc\u00bd\7v\2\2\u00bd\36\3\2\2\2\u00be\u00bf"+
		"\7t\2\2\u00bf\u00c0\7g\2\2\u00c0\u00c1\7v\2\2\u00c1\u00c2\7w\2\2\u00c2"+
		"\u00c3\7t\2\2\u00c3\u00c4\7p\2\2\u00c4 \3\2\2\2\u00c5\u00c6\7p\2\2\u00c6"+
		"\u00c7\7g\2\2\u00c7\u00c8\7y\2\2\u00c8\"\3\2\2\2\u00c9\u00ca\7h\2\2\u00ca"+
		"\u00cb\7c\2\2\u00cb\u00cc\7n\2\2\u00cc\u00cd\7u\2\2\u00cd\u00ce\7g\2\2"+
		"\u00ce$\3\2\2\2\u00cf\u00d0\7v\2\2\u00d0\u00d1\7t\2\2\u00d1\u00d2\7w\2"+
		"\2\u00d2\u00d3\7g\2\2\u00d3&\3\2\2\2\u00d4\u00d5\7p\2\2\u00d5\u00d6\7"+
		"w\2\2\u00d6\u00d7\7n\2\2\u00d7\u00d8\7n\2\2\u00d8(\3\2\2\2\u00d9\u00da"+
		"\7(\2\2\u00da\u00db\7(\2\2\u00db*\3\2\2\2\u00dc\u00dd\7?\2\2\u00dd\u00de"+
		"\7?\2\2\u00de,\3\2\2\2\u00df\u00e0\7#\2\2\u00e0\u00e1\7?\2\2\u00e1.\3"+
		"\2\2\2\u00e2\u00e3\7#\2\2\u00e3\60\3\2\2\2\u00e4\u00e5\7.\2\2\u00e5\62"+
		"\3\2\2\2\u00e6\u00e7\7\60\2\2\u00e7\64\3\2\2\2\u00e8\u00e9\7=\2\2\u00e9"+
		"\66\3\2\2\2\u00ea\u00eb\7<\2\2\u00eb8\3\2\2\2\u00ec\u00ed\7<\2\2\u00ed"+
		"\u00ee\7<\2\2\u00ee:\3\2\2\2\u00ef\u00f0\7>\2\2\u00f0<\3\2\2\2\u00f1\u00f2"+
		"\7@\2\2\u00f2>\3\2\2\2\u00f3\u00f4\7?\2\2\u00f4@\3\2\2\2\u00f5\u00f6\7"+
		",\2\2\u00f6B\3\2\2\2\u00f7\u00f8\7-\2\2\u00f8D\3\2\2\2\u00f9\u00fa\7/"+
		"\2\2\u00faF\3\2\2\2\u00fb\u00fc\7\61\2\2\u00fcH\3\2\2\2\u00fd\u00fe\7"+
		"\'\2\2\u00feJ\3\2\2\2\u00ff\u0100\7]\2\2\u0100L\3\2\2\2\u0101\u0102\7"+
		"_\2\2\u0102N\3\2\2\2\u0103\u0104\7*\2\2\u0104P\3\2\2\2\u0105\u0106\7+"+
		"\2\2\u0106R\3\2\2\2\u0107\u0108\7}\2\2\u0108T\3\2\2\2\u0109\u010a\7\177"+
		"\2\2\u010aV\3\2\2\2\u010b\u010f\t\3\2\2\u010c\u010e\t\4\2\2\u010d\u010c"+
		"\3\2\2\2\u010e\u0111\3\2\2\2\u010f\u010d\3\2\2\2\u010f\u0110\3\2\2\2\u0110"+
		"X\3\2\2\2\u0111\u010f\3\2\2\2\u0112\u0116\t\5\2\2\u0113\u0115\t\4\2\2"+
		"\u0114\u0113\3\2\2\2\u0115\u0118\3\2\2\2\u0116\u0114\3\2\2\2\u0116\u0117"+
		"\3\2\2\2\u0117Z\3\2\2\2\u0118\u0116\3\2\2\2\u0119\u011b\t\6\2\2\u011a"+
		"\u0119\3\2\2\2\u011b\u011c\3\2\2\2\u011c\u011a\3\2\2\2\u011c\u011d\3\2"+
		"\2\2\u011d\\\3\2\2\2\u011e\u0120\t\6\2\2\u011f\u011e\3\2\2\2\u0120\u0123"+
		"\3\2\2\2\u0121\u011f\3\2\2\2\u0121\u0122\3\2\2\2\u0122\u0124\3\2\2\2\u0123"+
		"\u0121\3\2\2\2\u0124\u0125\7\60\2\2\u0125\u0129\t\6\2\2\u0126\u0128\t"+
		"\6\2\2\u0127\u0126\3\2\2\2\u0128\u012b\3\2\2\2\u0129\u0127\3\2\2\2\u0129"+
		"\u012a\3\2\2\2\u012a^\3\2\2\2\u012b\u0129\3\2\2\2\u012c\u012e\7)\2\2\u012d"+
		"\u012f\t\7\2\2\u012e\u012d\3\2\2\2\u012f\u0130\3\2\2\2\u0130\u014f\7)"+
		"\2\2\u0131\u0132\7)\2\2\u0132\u0133\7^\2\2\u0133\u0134\7p\2\2\u0134\u0135"+
		"\3\2\2\2\u0135\u014f\7)\2\2\u0136\u0137\7)\2\2\u0137\u0138\7^\2\2\u0138"+
		"\u0139\7v\2\2\u0139\u013a\3\2\2\2\u013a\u014f\7)\2\2\u013b\u013c\7)\2"+
		"\2\u013c\u013d\7^\2\2\u013d\u013e\7d\2\2\u013e\u013f\3\2\2\2\u013f\u014f"+
		"\7)\2\2\u0140\u0141\7)\2\2\u0141\u0142\7^\2\2\u0142\u0143\7t\2\2\u0143"+
		"\u0144\3\2\2\2\u0144\u014f\7)\2\2\u0145\u0146\7)\2\2\u0146\u0147\7^\2"+
		"\2\u0147\u0148\7^\2\2\u0148\u0149\3\2\2\2\u0149\u014f\7)\2\2\u014a\u014b"+
		"\7)\2\2\u014b\u014c\7^\2\2\u014c\u014d\7)\2\2\u014d\u014f\7)\2\2\u014e"+
		"\u012c\3\2\2\2\u014e\u0131\3\2\2\2\u014e\u0136\3\2\2\2\u014e\u013b\3\2"+
		"\2\2\u014e\u0140\3\2\2\2\u014e\u0145\3\2\2\2\u014e\u014a\3\2\2\2\u014f"+
		"`\3\2\2\2\16\2bku\u0082\u010f\u0116\u011c\u0121\u0129\u012e\u014e\3\b"+
		"\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}