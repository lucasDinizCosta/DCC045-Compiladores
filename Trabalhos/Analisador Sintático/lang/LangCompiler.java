package lang;

import java.io.*;
import lang.parser.*;
import lang.ast.*;

public class LangCompiler{
   // Recupera o nome base (sem extensão) de um arquivo.
   public static void main(String[] args){
       if( args.length < 1 ){
          System.out.println("Lang compiler v 0.0.1 - Maio de 2020");
          System.out.println("Use java -cp . Lang ação <Caminho para código Fonte> ");
          System.out.println("Ação (uma das seguintes possibilidades): ");
          
          System.out.println(" -bs : Executa uma bateria de testes sintáticos");
          System.out.println(" -bty : Executa uma bateria de testes no sistemas de tipos");
          System.out.println(" -bsm : Executa uma bateria de testes no interpretador");          
        
          System.out.println(" -pp: Pretty print program.");        
          System.out.println(" -tp: Verficar tipos e imprimir o ambiente de tipos");
          System.out.println(" -i : Apenas interpretar");
          
          System.out.println(" -ti: Verificar tipos e depois interpretar");
          System.out.println(" -dti: Verificar tipos, imprimir o ambiente de tipos e depois interpretar");
          System.out.println(" -gvz: Create a dot file. (Feed it to graphviz dot tool to generate graphical representation of the AST)");
          
       }
       try{
	   ParseAdaptor langParser = null;
          
          if(args[0].equals("-bs") ){
              System.out.println("Executando bateria de testes sintáticos:");
              TestParser tp = new TestParser(langParser);
              return;
          }if(args[0].equals("-byt") ){
              System.out.println("Executando bateria de testes sintáticos:");
              // TestParser tp = new TestParser(langParser); ;
              return;
          } if(args[0].equals("-bsm") ){
              System.out.println("Executando bateria de testes sintáticos:");
              // TestParser tp = new TestParser(langParser); 
              return;
          }
          if(args.length != 2){
              System.out.println("Para usar essa opção, especifique um nome de arquivo");
              return; 
          }
          SuperNode result = langParser.parseFile(args[1]);
          if(result == null){
               System.err.println("Aborting due to syntax error(s)");
               System.exit(1);
          }
          else if(args[0].equals("-i") ){
              //iv = new InterpreterVisitor();
              //result.accept(iv);
              //((InterpreterVisitor)iv).printEnv();
          }
          else if(args[0].equals("-ii") ){
            // iv = new InteractiveInterpreterVisitor();
            // result.accept(iv);
          }
          else if(args[0].equals("-tp") ){
             //iv = new TypeChecker();
             //result.accept(iv);
          }
          else if(args[0].equals("-pp") ){
	      // iv = new PPrint();
              //result.accept(iv);
              //((PPrint)iv).print();
          }
      }catch(Exception e){
          e.printStackTrace();
      }
   }
}
 
