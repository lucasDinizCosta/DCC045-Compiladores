package parser;


import java.util.Stack;
import java.util.LinkedList;
import java.io.FileReader;
import java.io.IOException;
import ast.*;

public abstract class TopDownRecursiveParser {
      
      private LexTest lx;       // Lexer implementado pelo JFLEX
      protected Stack<Token> stk;   // Pilha de Tokens
      protected Stack<Node> ast;        // Arvore de Sintaxe Abstrata (AST)
      private LinkedList<Token> bck;    // Lista de Tokens
      protected Token nxt;              // Próximo Token
      protected boolean eof;            // Fim de arquivo ou Fim da entrada
      
      
      //-- Apenas para propósitos de depuração:
      protected String idnt;
      protected void incS(){ idnt += "    ";}
      protected void decS(){ idnt = idnt.substring(0,idnt.length() - 4);}
      protected void msg(String s){
          System.out.println(idnt + s + " [>|" + nxt + " ]");
      }
      protected void tryAlt(String body){
           msg("Tentando " + body);
           incS();
      }
      protected void retryAlt(String body){
           decS();
           msg("Backtrack: " + body);
           incS();
      }
      protected void success(){
           decS();
           msg("[OK]");
      }
      protected void fail(){
           decS();
           msg("[Falhou]");
      }
     
      public TopDownRecursiveParser(String fileName) throws IOException{
          lx = new LexTest(new FileReader(fileName));
          stk = new Stack<Token>();
          ast = new Stack<Node>();
          bck = new LinkedList<Token>();    // Lista encadeada que armazena todos os tokens lidos até o momento
          nxt = lx.nextToken();         // Inicia já lendo primeiro token
          eof = nxt == null;            // o JFLEX quando não tem nenhum tipo de retorno que faz a análise léxica
                                        // Geralmente o tipo é objeto, então ele retorna null
                                        // E enquanto não for o fim de entrada
          idnt = "";
      }
      
      protected void readToken() throws IOException{
          if(!eof){
             stk.push(nxt);         // Empilha token atual
             if(bck.isEmpty()){     // Verifica se a lista de tokens até o momento está vazia
                // Se não tem nada no bck, irá ler do analisador léxico
                // Ou seja, não fez o backtracking.
                // Empilha o token que leu e lê o próximo da entrada
                 nxt = lx.nextToken();
                 eof = nxt == null;
             }else{
                // Tem tokens lidos
                // Devolve os tokens para a entrada na ordem inversa lida
                // Depois tira pra poder ler
                 nxt = bck.remove();
             }
          }
      }
      
      protected void backtrack(int n, int k) throws IOException{
          // k => quantos símbolos de AST deseja retirar da entrada
          // Se for feito o backtracking para qualquer valor maior que zero
          if(n > 0 && nxt == null){ 
              nxt = stk.pop(); 
              n--; 
              eof = false;
          } 
          while(n > 0){ // Enquanto for maior que zero
               bck.addFirst(nxt);
               nxt = stk.pop();
               n--;
          }
          if(!bck.isEmpty()){ eof = false;}
          while(k > 0){ // Remove os Nós da entrada
             ast.pop();
             k--;
          }
      }
      
      protected void backtrack(int n) throws IOException{
          // Se for feito o backtracking para qualquer valor maior que zero
          if(n > 0 && nxt == null){ 
              nxt = stk.pop(); 
              n--; 
              eof = false;
          } 
          while(n > 0){ 
               bck.addFirst(nxt);
               nxt = stk.pop();
               n--;
          }
          if(!bck.isEmpty()){ eof = false;}
      }
      
      protected int getCurrLine(){ return nxt == null ? -1 : nxt.l; }
      protected int getCurrCol(){ return nxt == null ? -1 : nxt.c; }
      
      protected boolean match(TOKEN_TYPE t) throws IOException {
           if(nxt != null && nxt.t == t){
               readToken();
               return true;
           }
           return false;
      }
      
}
