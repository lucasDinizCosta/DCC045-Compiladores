/********************************************************
* Trabalho de Teoria dos Compiladores(DCC045)(2021/1)   *
*                                                       *
* Projeto do Interpretador para a Linguagem Lang        *
* Nome: Lucas Diniz da Costa -- Matricula: 201465524C   *
*                                                       *
*********************************************************/

package lang.interpreter;

import lang.ast.SuperNode;
import lang.ast.Node;
import lang.parser.*;
import lang.interpreter.InterpreterAdaptor;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.IOException;

// import lang.parser.LexerErrors;

// Adaptador para classe de interpreter. a Função parseFile deve retornar null caso o parser resulte em erro. 
public class InterpreterAdaptorImplementation implements InterpreterAdaptor {

    // Retorna null se encontrar erros de sintaxe no arquivo de entrada
    @Override
    public SuperNode interpretFile(String path) {
        try {
            // Cria uma variavel que irá armazenar um charStream de ANTLR
            // a partir de um arquivo
            CharStream stream = CharStreams.fromFileName(path);

            // Cria um analisador léxico que é carregado com os dados do arquivo
            LangLexer lexer = new LangLexer(stream);
            // LangLexer lexer = new LexerErrors(stream);

            // Cria um buffer de tokens com base no analisador léxico
            CommonTokenStream tokens = new CommonTokenStream(lexer);

            // Utiliza o analisador sintático criado e será alimentado com os buffers
            // dos tokens
            LangParser parser = new LangParser(tokens);

            // Cria a árvore da sintaxe padrão (PARSETREE)
            ParseTree tree = parser.prog();

            // Verifica se o analisador sintático encontrou erros
            if (parser.getNumberOfSyntaxErrors() != 0) {
                return null;
            }

            // Cria um adaptador da ParseTree do ANTLR para receber o padrão Node 
            // criado para a AST do trabalho
            VisitorAdapter ast = new VisitorAdapter();

            // Passa um objeto do tipo parseTree e retorna do tipo Node
            // Visita a árvore
            Node node = ast.visit(tree);     
            
            // Interpreta o Visitor e elabora o ambiente de desenvolvimento
            InterpretVisitor interpreter = new InterpretVisitor();  
            
            // Aceita o nó e caminha na árvore
            node.accept(interpreter);               // Passa o node criado e testa o interpretador

            return node;
        } catch (IOException e) {
            e.printStackTrace();
            // O Nó é vazio mas esta classe poderá ser utilizada nas próximas etapas do
            // compilador
            return null;//new Node();
        }
    }
}