
 /*  Esta seção é copiada antes da declaração da classe do analisador léxico.
  *  É nesta seção que se deve incluir imports e declaração de pacotes.
  *  Neste exemplo não temos nada a incluir nesta seção.
  */
  
%%

%unicode
%line
%column
%class LexTest
%function nextToken
%type Token

%{
    
    /* Código arbitrário pode ser inserido diretamente no analisador dessa forma. 
     * Aqui podemos declarar variáveis e métodos adicionais que julgarmos necessários. 
     */
    private int ntk;
    
    public int readedTokens(){
       return ntk;
    }
    private Token symbol(TOKEN_TYPE t) {
        ntk++;
        return new Token(t,yytext(), yyline+1, yycolumn+1);
        
    }
    private Token symbol(TOKEN_TYPE t, Object value) {
        ntk++;
        return new Token(t, value, yyline+1, yycolumn+1);
    }
%}

%init{
    ntk = 0; // Isto é copiado direto no construtor do lexer. 
%init}
// %eofval{
//    return new Token(TOKEN_TYPE.EOF, "", yyline+1, yycolumn+1);
// %eofval}

  
  
  /* Agora vamos definir algumas macros */
  FimDeLinha  = \r|\n|\r\n
//   NOT_EOL     = [^\'\\\n\r]*     /* identifica quando nao é final de linha */
  Brancos     = {FimDeLinha} | [ \t\f] | [\ \t]
  lineCmt       = "//" .* {FimDeLinha}
  
%state COMMENT
%state SINGLELINECOMMENT
%%

<YYINITIAL>{
    "true"          { return symbol(TOKEN_TYPE.TRUE);    }
    "false"         { return symbol(TOKEN_TYPE.FALSE);   }
    "∨"             { return symbol(TOKEN_TYPE.OR);      }
    "¬"             { return symbol(TOKEN_TYPE.NOT);     }
    "/*"            { yybegin(COMMENT);               }    
    {Brancos}       { /* Não faz nada  */             }
    {lineCmt}       { /* Não faz nada  */             }

}

<COMMENT>{
   "*/"     { yybegin(YYINITIAL); } 
   [^"*/"]* {                     }
}

[^]                 { throw new RuntimeException("Illegal character <"+yytext()+">"); }



