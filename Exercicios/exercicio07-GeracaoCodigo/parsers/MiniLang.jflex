
 /*  Esta seção é copiada antes da declaração da classe do analisador léxico.
  *  É nesta seção que se deve incluir imports e declaração de pacotes.
  *  Neste exemplo não temos nada a incluir nesta seção.
  */
  
package parsers;

import beaver.Symbol;
import beaver.Scanner;
import java.math.BigDecimal;

%%
%public
%class MiniLangLex
%extends Scanner
%function nextToken
%type Symbol
%yylexthrow Scanner.Exception
%eofval{
	return newToken(Terminals.EOF, "end-of-file");
%eofval}
%unicode
%line
%column
%{
	private Symbol newToken(short id)
	{
		return new Symbol(id, yyline + 1, yycolumn + 1, yylength());
	}

	private Symbol newToken(short id, Object value)
	{
		return new Symbol(id, yyline + 1, yycolumn + 1, yylength(), value);
	}
%}  
  
  /* Agora vamos definir algumas macros */
  FimDeLinha  = \r|\n|\r\n
  Brancos     = {FimDeLinha} | [ \t\f]
  int         = [:digit:] [:digit:]*
  float       = {int} "." {int}
  identificador = [:lowercase:] ([:lowercase:] | [:uppercase:] | [:digit:])*
  lineCmt       = "//" .* {FimDeLinha}
  
  
%state COMMENT

%%

<YYINITIAL>{
    "false"         { return newToken(Terminals.FALSE, false ); }
    "true"          { return newToken(Terminals.TRUE, true );   }
    "<-"            { return newToken(Terminals.ATTR);          }
    "$"             { return newToken(Terminals.INST);   }
    "@"             { return newToken(Terminals.RET);    }
    "?"             { return newToken(Terminals.IF);     }
    "?["            { return newToken(Terminals.WHILE);  }
    "#"             { return newToken(Terminals.PRINT);  }
    
    "="             { return newToken(Terminals.EQ);     }
    ";"             { return newToken(Terminals.SEMI);   }
    ","             { return newToken(Terminals.COMMA);  }
    ":"             { return newToken(Terminals.COLON);  }
    "("             { return newToken(Terminals.AP);     }
    ")"             { return newToken(Terminals.FP);     }
    "["             { return newToken(Terminals.LB);     }
    "]"             { return newToken(Terminals.RB);     }
    "{"             { return newToken(Terminals.LBRACE); }
    "}"             { return newToken(Terminals.RBRACE); }
    
    "*"             { return newToken(Terminals.MULT);   }
    "&"             { return newToken(Terminals.AND);    }
    "!"             { return newToken(Terminals.NOT);    }
    "+"             { return newToken(Terminals.PLUS);   }
    "-"             { return newToken(Terminals.MINUS);  }
    "<"             { return newToken(Terminals.LT);     }
    "/"             { return newToken(Terminals.DIV);    }
    "%"             { return newToken(Terminals.MOD);    }
    

    
    "/*"            { yybegin(COMMENT);                  }
    "Int"           { return newToken(Terminals.TYINT);  }
    "Float"         { return newToken(Terminals.TYFLOAT);  } 
    "Bool"          { return newToken(Terminals.TYBOOL);  } 
    
    {identificador} { return newToken(Terminals.ID, yytext());   }
    {float}         { return newToken(Terminals.FLOAT, Float.parseFloat(yytext()) );  }
    {int}           { return newToken(Terminals.INT, Integer.parseInt(yytext()) );  }
    {Brancos}       { /* Não faz nada  */                }
    {lineCmt}       { /* Não faz nada  */                }

}

<COMMENT>{
   "*/"     { yybegin(YYINITIAL); } 
   [^"*/"]* {                     }
}

[^]                 { throw new RuntimeException("Illegal character <"+yytext()+">"); }



