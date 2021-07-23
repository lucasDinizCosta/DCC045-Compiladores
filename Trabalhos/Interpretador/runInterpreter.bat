@ECHO OFF
cls
echo "----  EXECUTANDO O PROGRAMA DO ANALISADOR SINTATICO COM BASE NOS TESTES ----"
java -cp lib/ANTLR.jar;. lang/LangCompiler -interp
echo "----  EXECUCAO CONCLUIDA COM SUCESSO ----"
pause