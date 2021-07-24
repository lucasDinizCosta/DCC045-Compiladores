package lang.parser;

import lang.parser.LangLexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.LexerNoViableAltException;
import org.antlr.v4.runtime.RecognitionException;

public class LexerErrors extends LangLexer{
    public LexerErrors(CharStream input) {
        super(input);
    }
    // Quando encontrar um erro léxico não faz nada, para a execução
    public void recover(LexerNoViableAltException e) { }
    public void recover(RecognitionException re) { }
}
