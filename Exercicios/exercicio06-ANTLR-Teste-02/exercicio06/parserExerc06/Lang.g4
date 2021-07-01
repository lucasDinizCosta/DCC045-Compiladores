grammar Lang;   // Arquivo Lang.g4

//@parser::header => Diz o que será colocado no início do analisador sintático
@parser::header         
{
package parserExerc06;
}
//Vincula o analisador léxico junto ao sintático
@lexer::header
{
package parserExerc06;
}
/* Regras da gramática */
prog: stmt+;
stmt:
 ID '=' expr ';'
|
 expr ';'
;

expr:
 factor ('+' | '*') expr
|
 factor
;

factor:
 ID
|
 INT
;

/* Regras Léxicas */
// Tudo que começa com letra maiúscula a ferramenta entende que é do analisador léxico
ID : [a-z];
INT : [0-9]+;

NEWLINE: '\r'? '\n' -> skip; // Ignorar(Skip) quando encontra quebra de linha ou espaço em branco
WS : [ \t]+ -> skip;
LINE_COMMENT : '//' ~('\r' | '\n')* NEWLINE -> skip;
COMMENT : '/*' .*? '*/' -> skip;