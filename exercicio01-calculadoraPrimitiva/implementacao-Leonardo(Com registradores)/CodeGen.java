import java.util.HashMap;
import java.util.Stack;

import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.io.BufferedWriter;

public class CodeGen{
    
    /** 
     * Pra compilar pelo terminal: javac -cp . Interp.java
    */

    private char[] vars;
    private Stack<String> stk;
    private int pos;
    private boolean addPending;

    // Representa a sessão de dados do ASSEMBLER
    private ArrayList<String> data;
    // Representa a sessão bss do ASSEMBLER
    private ArrayList<String> bss;
    private ArrayList<String> code;
    private BufferedWriter outs;

    private void emit(String s) throws IOException{
        // Salva a string inteira no arquivo
        outs.write(s, 0, s.length());
        outs.newLine();
    }

    private void val(char[] line){
        String x = "";
        if(pos >= line.length){throw new RuntimeException("Fim de entrada inesperado.");}
        if(line[pos] >= 'a' && line[pos] <= 'z'){
            // Empilha o texto correspondente ao endereço de memoria da variavel
            // Usando o código do NASM
            stk.push("[var_" + line[pos++] + "]");
            return;
        }else if(line[pos] >= '0' && line[pos] <= '9'){
            while(pos < line.length && line[pos] >= '0' && line[pos] <= '9'){
                x += line[pos++];
            }
            stk.push(x);
            return;
        } else{
            throw new RuntimeException("Valor inesperado na coluna " + pos + " linha " + new String(line));
        }
    }

    private void evalExp(char[] line){
        String v1, v2;
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
                    // Copia o valor do v2 pro registrador ebx
                    code.add("        mov ebx, " + v2 + " ");
                    // Adiciona o valor v1 com o valor do registrador ebx
                    code.add("        add ebx, " + v1 + " ");
                    stk.push("ebx");
                }
            }else if(line[pos] == '*'){
                pos++;
                val(line);
                v1 = stk.pop();
                v2 = stk.pop();
                // Copia o valor do v2 pro registrador eax
                code.add("        mov eax, " + v2 + " "); // 1 * 2 * 3
                // Adiciona o valor v1 com o valor do registrador ebx
                code.add("        add ebx, " + v1 + " ");
                // Armazena a multiplicação no registrador ebx
                code.add("        mul ebx");
                stk.push("eax");
                if(pos < line.length && addPending && line[pos] != '*'){
                    v1 = stk.pop();
                    v2 = stk.pop();
                    // Copia o valor do v2 pro registrador ebx
                    code.add("        mov ebx, " + v2 + " ");
                    // Adiciona o valor v1 com o valor do registrador ebx
                    code.add("        add ebx, " + v1 + " ");
                    stk.push("ebx");
                    addPending = true;
                }
            }else{pos++;}
        }
    }

    public void genASM(List<char[]> prog, String file){
        int x;

        code = new ArrayList<String>();
        data = new ArrayList<String>();
        bss = new ArrayList<String>();
        
        vars = new char[27];
        // Inicializa as variaveis com branco ' '
        for(int i = 0; i < 27; i++){vars[i] = ' ';}

        stk = new Stack<String>();
        for(char[] line : prog){
            addPending = false;
            if(line[1] == '='){
                pos = 2;
                evalExp(line);
                // move para o registrador e especifica o tanto de bytes
                // a partir do prefixo DWORD => 4 bits
                code.add("        mov DWORD [var_" + line[0] + "], " + stk.pop());
                
                x = ((int) line[0]);
                if(vars[x] == ' '){
                    vars[x] = line[0];
                    // Cria um label e esse label é um endereço de memoria
                    // uma palavra dupla dd de tamanho 32 bits preenchido com zero
                    data.add("        var_" + line[0] + "        dd 0");
                }
            }else{
                pos = 0;
                evalExp(line);
                // o registrador eax será usado pra receber os parametros de funcoes
                code.add("        mov eax, " + stk.pop());
                code.add("        call inttostr");
            }
            stk.clear();
        }
        addSections();
        writeCode(file);
    }

    private void addSections(){
        bss.add("buff         resb 11");
        code.add("                                               ; Ending the program ");
        code.add("         mov    rax, 60                        ; exit");
        code.add("         mov    rdi, 0                         ; with success");
        code.add("         syscall                             ;_");
        code.add("         ");
        code.add("                           ; This function converts a unsigned into a string prints the number");
        code.add("                           ; The value to be printed is expected on to be on EAX. This functions do not obey C calling conventions");
        code.add(" inttostr:");
        code.add("         push rbp          ; Save the old base pointer value.");
        code.add("         mov rbp, rsp      ; Set the new base pointer value.");
        code.add("         push rdi          ; Save the values of registers that the function");
        code.add("         push rsi          ; will modify. This function uses EDI and ESI.");
        code.add("         ");
        code.add("         mov r8, 0         ; r8 is a counter for the string length ! (number of digits)");
        code.add("         mov r9, 0         ; r9 is the addrress of the first byte of the string.");
        code.add("         mov R9D, buff     ; ");
        code.add("         add R9D, 10       ; buff + 11, is at the end of the output buffer.");
        code.add("         mov BYTE [R9D], 10; We add the new line symbol ! (byte 10 = new line)");
        code.add("         dec r9d           ; we already print one symbol, r9 points at an empty byte now");
        code.add("         inc r8b,          ; Thus we have 1 on byte length !");
        code.add("         ");
        code.add(" divide:");
        code.add("         mov edx, 0        ");
        code.add("         mov ecx, 10       ; dividendo;");
        code.add("         div ecx;          ; This divides eax by exc, quotient goes to eax and reminder to edx !");
        code.add("         ");
        code.add("         mov bl, dl        ; ");
        code.add("         add bl, 48        ; adds 48 to reminder (48 =   '0')");
        code.add("         ");
        code.add("         mov [R9d], bl     ; saves the byte to the output buffer");
        code.add("         dec R9d;          ; backwards to new 'empty' position on the buffer");
        code.add("         inc r8b;          ; string now has 1 more char ");
        code.add("         cmp eax, 0        ; If the quotient is not zero ");
        code.add("         jnz divide;       ; We do all over again !");
        code.add(" print:");
        code.add("         inc r9d;           ");
        code.add("         mov rax, 1        ; write");
        code.add("         mov rdi, 1        ; to stdout");
        code.add("         mov rsi, r9       ; starting at msg");
        code.add("         mov rdx, r8       ; for len bytes");
        code.add("         syscall           ");
        code.add("         ");
        code.add("         pop rsi           ; Recover register values");
        code.add("         pop rdi           ; ");
        code.add("         mov rsp, rbp      ; Deallocate local variables");
        code.add("         pop rbp           ; Restore the caller's base pointer value");
        code.add("         ret               ; ");
    }

    private void writeCode(){

    }



}