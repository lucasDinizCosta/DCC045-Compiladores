grammar Lang;

@parser::header
{ 
package parser;    
}
@lexer::header
{ 
package parser;    
}

/* Regras da gramática */
prog : data* func*;
data : DATA NAME_TYPE OPEN_BRACES decl* CLOSE_BRACES;
decl : ID DOUBLE_COLON type SEMI;
func : ID OPEN_PARENT params? CLOSE_PARENT (COLON type (COMMA type)*)? OPEN_BRACES cmd* CLOSE_BRACES;
params : ID DOUBLE_COLON (COMMA ID DOUBLE_COLON type)*;
type : type OPEN_BRACKET CLOSE_BRACKET
     | btype
     ;
btype : INT
      | CHAR
      | BOOL
      | FLOAT
      | NAME_TYPE               // Como NAME_TYPE e ID foram separados, aqui tbm deve ser incluso o NAME_TYPE
      | ID
      ;
cmd : OPEN_BRACES cmd* CLOSE_BRACES
    | IF OPEN_PARENT exp CLOSE_PARENT cmd
    | IF OPEN_PARENT exp CLOSE_PARENT cmd ELSE cmd
    | ITERATE OPEN_PARENT exp CLOSE_PARENT cmd
    | READ one_value SEMI
    | PRINT exp SEMI
    | RETURN exp {COMMA exp} SEMI
    | one_value EQUALS exp SEMI
    | ID OPEN_PARENT exps? CLOSE_PARENT (LESS_THAN one_value (COMMA one_value)* GREATER_THAN)? SEMI
    ;
exp : exp AND exp
    | rexp
    ;
rexp : aexp LESS_THAN aexp
      | rexp EQUALITY aexp
      | rexp DIFFERENCE aexp
      | aexp
      ;
aexp : aexp PLUS mexp
     | aexp MINUS mexp
     | mexp
     ;
mexp : mexp TIMES sexp
     | mexp SLASH sexp
     | mexp PERCENT sexp
     | sexp
     ;
sexp : EXCLAMATION sexp
     | MINUS sexp
     | TRUE
     | FALSE
     | NULL
     | NUMBER_INTEGER
     | NUMBER_FLOAT
     | CHARACTER_LITTERAL
     | pexp
     ;
pexp : one_value
     | OPEN_PARENT exp CLOSE_PARENT
     | NEW type (OPEN_BRACKET exp CLOSE_BRACKET)?
     | ID OPEN_PARENT exps? CLOSE_PARENT OPEN_BRACKET exp CLOSE_BRACKET
     ;
one_value : ID
          | one_value OPEN_BRACKET exp CLOSE_BRACKET
          | one_value DOT ID
          ;
exps : exp (COMMA exp)*;


/* Tokens da linguagem -- Parte Léxica */
// Tudo que começa com letra maiúscula a ferramenta entende que é do analisador léxico

EOL : ('\r'|'\n'|'\r\n') -> skip;                 // Identifica o Final de Linha
WS  : (EOL | [\ \t] | [ \t\f]) -> skip;             // WhiteSpace => espaço em branco
LINE_COMMENT : ('--' .*? EOL) -> skip;              // Identifica o comentário de uma linha
COMMENT : ('{-' .*? '-}') -> skip;

NUMBER_INTEGER : [0-9]+;                 // Identifica os inteiros:(Com pelo menos 1 digito)
NUMBER_FLOAT : ([0-9]* '.' ([0-9] [0-9]*));  // Identifica os reais(Float): 123.23, 1.0, .12345

LETTER : [a-zA-Z];              // Letras maiusculas ou minúsculas
DIGIT : [0-9];
LOWERCASE : [a-z];
UPPERCASE : [A-Z];
CHARACTER_LITTERAL : '\''(([\\'])|'\\n'|'\\t'|'\\b'|'\\r'|'\\\\'|[^\n\r])'\'';  // Identifica os caracteres

ID : (LOWERCASE (LETTER | '_' | DIGIT))* ; // Reconhece os identificadores. Ex: Nome de variaveis

// Apesar de o nome de tipo ser um identificador, ele tem suas particularidades
// como começar com letra maiúscula, portanto, foi resolvido separar em um macro diferente
NAME_TYPE : (UPPERCASE (LETTER | '_' | DIGIT)*);

// PALAVRAS RESERVADAS PELA LINGUAGEM LANG

// Tipos de dados da linguagem

INT : 'Int';
FLOAT : 'Float';
CHAR : 'Char';
BOOL : 'Bool';
DATA : 'data';  // Novo tipo de dados

// Expressões reservadas
IF : 'if';
ELSE : 'else';
ITERATE : 'iterate';
READ : 'read';
PRINT : 'print';
RETURN : 'return';
NEW : 'new';
FALSE : 'false';
TRUE : 'true';
NULL : 'null';

// SIMBOLOS RESERVADOS

// Simbolos Lógicos
AND : '&&';
EQUALITY : '==';
DIFFERENCE : '!='; 

// Outros Simbolos
EXCLAMATION : '!';
COMMA : ',';
DOT : '.';
SEMI : ';';
COLON : ':';
DOUBLE_COLON : '::';
LESS_THAN : '<';
GREATER_THAN : '>';
EQUALS : '=';
TIMES : '*';
PLUS : '+';
MINUS : '-';
SLASH : '/';
BACK_SLASH : '\\';
PERCENT : '%';
AMPERSAND : '&';
SINGLE_QUOTES : '\'';
    
// Simbolos pareados (abertura e fechamento)
OPEN_BRACKET : '[';
CLOSE_BRACKET : ']';
OPEN_PARENT : '(';
CLOSE_PARENT : ')';
OPEN_BRACES : '{';
CLOSE_BRACES : '}';
    