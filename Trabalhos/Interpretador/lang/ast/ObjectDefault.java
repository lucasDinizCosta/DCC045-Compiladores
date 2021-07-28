/********************************************************
* Trabalho de Teoria dos Compiladores(DCC045)(2021/1)   *
*                                                       *
* Projeto do Interpretador para a Linguagem Lang        *
* Nome: Lucas Diniz da Costa -- Matricula: 201465524C   *
*                                                       *
*********************************************************/
package lang.ast;

import lang.ast.Type;

public class ObjectDefault extends LValue{
    // Objeto Auxiliar para o hashmap de variaveis Instanciadas
    
    private String id;              // Nome da variavel e o tipo
    private Object content;         // Conteudo: Numero inteiro, numero float, caracteres
    private Type tipo;

    public ObjectDefault(int line, int column) {
        super(line, column);
        id = "";
        tipo = null;
    }

    // Construtor para os tipos de dados comuns: Int, Char, Boolean e Float
    public ObjectDefault(int line, int column, Type tipo) {
        super(line, column);
        this.id = null;
        this.tipo = tipo;
    }

    public ObjectDefault(int line, int column, String id, Type tipo) {
        super(line, column);
        this.id = id;
        this.tipo = tipo;
    }

    public String getId() {
        return this.id;
    }

    public Type getType() {
        return tipo;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setType(Type tipo){
        this.tipo = tipo;
    }

    public Object getContent() {
        return this.content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    @Override
    public String toString(){
        return "" + content + " (" + tipo.toString() + ")";
    }
}
