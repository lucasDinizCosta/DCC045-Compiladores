package parser;


import java.util.Stack;
import java.util.LinkedList;
import java.io.FileReader;
import java.io.IOException;
import ast.*;

public class LogicParser extends TopDownRecursiveParser {

    public LogicParser(String fileName) throws IOException {
        super(fileName);
    }

    public Node prog() throws IOException {
        Node list = null;

        if (stmtList() && eof) {
            list = ast.peek();
        }

        System.out.println("Last: " + nxt);
        System.out.println("Remaining input: ");

        while (nxt != null) {
            System.out.println("" + nxt);
            readToken();
        }

        System.out.println("EOF status: " + eof);
        return list;
    }

    public boolean stmtList() throws IOException {
        int n = stk.size();
        int k = ast.size(); // Tamanho da pilha de nós
        // stmt -> stmt ; stmtList
        if (stmt()) {
            int x = getCurrLine();
            int y = getCurrCol();
            if (match(TOKEN_TYPE.SEMI)) {
                if (stmtList()) {
                    Node list = ast.pop();
                    Node stmt = ast.pop();
                    Node stmtl = new StmtList(x, y, stmt, list); // Cria um novo nó
                    ast.push(stmtl); // Empilha o novo nó criado
                    System.out.println("putting CmdList: " + stmt + ",  " + list);
                    return true;
                }
            }
        }

        backtrack(stk.size() - n, ast.size() - k);
        // stmtlist -> stmt ;
        if (stmt()) {
            if (match(TOKEN_TYPE.SEMI)) {
                System.out.println("putting Stmt: " + ast.peek());
                return true;
            }
        }
        backtrack(stk.size() - n, ast.size() - k);

        return false;
    }

    public boolean stmt() throws IOException {
        int n = stk.size(); // Número de elementos lidos até o momento
        int k = ast.size();
        int x = getCurrLine(); // Linha e coluna do token
        int y = getCurrCol();
        // Atribuição
        // stmt -> Exp1 Exp
        if (exp1()) {
            if (exp()) {
                Print p = new Print(x,y,(Expr)ast.pop());
                ast.push(p);
                return true;
            }
        }
        // Se chegar nesse ponto significa que deu algo errado no casamento de tokens
        // Portanto tem que desfazer, para isso faz-se o backtrack
        backtrack(stk.size() - n, ast.size() - k);
        return false;
    }

    public boolean exp() throws IOException {
        int n = stk.size(); // número de tokens lidos até o momento
        int k = ast.size();
        int x = getCurrLine(); // linha e coluna do token atual
        int y = getCurrCol();

        // Exp -> ∨ Exp1 Exp
        if (match(TOKEN_TYPE.OR)) {
            if (exp1()) {
                Expr rgt = (Expr) ast.pop();
                Expr lft = (Expr) ast.pop();
                System.out.println("putting Or " + rgt + ", " + lft);
                ast.push(new Or(x, y, lft, rgt));
                if (exp()) {
                    return true;
                }
            }
        }

        backtrack(stk.size() - n, ast.size() - k);

        // Exp -> ​Vazio
        return true;
    }

    public boolean exp1() throws IOException {
        int n = stk.size();
        int k = ast.size();
        int x = getCurrLine(); // Linha e coluna do token atual
        int y = getCurrCol();

        // Exp1 -> ¬ Exp1
        if (match(TOKEN_TYPE.NOT)) {
            if (exp1()) {
                Expr rgt = (Expr) ast.pop();
                System.out.println("putting Not " + rgt);
                ast.push(new Not(x, y, rgt));
                return true;
            }
        }

        backtrack(stk.size() - n, ast.size() - k);
        // Exp1 -> TRUE
        if (match(TOKEN_TYPE.TRUE)) {
            ast.push(new Bool(x, y, stk.peek().lexeme, true));
            return true;
        }

        backtrack(stk.size() - n, ast.size() - k);

        // Exp1 -> FALSE
        if (match(TOKEN_TYPE.FALSE)) {
            ast.push(new Bool(x, y, stk.peek().lexeme, false));
            return true;
        }

        backtrack(stk.size() - n, ast.size() - k);
        return false;
    }
}
