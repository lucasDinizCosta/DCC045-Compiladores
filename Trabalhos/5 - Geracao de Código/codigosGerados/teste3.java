import java.util.Scanner;

public class teste3 {
    public Scanner __Scanner = new Scanner(System.in); // Scanner para fazer a leitura de entrada pelo teclado


    int main() {
        Integer i;
        Integer k;
        Integer[] x;
        k = 4;
        x = new Integer[k];
        x [ 0 ] = 0;
        x [ 3 ] = 15;
        System.out.print(x [ 3 ]);
        System.out.print('\n');
        i = 0;
        for (int _Indice_Loop_1 = 0, _Indice_Loop_1_EndLoop = k; _Indice_Loop_1 < _Indice_Loop_1_EndLoop; _Indice_Loop_1++) {
            if(((i % 2) == 0)) {
                x [ i ] = (2 * i);
            } else {
                x [ i ] = ((2 * i) + 1);
            }

            i = (i + 1);
        }
        i = 0;
        System.out.print('{');
        if((0 < k)) {
            System.out.print(x [ 0 ]);
            for (int _Indice_Loop_1 = 0, _Indice_Loop_1_EndLoop = (k - 1); _Indice_Loop_1 < _Indice_Loop_1_EndLoop; _Indice_Loop_1++) {
                System.out.print(',');
                System.out.print(x [ i + 1 ]);
                i = (i + 1);
            }
        }
        System.out.print('}');
        System.out.print('\n');
        return 0;
    }

    public static void main(String args[]) {
        teste3 m = new teste3();
        m.main();
    }
}