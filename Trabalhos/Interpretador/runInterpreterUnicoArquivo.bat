@ECHO OFF
cls
echo "----  EXECUTANDO O INTERPRETADOR COM BASE NOS TESTES ----"
java -cp lib/ANTLR.jar;. lang/LangCompiler -i ./input.txt
echo "----  EXECUCAO CONCLUIDA COM SUCESSO ----"
pause