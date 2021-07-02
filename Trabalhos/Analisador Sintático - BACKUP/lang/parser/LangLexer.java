// Generated from Lang.g4 by ANTLR 4.9.2
 
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
		EOL=1, WS=2, LINE_COMMENT=3, COMMENT=4, NUMBER_INTEGER=5, NUMBER_FLOAT=6, 
		LETTER=7, DIGIT=8, LOWERCASE=9, UPPERCASE=10, CHARACTER_LITTERAL=11, ID=12, 
		NAME_TYPE=13, INT=14, FLOAT=15, CHAR=16, BOOL=17, DATA=18, IF=19, ELSE=20, 
		ITERATE=21, READ=22, PRINT=23, RETURN=24, NEW=25, FALSE=26, TRUE=27, NULL=28, 
		AND=29, EQUALITY=30, DIFFERENCE=31, EXCLAMATION=32, COMMA=33, DOT=34, 
		SEMI=35, COLON=36, DOUBLE_COLON=37, LESS_THAN=38, GREATER_THAN=39, EQUALS=40, 
		TIMES=41, PLUS=42, MINUS=43, SLASH=44, BACK_SLASH=45, PERCENT=46, AMPERSAND=47, 
		SINGLE_QUOTES=48, OPEN_BRACKET=49, CLOSE_BRACKET=50, OPEN_PARENT=51, CLOSE_PARENT=52, 
		OPEN_BRACES=53, CLOSE_BRACES=54;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"EOL", "WS", "LINE_COMMENT", "COMMENT", "NUMBER_INTEGER", "NUMBER_FLOAT", 
			"LETTER", "DIGIT", "LOWERCASE", "UPPERCASE", "CHARACTER_LITTERAL", "ID", 
			"NAME_TYPE", "INT", "FLOAT", "CHAR", "BOOL", "DATA", "IF", "ELSE", "ITERATE", 
			"READ", "PRINT", "RETURN", "NEW", "FALSE", "TRUE", "NULL", "AND", "EQUALITY", 
			"DIFFERENCE", "EXCLAMATION", "COMMA", "DOT", "SEMI", "COLON", "DOUBLE_COLON", 
			"LESS_THAN", "GREATER_THAN", "EQUALS", "TIMES", "PLUS", "MINUS", "SLASH", 
			"BACK_SLASH", "PERCENT", "AMPERSAND", "SINGLE_QUOTES", "OPEN_BRACKET", 
			"CLOSE_BRACKET", "OPEN_PARENT", "CLOSE_PARENT", "OPEN_BRACES", "CLOSE_BRACES"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\28\u015f\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\3\2\3\2\3\2\5\2s\n\2\3\2\3\2\3\3\3\3"+
		"\3\3\3\3\3\3\5\3|\n\3\3\3\3\3\3\4\3\4\3\4\3\4\7\4\u0084\n\4\f\4\16\4\u0087"+
		"\13\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\7\5\u0091\n\5\f\5\16\5\u0094\13"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\6\6\6\u009c\n\6\r\6\16\6\u009d\3\7\7\7\u00a1"+
		"\n\7\f\7\16\7\u00a4\13\7\3\7\3\7\3\7\7\7\u00a9\n\7\f\7\16\7\u00ac\13\7"+
		"\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\5\f\u00c3\n\f\3\f\3\f\3\r\3\r\3\r\3\r\7\r\u00cb\n\r\f"+
		"\r\16\r\u00ce\13\r\3\16\3\16\3\16\3\16\7\16\u00d4\n\16\f\16\16\16\u00d7"+
		"\13\16\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21"+
		"\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\24\3\24"+
		"\3\24\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31"+
		"\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33"+
		"\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\37"+
		"\3\37\3\37\3 \3 \3 \3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\3&\3&\3\'\3\'\3"+
		"(\3(\3)\3)\3*\3*\3+\3+\3,\3,\3-\3-\3.\3.\3/\3/\3\60\3\60\3\61\3\61\3\62"+
		"\3\62\3\63\3\63\3\64\3\64\3\65\3\65\3\66\3\66\3\67\3\67\4\u0085\u0092"+
		"\28\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35"+
		"\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36"+
		";\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a\62c\63e\64g\65i\66k\67"+
		"m8\3\2\t\4\2\f\f\17\17\3\2\62;\4\2C\\c|\3\2c|\3\2C\\\4\2))^^\5\2\f\f\17"+
		"\17``\2\u0172\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2"+
		"\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2"+
		"\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2"+
		"\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2"+
		"\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3"+
		"\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2"+
		"\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2"+
		"S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3"+
		"\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2"+
		"\2\2m\3\2\2\2\3r\3\2\2\2\5{\3\2\2\2\7\177\3\2\2\2\t\u008c\3\2\2\2\13\u009b"+
		"\3\2\2\2\r\u00a2\3\2\2\2\17\u00ad\3\2\2\2\21\u00af\3\2\2\2\23\u00b1\3"+
		"\2\2\2\25\u00b3\3\2\2\2\27\u00b5\3\2\2\2\31\u00c6\3\2\2\2\33\u00cf\3\2"+
		"\2\2\35\u00d8\3\2\2\2\37\u00dc\3\2\2\2!\u00e2\3\2\2\2#\u00e7\3\2\2\2%"+
		"\u00ec\3\2\2\2\'\u00f1\3\2\2\2)\u00f4\3\2\2\2+\u00f9\3\2\2\2-\u0101\3"+
		"\2\2\2/\u0106\3\2\2\2\61\u010c\3\2\2\2\63\u0113\3\2\2\2\65\u0117\3\2\2"+
		"\2\67\u011d\3\2\2\29\u0122\3\2\2\2;\u0127\3\2\2\2=\u012a\3\2\2\2?\u012d"+
		"\3\2\2\2A\u0130\3\2\2\2C\u0132\3\2\2\2E\u0134\3\2\2\2G\u0136\3\2\2\2I"+
		"\u0138\3\2\2\2K\u013a\3\2\2\2M\u013d\3\2\2\2O\u013f\3\2\2\2Q\u0141\3\2"+
		"\2\2S\u0143\3\2\2\2U\u0145\3\2\2\2W\u0147\3\2\2\2Y\u0149\3\2\2\2[\u014b"+
		"\3\2\2\2]\u014d\3\2\2\2_\u014f\3\2\2\2a\u0151\3\2\2\2c\u0153\3\2\2\2e"+
		"\u0155\3\2\2\2g\u0157\3\2\2\2i\u0159\3\2\2\2k\u015b\3\2\2\2m\u015d\3\2"+
		"\2\2os\t\2\2\2pq\7\17\2\2qs\7\f\2\2ro\3\2\2\2rp\3\2\2\2st\3\2\2\2tu\b"+
		"\2\2\2u\4\3\2\2\2v|\5\3\2\2wx\7\"\2\2x|\7\13\2\2yz\7\13\2\2z|\7\16\2\2"+
		"{v\3\2\2\2{w\3\2\2\2{y\3\2\2\2|}\3\2\2\2}~\b\3\2\2~\6\3\2\2\2\177\u0080"+
		"\7/\2\2\u0080\u0081\7/\2\2\u0081\u0085\3\2\2\2\u0082\u0084\13\2\2\2\u0083"+
		"\u0082\3\2\2\2\u0084\u0087\3\2\2\2\u0085\u0086\3\2\2\2\u0085\u0083\3\2"+
		"\2\2\u0086\u0088\3\2\2\2\u0087\u0085\3\2\2\2\u0088\u0089\5\3\2\2\u0089"+
		"\u008a\3\2\2\2\u008a\u008b\b\4\2\2\u008b\b\3\2\2\2\u008c\u008d\7}\2\2"+
		"\u008d\u008e\7/\2\2\u008e\u0092\3\2\2\2\u008f\u0091\13\2\2\2\u0090\u008f"+
		"\3\2\2\2\u0091\u0094\3\2\2\2\u0092\u0093\3\2\2\2\u0092\u0090\3\2\2\2\u0093"+
		"\u0095\3\2\2\2\u0094\u0092\3\2\2\2\u0095\u0096\7/\2\2\u0096\u0097\7\177"+
		"\2\2\u0097\u0098\3\2\2\2\u0098\u0099\b\5\2\2\u0099\n\3\2\2\2\u009a\u009c"+
		"\t\3\2\2\u009b\u009a\3\2\2\2\u009c\u009d\3\2\2\2\u009d\u009b\3\2\2\2\u009d"+
		"\u009e\3\2\2\2\u009e\f\3\2\2\2\u009f\u00a1\t\3\2\2\u00a0\u009f\3\2\2\2"+
		"\u00a1\u00a4\3\2\2\2\u00a2\u00a0\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3\u00a5"+
		"\3\2\2\2\u00a4\u00a2\3\2\2\2\u00a5\u00a6\7\60\2\2\u00a6\u00aa\t\3\2\2"+
		"\u00a7\u00a9\t\3\2\2\u00a8\u00a7\3\2\2\2\u00a9\u00ac\3\2\2\2\u00aa\u00a8"+
		"\3\2\2\2\u00aa\u00ab\3\2\2\2\u00ab\16\3\2\2\2\u00ac\u00aa\3\2\2\2\u00ad"+
		"\u00ae\t\4\2\2\u00ae\20\3\2\2\2\u00af\u00b0\t\3\2\2\u00b0\22\3\2\2\2\u00b1"+
		"\u00b2\t\5\2\2\u00b2\24\3\2\2\2\u00b3\u00b4\t\6\2\2\u00b4\26\3\2\2\2\u00b5"+
		"\u00c2\7)\2\2\u00b6\u00c3\t\7\2\2\u00b7\u00b8\7^\2\2\u00b8\u00c3\7p\2"+
		"\2\u00b9\u00ba\7^\2\2\u00ba\u00c3\7v\2\2\u00bb\u00bc\7^\2\2\u00bc\u00c3"+
		"\7d\2\2\u00bd\u00be\7^\2\2\u00be\u00c3\7t\2\2\u00bf\u00c0\7^\2\2\u00c0"+
		"\u00c3\7^\2\2\u00c1\u00c3\t\b\2\2\u00c2\u00b6\3\2\2\2\u00c2\u00b7\3\2"+
		"\2\2\u00c2\u00b9\3\2\2\2\u00c2\u00bb\3\2\2\2\u00c2\u00bd\3\2\2\2\u00c2"+
		"\u00bf\3\2\2\2\u00c2\u00c1\3\2\2\2\u00c3\u00c4\3\2\2\2\u00c4\u00c5\7)"+
		"\2\2\u00c5\30\3\2\2\2\u00c6\u00cc\5\23\n\2\u00c7\u00cb\5\17\b\2\u00c8"+
		"\u00cb\7a\2\2\u00c9\u00cb\5\21\t\2\u00ca\u00c7\3\2\2\2\u00ca\u00c8\3\2"+
		"\2\2\u00ca\u00c9\3\2\2\2\u00cb\u00ce\3\2\2\2\u00cc\u00ca\3\2\2\2\u00cc"+
		"\u00cd\3\2\2\2\u00cd\32\3\2\2\2\u00ce\u00cc\3\2\2\2\u00cf\u00d5\5\25\13"+
		"\2\u00d0\u00d4\5\17\b\2\u00d1\u00d4\7a\2\2\u00d2\u00d4\5\21\t\2\u00d3"+
		"\u00d0\3\2\2\2\u00d3\u00d1\3\2\2\2\u00d3\u00d2\3\2\2\2\u00d4\u00d7\3\2"+
		"\2\2\u00d5\u00d3\3\2\2\2\u00d5\u00d6\3\2\2\2\u00d6\34\3\2\2\2\u00d7\u00d5"+
		"\3\2\2\2\u00d8\u00d9\7K\2\2\u00d9\u00da\7p\2\2\u00da\u00db\7v\2\2\u00db"+
		"\36\3\2\2\2\u00dc\u00dd\7H\2\2\u00dd\u00de\7n\2\2\u00de\u00df\7q\2\2\u00df"+
		"\u00e0\7c\2\2\u00e0\u00e1\7v\2\2\u00e1 \3\2\2\2\u00e2\u00e3\7E\2\2\u00e3"+
		"\u00e4\7j\2\2\u00e4\u00e5\7c\2\2\u00e5\u00e6\7t\2\2\u00e6\"\3\2\2\2\u00e7"+
		"\u00e8\7D\2\2\u00e8\u00e9\7q\2\2\u00e9\u00ea\7q\2\2\u00ea\u00eb\7n\2\2"+
		"\u00eb$\3\2\2\2\u00ec\u00ed\7f\2\2\u00ed\u00ee\7c\2\2\u00ee\u00ef\7v\2"+
		"\2\u00ef\u00f0\7c\2\2\u00f0&\3\2\2\2\u00f1\u00f2\7k\2\2\u00f2\u00f3\7"+
		"h\2\2\u00f3(\3\2\2\2\u00f4\u00f5\7g\2\2\u00f5\u00f6\7n\2\2\u00f6\u00f7"+
		"\7u\2\2\u00f7\u00f8\7g\2\2\u00f8*\3\2\2\2\u00f9\u00fa\7k\2\2\u00fa\u00fb"+
		"\7v\2\2\u00fb\u00fc\7g\2\2\u00fc\u00fd\7t\2\2\u00fd\u00fe\7c\2\2\u00fe"+
		"\u00ff\7v\2\2\u00ff\u0100\7g\2\2\u0100,\3\2\2\2\u0101\u0102\7t\2\2\u0102"+
		"\u0103\7g\2\2\u0103\u0104\7c\2\2\u0104\u0105\7f\2\2\u0105.\3\2\2\2\u0106"+
		"\u0107\7r\2\2\u0107\u0108\7t\2\2\u0108\u0109\7k\2\2\u0109\u010a\7p\2\2"+
		"\u010a\u010b\7v\2\2\u010b\60\3\2\2\2\u010c\u010d\7t\2\2\u010d\u010e\7"+
		"g\2\2\u010e\u010f\7v\2\2\u010f\u0110\7w\2\2\u0110\u0111\7t\2\2\u0111\u0112"+
		"\7p\2\2\u0112\62\3\2\2\2\u0113\u0114\7p\2\2\u0114\u0115\7g\2\2\u0115\u0116"+
		"\7y\2\2\u0116\64\3\2\2\2\u0117\u0118\7h\2\2\u0118\u0119\7c\2\2\u0119\u011a"+
		"\7n\2\2\u011a\u011b\7u\2\2\u011b\u011c\7g\2\2\u011c\66\3\2\2\2\u011d\u011e"+
		"\7v\2\2\u011e\u011f\7t\2\2\u011f\u0120\7w\2\2\u0120\u0121\7g\2\2\u0121"+
		"8\3\2\2\2\u0122\u0123\7p\2\2\u0123\u0124\7w\2\2\u0124\u0125\7n\2\2\u0125"+
		"\u0126\7n\2\2\u0126:\3\2\2\2\u0127\u0128\7(\2\2\u0128\u0129\7(\2\2\u0129"+
		"<\3\2\2\2\u012a\u012b\7?\2\2\u012b\u012c\7?\2\2\u012c>\3\2\2\2\u012d\u012e"+
		"\7#\2\2\u012e\u012f\7?\2\2\u012f@\3\2\2\2\u0130\u0131\7#\2\2\u0131B\3"+
		"\2\2\2\u0132\u0133\7.\2\2\u0133D\3\2\2\2\u0134\u0135\7\60\2\2\u0135F\3"+
		"\2\2\2\u0136\u0137\7=\2\2\u0137H\3\2\2\2\u0138\u0139\7<\2\2\u0139J\3\2"+
		"\2\2\u013a\u013b\7<\2\2\u013b\u013c\7<\2\2\u013cL\3\2\2\2\u013d\u013e"+
		"\7>\2\2\u013eN\3\2\2\2\u013f\u0140\7@\2\2\u0140P\3\2\2\2\u0141\u0142\7"+
		"?\2\2\u0142R\3\2\2\2\u0143\u0144\7,\2\2\u0144T\3\2\2\2\u0145\u0146\7-"+
		"\2\2\u0146V\3\2\2\2\u0147\u0148\7/\2\2\u0148X\3\2\2\2\u0149\u014a\7\61"+
		"\2\2\u014aZ\3\2\2\2\u014b\u014c\7^\2\2\u014c\\\3\2\2\2\u014d\u014e\7\'"+
		"\2\2\u014e^\3\2\2\2\u014f\u0150\7(\2\2\u0150`\3\2\2\2\u0151\u0152\7)\2"+
		"\2\u0152b\3\2\2\2\u0153\u0154\7]\2\2\u0154d\3\2\2\2\u0155\u0156\7_\2\2"+
		"\u0156f\3\2\2\2\u0157\u0158\7*\2\2\u0158h\3\2\2\2\u0159\u015a\7+\2\2\u015a"+
		"j\3\2\2\2\u015b\u015c\7}\2\2\u015cl\3\2\2\2\u015d\u015e\7\177\2\2\u015e"+
		"n\3\2\2\2\17\2r{\u0085\u0092\u009d\u00a2\u00aa\u00c2\u00ca\u00cc\u00d3"+
		"\u00d5\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}