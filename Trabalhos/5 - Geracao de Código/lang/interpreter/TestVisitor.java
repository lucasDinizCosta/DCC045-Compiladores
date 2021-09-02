/**********************************************************
* Trabalho de Teoria dos Compiladores(DCC045)(2021/1)     *
*            Linguagem Lang                               *
* Nome: Lucas Diniz da Costa       Matricula: 201465524C  *
*       Luís Henrique Simplício Ribeiro       201635037   *                                               *
***********************************************************/
package lang.interpreter;

import java.io.*;
import lang.ast.SuperNode;
import lang.parser.*;
import lang.interpreter.*;
import java.util.List;

// Adaptador para classe do interpretador. 
// a Função interpretFile deve retornar null caso o parser resulte em erro. 

public class TestVisitor {
   private InterpreterAdaptor adp;
   private String okSrcs = "testes/semantica/certo/";
   private File f;
   
   public TestVisitor(InterpreterAdaptor adp){
        this.adp = adp;
        f = new File(okSrcs);
        runOkTests();
   }
   
   private String filler(int n){
      String s = "";
      for(int i =0; i< n; i++){ s += " "; }
      return s;
   }
   
   public void runOkTests(){
       File inst[];
       int flips, flops;
       flips = 0;
       flops = 0;
       try{
           if( f.isDirectory() ){
               String pth;
               inst = f.listFiles();
               for(File s : inst){
                   pth = s.getPath();
                   System.out.print("Testando " + pth + filler(50 -pth.length()) + "[");
                   if(adp.interpretFile(s.getPath()) != null){
                       System.out.println("  OK  ]");
                       flips++;
                   }else{ 
                      System.out.println("FALHOU]");
                      flops++;
                   }
               }
               System.out.println("Total de acertos: " + flips );
               System.out.println("Total de erros: " + flops );
           }else{
              System.out.println("O caminho " + f.getPath() + " não é um diretório ou não existe.");
           }
           
       }catch(Exception e){
           e.printStackTrace();
       }
   }
}

