@ECHO OFF
cls
echo "----  EXECUTANDO O TYPE-CHECKER COM BASE NOS TESTES ----"
java -cp lib/ANTLR.jar;. lang/LangCompiler -tp ./testes/semantica/errado/parameters1.lan
echo "----  EXECUCAO CONCLUIDA COM SUCESSO ----"
pause