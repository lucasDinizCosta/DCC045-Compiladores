@ECHO OFF
cls
echo "----  EXECUTANDO O TYPECHECKER COM BASE NOS TESTES ----"
java -cp lib/ANTLR.jar;. lang/LangCompiler -tp ./testes/semantica/certo/teste1.lan
echo "----  EXECUCAO CONCLUIDA COM SUCESSO ----"
pause