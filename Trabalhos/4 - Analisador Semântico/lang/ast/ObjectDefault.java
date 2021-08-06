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

    // Construtor para os tipos de dados comuns: Int, Char, Bool e Float
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

    public Boolean coincideTipo(Object exp){
        if(tipo instanceof TypeFloat){
            if(exp.getClass() == Float.class){
                return true;
            }
            if(exp.getClass() == Integer.class){    //Nesse caso o Integer será tratado como Float
                return true;
            }
        }
        if((exp.getClass() == Integer.class) && (tipo instanceof TypeInt)){
            return true;
        }
        if((exp.getClass() == Boolean.class) && (tipo instanceof TypeBool)){
            return true;
        }
        if(exp instanceof ObjectDefault){           // Checa os objetos do tipo data 
            System.out.println("TESTE " + tipo + " --- " + ((TypeArray)tipo).getType() + " --- " + ((ObjectDefault)exp).getType());
            if(tipo instanceof TypeArray && ((TypeArray)tipo).getType().getClass() == ((ObjectDefault)exp).getType().getClass()){
                System.out.println("TESTE");
                return true;
    
            }
            if(((ObjectDefault) exp).getType() == tipo){    // Ex: Ponto igual a Ponto
                return true;
            }
        }
        
        if((exp.getClass() == Character.class) && (tipo instanceof TypeChar)){
            return true;
        }
        return false;
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
        // return content + "";
    }
}
