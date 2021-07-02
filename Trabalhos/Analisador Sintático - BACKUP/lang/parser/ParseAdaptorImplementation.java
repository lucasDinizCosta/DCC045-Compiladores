package lang.parser;

import lang.ast.SuperNode;
import lang.ast.Node;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.IOException;

// Adaptador para classe de parser. a Função parseFile deve retornar null caso o parser resulte em erro. 
public class ParseAdaptorImplementation implements ParseAdaptor {

    // Retorna null se encontrar erros de sintaxe no arquivo de entrada
    @Override
    public SuperNode parseFile(String path) throws IOException{
        // Cria uma variavel que irá armazenar um charStream de ANTLR 
        // a partir de um arquivo
        CharStream stream = CharStreams.fromFileName(path);

        // Cria um analisador léxico que é carregado com os dados do arquivo
        LangLexer lex = new LangLexer(stream);

        // Cria um buffer de tokens com base no analisador léxico
        CommonTokenStream tokens = new CommonTokenStream(lex);

        // Utiliza o analisador sintático criado e será alimentado com os buffers 
        // dos tokens
        LangParser parser = new LangParser(tokens);

        // Cria uma árvore da sintaxe
        ParseTree tree = parser.prog();

        // Verifica se o analisador sintático encontrou erros
        if(parser.getNumberOfSyntaxErrors() != 0){
            return null;
        }
        // Retorna um nó caso não encontre erros no arquivo passado
        // O Nó é vazio mas esta classe poderá ser utilizada nas próximas etapas do compilador
        return new Node();
    }
}