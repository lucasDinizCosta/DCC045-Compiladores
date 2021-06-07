import java.io.FileReader;
import java.io.IOException;

public class Teste{
    public static void main(String args[]) throws IOException{
        LextTest lx = new LextTest(new FileReader(args[0]));
        Token t = lx.nextToken();
        while(t != null){
            System.out.println(t.toString());
            t = lx.nextToken();
        }
        System.out.println("Total de tokens lidos " + lx.readedTokens());
    }
}