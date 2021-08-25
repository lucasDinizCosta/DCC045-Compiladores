/********************************************************
* Trabalho de Teoria dos Compiladores(DCC045)(2021/1)   *
*            Linguagem Lang                             *
* Nome: Lucas Diniz da Costa -- Matricula: 201465524C   *
*                                                       *
*********************************************************/
package lang;

import java.io.*;
import lang.parser.*;
import lang.ast.*;
import lang.interpreter.*;
import lang.semantic.*;
import lang.codeGenerator.*;

public class LangCompiler {
    // Recupera o nome base (sem extensão) de um arquivo.
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Lang compiler v 0.0.1 - Agosto de 2021");
            System.out.println("Use java -cp . Lang ação <Caminho para código Fonte> ");
            System.out.println("Ação (uma das seguintes possibilidades): ");

            System.out.println(" -bs : Executa uma bateria de testes sintáticos");
            System.out.println(" -bsm : Executa uma bateria de testes no interpretador");
            System.out.println(" -byt : Executa uma bateria de testes no sistemas de tipos na analise semantica");

            System.out.println(" -pp: Pretty print program.");
            System.out.println(" -tp: Verificar tipos e imprimir o ambiente de tipos");
            System.out.println(" -i : Apenas interpretar");

            System.out.println(" -ti: Verificar tipos e depois interpretar");
            System.out.println(" -dti: Verificar tipos, imprimir o ambiente de tipos e depois interpretar");
            System.out.println(
                    " -gvz: Create a dot file. (Feed it to graphviz dot tool to generate graphical representation of the AST)");

            System.out.println("------ Geracao de codigo para linguagem de alto nivel em um compilador -----");
            System.out.println("Use java -cp . Lang ação <Caminho para código Fonte> ");
            System.out.println("Ação (uma das seguintes possibilidades): ");
            System.out.println(" -C++ : Executa a geraçao de codigo da lang para C++ em relacao ao codigo fonte passado.");
            // System.out.println(" -Java : Executa a geraçao de codigo da minilang para Java");
            // System.out.println(" -Python : Executa a geraçao de codigo da minilang para Python");
            System.out.println("-------------------------------------------------------------------");
        }
        try {
            if(args.length < 1){
                System.out.println(" Nao foram passados parametros com local(nome) de arquivo ou acao para ser executada.");
                System.out.println("--------------------------");
                System.out.println("| Abortando o programa !!!");
                System.out.println("--------------------------");
                return;
            }
            ParseAdaptor langParser = new ParseAdaptorImplementation();
            InterpreterAdaptorImplementation interpreterImplementation = new InterpreterAdaptorImplementation();
            SemanticAdaptorImplementation semanticImplementation = new SemanticAdaptorImplementation();
            
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
            if (args[0].equals("-byt")) {
                System.out.println("Executando bateria de testes de tipos na analise semantica:\n");
                TestSemantic tp = new TestSemantic(semanticImplementation);
                System.out.println("\nFim da execucao.\n");
                return;
            }
            if (args.length != 2) {
                System.out.println("Para usar essa opção, especifique um nome de arquivo");
                return;
            }
            SuperNode result = langParser.parseFile(args[1]);
            System.out.println("--------------------------------");
            System.out.println("\n======> parsing ... [ ok ] \n");
            System.out.println("--------------------------------");
            if (result == null) {
                System.err.println("Aborting due to syntax error(s)");
                System.exit(1);
            } else if (args[0].equals("-i")) {
                // Interpreta o Visitor e elabora o ambiente de desenvolvimento
                InterpretVisitor interpreter = new InterpretVisitor();  
                
                System.out.println("\n---------- Executando o Interpretador ------------\n");
                // Aceita o nó e caminha na árvore
                ((Node)result).accept(interpreter);               // Passa o node criado e testa o interpretador
                System.out.println("\n--------------------------------------------------\n");

                // Imprime o ambiente criado pelo interpretador
                System.out.println("\n\n---------- Ambiente de execucao ------------");
                interpreter.debugMode();            // ((InterpreterVisitor)iv).printEnv();
                System.out.println("\n-----------------------------\n");
            } else if (args[0].equals("-ii")) {
                // iv = new InteractiveInterpreterVisitor();
                // result.accept(iv);
            } else if (args[0].equals("-tp")) {
                // Checa o tipo e a parte semantica elabora o ambiente de desenvolvimento
                TypeCheckVisitor typeCheck = new TypeCheckVisitor();  
                
                // Aceita o nó e caminha na árvore
                ((Node)result).accept(typeCheck);               
                
                System.out.println("\n\n---------- Checagem de tipos ------------");
                if(typeCheck.getNumErrors() > 0){
                    typeCheck.printErrors();
                 }else{
                    System.out.println("typing check ... [ ok ]"); 
                 }
                System.out.println("\n------------------------------------------\n");
            
            } 
            else if (args[0].equals("-ti")) {
                // Checa a parte semantica e elabora o ambiente de desenvolvimento
                TypeCheckVisitor typeCheck = new TypeCheckVisitor();  
                
                // Aceita o nó e caminha na árvore
                ((Node)result).accept(typeCheck);               
                
                if(typeCheck.getNumErrors() > 0){
                    typeCheck.printErrors();
                 }else{
                    System.out.println("--------------------------------");
                    System.out.println("\n======> typing check ... [ ok ]\n"); 
                    System.out.println("--------------------------------\n");
                    // Interpreta o Visitor e elabora o ambiente de desenvolvimento
                    InterpretVisitor interpreter = new InterpretVisitor();  
                    
                    System.out.println("\n---------- Executando o Interpretador ------------\n");
                    // Aceita o nó e caminha na árvore
                    ((Node)result).accept(interpreter);               // Passa o node criado e testa o interpretador

                    System.out.println("\n--------------------------------------------------\n");
                 }
            } 
            else if(args[0].equals("-C++")){    // Geração de código para C++
                TypeCheckVisitor v = new TypeCheckVisitor();
                ((Node)result).accept(v);

                if(v.getNumErrors() != 0) {
                    System.out.println(" Erros ocorreram durante a analise semântica.\nAbortando");
                    v.printErrors();
                    System.exit(1);
                }
                TyEnv<LocalAmbiente<SType>> env = v.getEnv();
                System.out.println("Executando a geracao de codigo de lang para C++:\n");
                System.out.println(args[1] + " --- " + args[1].substring(0, args[1].length() - 4));
                ((Node)result).accept(new CPlusPlusVisitor(getFileName(args[1]), env, v.getDatas()));
            }
            else if (args[0].equals("-pp")) {
                // iv = new PPrint();
                // result.accept(iv);
                // ((PPrint)iv).print();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getFileName(String path){
        System.out.println(path.lastIndexOf('/'));
        String nomeArquivo = path.substring(path.lastIndexOf('/') != -1 ? path.lastIndexOf('/') + 1 : 0, 
        path.lastIndexOf('.') != -1 ? path.lastIndexOf('.') : path.length());
        System.out.println(nomeArquivo);
        return nomeArquivo;
    }
}
