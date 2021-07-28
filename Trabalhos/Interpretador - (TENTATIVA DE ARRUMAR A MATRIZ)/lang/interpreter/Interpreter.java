/********************************************************
* Trabalho de Teoria dos Compiladores(DCC045)(2021/1)   *
*                                                       *
* Projeto do Interpretador para a Linguagem Lang        *
* Nome: Lucas Diniz da Costa -- Matricula: 201465524C   *
*                                                       *
*********************************************************/
package lang.interpreter;

import lang.ast.SuperNode;
import lang.ast.Node;
import lang.interpreter.InterpretVisitor;
import lang.interpreter.Visitor;
import lang.parser.*;

import java.io.IOException;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Interpreter {
    public void interpret() {
        String src = "input.txt";
        CharStream stream = null;
        try {
            stream = CharStreams.fromFileName(src);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        LangLexer lexer = new LangLexer(stream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LangParser parser = new LangParser(tokens);
        ParseTree tree = parser.prog();

        // Verifica se o analisador sintático encontrou erros
        if (parser.getNumberOfSyntaxErrors() != 0) {
            return;
        }
        
        // Cria um adaptador da ParseTree do ANTLR para receber o padrão Node criado para a AST do trabalho
        VisitorAdapter ast = new VisitorAdapter();
        Node node = ast.visit(tree);            // Passa um do tipo parseTree e retorna do tipo Node
        // Interpreta o Visitor e elabora o ambiente de desenvolvimento
        InterpretVisitor interpreter = new InterpretVisitor();      
        node.accept(interpreter);               // Passa o node criado e testa o interpretador
    }
}
