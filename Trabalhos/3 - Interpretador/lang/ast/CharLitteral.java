/********************************************************
* Trabalho de Teoria dos Compiladores(DCC045)(2021/1)   *
*                                                       *
* Projeto do Interpretador para a Linguagem Lang        *
* Nome: Lucas Diniz da Costa -- Matricula: 201465524C   *
*                                                       *
*********************************************************/
package lang.ast;

import lang.ast.LValue;
import lang.interpreter.Visitor;

public class CharLitteral extends LValue{
    /**
     * ---- Regra
     * sexp: CHAR  # CharLitteral
    */

    private char value; 
    private String originalValue;   // String pega pelo token sem o tratamento
    
    public CharLitteral(int line, int column, String valueString){
        super(line, column);
        this.treatString(valueString);
    }

    // Função que trata caracteres que tenham mais de um simbolo
    public void treatString(String valueString){
        if(valueString.charAt(1) == '\\'){  // Se for uma contra-barra na posição após a aspas simples
            String symbol = valueString.substring(1, valueString.length() - 1); // Pega tudo entre as aspas
            switch(symbol.charAt(1)){           
                case 'n':
                    this.value = '\n';
                    break;
                case 't':
                    this.value = '\t';
                    break;
                case 'b':
                    this.value = '\b';
                    break;
                case 'r':
                    this.value = '\r';
                    break;
                case '\\':              // Caractere é a contra-barra
                    this.value = '\\';
                    break;
                case '\'':              // Caractere é a aspas simples \'
                    this.value = '\'';
                    break;
            }
            this.originalValue = valueString;
        }
        else{
            this.value = valueString.charAt(1);
            this.originalValue = valueString;
        }
    }

    public void setValue(char value){
        this.value = value;
    }

    public String getValue(){
        return value + "";
    }

    @Override
    public String toString(){
        return value + "";
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    @Override
    public String getId() {
        // TODO Auto-generated method stub
        return null;
    }
}
