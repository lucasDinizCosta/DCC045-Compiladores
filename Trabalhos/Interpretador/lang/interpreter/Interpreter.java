package lang.interpreter;

import lang.ast.SuperNode;
import lang.ast.Node;
import lang.interpreter.InterpreterVisitor;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Interpreter {
    public void interpret() {
        CharStream stream = null;
        try {
            stream = CharStreams.fromFileName("input.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        LangLexer lexer = new LangLexer(stream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LangParser parser = new LangParser(tokens);
        ParseTree tree = parser.prog();
        VisitorAdapter ast = new VisitorAdapter();
        Node node = ast.visit(tree);
        InterpretVisitor interpreter = new InterpretVisitor();
        node.accept(this);
    }
}
