/******************************************************
* Trabalho de Teoria dos Compiladores(DCC045)(2021/1) *
*                                                     *
* Nome: Lucas Diniz da Costa -- Matricula: 201465524C *
******************************************************/

/********************************************************************
 *                      Token:                                      *
 * Define o que é um token, a linha e coluna que o compõe, o lexema *
 * correspondente, o tipo do token e ainda carrega um objeto        *
 * com informações adicionais.                                      *
 ********************************************************************/

public class Token {
    public int l, c;          // Linha e coluna dos token
    public TOKEN_TYPE t;      // Codigo correspondente
    public String lexeme;     // lexema do token
    public Object info;       // Informações adicionais (numero inteiro, float,...)
    
    // Construtores da classe

    public Token(TOKEN_TYPE t, String lex, Object o ,int l, int c){
          this.t = t;
          lexeme = lex;
          info =  o;
          this.l = l;
          this.c = c;
    }
    
    public Token(TOKEN_TYPE t, String lex,int l, int c){
          this.t = t;
          lexeme = lex;
          info =  null;
          this.l = l;
          this.c = c;
    }
    
    public Token(TOKEN_TYPE t,Object o,int l, int c){
          this.t = t;
          lexeme = "";
          info =  o;
          this.l = l;
          this.c = c;
    }
    
    // Função de impressão do token
    // Imprime conforme a especificação do trabalho
    /**
     * Exemplo:
     * ID : main
     * (
     * )
     * {
     * PRINT
     * ID : fat
     * (
     * INT: 10
     */
    @Override
    public String toString(){
      // Caso o token apresente uma informação adicional como um valor inteiro, float,
      // nome do tipo, identificador...
      if(info != null){ 
            return (t + ":" + info.toString());
      }
      else if(t == TOKEN_TYPE.VALUE_CHAR || t == TOKEN_TYPE.ID || t == TOKEN_TYPE.NAME_TYPE){
            // Retorna o tipo de token caso o lexema seja um valor literal de um char
            // ou um identificador ou um nome de tipo
            return (t + ":" + lexeme);
      }
      else{
            return lexeme;
      }
    }
}

 
