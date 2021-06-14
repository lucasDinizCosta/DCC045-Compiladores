public class Token {
    public int l, c;        // Linha e coluna do token
    public TOKEN_TYPE t;    // Um dos elementos da enumeração de Token
    public String lexeme;   // String que casou com o Token
    public Object info;     // Informações adicionais
                            // Pra um numero poderia ser o valor numerico do lexema

    public Token(TOKEN_TYPE t, String lex, Object o, int l, int c){
        this.t = t;
        lexeme = lex;
        info = o;
        this.l = l;
        this.c = c;
    }

    public Token(TOKEN_TYPE t, String lex, int l, int c){
        this.t = t;
        lexeme = lex;
        info = null;
        this.l = l;
        this.c = c;
    }

    public Token(TOKEN_TYPE t, Object o, int l, int c){
        this.t = t;
        lexeme = "";
        info = o;
        this.l = l;
        this.c = c;
    }

    @Override
    public String toString(){
        return "[("+ l +","+ c + ") \"" + lexeme + "\" : <" + (info == null ? "" : info.toString()) + ">]";
    }
}
