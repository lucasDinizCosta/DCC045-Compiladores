@ECHO OFF
cls
echo "----  EXECUTANDO O INTERPRETADOR COM BASE NOS TESTES ./testes/semantica/errado/data0.lan"
java -cp lib/ANTLR.jar;. lang/LangCompiler -i ./testes/semantica/certo/teste3.lan
echo "----  EXECUCAO CONCLUIDA COM SUCESSO ----./meusTestes/input.txt"
pause