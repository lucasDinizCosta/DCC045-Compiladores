/********************************************************
* Trabalho de Teoria dos Compiladores(DCC045)(2021/1)   *
*                                                       *
* Projeto do Interpretador para a Linguagem Lang        *
* Nome: Lucas Diniz da Costa -- Matricula: 201465524C   *
*                                                       *
*********************************************************/
package lang;

import java.io.*;
import lang.parser.*;
import lang.ast.*;
import lang.interpreter.*;

public class LangCompiler {
    // Recupera o nome base (sem extensão) de um arquivo.
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Lang compiler v 0.0.1 - Julho de 2020");
            System.out.println("Use java -cp . Lang ação <Caminho para código Fonte> ");
            System.out.println("Ação (uma das seguintes possibilidades): ");

            System.out.println(" -bs : Executa uma bateria de testes sintáticos");
            System.out.println(" -bsm : Executa uma bateria de testes no interpretador");
            // System.out.println(" -bty : Executa uma bateria de testes no sistemas de tipos");

            System.out.println(" -pp: Pretty print program.");
            System.out.println(" -tp: Verificar tipos e imprimir o ambiente de tipos");
            System.out.println(" -i : Apenas interpretar");

            System.out.println(" -ti: Verificar tipos e depois interpretar");
            System.out.println(" -dti: Verificar tipos, imprimir o ambiente de tipos e depois interpretar");
            System.out.println(
                    " -gvz: Create a dot file. (Feed it to graphviz dot tool to generate graphical representation of the AST)");

        }
        try {
            ParseAdaptor langParser = new ParseAdaptorImplementation();
            InterpreterAdaptorImplementation interpreterImplementation = new InterpreterAdaptorImplementation();
            
            if (args[0].equals("-bs")) {
                System.out.println("Executando bateria de testes sintáticos:");
                TestParser tp = new TestParser(langParser);
                return;
            }
            if (args[0].equals("-bsm")) {
                System.out.println("\nExecuta uma bateria de testes no interpretador:\n");
                TestVisitor tp = new TestVisitor(interpreterImplementation);
                System.out.println("\nFim da execucao.\n");
                return;
            }
            /*if (args[0].equals("-byt")) {
                System.out.println("Executando bateria de testes sintáticos:");
                // TestParser tp = new TestParser(langParser); ;
                return;
            }*/
            if (args.length != 2) {
                System.out.println("Para usar essa opção, especifique um nome de arquivo");
                return;
            }
            SuperNode result = langParser.parseFile(args[1]);
            if (result == null) {
                System.err.println("Aborting due to syntax error(s)");
                System.exit(1);
            } else if (args[0].equals("-i")) {
                // Interpreta o Visitor e elabora o ambiente de desenvolvimento
                InterpretVisitor interpreter = new InterpretVisitor();  
                
                // Aceita o nó e caminha na árvore
                ((Node)result).accept(interpreter);               // Passa o node criado e testa o interpretador

                // Imprime o ambiente criado pelo interpretador
                interpreter.debugMode();            // ((InterpreterVisitor)iv).printEnv();
            } else if (args[0].equals("-ii")) {
                // iv = new InteractiveInterpreterVisitor();
                // result.accept(iv);
            } else if (args[0].equals("-tp")) {
                // iv = new TypeChecker();
                // result.accept(iv);
            } else if (args[0].equals("-pp")) {
                // iv = new PPrint();
                // result.accept(iv);
                // ((PPrint)iv).print();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
