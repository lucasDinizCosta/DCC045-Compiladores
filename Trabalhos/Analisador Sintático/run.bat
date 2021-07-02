@ECHO OFF
cls
echo "----  EXECUTANDO O ANALISADOR SINTATICO"
java -cp lib/ANTLR.jar;. lang/LangCompiler -bs > saida.txt
pause