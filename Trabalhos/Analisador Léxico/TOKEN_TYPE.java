/******************************************************
* Trabalho de Teoria dos Compiladores (DCC045)(2021/1)*
*                                                     *
* Nome: Lucas Diniz da Costa -- Matricula: 201465524C *
******************************************************/

/**********************************************************************
 * ENUMERACAO DOS TOKENS, OU SEJA, DEFINE O QUE É VALIDO NA LINGUAGEM *
 **********************************************************************/

public enum TOKEN_TYPE {
    /* PALAVRAS RESERVADAS */

    // IDENTIFICADOR E VALORES
    ID,
    VALUE_INT,
    VALUE_FLOAT,
    VALUE_CHAR,
    NAME_TYPE,

    // Expressoes reservadas
    INT,
    FLOAT,
    CHAR,
    BOOL,
    DATA,
    IF,
    ELSE,
    ITERATE,
    READ,
    PRINT,
    RETURN,
    NEW,
    TRUE,
    FALSE,
    NULL,

    // SIMBOLOS RESERVADOS

    EQUALITY,       // '=='
    DIFFERENCE,     // '!='
    AND,            // '&&'
    EXCLAMATION,    // '!'
    EQUALS,         // '='
    PLUS,           // '+'
    MINUS,          // '-'
    TIMES,          // '*'
    SLASH,          // '/'
    BACK_SLASH,     // '\\'
    PERCENT,        // '%'
    AMPERSAND,      // '&'
    COMMA,          // ',' -- COMMA - Vírgula
    DOT,            // '.' -- Ponto
    COLON,          // ':'
    DOUBLE_COLON,   // '::'
    SEMI,           // ';'
    SINGLE_QUOTES,  // '\'' -- ASPAS SIMPLES
    LESS_THAN,      // '<' -- LESS-THAN SIGN -- Menor que
    GREATER_THAN,   // '>' -- GREATER-THAN SIGN -- Maior que
    OPEN_BRACKET,   // '[' -- LEFT SQUARE BRACKET -- Colchete esquerdo
    CLOSE_BRACKET,  // ']' -- RIGHT SQUARE BRACKET -- Colchete direito
    OPEN_PARENT,    // '(' -- LEFT PARENTHESIS -- Parêntese esquerdo
    CLOSE_PARENT,   // ')' -- RIGHT PARENTHESIS -- Parêntese esquerdo
    OPEN_BRACES,    // '{' -- LEFT CURLY BRACKETS / BRACES -- Chave esquerda
    CLOSE_BRACES,   // '}' -- RIGHT CURLY BRACKETS / BRACES -- Chave direita
    EOF
}
