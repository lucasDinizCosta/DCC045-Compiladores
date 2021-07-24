grammar Lang;

// No cabeçalho de todas as classes geradas irá aparecer que estiver dentro do header
@header
{
/********************************************************
* Trabalho de Teoria dos Compiladores(DCC045)(2021/1)   *
*                                                       *
* Projeto do Interpretador para a Linguagem Lang        *
* Nome: Lucas Diniz da Costa -- Matricula: 201465524C   *
*                                                       *
*********************************************************/

package lang.parser;    
}
/**
 Foi adicionado um ("#")label para cada expressão para facilitar a identificação
 no interpretador e na semântica posteriormente
*/

/* Regras da gramática */
prog: data* func*   # Program
    ;
data: DATA_TYPE NAME_TYPE OPEN_BRACES decl* CLOSE_BRACES    # DataDeclaration
    ;
decl: ID DOUBLE_COLON type SEMI                             # VarDeclaration
    ;
func: ID OPEN_PARENT params? CLOSE_PARENT (COLON type (COMMA type)*)? OPEN_BRACES cmd* CLOSE_BRACES    # Function
    ;
params: ID DOUBLE_COLON type (COMMA ID DOUBLE_COLON type)*  # ParametersFunction
      ;
type: type OPEN_BRACKET CLOSE_BRACKET   # TypeDeclaration   // tipo de array. Exemplo: Int[]
    | btype     # BTypeCall
    ;
btype: INT_TYPE     # BTypeInt
    | CHAR_TYPE     # BTypeChar
    | BOOL_TYPE     # BTypeBool
    | FLOAT_TYPE    # BTypeFloat
    | NAME_TYPE     # BTypeNameType
    | ID            # BTypeID
    ;
cmd: OPEN_BRACES cmd* CLOSE_BRACES      # CommandsList
    | IF OPEN_PARENT exp CLOSE_PARENT cmd   # If
    | IF OPEN_PARENT exp CLOSE_PARENT cmd ELSE cmd  # IfElse
    | ITERATE OPEN_PARENT exp CLOSE_PARENT cmd  # Iterate
    | READ lvalue SEMI  # Read
    | PRINT exp SEMI    # Print
    | RETURN exp (COMMA exp)* SEMI  # Return
    | lvalue EQUALS exp SEMI    # Attribution
    | ID OPEN_PARENT exps? CLOSE_PARENT (LESS_THAN lvalue (COMMA lvalue)* GREATER_THAN)? SEMI   # FunctionCall
    ;
exp: exp AND exp    # AndOperation
    | rexp      # RExpCall
    ;
rexp: aexp LESS_THAN aexp   # LessThan
    | rexp EQUALITY aexp    # Equality
    | rexp DIFFERENCE aexp  # Difference
    | aexp      # AExpCall
    ;
aexp: aexp PLUS mexp    # AdditionOperation
    | aexp MINUS mexp   # SubtractionOperation
    | mexp      # MExpCall
    ;
mexp: mexp TIMES sexp   # MultiplicationOperation
    | mexp SLASH sexp   # DivisionOperation
    | mexp PERCENT sexp # ModularOperation
    | sexp      # SExpCall
    ;
sexp: EXCLAMATION sexp # Not
    | MINUS sexp   # Minus  //<assoc=right> MINUS sexp   # Minus
    | TRUE  # True
    | FALSE # False
    | NULL  # Null
    | INT   # IntegerNumber
    | FLOAT # FloatNumber
    | CHAR  # CharLitteral
    | pexp  # PExpCall
    ;
pexp: lvalue    # PexpIdentifier       // Chama lValue e o ID
    | OPEN_PARENT exp CLOSE_PARENT  # ExpParenthesis
    | NEW type (OPEN_BRACKET exp CLOSE_BRACKET)?    # TypeInstanciate
    | ID OPEN_PARENT exps? CLOSE_PARENT OPEN_BRACKET exp CLOSE_BRACKET  # FunctionReturn // Como retorna 2 valores, logo precisa do funcao(parametros)[indice] Exemplo: fat(num−1)[0]
    ;
lvalue: ID      # Identifier
    | lvalue OPEN_BRACKET exp CLOSE_BRACKET # ArrayAccess
    | lvalue DOT ID     # DataAccess
    ;
exps: exp (COMMA exp)*      # FCallParams
    ;

/* Tokens da linguagem -- Parte Léxica */
// Tudo que começa com letra maiúscula a ferramenta entende que é do analisador léxico

EOL: '\r' ? '\n' -> skip;                               // Identifica o Final de Linha
WS : [ \t]+ -> skip;                                    // WhiteSpace => espaço em branco
SINGLE_LINE_COMMENT: '--' .*? EOL -> skip;              // Identifica o comentário de uma linha
MULTI_LINE_COMMENT: '{-' .*? '-}' -> skip;

// PALAVRAS RESERVADAS PELA LINGUAGEM LANG

// Tipos de dados da linguagem

INT_TYPE: 'Int';
FLOAT_TYPE: 'Float';
CHAR_TYPE: 'Char';
BOOL_TYPE: 'Bool';
DATA_TYPE: 'data';  // Novo tipo de dados

// Expressões reservadas
IF: 'if';
ELSE: 'else';
ITERATE: 'iterate';
READ: 'read';
PRINT: 'print';
RETURN: 'return';
NEW: 'new';
FALSE: 'false';
TRUE: 'true';
NULL: 'null';

// SIMBOLOS RESERVADOS

// Simbolos Lógicos
AND: '&&';
EQUALITY: '==';
DIFFERENCE: '!='; 

// Outros Simbolos
EXCLAMATION: '!';       // Talvez da pra tirar
COMMA: ',';
DOT: '.';
SEMI: ';';
COLON: ':';
DOUBLE_COLON: '::';
LESS_THAN: '<';
GREATER_THAN: '>';
EQUALS: '=';
TIMES: '*';
PLUS: '+';
MINUS: '-';
SLASH: '/';
PERCENT: '%';
    
// Simbolos pareados (abertura e fechamento)
OPEN_BRACKET: '[';
CLOSE_BRACKET: ']';
OPEN_PARENT: '(';
CLOSE_PARENT: ')';
OPEN_BRACES: '{';
CLOSE_BRACES: '}';

// Tipos de dados

ID: [a-z][a-zA-Z0-9_]* ; // Reconhece os identificadores. Ex: Nome de variaveis
// Apesar de o nome de tipo ser um identificador, ele tem suas particularidades
// como começar com letra maiúscula, portanto, foi resolvido separar em um macro diferente
NAME_TYPE : [A-Z][a-zA-Z0-9_]* ;

INT: [0-9]+ ;                 // Identifica os inteiros:(Com pelo menos 1 digito)
FLOAT: [0-9]* '.' ([0-9] [0-9]*) ;  // Identifica os reais(Float): 123.23, 1.0, .12345
CHAR: ('\''([\u0000-\u0026]|[\u0028-\u005B]|[\u005D-\u007F])'\'')       // (000 - 127)(Menos o 27 => aspas simples ' e nem 97 => Contrabarra \ ) Captura todos os caracteres da tabela ASCII, conforme a especificação da linguagem
    | ('\'''\\n''\'')           // '\n' => Contrabarra_n
    | ('\'''\\t''\'')           // '\t' => Contrabarra_t
    | ('\'''\\b''\'')           // '\b' => Contrabarra_b
    | ('\'''\\r''\'')           // '\r' => Contrabarra_r
    | ('\'''\\\\''\'')          // Especifica '\\' que é a '\' => Contrabarra
    | ('\'\\\'\'')              // Especifica a aspas simples: "\\\'" => \' => '
    ;
    
// TRATAMENTO DE ERRO DE ENCONTRAR UM 