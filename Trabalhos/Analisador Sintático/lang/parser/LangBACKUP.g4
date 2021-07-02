/********************************************************
* Trabalho de Teoria dos Compiladores(DCC045)(2021/1)   *
*                                                       *
* Projeto do Analisador Sintático para a Linguagem Lang *
* Nome: Lucas Diniz da Costa -- Matricula: 201465524C   *
*                                                       *
*********************************************************/

grammar Lang;

@parser::header
{ 
package lang.parser;    
}
@lexer::header
{ 
package lang.parser;    
}

/* Regras da gramática */
prog : data* func* ;
data : DATA_TYPE NAME_TYPE OPEN_BRACES decl* CLOSE_BRACES ;
decl : ID DOUBLE_COLON type SEMI ;
func : ID OPEN_PARENT params? CLOSE_PARENT (COLON type (COMMA type)*)? OPEN_BRACES cmd* CLOSE_BRACES ;
params : ID DOUBLE_COLON (COMMA ID DOUBLE_COLON type)* ;
type : type OPEN_BRACKET CLOSE_BRACKET
     | btype
     ;
btype : INT_TYPE
      | CHAR_TYPE
      | BOOL_TYPE
      | FLOAT_TYPE
      | NAME_TYPE               // Como NAME_TYPE e ID foram separados, aqui tbm deve ser incluso o NAME_TYPE
      | ID
      ;
cmd : OPEN_BRACES cmd* CLOSE_BRACES
    | IF OPEN_PARENT exp CLOSE_PARENT cmd
    | IF OPEN_PARENT exp CLOSE_PARENT cmd ELSE cmd
    | ITERATE OPEN_PARENT exp CLOSE_PARENT cmd
    | READ one_value SEMI
    | PRINT exp SEMI
    | RETURN exp (COMMA exp)* SEMI
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
     | INT
     | FLOAT
     | CHAR
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
exps : exp (COMMA exp)* ;


/* Tokens da linguagem -- Parte Léxica */
// Tudo que começa com letra maiúscula a ferramenta entende que é do analisador léxico

EOL : '\r' ? '\n' -> skip;                 // Identifica o Final de Linha
WS  : [ \t]+ -> skip;             // WhiteSpace => espaço em branco
SINGLE_LINE_COMMENT : '--' .*? EOL -> skip;              // Identifica o comentário de uma linha
MULTI_LINE_COMMENT : '{-' .*? '-}' -> skip;

INT : [0-9]+ ;                 // Identifica os inteiros:(Com pelo menos 1 digito)
FLOAT : [0-9]* '.' ([0-9] [0-9]*) ;  // Identifica os reais(Float): 123.23, 1.0, .12345

LETTER : [a-zA-Z] ;              // Letras maiusculas ou minúsculas
DIGIT : [0-9] ;
LOWERCASE : [a-z] ;
UPPERCASE : [A-Z] ;
CHAR : '\''([\\']|'\\n'|'\\t'|'\\b'|'\\r'|'\\\\'|[^\n\r])'\'' ;  // Identifica os caracteres

ID : [a-z][a-zA-Z0-9_]* ; // Reconhece os identificadores. Ex: Nome de variaveis

// Apesar de o nome de tipo ser um identificador, ele tem suas particularidades
// como começar com letra maiúscula, portanto, foi resolvido separar em um macro diferente
NAME_TYPE : [A-Z][a-zA-Z0-9_]* ;

// PALAVRAS RESERVADAS PELA LINGUAGEM LANG

// Tipos de dados da linguagem

INT_TYPE : 'Int';
FLOAT_TYPE : 'Float';
CHAR_TYPE : 'Char';
BOOL_TYPE : 'Bool';
DATA_TYPE : 'data';  // Novo tipo de dados

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
    