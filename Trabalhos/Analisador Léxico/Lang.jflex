/******************************************************
* Trabalho de Teoria dos Compiladores(DCC045)(2021/1) *
*                                                     *
* Nome: Lucas Diniz da Costa -- Matricula: 201465524C *
******************************************************/

%%

%unicode
%line
%column
%class Lexer
%function nextToken
%type Token

%{
    
	private int ntk;	// Servirá pra contar o número de tokens

	public int readedTokens(){	// Número de tokens lidos até o momento
		return ntk;
	}
	// Symbol: Servem para construir um token e retornar um token na entrada
	private Token symbol(TOKEN_TYPE t){	
		ntk++;
		return new Token(t, yytext(), yyline+1, yycolumn+1); // yytext() é o lexema
	}
  // Cria um token com um valor determinado
	private Token symbol(TOKEN_TYPE t, Object value){
		ntk++;
		return new Token(t, value, yyline+1, yycolumn+1);
	}
%}

%init{
	ntk = 0;
%init}

/*********************** 		
* DEFINIÇÃO DOS MACROS *
************************/

EOL = \r|\n|\r\n                 // Identifica o Final de Linha
WS  = {EOL} | [\ \t] | [ \t\f]   // WhiteSpace => espaço em branco
lineCmt       = "--" .* {EOL}    // Identifica o comentário de uma linha

numberInteger  = [:digit:]+                   // Identifica os inteiros:(Com pelo menos 1 digito)
numberFloat    = ([:digit:]* "." ([:digit:] [:digit:]*))  // Identifica os reais(Float): 123.23, 1.0, .12345

letter = [:letter:]               // Letras maiusculas ou minúsculas
characterLitteral     = "'"("\\n" | "\\b" | "\\t" | "\\r" | "\\" | "\\\\")"'" 
id = [:lowercase:] ({letter} | "_" | [:digit:])*  // Reconhece os identificadores. Ex: Nome de variaveis

// Apesar de o nome de tipo ser um identificador, ele tem suas particularidades
// como começar com letra maiúscula, portanto, foi resolvido separar em um macro diferente
nameType = [:uppercase:] ({letter} | "_" | [:digit:])*
  
%state MULTI_LINE_COMMENT

%%

<YYINITIAL>{
    // PALAVRAS RESERVADAS PELA LINGUAGEM LANG

    // Tipos de dados da linguagem
    "Int"               { return symbol(TOKEN_TYPE.INT);    }
    "Float"             { return symbol(TOKEN_TYPE.FLOAT);  }
    "Char"              { return symbol(TOKEN_TYPE.CHAR);   }
    "Bool"              { return symbol(TOKEN_TYPE.BOOL);   }
    "data"              { return symbol(TOKEN_TYPE.DATA);   }  // Novo tipo de dados

    // Expressões reservadas
    "if"                { return symbol(TOKEN_TYPE.IF);     }
    "else"              { return symbol(TOKEN_TYPE.ELSE);   }
    "iterate"           { return symbol(TOKEN_TYPE.ITERATE);}
    "read"              { return symbol(TOKEN_TYPE.READ);   }
    "print"             { return symbol(TOKEN_TYPE.PRINT);  }
    "return"            { return symbol(TOKEN_TYPE.RETURN); }
    "new"               { return symbol(TOKEN_TYPE.NEW);    }
    "false"             { return symbol(TOKEN_TYPE.FALSE);  }
    "true"              { return symbol(TOKEN_TYPE.TRUE);   }
    "null"              { return symbol(TOKEN_TYPE.NULL);   }

    // IDENTIFICADOR E VALORES

    {id}                { return symbol(TOKEN_TYPE.ID);   }
    {numberInteger}     { return symbol(TOKEN_TYPE.VALUE_INT, Integer.parseInt(yytext()) );    }
    {numberFloat}       { return symbol(TOKEN_TYPE.VALUE_FLOAT, Float.parseFloat(yytext()) );  }
    {characterLitteral} { return symbol(TOKEN_TYPE.VALUE_CHAR);}
    {nameType}          { return symbol(TOKEN_TYPE.NAME_TYPE); }
    
    
    // SIMBOLOS RESERVADOS
    
    // Simbolos Lógicos
    "&&"            { return symbol(TOKEN_TYPE.AND);          }
    "=="            { return symbol(TOKEN_TYPE.EQUALITY);     }
    "!="            { return symbol(TOKEN_TYPE.DIFFERENCE);   }

    // Outros Simbolos
    "!"             { return symbol(TOKEN_TYPE.EXCLAMATION);  }
    ","             { return symbol(TOKEN_TYPE.COMMA);        }
    "."             { return symbol(TOKEN_TYPE.DOT);          }
    ";"             { return symbol(TOKEN_TYPE.SEMI);         }
    ":"             { return symbol(TOKEN_TYPE.COLON);        }
    "::"            { return symbol(TOKEN_TYPE.DOUBLE_COLON); }
    "<"             { return symbol(TOKEN_TYPE.LESS_THAN);    }
    ">"             { return symbol(TOKEN_TYPE.GREATER_THAN); }
    "="             { return symbol(TOKEN_TYPE.EQUALS);       }
    "*"             { return symbol(TOKEN_TYPE.TIMES);        }
    "+"             { return symbol(TOKEN_TYPE.PLUS);         }
    "-"             { return symbol(TOKEN_TYPE.MINUS);        }
    "/"             { return symbol(TOKEN_TYPE.SLASH);        }
    "\\"            { return symbol(TOKEN_TYPE.BACK_SLASH);   }
    "%"             { return symbol(TOKEN_TYPE.PERCENT);      }
    "&"             { return symbol(TOKEN_TYPE.AMPERSAND);    }
    "\'"            { return symbol(TOKEN_TYPE.SINGLE_QUOTES);}
    

    // Simbolos pareados (abertura e fechamento)
    "["             { return symbol(TOKEN_TYPE.OPEN_BRACKET); }
    "]"             { return symbol(TOKEN_TYPE.CLOSE_BRACKET);}
    "("             { return symbol(TOKEN_TYPE.OPEN_PARENT);  }
    ")"             { return symbol(TOKEN_TYPE.CLOSE_PARENT); }
    "{"             { return symbol(TOKEN_TYPE.OPEN_BRACES);  }
    "}"             { return symbol(TOKEN_TYPE.CLOSE_BRACES); }

    // SIMBOLOS E EXPRESSÕES IGNORADAS (Comentarios e espaços em branco)

    {lineCmt}       {  /* Não faz nada  */                    }
    "{-"            { yybegin(MULTI_LINE_COMMENT);            }    
    {WS}            {  /* Não faz nada  */                    }
}

// Trata a condição do comentário em várias linhas
/**
  Apresenta casos especiais:
  => Caso especial 01: Se tiver '-' no meio do comentario ele não irá casar
  com '-}' e nem conseguirá prosseguir pois '-' é parte do atributo "-}". 
  Logo retornará '-' como caractere ilegal caso não seja feita esta verificação 
  de caractere único para '-'
  => Caso especial 02: Se tiver '}' no meio do comentario ele não irá casar
  com '-}' e nem conseguirá prosseguir pois '}' é parte do atributo "-}". 
  Logo retornará '}' como caractere ilegal caso não seja feita esta verificação 
  de caractere único para '}'
*/
<MULTI_LINE_COMMENT>{
   [^"-}"]* { /*Nao faz nada, cotinua no comentario*/ } // Tudo que não for "-}"
   "-"      { /*Nao faz nada, cotinua no comentario*/ } // caso especial 01
   "}"      { /*Nao faz nada, cotinua no comentario*/ } // caso especial 02
   "-}"     { yybegin(YYINITIAL); }
}

[^]                 { throw new RuntimeException("Illegal character <"+yytext()+">"); }



