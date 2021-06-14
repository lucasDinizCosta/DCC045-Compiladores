package parser;


public class Token {
    public int l, c;
    public TOKEN_TYPE t;
    public String lexeme;
    public Object info;
    
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
    
    @Override
    public String toString(){
       String tki = lexeme.equals("") && info != null ? "<" + info.toString() + ">" : "\"" + lexeme + "\"";
       return "("+l+","+ c+ ")" + tki ;
    }
}

 
