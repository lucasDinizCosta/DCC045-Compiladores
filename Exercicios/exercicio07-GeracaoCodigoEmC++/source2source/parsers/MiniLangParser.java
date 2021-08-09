package parsers;

import ast.*;
import java.io.IOException;
import beaver.*;
import java.util.ArrayList;

/**
 * This class is a LALR parser generated by
 * <a href="http://beaver.sourceforge.net">Beaver</a> v0.9.6.1
 * from the grammar specification "MiniLang.grammar".
 */
public class MiniLangParser extends Parser {

	static final ParsingTables PARSING_TABLES = new ParsingTables(
		"U9pTbCbpL5KKnh$R7PAGXaxI8GF691X3qeaa6aDm0aLmn15nm5bPk7FdpYhBSkVEXLMwSbX" +
		"OfUL6YoqR5rfbeH1HSK9H7554H0HKzFXxzvsGcwRv1tXLtnc$Tz#vvvrxfR1E8xfGGvhG#R" +
		"f0azga1ZjXNzH4xz9ctQspj4o3zhlELh$zgmPTeUjKr8swHRVNvVTeJKtypFCsvLtHegmhU" +
		"jYz1T6hgg2RrQJBLBBjMXdxrUdzodcNH5wpsdMRHnR6IAiMwqwFjCJ8iBerxP7xO#GXrchN" +
		"4bNKgBBEqpqQvRssoEo8iilvkSmYoxKwwd6DwIAjYlPKb5T6UJtv#E$iSyyl1RsW3mo0Sy2" +
		"vu5A#lW9zkTRfAlj7dNPOLnFRGEmci17SGQKF4BkFVLwiUxMMBgq7r#XQP4atqBrRoJseGA" +
		"UAJDG2zjA45yYMQg9BMAcBNYsa8nNUNop1w6RAgkombvxKuo#ZujhLppmlxqfhx4hLlKBq2" +
		"anf6xrSoiHCexD#bRrVEQyZyjgPa3QF3CV863Cr6raU8uFy#Q5vd57MojuRu3nKE1UJJ7ds" +
		"NivC#RdC8oEkIqpG#3pkR3x$TtFvqw7CN6Qwn6aiuLMOvZ9xJJd3xArCfHsisqS$MjbDAx5" +
		"CzYC3h0wajEL9nQS9zBhzgfdzXtu5l06wmBDPplClW1ct6nd82lejq09sUdpGTJQn4#1jCE" +
		"snrU07i1xi1niylj7rvkHRhrEBeG$bVbOoPqUXPvQJS1y1hpdFlny6FFSeU3xXFWPsa9z2F" +
		"uxk12EWEiS9eyvz2hiCLhZ$D7ZHEMiGpu2jxeyZdWFRJgqlT2EEkDqFrjRPmnDizrZYRqti" +
		"bxD#9FvBYRqjiTz9x7SJ#u3hWwwzfphisclJKTUxadTdm9irjUwkhT$ZUqwDXKMUErx3FP7" +
		"OUvrRcQt9$KxFxtE$ntLZmka3pSbQN#EtrAlF#SsdpsbBhk8PEFdOt$P7r8UIs6$WU$iMoJ" +
		"pRFl0DuDks5tm5lhGzjZDoFujobtrgdzXk#pXw7zc7zhxji9delMSV8ByxkVf1Es2$edzq$" +
		"vSeVtPllw$xansrF#r8j8z7USp#YcTc9VTW00LkmH1FuGIwobcVH4zocuw2OUmv5BZ9CWHk" +
		"YIBtMWEtZ430Rep#cCSpFGLt23s7mErJh8C225IKLPLn6g8$7qLkqm2At84159ITfl5uMnM" +
		"uoOfQzJzns2L6");
 private boolean good;
          
          public boolean isGood(){ return good;}
          
          protected void recoverFromError(Symbol token, TokenStream in) throws IOException, Parser.Exception{
              super.recoverFromError(token, in);
              good = false;
          }

	private final Action[] actions;

	public MiniLangParser() {
		super(PARSING_TABLES);
		actions = new Action[] {
			new Action() {	// [0] lst$func = func
				public Symbol reduce(Symbol[] _symbols, int offset) {
					ArrayList lst = new ArrayList(); lst.add(_symbols[offset + 1].value); return new Symbol(lst);
				}
			},
			new Action() {	// [1] lst$func = lst$func func
				public Symbol reduce(Symbol[] _symbols, int offset) {
					((ArrayList) _symbols[offset + 1].value).add(_symbols[offset + 2].value); return _symbols[offset + 1];
				}
			},
			new Action() {	// [2] Prog = lst$func.s
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_s = _symbols[offset + 1];
					final ArrayList _list_s = (ArrayList) _symbol_s.value;
					final Func[] s = _list_s == null ? new Func[0] : (Func[]) _list_s.toArray(new Func[_list_s.size()]);
					  return new Program(s);
				}
			},
			Action.NONE,  	// [3] opt$PList = 
			Action.RETURN,	// [4] opt$PList = PList
			new Action() {	// [5] func = ID.fn AP opt$PList.p FP COLON Type.t Block.b
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_fn = _symbols[offset + 1];
					final String fn = (String) _symbol_fn.value;
					final Symbol _symbol_p = _symbols[offset + 3];
					final ArrayList _list_p = (ArrayList) _symbol_p.value;
					final Param[] p = _list_p == null ? new Param[0] : (Param[]) _list_p.toArray(new Param[_list_p.size()]);
					final Symbol _symbol_t = _symbols[offset + 6];
					final Tipo t = (Tipo) _symbol_t.value;
					final Symbol _symbol_b = _symbols[offset + 7];
					final Node b = (Node) _symbol_b.value;
					 return new Func(fn,p,t,b);
				}
			},
			new Action() {	// [6] PList = param
				public Symbol reduce(Symbol[] _symbols, int offset) {
					ArrayList lst = new ArrayList(); lst.add(_symbols[offset + 1].value); return new Symbol(lst);
				}
			},
			new Action() {	// [7] PList = PList COMMA param
				public Symbol reduce(Symbol[] _symbols, int offset) {
					((ArrayList) _symbols[offset + 1].value).add(_symbols[offset + 3].value); return _symbols[offset + 1];
				}
			},
			new Action() {	// [8] param = Type.t COLON ID.v
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_t = _symbols[offset + 1];
					final Tipo t = (Tipo) _symbol_t.value;
					final Symbol _symbol_v = _symbols[offset + 3];
					final String v = (String) _symbol_v.value;
					 return new Param(v,t);
				}
			},
			new Action() {	// [9] Type = TYINT
				public Symbol reduce(Symbol[] _symbols, int offset) {
					 return new TyInt();
				}
			},
			new Action() {	// [10] Type = TYFLOAT
				public Symbol reduce(Symbol[] _symbols, int offset) {
					 return new TyFloat();
				}
			},
			new Action() {	// [11] Type = TYBOOL
				public Symbol reduce(Symbol[] _symbols, int offset) {
					 return new TyBool();
				}
			},
			new Action() {	// [12] Type = Type.t TYARR
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_t = _symbols[offset + 1];
					final Tipo t = (Tipo) _symbol_t.value;
					 return new TyArr(t);
				}
			},
			new Action() {	// [13] StmtList = Stmt.s StmtList.l
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_s = _symbols[offset + 1];
					final Node s = (Node) _symbol_s.value;
					final Symbol _symbol_l = _symbols[offset + 2];
					final Node l = (Node) _symbol_l.value;
					 return new StmtList(s,l);
				}
			},
			new Action() {	// [14] StmtList = Stmt.s
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_s = _symbols[offset + 1];
					final Node s = (Node) _symbol_s.value;
					 return s;
				}
			},
			new Action() {	// [15] Block = LBRACE StmtList.s RBRACE
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_s = _symbols[offset + 2];
					final Node s = (Node) _symbol_s.value;
					 return s;
				}
			},
			new Action() {	// [16] Stmt = Var.v ATTR CExpr.e SEMI
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_v = _symbols[offset + 1];
					final Var v = (Var) _symbol_v.value;
					final Symbol _symbol_e = _symbols[offset + 3];
					final Expr e = (Expr) _symbol_e.value;
					 return new Attr(v, e);
				}
			},
			Action.NONE,  	// [17] opt$Els = 
			Action.RETURN,	// [18] opt$Els = Els
			new Action() {	// [19] Stmt = IF AP CExpr.e FP Block.s opt$Els.a
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_e = _symbols[offset + 3];
					final Expr e = (Expr) _symbol_e.value;
					final Symbol _symbol_s = _symbols[offset + 5];
					final Node s = (Node) _symbol_s.value;
					final Symbol _symbol_a = _symbols[offset + 6];
					final Node a = (Node) _symbol_a.value;
					 return new If(e,s,a);
				}
			},
			new Action() {	// [20] Stmt = WHILE CExpr.e RB Block.s
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_e = _symbols[offset + 2];
					final Expr e = (Expr) _symbol_e.value;
					final Symbol _symbol_s = _symbols[offset + 4];
					final Node s = (Node) _symbol_s.value;
					 return new While(e,s);
				}
			},
			new Action() {	// [21] Stmt = PRINT Expr.e SEMI
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_e = _symbols[offset + 2];
					final Expr e = (Expr) _symbol_e.value;
					 return new Print(e);
				}
			},
			new Action() {	// [22] Stmt = INST Var.v Expr.e SEMI
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_v = _symbols[offset + 2];
					final Var v = (Var) _symbol_v.value;
					final Symbol _symbol_e = _symbols[offset + 3];
					final Expr e = (Expr) _symbol_e.value;
					 return new Inst(v,null,e);
				}
			},
			new Action() {	// [23] Stmt = RET Expr.e SEMI
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_e = _symbols[offset + 2];
					final Expr e = (Expr) _symbol_e.value;
					 return new Return(e);
				}
			},
			new Action() {	// [24] Els = COLON Block.a
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_a = _symbols[offset + 2];
					final Node a = (Node) _symbol_a.value;
					 return a;
				}
			},
			new Action() {	// [25] CExpr = Expr.a EQ Expr.b
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_a = _symbols[offset + 1];
					final Expr a = (Expr) _symbol_a.value;
					final Symbol _symbol_b = _symbols[offset + 3];
					final Expr b = (Expr) _symbol_b.value;
					 return new Eq(a,b);
				}
			},
			new Action() {	// [26] CExpr = CExpr.a AND CExpr.b
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_a = _symbols[offset + 1];
					final Expr a = (Expr) _symbol_a.value;
					final Symbol _symbol_b = _symbols[offset + 3];
					final Expr b = (Expr) _symbol_b.value;
					 return new And(a,b);
				}
			},
			new Action() {	// [27] CExpr = Expr.a LT Expr.b
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_a = _symbols[offset + 1];
					final Expr a = (Expr) _symbol_a.value;
					final Symbol _symbol_b = _symbols[offset + 3];
					final Expr b = (Expr) _symbol_b.value;
					 return new Lt(a,b);
				}
			},
			new Action() {	// [28] CExpr = Expr.e
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_e = _symbols[offset + 1];
					final Expr e = (Expr) _symbol_e.value;
					 return e;
				}
			},
			new Action() {	// [29] Expr = Expr.a MULT Expr.b
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_a = _symbols[offset + 1];
					final Expr a = (Expr) _symbol_a.value;
					final Symbol _symbol_b = _symbols[offset + 3];
					final Expr b = (Expr) _symbol_b.value;
					 return new Mul(a,b);
				}
			},
			new Action() {	// [30] Expr = Expr.a PLUS Expr.b
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_a = _symbols[offset + 1];
					final Expr a = (Expr) _symbol_a.value;
					final Symbol _symbol_b = _symbols[offset + 3];
					final Expr b = (Expr) _symbol_b.value;
					 return new Add(a, b);
				}
			},
			new Action() {	// [31] Expr = Expr.a MINUS Expr.b
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_a = _symbols[offset + 1];
					final Expr a = (Expr) _symbol_a.value;
					final Symbol _symbol_b = _symbols[offset + 3];
					final Expr b = (Expr) _symbol_b.value;
					 return new Sub(a, b);
				}
			},
			new Action() {	// [32] Expr = Expr.a DIV Expr.b
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_a = _symbols[offset + 1];
					final Expr a = (Expr) _symbol_a.value;
					final Symbol _symbol_b = _symbols[offset + 3];
					final Expr b = (Expr) _symbol_b.value;
					 return new Div(a, b);
				}
			},
			new Action() {	// [33] Expr = Expr.a MOD Expr.b
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_a = _symbols[offset + 1];
					final Expr a = (Expr) _symbol_a.value;
					final Symbol _symbol_b = _symbols[offset + 3];
					final Expr b = (Expr) _symbol_b.value;
					 return new Mod(a, b);
				}
			},
			new Action() {	// [34] Expr = NOT CExpr.e
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_e = _symbols[offset + 2];
					final Expr e = (Expr) _symbol_e.value;
					 return new Not(e);
				}
			},
			new Action() {	// [35] Expr = Var.v
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_v = _symbols[offset + 1];
					final Var v = (Var) _symbol_v.value;
					 return v;
				}
			},
			Action.NONE,  	// [36] opt$argList = 
			Action.RETURN,	// [37] opt$argList = argList
			new Action() {	// [38] Expr = ID.v AP opt$argList.e FP
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_v = _symbols[offset + 1];
					final String v = (String) _symbol_v.value;
					final Symbol _symbol_e = _symbols[offset + 3];
					final ArrayList _list_e = (ArrayList) _symbol_e.value;
					final Expr[] e = _list_e == null ? new Expr[0] : (Expr[]) _list_e.toArray(new Expr[_list_e.size()]);
					 return new Call(v,e);
				}
			},
			new Action() {	// [39] Expr = INT.n
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_n = _symbols[offset + 1];
					final Integer n = (Integer) _symbol_n.value;
					 return new NInt(n);
				}
			},
			new Action() {	// [40] Expr = FLOAT.f
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_f = _symbols[offset + 1];
					final Float f = (Float) _symbol_f.value;
					 return new NFloat(f);
				}
			},
			new Action() {	// [41] Expr = TRUE
				public Symbol reduce(Symbol[] _symbols, int offset) {
					 return new True();
				}
			},
			new Action() {	// [42] Expr = FALSE
				public Symbol reduce(Symbol[] _symbols, int offset) {
					 return new False();
				}
			},
			new Action() {	// [43] Expr = AP CExpr.e FP
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_e = _symbols[offset + 2];
					final Expr e = (Expr) _symbol_e.value;
					 return e;
				}
			},
			new Action() {	// [44] argList = CExpr
				public Symbol reduce(Symbol[] _symbols, int offset) {
					ArrayList lst = new ArrayList(); lst.add(_symbols[offset + 1].value); return new Symbol(lst);
				}
			},
			new Action() {	// [45] argList = argList COMMA CExpr
				public Symbol reduce(Symbol[] _symbols, int offset) {
					((ArrayList) _symbols[offset + 1].value).add(_symbols[offset + 3].value); return _symbols[offset + 1];
				}
			},
			new Action() {	// [46] lst$index = index
				public Symbol reduce(Symbol[] _symbols, int offset) {
					ArrayList lst = new ArrayList(); lst.add(_symbols[offset + 1].value); return new Symbol(lst);
				}
			},
			new Action() {	// [47] lst$index = lst$index index
				public Symbol reduce(Symbol[] _symbols, int offset) {
					((ArrayList) _symbols[offset + 1].value).add(_symbols[offset + 2].value); return _symbols[offset + 1];
				}
			},
			Action.NONE,  	// [48] opt$lst$index = 
			Action.RETURN,	// [49] opt$lst$index = lst$index
			new Action() {	// [50] Var = ID.v opt$lst$index.i
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_v = _symbols[offset + 1];
					final String v = (String) _symbol_v.value;
					final Symbol _symbol_i = _symbols[offset + 2];
					final ArrayList _list_i = (ArrayList) _symbol_i.value;
					final Expr[] i = _list_i == null ? new Expr[0] : (Expr[]) _list_i.toArray(new Expr[_list_i.size()]);
					 return new Var(v,i);
				}
			},
			new Action() {	// [51] index = LB Expr.e RB
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_e = _symbols[offset + 2];
					final Expr e = (Expr) _symbol_e.value;
					 return e;
				}
			}
		};

 good = true;
	}

	protected Symbol invokeReduceAction(int rule_num, int offset) {
		return actions[rule_num].reduce(_symbols, offset);
	}
}