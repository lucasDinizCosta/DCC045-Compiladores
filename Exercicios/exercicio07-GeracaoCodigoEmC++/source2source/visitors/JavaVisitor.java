package visitors;

import ast.*;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import langUtil.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;

public class  JavaVisitor extends Visitor {

    private STGroup groupTemplate;
    private ST type, stmt, expr;
    private List<ST> funcs, params;

    private String fileName;

    TyEnv<LocalEnv<SType>> env;
    
    public JavaVisitor(String fileName, TyEnv<LocalEnv<SType>> env) {
	groupTemplate = new STGroupFile("./template/java.stg");
	this.fileName = fileName;
	this.env = env;
    }

    public void visit(Program p) {
	ST template = groupTemplate.getInstanceOf("program");

	template.add("name", fileName);
	funcs = new ArrayList<ST>();
	for(Func f : p.getFuncs()) {
	    f.accept(this);
	}
	template.add("funcs", funcs);

	System.out.println(template.render());

    }
     
    public void visit(Add e) {
	ST aux = groupTemplate.getInstanceOf("add_expr");
	e.getLeft().accept(this);
	aux.add("left_expr", expr);
	e.getRight().accept(this);
	aux.add("right_expr", expr);
	expr = aux;
    }
    
    public void visit(Sub e) {
	ST aux = groupTemplate.getInstanceOf("sub_expr");
	e.getLeft().accept(this);
	aux.add("left_expr", expr);
	e.getRight().accept(this);
	aux.add("right_expr", expr);
	expr = aux;
    }
    
    public void visit(Mul e) {
	ST aux = groupTemplate.getInstanceOf("mul_expr");
	e.getLeft().accept(this);
	aux.add("left_expr", expr);
	e.getRight().accept(this);
	aux.add("right_expr", expr);
	expr = aux;
    }
    
    public void visit(Div e) {
	ST aux = groupTemplate.getInstanceOf("div_expr");
	e.getLeft().accept(this);
	aux.add("left_expr", expr);
	e.getRight().accept(this);
	aux.add("right_expr", expr);
	expr = aux;
    }
    
    public void visit(Mod e) {
	ST aux = groupTemplate.getInstanceOf("mod_expr");
	e.getLeft().accept(this);
	aux.add("left_expr", expr);
	e.getRight().accept(this);
	aux.add("right_expr", expr);
	expr = aux;
    }
     
    public void visit(And e) {
	ST aux = groupTemplate.getInstanceOf("and_expr");
	e.getLeft().accept(this);
	aux.add("left_expr", expr);
	e.getRight().accept(this);
	aux.add("right_expr", expr);
	expr = aux;
    }
    
    public void visit(Lt e) {
	ST aux = groupTemplate.getInstanceOf("lt_expr");
	e.getLeft().accept(this);
	aux.add("left_expr", expr);
	e.getRight().accept(this);
	aux.add("right_expr", expr);
	expr = aux;
    }
    
    public void visit(Eq e) {
	ST aux = groupTemplate.getInstanceOf("lt_expr");
	e.getLeft().accept(this);
	aux.add("left_expr", expr);
	e.getRight().accept(this);
	aux.add("right_expr", expr);
	expr = aux;
    }
    
    public void visit(Not e) {
	ST aux = groupTemplate.getInstanceOf("not_expr");
	e.getExpr().accept(this);
	aux.add("expr", expr);
	expr = aux;
    }
     
    public void visit(True e) {
	expr = groupTemplate.getInstanceOf("boolean_expr");
	expr.add("value", true);
    }
    
    public void visit(False e) {
	expr = groupTemplate.getInstanceOf("boolean_expr");
	expr.add("value", false);
    }
    
    public void visit(NInt e) {
	expr = groupTemplate.getInstanceOf("int_expr");
	expr.add("value", e.getValue());
    }
    
    public void visit(NFloat e) {
	expr = groupTemplate.getInstanceOf("float_expr");
	expr.add("value", e.getValue());
    }
    
    public void visit(Var e) {
	ST lvalue = groupTemplate.getInstanceOf("lvalue");
	lvalue.add("name",e.getName());
	for( Expr exp : e.getIdx()) {
	    exp.accept(this);
	    lvalue.add("array", expr);
	}
	expr = lvalue;
    }
    
    public void visit(Call e) {
	ST aux = groupTemplate.getInstanceOf("call");
	aux.add("name", e.getName());
	for(Expr exp : e.getArgs()) {
	    exp.accept(this);
	    aux.add("args", expr);
	}
	expr = aux;
    }
     
    public void visit(Attr e) {
	stmt = groupTemplate.getInstanceOf("attr");
	e.getID().accept(this);
	stmt.add("var", expr);
	e.getExp().accept(this);
	stmt.add("expr", expr);
    }
    
    public void visit(If e) {
	ST aux = groupTemplate.getInstanceOf("if");
	
	e.getTeste().accept(this);
	aux.add("expr", expr);
	e.getThen().accept(this);
	aux.add("thn", stmt);
	Node n = e.getElse();
	if(n != null) {
	    n.accept(this);
	    aux.add("els", stmt);
	}
	stmt = aux;
    }
    
    public void visit(While e) {
	ST aux = groupTemplate.getInstanceOf("while");
	e.getTeste().accept(this);
	aux.add("expr", expr);
	e.getBody().accept(this);
	aux.add("stmt", stmt);
	stmt = aux;
    }
    
    public void visit(Print e) {
	stmt = groupTemplate.getInstanceOf("print");
	e.getExpr().accept(this);
	stmt.add("expr", expr);
    }
    
    public void visit(StmtList e) {
	ST aux = groupTemplate.getInstanceOf("stmt_list");
	e.getCmd1().accept(this);
	aux.add("stmt1", stmt);
	Node s = e.getCmd2();
	if(s != null) {
	    s.accept(this);
	    aux.add("stmt2", stmt);
	}
	stmt = aux;
    }
    
    public void visit(Func f) {
	ST fun = groupTemplate.getInstanceOf("func");
	fun.add("name", f.getID());

	LocalEnv<SType> local = env.get(f.getID());
	Set<String> keys = local.getKeys();

	f.getTipo().accept(this);
	fun.add("type", type);

	params = new ArrayList<ST>();
	for(Param p : f.getParams()) {
	    keys.remove(p.getID());
	    p.accept(this);
	}
	fun.add("params", params);

	for(String key : keys) {
	    ST decl = groupTemplate.getInstanceOf("param");
	    decl.add("name", key);
	    SType t = local.get(key);
	    processSType(t);
	    decl.add("type", type);
	    fun.add("decl", decl);
	}
	
	f.getBody().accept(this);
	fun.add("stmt", stmt);

	funcs.add(fun);
    }
     
    public void visit(Inst e) {
	stmt = groupTemplate.getInstanceOf("new_array");
	e.getID().accept(this);
	stmt.add("var", expr);
	e.getTipo().accept(this);
	stmt.add("type", type);
	e.getSize().accept(this);
	stmt.add("expr", expr);
    }
    
    public void visit(Return e) {
	stmt = groupTemplate.getInstanceOf("return");
	e.getExpr().accept(this);
	stmt.add("expr", expr);
    }
    
    public void visit(Param e) {
	ST param = groupTemplate.getInstanceOf("param");
	e.getTipo().accept(this);
	param.add("type", type);
	param.add("name", e.getID());
	params.add(param);
    }
     
    public void visit(TyInt t) {
	type = groupTemplate.getInstanceOf("int_type");
    }
    
    public void visit(TyFloat t) {
	type = groupTemplate.getInstanceOf("float_type");
    }
    
    public void visit(TyBool t) {
	type = groupTemplate.getInstanceOf("boolean_type");
    }
    
    public void visit(TyArr t) {
	ST aux = groupTemplate.getInstanceOf("array_type");
	t.getTyArg().accept(this);
	aux.add("type", type);
	type = aux;
    }

    ////////////// Métodos ///////////
    private void processSType(SType t) {
	if(t instanceof STyInt)
	    type = groupTemplate.getInstanceOf("int_type");
	else if(t instanceof STyBool)
	    type = groupTemplate.getInstanceOf("boolean_type");
	else if(t instanceof STyFloat)
	    type = groupTemplate.getInstanceOf("float_type");
	else if(t instanceof STyArr) {
	    ST aux = groupTemplate.getInstanceOf("array_type");
	    processSType(((STyArr) t).getArg());
	    aux.add("type", type);
	    type = aux;
	}
    }

    
}
