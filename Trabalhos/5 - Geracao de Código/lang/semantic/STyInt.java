/**********************************************************
 * Trabalho de Teoria dos Compiladores(DCC045)(2021/1)     *
 *            Linguagem Lang                               *
 * Nome: Lucas Diniz da Costa       Matricula: 201465524C  *
 *       Luís Henrique Simplício Ribeiro       201635037   *                                               *
 ***********************************************************/

package lang.semantic;

import lang.semantic.*;

// Define o tipo int para a analise semantica no TypeCheckVisitor
public class STyInt extends SType {

    private static STyInt st = new STyInt();

    private STyInt() {
    }

    public static STyInt newSTyInt() {
        return st;
    }

    @Override
    public boolean match(SType v) {
        return (v instanceof STyErr) || (v instanceof STyInt);
    }

    @Override
    public String toString() {
        return "Int";
    }

}
