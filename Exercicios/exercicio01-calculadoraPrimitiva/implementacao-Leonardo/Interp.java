import java.util.HashMap;
import java.util.Stack;

import java.util.List;
import java.util.ArrayList;

public class Interp{
    
    /** 
     * Pra compilar pelo terminal: javac -cp . Interp.java
    */

    // Criação de uma tabela Hash
    // environment => mapeia as variaveis e valores
    private HashMap<Character, Integer> env;
    private Stack<Integer> stk;
    private int pos;
    private boolean addPending;

    private void val(char[] line){
        int x = 0;
        if(pos >= line.length){throw new RuntimeException("Fim de entrada inesperado.");}
        if(line[pos] >= 'a' && line[pos] <= 'z'){
            stk.push(env.get(line[pos++]));
            return;
        }else if(line[pos] >= '0' && line[pos] <= '9'){
            while(pos < line.length && line[pos] >= '0' && line[pos] <= '9'){
                x = x*10 + ((int) line[pos++]) - ((int) '0');
            }
            stk.push(x);
            return;
        } else{
            throw new RuntimeException("Valor inesperado na coluna " + pos + " linha " + new String(line));
        }
    }

    private void evalExp(char[] line){
        int v1, v2;
        val(line);
        while(pos < line.length){
            if(line[pos] == '+'){
                pos++;
                val(line);
                if(pos < line.length && line[pos] == '*'){
                    addPending = true;
                }
                else{
                    v1 = stk.pop();
                    v2 = stk.pop();
                    stk.push(v1+v2);
                }
            }else if(line[pos] == '*'){
                pos++;
                val(line);
                v1 = stk.pop();
                v2 = stk.pop();
                stk.push(v1*v2);
                if(pos < line.length && addPending && line[pos] != '*'){
                    v1 = stk.pop();
                    v2 = stk.pop();
                    stk.push(v1+v2);
                    addPending = true;
                }
            }else{pos++;}
        }
    }

    public void interp(List<char[]> prog){
        env = new HashMap<Character, Integer>();
        stk = new Stack<Integer>();
        for(char[] line : prog){
            addPending = false;
            if(line[1] == '='){
                pos = 2;
                evalExp(line);
                env.put(line[0], stk.pop());
            }else{
                pos = 0;
                evalExp(line);
                System.out.println("" + stk.pop());
            }
            stk.clear();
        }
    }



}