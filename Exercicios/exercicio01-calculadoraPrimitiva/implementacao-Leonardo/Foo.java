import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

import java.util.List;
import java.util.ArrayList;

public class Foo{

    /**
     * Pra compilar pelo terminal: javac -cp . Foo.java
     * Pra rodar pelo terminal: java -cp . Foo sample1.txt
     */

    // Classe do analisador sintático
    private int pos;
    private char[] str;
    private List<char[]> code;
    private BufferedReader file;

    public Foo(String filename) throws IOException{
        pos = 0;
        str = new char[0];
        code = new ArrayList<char[]>();
        file = new BufferedReader(new FileReader(filename));
    }

    private boolean isOperator(){
        if(pos < str.length){
            if(str[pos] == '+' || str[pos] == '*'){
                pos++;
                return true;
            }
        }
        return false;
    }

    private boolean isOperand(){
        if(pos < str.length){
            if(str[pos] >= 'a' && str[pos] <= 'z'){
                pos++;
                return true;
            }
            if(str[pos] < '0' || str[pos] > '9'){
                return false;
            }
            while(pos < str.length && str[pos] >= '0' && str[pos] <= '9'){
                pos++;
            }
            return true;
        }
        return false;
    }

    public List<char[]> parse() throws IOException{
        String line;
        code.clear();
        while ((line = file.readLine()) != null) {

            // Remove os espaços em branco na linha
            str = line.replaceAll(" ", "").toCharArray();
            pos = 0;
            System.out.println(line);
            if(str.length > 1 && str[1] == '='){
                if(str[0] < 'a' || str[0] > 'z'){
                    System.out.println("Expressão deve começar com uma variável");
                    return null;
                }
                pos = 2;
            }
            if(!isOperand()){
                System.out.println("Esperando um operando");
                return null;
            }
            while(isOperator()){
                if(!isOperand()){
                    System.out.println("Esperando um operando");
                    return null;
                }
            }
            if(pos >= str.length || str[pos] != ';'){
                System.out.println("Esperando um ';'");
                return null;
            }
            code.add(str);
        }
        return code;
    }

    public static void main(String[] args){
        try{
            Foo p = new Foo(args[0]);
            Interp i = new Interp();
            List<char[]> l = p.parse();
            if(l != null){
                System.out.println("Parser Ok. Interpretando...");
                i.interp(l);
            }
            else{
                System.out.println("Falha de parser");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}