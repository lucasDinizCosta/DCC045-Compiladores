@ECHO OFF
cls
echo "----  EXECUTANDO O TYPECHECKER COM BASE NOS TESTES ----"
java -cp lib/ANTLR.jar;. lang/LangCompiler -tp ./meusTestes/input.txt
echo "----  EXECUCAO CONCLUIDA COM SUCESSO ----"
pause