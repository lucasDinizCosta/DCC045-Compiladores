@ECHO OFF
cls
echo "----  EXECUTANDO O TYPE-CHECKER COM BASE NOS TESTES ----"
java -cp lib/ANTLR.jar;. lang/LangCompiler -tp ./testes/semantica/errado/function_call_ret_use.lan
echo "----  EXECUCAO CONCLUIDA COM SUCESSO ----"
pause