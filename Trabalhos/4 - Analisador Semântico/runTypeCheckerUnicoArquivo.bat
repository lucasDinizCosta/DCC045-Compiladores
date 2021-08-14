@ECHO OFF
cls
echo "----  EXECUTANDO O TYPE-CHECKER COM BASE NOS TESTES ----"
java -cp lib/ANTLR.jar;. lang/LangCompiler -tp ./testes/semantica/errado/return.lan
echo "----  EXECUCAO CONCLUIDA COM SUCESSO ----"
pause