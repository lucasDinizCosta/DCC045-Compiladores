
public class Token{
    public static final int VAR = 1;    // a,b,c,...
    public static final int INT = 2;    // 1,2,3,..
    public static final int EQ = 3;     // =
    public static final int SEMI = 4;   // ;
    public static final int PLUS = 5;   // +
    public static final int MULT = 6;   // *
    public static final int EOF = -1;

    private int line, col;  
    private int type;       // O tipo do token
    private String lexeme;

    public Token(int type, String lexeme, int l, int c){
        line = l;
        col = c;
        this.type = type;
        this.lexeme = lexeme;
    }

    public int getLine(){ return line; }
    public int getColumn(){ return line;}
    public String getLexeme(){ return lexeme;}
    public int getToken(){ return type;}
}