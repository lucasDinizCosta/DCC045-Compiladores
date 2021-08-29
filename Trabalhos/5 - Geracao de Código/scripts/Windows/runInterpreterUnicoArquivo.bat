@ECHO OFF
cls
cd ..
cd ..
echo "----  EXECUTANDO O INTERPRETADOR COM BASE NOS TESTES "
java -cp lib/ANTLR.jar;. lang/LangCompiler -i ./meusTestes/input.txt
echo "----  EXECUCAO CONCLUIDA COM SUCESSO -------- "
pause