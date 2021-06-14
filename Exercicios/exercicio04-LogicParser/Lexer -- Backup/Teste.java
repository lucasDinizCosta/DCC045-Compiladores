import java.io.FileReader;
import java.io.IOException;

public class Teste{
    public static void main(String args[]) throws IOException{
        LexTest lx = new LexTest(new FileReader(args[0]));
        Token t = lx.nextToken();
        int i = 0;
        while(t != null){
            System.out.println(t.toString());
            t = lx.nextToken();
            i++;
            // if(i>10){    // pra teste caso der looping infinito
            //     break;
            // }
        }
        System.out.println("Total de tokens lidos " + lx.readedTokens());
    }
}