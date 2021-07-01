grammar Lang;
@parser::header
{
package parserAUX;
}
@lexer::header
{
package parserAUX;
}				
prog:	(expr NEWLINE)* ;
expr:	expr ('*'|'/') expr
    |	expr ('+'|'-') expr
    |	INT
    |	'(' expr ')'
    ;
NEWLINE : [\r\n]+ ;
INT     : [0-9]+ ;