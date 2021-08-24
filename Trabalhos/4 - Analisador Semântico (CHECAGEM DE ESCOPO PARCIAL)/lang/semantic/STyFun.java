/********************************************************
* Trabalho de Teoria dos Compiladores(DCC045)(2021/1)   *
*            Linguagem Lang                             *
* Nome: Lucas Diniz da Costa -- Matricula: 201465524C   *
*                                                       *
*********************************************************/
package lang.semantic;

// Define os tipos de função para a analise semantica no TypeCheckVisitor
public class STyFun extends SType {
    private String id;                  // Nome da função
    private SType parameterType[];      // Tipos do parametro
    private String nameParams[];        // Nome dos parametros
    private SType returnType[];         // Tipos de retorno

    public STyFun(SType t[], SType retornos[]) {
        parameterType = t;
        returnType = retornos;
    }

    public STyFun(SType t[], SType retornos[], String[] names, String nomeFuncao) {
        parameterType = t;
        returnType = retornos;
        nameParams = names;
        id = nomeFuncao;
    }

    public SType[] getTypes() {
        return parameterType;
    }

    public SType[] getReturnTypes() {
        return returnType;
    }

    public String[] getTypesName() {
        return nameParams;
    }

    public boolean match(SType v) {
        boolean r = false;
        if (v instanceof STyFun) {
            if (((STyFun) v).getTypes().length == parameterType.length) {
                r = true;
                for (int i = 0; i < parameterType.length; i++) {
                    r = r && parameterType[i].match(((STyFun) v).getTypes()[i]);
                }
            }
        }
        return r;
    }

    @Override
    public String toString() {
        String s = "";
        if (parameterType.length > 0) {
            s = parameterType[0].toString();
            for (int i = 1; i < parameterType.length; i++) {
                s += "->" + parameterType[i].toString();
            }
        }
        return s;
    }
}
