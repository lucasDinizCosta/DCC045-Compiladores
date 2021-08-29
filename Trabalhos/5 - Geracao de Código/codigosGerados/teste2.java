import java.util.Scanner;

public class teste2 {
    public Scanner __Scanner = new Scanner(System.in); // Scanner para fazer a leitura de entrada pelo teclado


    int main() {
        Integer x;
        x = 0;
        System.out.print('>');
        x = Integer.parseInt(__Scanner.nextLine());
        System.out.print(x);
        System.out.print('\n');
        return 0;
    }

    public static void main(String args[]) {
        teste2 m = new teste2();
        m.main();
    }
}