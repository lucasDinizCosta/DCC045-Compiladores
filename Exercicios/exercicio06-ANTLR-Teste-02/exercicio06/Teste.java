import parserExerc06.*;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Teste {
    public static void main(String args[]) throws Exception{
        // Create a ANTLR CharStream from a file
        CharStream stream = CharStreams.fromFileName(args[0]);

        // Create a lexer that feeds off of stream
        LangLexer lex = new LangLexer(stream);

        // Create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lex);

        // Create a parser that feeds off the tokens buffer
        LangParser parser = new LangParser(tokens);

        ParseTree tree = parser.prog();
        System.out.println(tree.toStringTree(parser));
    }
}
