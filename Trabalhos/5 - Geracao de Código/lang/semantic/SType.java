/**********************************************************
* Trabalho de Teoria dos Compiladores(DCC045)(2021/1)     *
*            Linguagem Lang                               *
* Nome: Lucas Diniz da Costa       Matricula: 201465524C  *
*       Luís Henrique Simplício Ribeiro       201635037   *                                               *
***********************************************************/
package lang.semantic;

// Define a classe abstrata de um "type" para a analise semantica no TypeCheckVisitor
public abstract class SType {
    // Atributo para verificar se um tipo está com o valor 'null'
    // public boolean nullValue = false;
    
    // Metodo que verifica se coincide os tipos
    public abstract boolean match(SType v);
}