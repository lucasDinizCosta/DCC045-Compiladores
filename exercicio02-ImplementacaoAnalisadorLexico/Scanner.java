import java.io.FileInputStream;
import java.io.IOException;
import java.io.PushbackInputStream;
import java.util.Stack;

// Classe do analisador Lexico
public class Scanner {
    private class CharInput{
        int l, c;
        char ch;

        CharInput(char ch, int line, int col){
            l = line;
            c = col;
            this.ch = ch;
        }
    }    

    // Toda vez que for feita a leitura de um caractere pelo NextChar
    // Será adicionado nesta pilha para poder voltar o caractere
    private Stack<CharInput> buf;

    private PushbackInputStream file;

    private Stack<Integer> stack; // pilha dos estados sendo processados na simulação do autômato

    private boolean flag_eof = false;

    private String lexeme;
    private int line, column;
    private int Type[] = {Token.VAR, Token.INT, Token.PLUS, Token.MULT, Token.EQ, Token.SEMI, Token.EOF};

    // Automato

    private final int ST_INIT = 0;
    private final int ST_1 = 1;
    private final int ST_2 = 2;
    private final int ST_3 = 3;
    private final int ST_4 = 4;
    private final int ST_VAR = 5;
    private final int ST_INT = 6;
    private final int ST_PLUS = 7;
    private final int ST_MULT = 8;
    private final int ST_EQ = 9;
    private final int ST_SEMI = 10;
    private final int ST_EOF = 11;
    private final int ST_SKIP = 12;
    private final int ST_ERROR = 13;

    private final int ST_BAD = -1;
    
    // Tipos de Tokens
    private final int CAT_EOF = 0;
    private final int CAT_LETTER = 1;
    private final int CAT_DIGIT = 2;
    private final int CAT_PLUS = 3;
    private final int CAT_MULT = 4;
    private final int CAT_EQ = 5;
    private final int CAT_SEMI = 6;
    private final int CAT_DIV = 7;  // /
    private final int CAT_BKL = 8;  // \n
    private final int CAT_WS = 9;   // whitespace
    private final int CAT_ANY = 10;

    // Tabela de Tokens com (Quantidade de estados x Quantidade de possíveis Tokens)
    private int[][] d;

    public Scanner(String path) throws IOException{
        // Abrir o arquivo
        //PushbackInputStream: facilita a voltar caractere para a entrada quando nao precisar
        file = new PushbackInputStream(new FileInputStream(path));

        // inicializar os atributos
        stack = new Stack<Integer>();
        buf = new Stack<CharInput>();

        // Inicializar a função de transição
        d = new int[13][11];
        d[ST_INIT][CAT_EOF] = ST_EOF;
        d[ST_INIT][CAT_LETTER] = ST_VAR;
        d[ST_INIT][CAT_DIGIT] = ST_INT;
        d[ST_INIT][CAT_PLUS] = ST_PLUS;
        d[ST_INIT][CAT_MULT] = ST_MULT;
        d[ST_INIT][CAT_EQ] = ST_EQ;
        d[ST_INIT][CAT_SEMI] = ST_SEMI;
        d[ST_INIT][CAT_DIV] = ST_1;
        d[ST_INIT][CAT_BKL] = ST_SKIP;
        d[ST_INIT][CAT_WS] = ST_SKIP;
        d[ST_INIT][CAT_ANY] = ST_ERROR;
        // Transições do estado 1
        d[ST_1][CAT_EOF] = ST_ERROR;
        d[ST_1][CAT_LETTER] = ST_ERROR;
        d[ST_1][CAT_DIGIT] = ST_ERROR;
        d[ST_1][CAT_PLUS] = ST_ERROR;
        d[ST_1][CAT_MULT] = ST_3;
        d[ST_1][CAT_EQ] = ST_ERROR;
        d[ST_1][CAT_SEMI] = ST_ERROR;
        d[ST_1][CAT_DIV] = ST_2;
        d[ST_1][CAT_BKL] = ST_ERROR;
        d[ST_1][CAT_WS] = ST_ERROR;
        d[ST_1][CAT_ANY] = ST_ERROR;
        // Transições do estado 2
        d[ST_2][CAT_EOF] = ST_SKIP;
        d[ST_2][CAT_LETTER] = ST_2;
        d[ST_2][CAT_DIGIT] = ST_2;
        d[ST_2][CAT_PLUS] = ST_2;
        d[ST_2][CAT_MULT] = ST_2;
        d[ST_2][CAT_EQ] = ST_2;
        d[ST_2][CAT_SEMI] = ST_2;
        d[ST_2][CAT_DIV] = ST_2;
        d[ST_2][CAT_BKL] = ST_SKIP;
        d[ST_2][CAT_WS] = ST_2;
        d[ST_2][CAT_ANY] = ST_2;
        // Transições do estado 3
        d[ST_3][CAT_EOF] = ST_3;
        d[ST_3][CAT_LETTER] = ST_3;
        d[ST_3][CAT_DIGIT] = ST_3;
        d[ST_3][CAT_PLUS] = ST_3;
        d[ST_3][CAT_MULT] = ST_4;
        d[ST_3][CAT_EQ] = ST_3;
        d[ST_3][CAT_SEMI] = ST_3;
        d[ST_3][CAT_DIV] = ST_3;
        d[ST_3][CAT_BKL] = ST_3;
        d[ST_3][CAT_WS] = ST_3;
        d[ST_3][CAT_ANY] = ST_3;
        // Transições do estado 4
        d[ST_4][CAT_EOF] = ST_ERROR;
        d[ST_4][CAT_LETTER] = ST_3;
        d[ST_4][CAT_DIGIT] = ST_3;
        d[ST_4][CAT_PLUS] = ST_3;
        d[ST_4][CAT_MULT] = ST_4;
        d[ST_4][CAT_EQ] = ST_3;
        d[ST_4][CAT_SEMI] = ST_3;
        d[ST_4][CAT_DIV] = ST_SKIP;
        d[ST_4][CAT_BKL] = ST_3;
        d[ST_4][CAT_WS] = ST_3;
        d[ST_4][CAT_ANY] = ST_3;
        // Transições do estado VAR
        d[ST_VAR][CAT_EOF] = ST_ERROR;
        d[ST_VAR][CAT_LETTER] = ST_ERROR;
        d[ST_VAR][CAT_DIGIT] = ST_ERROR;
        d[ST_VAR][CAT_PLUS] = ST_ERROR;
        d[ST_VAR][CAT_MULT] = ST_ERROR;
        d[ST_VAR][CAT_EQ] = ST_ERROR;
        d[ST_VAR][CAT_SEMI] = ST_ERROR;
        d[ST_VAR][CAT_DIV] = ST_ERROR;
        d[ST_VAR][CAT_BKL] = ST_ERROR;
        d[ST_VAR][CAT_WS] = ST_ERROR;
        d[ST_VAR][CAT_ANY] = ST_ERROR;
        // Transições do estado INT
        d[ST_INT][CAT_EOF] = ST_ERROR;
        d[ST_INT][CAT_LETTER] = ST_ERROR;
        d[ST_INT][CAT_DIGIT] = ST_INT;
        d[ST_INT][CAT_PLUS] = ST_ERROR;
        d[ST_INT][CAT_MULT] = ST_ERROR;
        d[ST_INT][CAT_EQ] = ST_ERROR;
        d[ST_INT][CAT_SEMI] = ST_ERROR;
        d[ST_INT][CAT_DIV] = ST_ERROR;
        d[ST_INT][CAT_BKL] = ST_ERROR;
        d[ST_INT][CAT_WS] = ST_ERROR;
        d[ST_INT][CAT_ANY] = ST_ERROR;
        // Transições do estado PLUS
        d[ST_PLUS][CAT_EOF] = ST_ERROR;
        d[ST_PLUS][CAT_LETTER] = ST_ERROR;
        d[ST_PLUS][CAT_DIGIT] = ST_ERROR;
        d[ST_PLUS][CAT_PLUS] = ST_ERROR;
        d[ST_PLUS][CAT_MULT] = ST_ERROR;
        d[ST_PLUS][CAT_EQ] = ST_ERROR;
        d[ST_PLUS][CAT_SEMI] = ST_ERROR;
        d[ST_PLUS][CAT_DIV] = ST_ERROR;
        d[ST_PLUS][CAT_BKL] = ST_ERROR;
        d[ST_PLUS][CAT_WS] = ST_ERROR;
        d[ST_PLUS][CAT_ANY] = ST_ERROR;
        // Transições do estado MULT
        d[ST_MULT][CAT_EOF] = ST_ERROR;
        d[ST_MULT][CAT_LETTER] = ST_ERROR;
        d[ST_MULT][CAT_DIGIT] = ST_ERROR;
        d[ST_MULT][CAT_PLUS] = ST_ERROR;
        d[ST_MULT][CAT_MULT] = ST_ERROR;
        d[ST_MULT][CAT_EQ] = ST_ERROR;
        d[ST_MULT][CAT_SEMI] = ST_ERROR;
        d[ST_MULT][CAT_DIV] = ST_ERROR;
        d[ST_MULT][CAT_BKL] = ST_ERROR;
        d[ST_MULT][CAT_WS] = ST_ERROR;
        d[ST_MULT][CAT_ANY] = ST_ERROR;
        // Transições do estado EQ
        d[ST_EQ][CAT_EOF] = ST_ERROR;
        d[ST_EQ][CAT_LETTER] = ST_ERROR;
        d[ST_EQ][CAT_DIGIT] = ST_ERROR;
        d[ST_EQ][CAT_PLUS] = ST_ERROR;
        d[ST_EQ][CAT_MULT] = ST_ERROR;
        d[ST_EQ][CAT_EQ] = ST_ERROR;
        d[ST_EQ][CAT_SEMI] = ST_ERROR;
        d[ST_EQ][CAT_DIV] = ST_ERROR;
        d[ST_EQ][CAT_BKL] = ST_ERROR;
        d[ST_EQ][CAT_WS] = ST_ERROR;
        d[ST_EQ][CAT_ANY] = ST_ERROR;
        // Transições do estado SEMI
        d[ST_SEMI][CAT_EOF] = ST_ERROR;
        d[ST_SEMI][CAT_LETTER] = ST_ERROR;
        d[ST_SEMI][CAT_DIGIT] = ST_ERROR;
        d[ST_SEMI][CAT_PLUS] = ST_ERROR;
        d[ST_SEMI][CAT_MULT] = ST_ERROR;
        d[ST_SEMI][CAT_EQ] = ST_ERROR;
        d[ST_SEMI][CAT_SEMI] = ST_ERROR;
        d[ST_SEMI][CAT_DIV] = ST_ERROR;
        d[ST_SEMI][CAT_BKL] = ST_ERROR;
        d[ST_SEMI][CAT_WS] = ST_ERROR;
        d[ST_SEMI][CAT_ANY] = ST_ERROR;
        // Transições do estado SKIP
        d[ST_SKIP][CAT_EOF] = ST_ERROR;
        d[ST_SKIP][CAT_LETTER] = ST_ERROR;
        d[ST_SKIP][CAT_DIGIT] = ST_ERROR;
        d[ST_SKIP][CAT_PLUS] = ST_ERROR;
        d[ST_SKIP][CAT_MULT] = ST_ERROR;
        d[ST_SKIP][CAT_EQ] = ST_ERROR;
        d[ST_SKIP][CAT_SEMI] = ST_ERROR;
        d[ST_SKIP][CAT_DIV] = ST_ERROR;
        d[ST_SKIP][CAT_BKL] = ST_SKIP;
        d[ST_SKIP][CAT_WS] = ST_SKIP;
        d[ST_SKIP][CAT_ANY] = ST_ERROR;
        // Transições do estado EOF
        d[ST_EOF][CAT_EOF] = ST_ERROR;
        d[ST_EOF][CAT_LETTER] = ST_ERROR;
        d[ST_EOF][CAT_DIGIT] = ST_ERROR;
        d[ST_EOF][CAT_PLUS] = ST_ERROR;
        d[ST_EOF][CAT_MULT] = ST_ERROR;
        d[ST_EOF][CAT_EQ] = ST_ERROR;
        d[ST_EOF][CAT_SEMI] = ST_ERROR;
        d[ST_EOF][CAT_DIV] = ST_ERROR;
        d[ST_EOF][CAT_BKL] = ST_ERROR;
        d[ST_EOF][CAT_WS] = ST_ERROR;
        d[ST_EOF][CAT_ANY] = ST_ERROR;
        // Transições do estado ERROR
        // Não é necessário pois nunca teremos acesso depois do estado de erro
    }

    public boolean isSkip(int state){
        return state == ST_SKIP;
    }

    private boolean isFinal(int state){
        if(state >= ST_VAR && state < ST_SKIP)
            return true;
        return false;
    }

    private char nextChar() throws IOException{
        int ch = file.read();
        if(ch == -1){
            flag_eof = true;
            return '\0';        // Nao vai fazer diferença o retorno mas é só pq a função pede mesmo
        }
        if((char) ch == '\n'){  // nova linha
            line++;
            column = 0;
        } else
            column++;
        buf.push(new CharInput((char) ch, line, column));
        return (char) ch;
    }

    private void rollback() throws IOException{
        line = buf.peek().l;
        column = buf.peek().c;
        file.unread(buf.pop().ch);
        // Truncar o lexeme e Voltar a linha e coluna
        lexeme = lexeme.substring(0, lexeme.length() - 1);
    }

    private int charCat(char ch){
        if(flag_eof){
            return CAT_EOF;
        }
        if(ch >= 'a' && ch <= 'z')  // Uma letra
            return CAT_LETTER;
        if(ch >= '0' && ch <= '9')
            return CAT_DIGIT;
        switch(ch){
        case '+':
            return CAT_PLUS;
        case '*':
            return CAT_MULT;
        case '=':
            return CAT_EQ;
        case ';':
            return CAT_SEMI;
        case '/':
            return CAT_DIV;
        case '\n':
            return CAT_BKL;
        case ' ':
        case '\t':
        case '\r':
            return CAT_WS;
        default:
            return CAT_ANY;
        }
    }

    private void runAFD(int state) throws IOException{
        // Inicializa
        lexeme = "";
        stack.clear();
        stack.push(ST_BAD);

        // processar
        char ch;
        // Será feita a leitura de um caractere e será verificado na tabela de mepeamento e verifico
        // Categoria de mapeamento
        int cat;
        while(state != ST_ERROR){
            ch = nextChar();
            lexeme = lexeme + ch;
            stack.push(state);
            cat = charCat(ch);
            state = d[state][cat];  //Função de transição
        }
        rollback();
    }

    // O parse vai chamar esse método e retornar o próximo token
    // Implementação por tabela
    public Token nextToken() throws IOException{
        if(flag_eof){   // Atingiu o final de arquivo
            return new Token(Token.EOF, "", line, column);
        }
        int state = ST_INIT;

        do{
            runAFD(state);
        } while(isSkip(stack.peek()));

        state = stack.pop();
        
        // Volta no automato
        // 1 -> 3 -> 5 -> 6 (final) -> 7 -> ... -> error
        // 1 -> 3 -> 5 -> 6 (final) -> 7 -> ... 9
        // 1 -> 3 -> 5 -> 6 (final)     // Reconheceu um token valido
        while(!isFinal(state) && state != ST_BAD){
            state = stack.pop();
            rollback();
        }

        // Emitir o token
        if(isFinal(state)) {
            return new Token(Type[state - ST_VAR], lexeme, line, column);
        } else {
            System.err.println("Error: invalid token at line " + line + ", column " + column);
            System.exit(1);
        }
        return null;
    }
}
