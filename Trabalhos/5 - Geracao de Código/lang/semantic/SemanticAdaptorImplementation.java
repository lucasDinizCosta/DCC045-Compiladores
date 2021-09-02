/**********************************************************
* Trabalho de Teoria dos Compiladores(DCC045)(2021/1)     *
*            Linguagem Lang                               *
* Nome: Lucas Diniz da Costa       Matricula: 201465524C  *
*       Luís Henrique Simplício Ribeiro       201635037   *                                               *
***********************************************************/

package lang.semantic;

import lang.ast.SuperNode;
import lang.ast.Node;
import lang.interpreter.*;
import lang.parser.*;
import lang.semantic.SemanticAdaptor;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.IOException;


// Adaptador para classe de parser. a Função parseFile deve retornar null caso o parser resulte em erro. 
public class SemanticAdaptorImplementation implements SemanticAdaptor {

    // Retorna null se encontrar erros de sintaxe no arquivo de entrada
    @Override
    public SuperNode parseFile(String path) {
        try {
            // Cria uma variavel que irá armazenar um charStream de ANTLR
            // a partir de um arquivo
            CharStream stream = CharStreams.fromFileName(path);

            // Cria um analisador léxico que é carregado com os dados do arquivo
            LangLexer lexer = new LangLexer(stream);

            // Cria um buffer de tokens com base no analisador léxico
            CommonTokenStream tokens = new CommonTokenStream(lexer);

            // Utiliza o analisador sintático criado e será alimentado com os buffers
            // dos tokens
            LangParser parser = new LangParser(tokens);

            // Remove os detectores de erros léxicos padrão gerado pela ferramenta
            // A ideia é que quando ocorrer um erro execução no lexer, pare tudo
            // e retorne uma excessão em Runtime
            lexer.removeErrorListeners();
            lexer.addErrorListener(new BaseErrorListener()  {
                // Sobreescreve o método base de identificação de erros
                @Override
                public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
                    System.out.println("line "+ line + ":" + charPositionInLine + " -- " + msg);
                    throw new RuntimeException(e.getCause());
                }
            });

            // Cria uma árvore da sintaxe
            ParseTree tree = parser.prog();

            // Verifica se o analisador sintático encontrou erros
            if (parser.getNumberOfSyntaxErrors() != 0) {
                return null;
            }
            // Retorna um nó caso não encontre erros no arquivo passado
            // O Nó é vazio mas esta classe poderá ser utilizada nas próximas etapas do
            // compilador

            // Metodo do visitor --- apenas será utilizado onde houver visitor
            // Cria um adaptador da ParseTree do ANTLR para receber o padrão Node 
            // criado para a AST do trabalho
            VisitorAdapter ast = new VisitorAdapter();

            // Passa um objeto do tipo parseTree e retorna do tipo Node
            // Visita a árvore
            Node node = ast.visit(tree);  

            // Interpreta o Visitor e elabora o ambiente de desenvolvimento
            TypeCheckVisitor semanticCheck = new TypeCheckVisitor();  
            
            // Aceita o nó e caminha na árvore
            node.accept(semanticCheck);               // Passa o node criado e testa o interpretador

            
            if(semanticCheck.getNumErrors() > 0){
                semanticCheck.printErrors();
                return null;
             }else{
                // System.out.println("typing check ... [ ok ]"); 
                return node;
             }
        }catch (IOException e) {
            e.printStackTrace();
            System.out.println("\n\n ===> ERRO no ParseAdaptorImplementation: O arquivo \'" + path
            +"\' nao foi encontrado !!! \n");
            // O Nó é vazio mas esta classe poderá ser utilizada nas próximas etapas do
            // compilador
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            // O Nó é vazio mas esta classe poderá ser utilizada nas próximas etapas do
            // compilador
            return null;
        }
    }
}
