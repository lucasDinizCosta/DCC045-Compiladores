@ECHO OFF
cls
echo "----  EXECUTANDO O TYPE-CHECKER COM BASE NOS TESTES ----./testes/semantica/errado/data0.lan"
java -cp lib/ANTLR.jar;. lang/LangCompiler -tp meusTestes/input.txt
echo "----  EXECUCAO CONCLUIDA COM SUCESSO ----meusTestes/input.txt"
pause