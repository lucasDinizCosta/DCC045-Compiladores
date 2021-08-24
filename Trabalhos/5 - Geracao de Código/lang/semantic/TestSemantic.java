/********************************************************
* Trabalho de Teoria dos Compiladores(DCC045)(2021/1)   *
*            Linguagem Lang                             *
* Nome: Lucas Diniz da Costa -- Matricula: 201465524C   *
*                                                       *
*********************************************************/
package lang.semantic;

import java.io.File;
import lang.ast.*;

public class TestSemantic {
    private SemanticAdaptor sdp;
    private String okSrcs = "testes/semantica/errado/";
    private File f;

    public TestSemantic(SemanticAdaptor sdp) {
        this.sdp = sdp;
        f = new File(okSrcs);
        runOkTests();
    }

    private String filler(int n) {
        String s = "";
        for (int i = 0; i < n; i++) {
            s += " ";
        }
        return s;
    }

    public void runOkTests() {
        File inst[];
        int flips, flops;
        flips = 0;
        flops = 0;
        try {
            if (f.isDirectory()) {
                String pth;
                inst = f.listFiles();
                for (File s : inst) {
                    pth = s.getPath();
                    System.out.println("\nTestando " + pth + filler(50 - pth.length()) + ": ");
                    Node no = (Node) sdp.parseFile(s.getPath());
                    if (no != null) {
                        System.out.println("\nTeste " + pth + filler(50 - pth.length()) + "[  OK  ]\n");
                        flips++;
                    } else {
                        System.out.println("\nTeste " + pth + filler(50 - pth.length()) + "[ FALHOU ]\n");
                        flops++;
                    }
                }
                System.out.println("Total de acertos: " + flips);
                System.out.println("Total de erros: " + flops);
            } else {
                System.out.println("O caminho " + f.getPath() + " não é um diretório ou não existe.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
