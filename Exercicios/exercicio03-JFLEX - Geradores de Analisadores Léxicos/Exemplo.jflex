
   /*	Esta seção é copiada antes da declaração da classe do analisador léxico.
	*	É nesta seção que se deve incluir imports e declaração de pacotes.
	*	Neste exemplo não temos nada a incluir nesta seção
	*/
	
%%
/**
	Nesta seção serão definidos parâmetros que podem ser usados pelo JFLEX
	class: Nome da classe a ser gerada pelo JFLEX
	function: Controla a função que faz a analise léxica e retorna o próximo token da entrada
	Type: controla o tipo de retorno da função, será a classe de Token criada pelo usuário
*/
%unicode
%line
%column
%class LextTest 
%function nextToken
%type Token

%{
   /* Código arbitrário pode ser inserido diretamente no analisador léxico dessa forma.
	* Aqui podemos declarar variáveis e métodos adicionais que julgaremos necessários.
	*/
	private int ntk;	// Servirá pra contar o número de tokens

	public int readedTokens(){	// Número de tokens lidos até o momento
		return ntk;
	}
	// Symbol: Servem para construir um token e retornar um token na entrada
	private Token symbol(TOKEN_TYPE t){	
		ntk++;
		return new Token(t, yytext(), yyline+1, yycolumn+1); // yytext() é o lexema
	}
	private Token symbol(TOKEN_TYPE t, Object value){
		ntk++;
		return new Token(t, value, yyline+1, yycolumn+1);
	}
%}

// Serve para colocar código no construtor do analisador léxico
%init{
	ntk = 0; // Isto é copiado direto no construtor do lexer
%init}



	/* 		Agora vamos definir alguns macros 
		Macros: Expressões Regulares com um nome pré-definido
		Alguns macros padrão definidos pelo JFLEX:
		[:lowercase:], [:letter:], [:digit:], [:uppercase:]
	*/
	FimDeLinha = \r|\n|\r\n
	Brancos    = {FimDeLinha} | [ \t\f]
	numero     = [:digit:] [:digit:]* 
	identificador = [:lowercase:]

%state COMMENT

%%

/*
	<YYINITIAL>: É um marcador de estado, Se o analisador léxico reconhecer um parametro
	ele executa uma ação específica
*/
<YYINITIAL>{
	{identificador} { return symbol(TOKEN_TYPE.ID);      }
	{numero}        { return symbol(TOKEN_TYPE.NUM, Integer.parseInt(yytext()) );   }
	"="             { return symbol(TOKEN_TYPE.EQ);      }
	";"             { return symbol(TOKEN_TYPE.SEMI);    }
	"*"             { return symbol(TOKEN_TYPE.TIMES);   }
	"+"             { return symbol(TOKEN_TYPE.PLUS);    }
	"/*"			{ yybegin(COMMENT);                  }
	{Brancos}       { /* Não faz nada  */                }
}

// Trata o estado COMMENT 
<COMMENT>{
	"*/"      { yybegin(YYINITIAL); }
	// Qualquer coisa que nao seja "*/"
	[^"*/"]* {                       }
}

/*
	[^]: É uma expressão negativa. Neste caso é se não for qualquer coisa dentro do YYINITIAL,
	lançar um erro de caractere ilegal
*/

[^]                 { throw new RuntimeException("Illegal character <"+yytext()+">"); }